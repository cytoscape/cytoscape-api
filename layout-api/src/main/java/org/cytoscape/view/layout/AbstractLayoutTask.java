package org.cytoscape.view.layout;


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyRow;
import org.cytoscape.model.CyTable;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;
import org.cytoscape.work.undo.UndoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  This is a basic implementation of a LayoutAlgorithm Task that does
 *  some bookkeeping, but primarily delegates to the doLayout() method.
 *  Extensions of this class are meant to operate on the CyNetworkView 
 *  provided to the constructor (and is available as a protected member 
 *  variable).
 *  @CyAPI.Abstract.Class
 */
public abstract class AbstractLayoutTask extends AbstractTask {
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractLayoutTask.class);
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
	 * The node views that will be laid out by the algorithm.
	 */
	protected final Set<View<CyNode>> nodesToLayOut;
	
	/** 
	 * The attribute to be used for this layout. May be null and/or ignored.
	 */
	protected final String layoutAttribute;
	
	/**
	 * Undo support for the task.
	 */
	protected final UndoSupport undo;
	
	/**
	 * Constructor.
	 * @param name The name of the layout algorithm. 
	 * @param networkView The network view that the layout algorithm will be applied to.
	 * @param nodesToLayOut The set of nodes to be laid out. 
	 * @param layoutAttribute The name of the attribute to use for the layout.  May be null or empty.
	 */
	public AbstractLayoutTask(String name, 
	                          CyNetworkView networkView, 
	                          Set<View<CyNode>> nodesToLayOut, 
	                          String layoutAttribute,
	                          UndoSupport undo) {
		super();

		this.networkView = networkView;
		this.name = name;
		this.undo = undo;

		if (nodesToLayOut.size() == 0) {
			this.nodesToLayOut = new HashSet<View<CyNode>>(networkView.getNodeViews());
		} else {
			this.nodesToLayOut = Collections.unmodifiableSet(nodesToLayOut);
		}

		this.layoutAttribute = layoutAttribute;
	}
 
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void run(final TaskMonitor taskMonitor) {
		final long start = System.currentTimeMillis();
		logger.debug("Layout Start: Algorithm = " + name);

		// do some sanity checking
		if (networkView == null)
			return;

		final CyNetwork network = networkView.getModel();
		
		if (nodesToLayOut.size() == 0 && networkView.getNodeViews().size() == 0)
			return;
		
		if ( undo != null )
			undo.postEdit(new LayoutEdit(name,networkView));

		// this is overridden by children and does the actual layout
		doLayout(taskMonitor);

		// Fit Content method always redraw the presentation.
		networkView.fitContent();

		// update the __layoutAlgorithm attribute
		final CyRow networkAttributes = network.getRow(network,CyNetwork.HIDDEN_ATTRS);
		final CyTable netAttrsTable = networkAttributes.getTable();
		if (netAttrsTable.getColumn(LAYOUT_ALGORITHM) == null)
			netAttrsTable.createColumn(LAYOUT_ALGORITHM, String.class, true);
		networkAttributes.set(LAYOUT_ALGORITHM, name);

		logger.debug("Layout finished in " + (System.currentTimeMillis() - start) + " msec.");
	}

	/**
	 * This method is designed to actually encapsulate the layout algorithm. It will be
	 * called from within the run() method of the task.  
	 * @param taskMonitor Provided to allow updates to the task status.
	 */
	protected abstract void doLayout(final TaskMonitor taskMonitor);
}
