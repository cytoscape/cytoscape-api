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
import java.util.concurrent.Future;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
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

		this.taskMonitor = taskMonitor;
		
		boolean useAllNodes = nodesToLayOut.size() == networkView.getNodeViews().size();
		
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
		current_start = 0;

		// Set up offsets -- we start with the overall min and max
		double xStart = (partitionList.get(0)).getMinX();
		double yStart = (partitionList.get(0)).getMinY();

		for (LayoutPartition part: partitionList) {
			xStart = Math.min(xStart, part.getMinX());
			yStart = Math.min(yStart, part.getMinY());
		}

		double next_x_start = xStart;
		double next_y_start = yStart;
		double current_max_y = 0;

		double max_dimensions = Math.sqrt((double) network.getNodeCount());
		// give each node room
		max_dimensions *= incr;
		max_dimensions += xStart;

		final int numThreads = 1;// Runtime.getRuntime().availableProcessors() * 4;	// Overthread to account for inefficiencies.
		
		// Parallelized run through partitionList that executes 
		// the layoutPartition method on each partition.
		class ParallelLayoutTask implements Runnable
		{
			private final int partitionID;
			
			public ParallelLayoutTask(int partitionID)
			{
				this.partitionID = partitionID;
			}
			
			@Override
			public void run() 
			{
				if (cancelled)
					return;
				
				LayoutPartition partition = partitionList.get(partitionID);
	
				// Partitions Requiring Layout
				if (partition.nodeCount() > 1) 
				{
					try 
					{
						layoutPartition(partition);
					} 
					catch (Throwable _e) 
					{
						_e.printStackTrace();
						return;
					}
				}
				else
				{
					// Reset our bounds
					partition.resetNodes();
				}
				
				synchronized (partitionList)
				{
					current_start += (double)partition.size();
					taskMonitor.setProgress(current_start / total_nodes);
				}
			} 
		}
		
		// Create thread pool, ...
		ExecutorService threadPool = Executors.newFixedThreadPool(numThreads);
		// ... spawn futures, ...
		List<Future<?>> futures = new LinkedList<Future<?>>();
		for (int i = 0; i < partitionList.size(); i++)
			futures.add(threadPool.submit(new ParallelLayoutTask(i)));
		// ... and wait for them to execute.
		for (Future<?> future : futures)
			try 
			{
				future.get();
			} 
			catch (Exception e) { } 
		
		// Now move the partitions into a reasonably compact grid.
		for (LayoutPartition partition : partitionList)
		{
			if (cancelled)
				break;

			// get the partition
			current_size = (double)partition.size();

			// Partitions Requiring Layout
			if (partition.nodeCount() > 1) 
			{
				// OFFSET
				if (useAllNodes && !singlePartition)
					partition.offset(next_x_start, next_y_start);
			} 
			else // single nodes
			{
				// Single node -- get it
				LayoutNode node = (LayoutNode) partition.getNodeList().get(0);
				node.setLocation(next_x_start, next_y_start);
				partition.moveNodeToLocation(node);
			}
			
			double last_max_x = partition.getMaxX();
			double last_max_y = partition.getMaxY();

			if (last_max_y > current_max_y)
				current_max_y = last_max_y;

			if (last_max_x > max_dimensions) 
			{
				next_x_start = xStart;
				next_y_start = current_max_y;
				next_y_start += incr;
			} 
			else 
			{
				next_x_start = last_max_x;
				next_x_start += incr;
			}
		}
	}
}
