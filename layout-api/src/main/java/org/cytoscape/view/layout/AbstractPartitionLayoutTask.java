package org.cytoscape.view.layout;

/*
 * #%L
 * Cytoscape Layout API (layout-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.cytoscape.view.presentation.property.BasicVisualLexicon;
import org.cytoscape.work.TaskMonitor;
import org.cytoscape.work.undo.UndoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a more helpful implementation of a LayoutAlgorithm Task 
 * that extends AbstractBasicLayoutTask and does the work of partitioning
 * the CyNetworkView so that partitions may be laid out individually.
 * Extensions of this class are meant to implement the layoutPartition()
 * method and operate on the LayoutParition object that is passed to that
 * method as an argument.
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule layout-api
 */
public abstract class AbstractPartitionLayoutTask extends AbstractLayoutTask {
	
	protected static Logger logger = LoggerFactory.getLogger("org.cytoscape.application.userlog");
	
	/**
	 * The TaskMonitor initially set in the run method of the task. 
	 * Used to track the layout progress.
	 */
	protected TaskMonitor taskMonitor;

	/**
	 * The value used for spacing nodes.  Defaults to 100.
	 */
	protected double incr = 100;
	
	/**
	 * The list of LayoutPartition objects that get laid out.
	 */
	protected List <LayoutPartition> partitionList = null;
	
	/**
	 * The EdgeWeighter used for edge weight calculations.
	 */
	protected EdgeWeighter edgeWeighter;
	
	private final boolean singlePartition;

	/**
	 * Starting node number used for taskMonitor.
	 */
	protected double current_start = 0;
	
	/**
	 * Partition size used for taskMonitor.
	 */
	protected double current_size = 0;
	
	/**
	 * Total number of nodes used for taskMonitor.
	 */
	protected double total_nodes = 0;

	/**
	 * Creates a new AbstractPartitionLayoutTask object.
	 * @param name The name of the layout algorithm. 
	 * @param singlePartition Whether this layout algorithm should execute on a 
	 * single partition instead of multiple partitions. 
	 * @param networkView the CyNetworkView being partitioned.
	 * @param nodesToLayOut the set of nodes to layout. 
	 * @param layoutAttribute the name of the attribute to use for this layout.
	 * Allowed to be empty or null.
	 */
	public AbstractPartitionLayoutTask(
			final String displayName,
			final boolean singlePartition,
			final CyNetworkView networkView,
			final Set<View<CyNode>> nodesToLayOut,
			final String layoutAttribute,
			final UndoSupport undo
	) {
		super(displayName, networkView, nodesToLayOut, layoutAttribute, undo);
		this.singlePartition = singlePartition;
	}

	/**
	 * Override this method and layout the LayoutPartion just
	 * like you would a NetworkView.
	 *
	 * @param partition The LayoutPartition to be laid out. 
	 */
	public abstract void layoutPartition(LayoutPartition partition);

	/**
	 * Used for 
	 *
	 * @param percent The percentage of completion for this partition. Value must be between 0 and 100.
	 */
	protected void setTaskStatus(int percent) {
		if (taskMonitor != null) {
			if ( percent < 0 || percent > 100 ) {
				logger.warn("invalid percent value set for layout status (must be between 0 and 100): " + percent);
				return;
			}	
			// Calculate the nodes done for this partition
			double nodesDone = current_size*(double)percent/100.;
			// Calculate the percent done overall
			double pDone = (nodesDone+current_start)/total_nodes;
			taskMonitor.setProgress(pDone);
		}
	}

	/**
	 * AbstractGraphPartitionLayout implements the doLayout method
	 * of AbstractBasicLayout in which it calls the layoutParition
	 * method on each LayoutPartition object created for the network.
	 * @param taskMonitor the TaskMonitor provided by the run() method
	 * of the Task.
	 */
	@Override
	public void doLayout(final TaskMonitor taskMonitor) {
		final CyNetwork network = networkView.getModel();
		if (edgeWeighter != null)
			edgeWeighter.reset();

		this.taskMonitor = taskMonitor;
		
		long visibleNodeCount =
			networkView.getNodeViews().stream()
			.filter(view -> view.getVisualProperty(BasicVisualLexicon.NODE_VISIBLE))
			.count();
		
		boolean useAllNodes = nodesToLayOut.size() == visibleNodeCount;
		
		// Depending on whether we are partitioned or not,
		// we use different initialization.  Note that if the user only wants
		// to lay out selected nodes, partitioning becomes a very bad idea!
		if (singlePartition || !useAllNodes) {
			// We still use the partition abstraction, even if we're
			// not partitioning.  This makes the code further down
			// much cleaner
			LayoutPartition partition = new LayoutPartition(networkView, nodesToLayOut, edgeWeighter);
			partitionList = new ArrayList<>(1);
			partitionList.add(partition);
		} else {
			Set<CyNode> nodes = nodesToLayOut.stream().map(nv->nv.getModel()).collect(Collectors.toSet());
			partitionList = PartitionUtil.partition(networkView, nodes, edgeWeighter);
		}

		total_nodes = network.getNodeCount();

		// Get the screen coordinates
		double screen_scale = networkView.getVisualProperty(BasicVisualLexicon.NETWORK_SCALE_FACTOR);
		double screen_width = networkView.getVisualProperty(BasicVisualLexicon.NETWORK_WIDTH)/screen_scale;
		double screen_height = networkView.getVisualProperty(BasicVisualLexicon.NETWORK_HEIGHT)/screen_scale;

		// Get the smallest and largest positions
		double min_x = Double.MAX_VALUE;
		double max_x = Double.MIN_VALUE;
		double min_y = Double.MAX_VALUE;
		double max_y = Double.MIN_VALUE;

		for (LayoutPartition partition: partitionList) {
			min_x = Math.min(min_x, partition.getMinX());
			max_x = Math.max(max_x, partition.getMaxX());
			min_y = Math.min(min_y, partition.getMinY());
			max_y = Math.max(max_y, partition.getMaxY());
		}

		// Layout each individual partition
		for (LayoutPartition partition: partitionList) {
			if (cancelled)
				break;

			// get the partition
			current_size = (double)partition.size();
			// System.out.println("Partition #"+partition.getPartitionNumber()+" has "+current_size+" nodes");
			setTaskStatus(1);

			// Partitions Requiring Layout
			if (partition.nodeCount() > 1) {
				try {
					partition.dontMove(true);
					layoutPartition(partition);
					partition.dontMove(false);
				} catch (Throwable _e) {
					_e.printStackTrace();
					partition.dontMove(false);
					return;
				}
			}

			setTaskStatus( 100 );
			current_start += current_size;
		} 

		double width = max_x - min_x;
		double height = max_y - min_y;

		double max_dimension = calculate_max_dimension(width, height, screen_width, screen_height, partitionList);
		double start_x = 0.0;
		double next_y_start = 0.0;
		double next_x_start = start_x;
		double y_max = 0.0;

		// Now, reposition each individual partition
		for (LayoutPartition partition: partitionList) {
			if (cancelled)
				break;

			partition.offset(next_x_start, next_y_start);

			next_x_start = partition.getMaxX()+incr;
			y_max = Math.max(y_max, partition.getMaxY());
			if (next_x_start > max_dimension) {
				next_x_start = start_x;
				next_y_start = y_max+incr;
			}
		}
	}

	protected double calculate_max_dimension(double width, double height, 
	                                         double screen_width, double screen_height, 
	                                         List<LayoutPartition> partitionList) {
		double max_dimension = Math.max(width, screen_width);
		double wh_ratio = screen_width/screen_height;
		// System.out.println("screen: "+screen_width+"x"+screen_height+", wh_ratio = "+wh_ratio);
		int max_attempts = 10;
		for (int attempt = 0; attempt < max_attempts; attempt++) {
			double next_y_start = 0.0;
			double next_x_start = 0.0;
			double y_max = 0.0;
			for (LayoutPartition partition: partitionList) {
				// System.out.println("Partition: "+partition.getPartitionNumber()+": "+partition.getWidth()+"x"+partition.getHeight());
				next_x_start += partition.getWidth()+incr;
				y_max = Math.max(y_max, partition.getHeight());
				if (next_x_start > max_dimension) {
					next_x_start = 0.0;
					next_y_start += y_max+incr;
					y_max = 0.0;
				}
			}
			// Figure out our new ratio
			double layout_ratio = max_dimension/(next_y_start+y_max);
			// System.out.println("Layout: "+max_dimension+"x"+(next_y_start+y_max)+", layout_ratio = "+layout_ratio);

			double ratio = wh_ratio / layout_ratio;
			max_dimension *= ratio;
		}
		return max_dimension;
	}
}
