package org.cytoscape.view.layout;


import java.util.Set;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyRow;
import org.cytoscape.model.CyTable;

import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;

import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  This is a basic implementation of a LayoutAlgorithm Task that does
 *  some bookkeeping, but primarily delegates to the doLayout() method.
 *  Extensions of this class are meant to operate on the CyNetworkView 
 *  provided to the constructor (and is available as a protected member 
 *  variable).
 */
public abstract class AbstractBasicLayoutTask extends AbstractTask {
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractBasicLayoutTask.class);
	private final String name;
	
	/**
	 * The table column name that provides the layout algorithm name.
	 */
	protected static final String LAYOUT_ALGORITHM = "layoutAlgorithm";

	/**
	 * The network view that the layout will be applied to.
	 */
	protected final CyNetworkView networkView;
	
	/**
	 * The set of nodes whose positions are locked and not meant to change.
	 */
	protected final Set<View<CyNode>> staticNodes;
	
	/**
	 * Indicates whether to apply the layout to all nodes or only the selected nodes.
	 */
	protected final boolean selectedOnly;

	/**
	 * Constructor.
	 * @param networkView The network view that the layout algorithm will be applied to.
	 * @param name The name of the algorithm.  Used for setting attributes associated with 
	 * this layout.
	 * @param selectedOnly Indicates whether the layout should be applied to the selected nodes
	 * or not.
	 * @param staticNodes The list of nodes whose positions are meant to be locked and
	 * not changed.
	 */
	public AbstractBasicLayoutTask(final CyNetworkView networkView, final String name, boolean selectedOnly,
			final Set<View<CyNode>> staticNodes) {
		super();

		this.networkView = networkView;
		this.name = name;
		this.selectedOnly = selectedOnly;
		this.staticNodes = staticNodes;
	}
 
	@Override
	public final void run(final TaskMonitor taskMonitor) {
		final long start = System.currentTimeMillis();
		logger.debug("Layout Start: Algorithm = " + name);

		// do some sanity checking
		if (networkView == null)
			return;

		final CyNetwork network = networkView.getModel();
		if (network.getNodeCount() <= 0)
			return;

		// this is overridden by children and does the actual layout
		doLayout(taskMonitor);

		// Fit Content method always redraw the presentation.
		networkView.fitContent();

		// update the __layoutAlgorithm attribute
		final CyRow networkAttributes = network.getCyRow(CyNetwork.HIDDEN_ATTRS);
		final CyTable netAttrsTable = networkAttributes.getTable();
		if (netAttrsTable.getColumn(LAYOUT_ALGORITHM) == null)
			netAttrsTable.createColumn(LAYOUT_ALGORITHM, String.class, true);
		networkAttributes.set(LAYOUT_ALGORITHM, name);

		logger.debug("Layout finished in " + (System.currentTimeMillis() - start) + " msec.");
	}

	/**
	 * Returns true if the specified node view is locked in place, false otherwise.
	 * @param v The node view to test.
	 * @return True if the specified node view is locked in place, false otherwise.
	 */
	protected boolean isLocked(View<CyNode> v) {
		return ((staticNodes != null) && (staticNodes.contains(v)));
	}

	/**
	 * This method is designed to actually encapsulate the layout algorithm. It will be
	 * called from within the run() method of the task.  
	 * @param taskMonitor Provided to allow updates to the task status.
	 */
	protected abstract void doLayout(final TaskMonitor taskMonitor);
}
