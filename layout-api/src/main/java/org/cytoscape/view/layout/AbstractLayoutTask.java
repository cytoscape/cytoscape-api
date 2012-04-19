package org.cytoscape.view.layout;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyRow;
import org.cytoscape.model.CyTable;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;
import org.cytoscape.work.util.ListSingleSelection;
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
	
	private static final String NODE_PREFIX = "(Node) "; 
	private static final String EDGE_PREFIX = "(Edge) ";
	
	/**
	 * Never use this method from within a layout to access the submenu options,
     * instead call the configureLayoutFromSubmenuSelection() method to configure
	 * the layout based on menu selection. 
	 * @return The list single selection object that specifies the submenu
	 * names to be used for generating selection submenus. 
	 */
	public ListSingleSelection<String> getWeightingOptions() {
		if (weightingOptions != null)
			return weightingOptions;
		
		List<String> possibleValues = new ArrayList<String>(initialAttributes);

		Set<Class<?>> nodeAttrTypes = supportedNodeAttributeTypes;
		Set<Class<?>> edgeAttrTypes = supportedEdgeAttributeTypes;
		CyNetwork network = networkView.getModel();
		
		if (nodeAttrTypes != null && !nodeAttrTypes.isEmpty()) {
			for (final CyColumn column : network .getDefaultNodeTable().getColumns())
				if (nodeAttrTypes.contains(column.getType()))
					possibleValues.add(NODE_PREFIX + column.getName());
		} else if (edgeAttrTypes != null && !edgeAttrTypes.isEmpty()) {
			for (final CyColumn column : network.getDefaultEdgeTable().getColumns())
				if (edgeAttrTypes.contains(column.getType()))
					possibleValues.add(EDGE_PREFIX + column.getName());
		}

		weightingOptions = new ListSingleSelection<String>(possibleValues);
		if (possibleValues.size() > 0) {
			weightingOptions.setSelectedValue(possibleValues.get(0));
		}
		return weightingOptions;
	}

	public void setWeightingOptions(ListSingleSelection<String> opts) {
		configureLayoutFromSubmenuSelection(opts);
	}

	private final void configureLayoutFromSubmenuSelection(ListSingleSelection<String> opts) {
		String selectedMenu = opts.getSelectedValue();

		if (selectedMenu == null || selectedMenu == "")
			return;

		if (selectedMenu.startsWith(NODE_PREFIX))
			selectedMenu = selectedMenu.substring(NODE_PREFIX.length());
		if (selectedMenu.startsWith(EDGE_PREFIX))
			selectedMenu = selectedMenu.substring(EDGE_PREFIX.length());

		if (selectedMenu.length() > 0)
			layoutAttribute = selectedMenu;
	}

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
	protected final Set<View<CyNode>> nodesToLayOut;
	
	protected String layoutAttribute; 
	private final Set<Class<?>> supportedNodeAttributeTypes;
	private final Set<Class<?>> supportedEdgeAttributeTypes;
	private List<String> initialAttributes;
	private ListSingleSelection<String> weightingOptions;
	
	public AbstractLayoutTask(String name, CyNetworkView networkView, Set<View<CyNode>> nodesToLayOut) {
		this(name, networkView, nodesToLayOut, null, null, null);
	}
	
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
	public AbstractLayoutTask(String name, CyNetworkView networkView, Set<View<CyNode>> nodesToLayOut, Set<Class<?>> supportedNodeAttributeTypes, Set<Class<?>> supportedEdgeAttributeTypes, List<String> initialAttributes) {
		super();

		this.networkView = networkView;
		this.name = name;

		if (nodesToLayOut.size() == 0) {
			this.nodesToLayOut = new HashSet<View<CyNode>>(networkView.getNodeViews());
		} else {
			this.nodesToLayOut = Collections.unmodifiableSet(nodesToLayOut);
		}
		this.supportedNodeAttributeTypes = supportedNodeAttributeTypes;
		this.supportedEdgeAttributeTypes = supportedEdgeAttributeTypes;
		this.initialAttributes = initialAttributes;
	}
 
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
