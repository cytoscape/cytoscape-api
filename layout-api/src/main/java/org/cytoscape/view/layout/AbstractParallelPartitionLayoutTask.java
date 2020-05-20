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
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.cytoscape.view.presentation.property.BasicVisualLexicon;
import org.cytoscape.work.TaskMonitor;
import org.cytoscape.work.undo.UndoSupport;

/**
 * This is an even more helpful implementation of a LayoutAlgorithm Task 
 * that extends AbstractPartitionLayoutTask and does the work of partitioning
 * the CyNetworkView so that partitions may be laid out individually.
 * The layout is applied to multiple partitions in parallel.
 * Extensions of this class are meant to implement the layoutPartition()
 * method and operate on the LayoutParition object that is passed to that
 * method as an argument. Extensions should also make sure everything is thread-safe.
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule layout-api
 */
public abstract class AbstractParallelPartitionLayoutTask extends AbstractPartitionLayoutTask {

	private final boolean singlePartition;
	private int nodesDone = 0;
	
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
	public AbstractParallelPartitionLayoutTask(final String displayName, 
	                                   final boolean singlePartition, 
	                                   CyNetworkView networkView, 
	                                   Set<View<CyNode>> nodesToLayOut,
	                                   String layoutAttribute,
	                                   UndoSupport undo) {
		super(displayName, singlePartition, networkView, nodesToLayOut, layoutAttribute, undo);
		this.singlePartition = singlePartition;
	}

	/**
	 * AbstractGraphPartitionLayout implements the doLayout method
	 * of AbstractBasicLayout in which it calls the layoutParition
	 * method on each LayoutPartition object created for the network.
	 * @param taskMonitor the TaskMonitor provided by the run() method
	 * of the Task.
	 */
	@Override
	public void doLayout(final TaskMonitor taskMonitor) 
	{
		final CyNetwork network = networkView.getModel();
		if (edgeWeighter != null)
			edgeWeighter.reset();

		// networkView.fitContent();

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
			partitionList = new ArrayList<LayoutPartition>(1);
			partitionList.add(partition);
		} else {
			partitionList = PartitionUtil.partition(networkView, false, edgeWeighter);
		}

		total_nodes = network.getNodeCount();

		// Get the screen coordinates
		double screen_scale = networkView.getVisualProperty(BasicVisualLexicon.NETWORK_SCALE_FACTOR);
		double screen_width = networkView.getVisualProperty(BasicVisualLexicon.NETWORK_WIDTH)/screen_scale;
		double screen_height = networkView.getVisualProperty(BasicVisualLexicon.NETWORK_HEIGHT)/screen_scale;

		final int numThreads = Runtime.getRuntime().availableProcessors();

		taskMonitor.setStatusMessage("Calling layout for individual partitions");

		// Create thread pool, ...
		ExecutorService threadPool = Executors.newFixedThreadPool(numThreads);

		// ... spawn tasks
		double min_x = Double.MAX_VALUE;
		double max_x = Double.MIN_VALUE;
		double min_y = Double.MAX_VALUE;
		double max_y = Double.MIN_VALUE;

		for (LayoutPartition partition: partitionList) {
			min_x = Math.min(min_x, partition.getMinX());
			max_x = Math.max(max_x, partition.getMaxX());
			min_y = Math.min(min_y, partition.getMinY());
			max_y = Math.max(max_y, partition.getMaxY());
			threadPool.submit(new ParallelLayoutTask(partition));
		}

		// ... and wait for them to execute.
		threadPool.shutdown();
		try {
			boolean result = threadPool.awaitTermination(7, TimeUnit.DAYS);
		} catch (Exception e) {
		}

		double width = max_x - min_x;
		double height = max_y - min_y;

		double max_dimension = calculate_max_dimension(width, height, screen_width, screen_height, partitionList);
		double start_x = 0.0;
		double next_y_start = 0.0;
		double next_x_start = start_x;
		double y_max = 0.0;
		
		taskMonitor.setStatusMessage("Moving partitions");

		// System.out.println("Max dimension = "+max_dimension);

		// Now move the partitions into a reasonably compact grid.
		for (LayoutPartition partition : partitionList)
		{
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

	// Parallelized run through partitionList that executes 
	// the layoutPartition method on each partition.
	class ParallelLayoutTask implements Runnable
	{
		private final LayoutPartition partition;

		public ParallelLayoutTask(final LayoutPartition partition)
		{
			this.partition = partition;
		}

		@Override
		public void run() 
		{
			if (cancelled)
				return;

			// Partitions Requiring Layout
			if (partition.nodeCount() > 1) 
			{
				try 
				{
					partition.dontMove(true);
					layoutPartition(partition);
					partition.dontMove(false);
				} 
				catch (Throwable _e) 
				{
					_e.printStackTrace();
					partition.dontMove(false);
					return;
				}
			}

			// current_start += (double)partition.size();
			// taskMonitor.setProgress(current_start / total_nodes);
			nodesDone += partition.size();
			double pDone = nodesDone/total_nodes;
			taskMonitor.setProgress(pDone);
		}
	}
}
