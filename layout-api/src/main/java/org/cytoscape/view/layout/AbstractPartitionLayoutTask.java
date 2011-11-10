package org.cytoscape.view.layout;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.cytoscape.work.TaskMonitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  This is a more helpful implementation of a LayoutAlgorithm Task 
 *  that extends AbstractBasicLayoutTask and does the work of partitioning
 *  the CyNetworkView so that partitions may be laid out individually.
 *  Extensions of this class are meant to implement the layoutPartition()
 *  method and operate on the LayoutParition object that is passed to that
 *  method as an argument.
 *  @CyAPI.Abstract.Class
 */
public abstract class AbstractPartitionLayoutTask extends AbstractBasicLayoutTask {
	
	private static Logger logger = LoggerFactory.getLogger(AbstractPartitionLayoutTask.class);
	
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
	protected EdgeWeighter edgeWeighter = null;
	
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
	 * @param networkView the CyNetworkView being partitioned.
	 * @param name #ASKMIKE
	 * @param singlePartition 
	 * @param selectedOnly 
	 * @param staticNodes 
	 */
	public AbstractPartitionLayoutTask(final CyNetworkView networkView, final String name,
				      final boolean singlePartition, final boolean selectedOnly, final Set<View<CyNode>> staticNodes) {
		super(networkView, name, selectedOnly, staticNodes);
		this.singlePartition = singlePartition;
	}

	/**
	 * Override this method and layout the LayoutPartion just
	 * like you would a NetworkView.
	 *
	 * @param partition The LayoutPartion to be laid out. 
	 */
	public abstract void layoutPartion(LayoutPartition partition);

	/**
	 * Returns true if the layout supports only applying the layout to selected nodes.
	 *
	 * @return True if the layout supports only applying the layout to selected nodes.
	 */
	public boolean supportsSelectedOnly() {
		return true;
	}

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
			taskMonitor.setStatusMessage("Completed " + (int)pDone + "%");
		}
	}

	/**
	 * AbstractGraphPartitionLayout implements the doLayout method
	 * of AbstractBasicLayout in which it calls the layoutParition
	 * method on each LayoutPartition object created for the network.
	 * @param taskMonitor the TaskMonitor provided by the run() method
	 * of the Task.
	 */
	public void doLayout(final TaskMonitor taskMonitor) {
		final CyNetwork network = networkView.getModel();
		if (edgeWeighter != null)
			edgeWeighter.reset();

		this.taskMonitor = taskMonitor;
		
		// Depending on whether we are partitioned or not,
		// we use different initialization.  Note that if the user only wants
		// to lay out selected nodes, partitioning becomes a very bad idea!
		if (selectedOnly || singlePartition) {
			// We still use the partition abstraction, even if we're
			// not partitioning.  This makes the code further down
			// much cleaner
			LayoutPartition partition = new LayoutPartition(networkView, selectedOnly, edgeWeighter);
			partition.setTaskMonitor(taskMonitor);
			partitionList = new ArrayList(1);
			partitionList.add(partition);
		} else if (staticNodes != null && staticNodes.size() > 0) {
			// Someone has programmatically locked a set of nodes -- construct
			// the list of unlocked nodes
			List<CyNode> unlockedNodes = new ArrayList();
			for (CyNode node: network.getNodeList()) {
				if (!isLocked(networkView.getNodeView(node))) {
					unlockedNodes.add(node);
				}
			}
			LayoutPartition partition = new LayoutPartition(networkView, unlockedNodes, edgeWeighter);
			partitionList = new ArrayList(1);
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
					layoutPartion(partition);
				} catch (Throwable _e) {
					_e.printStackTrace();
					return;
				}

			if (!selectedOnly && !singlePartition) {
				// System.out.println("Offsetting partition #"+partition.getPartitionNumber()+" to "+next_x_start+", "+next_y_start);
				// OFFSET
				partition.offset(next_x_start, next_y_start);
			}

			// single nodes
			} else if ( partition.nodeCount() == 1 ) {
				// Reset our bounds
				partition.resetNodes();

				// Single node -- get it
				LayoutNode node = (LayoutNode) partition.getNodeList().get(0);
				node.setLocation(next_x_start, next_y_start);
				partition.moveNodeToLocation(node);
			} else {
				continue;
			}

			double last_max_x = partition.getMaxX();
			double last_max_y = partition.getMaxY();

			if (last_max_y > current_max_y) {
				current_max_y = last_max_y;
			}

			if (last_max_x > max_dimensions) {
				next_x_start = xStart;
				next_y_start = current_max_y;
				next_y_start += incr;
			} else {
				next_x_start = last_max_x;
				next_x_start += incr;
			}

			setTaskStatus( 100 );
			current_start += current_size;
		} 
	}
}
