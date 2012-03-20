package org.cytoscape.view.layout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyTableUtil;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.cytoscape.work.Tunable;
import org.cytoscape.work.util.ListSingleSelection;

public class AbstractLayoutAlgorithmContext implements CyLayoutContext {

	private static final String ALL_NODES = " All Nodes";
	private static final String SELECTED_NODES_ONLY = " Selected Nodes Only";
	private static final String NODE_PREFIX = "(Node) "; 
	private static final String EDGE_PREFIX = "(Edge) "; 
	
	private ListSingleSelection<String> submenuDef;

	/**
	 * The set of nodes that are  
	 */
	protected Set<View<CyNode>> staticNodes = new HashSet<View<CyNode>>();
	
	/**
	 * Indicates that only selected nodes should be laid out.
	 */
	protected boolean selectedOnly;
	
	private String edgeAttribute = null;
	private String nodeAttribute = null;
	
	private Set<Class<?>> supportedNodeAttributes;
	private Set<Class<?>> supportedEdgeAttributes;
	private boolean supportsSelectedOnly;
	protected CyNetwork network;
	private CyNetworkView networkView;

	/**
	 * Never use this method from within a layout to access the submenu options,
     * instead call the configureLayoutFromSubmenuSelection() method to configure
	 * the layout based on menu selection. 
	 * @return The list single selection object that specifies the submenu
	 * names to be used for generating selection submenus. 
	 */
	@Tunable(description = "Apply to")
	public ListSingleSelection<String> getSubmenuOptions() {

		List<String> possibleValues = new ArrayList<String>();

		Set<Class<?>> nodeAttrTypes = supportedNodeAttributes;
		Set<Class<?>> edgeAttrTypes = supportedEdgeAttributes;

		if (nodeAttrTypes != null && !nodeAttrTypes.isEmpty()) {
			for (final CyColumn column : network.getDefaultNodeTable().getColumns())
				if (nodeAttrTypes.contains(column.getType()))
					possibleValues.add(NODE_PREFIX + column.getName());
		} else if (edgeAttrTypes != null && !edgeAttrTypes.isEmpty()) {
			for (final CyColumn column : network.getDefaultEdgeTable().getColumns())
				if (edgeAttrTypes.contains(column.getType()))
					possibleValues.add(EDGE_PREFIX + column.getName());
		}

		int numSelected = CyTableUtil.getNodesInState(network, CyNetwork.SELECTED, true).size();
		if (supportsSelectedOnly && numSelected > 0) {

			if (possibleValues.isEmpty()) {
				possibleValues.add(ALL_NODES);
				possibleValues.add(SELECTED_NODES_ONLY);
			} else {
				List<String> newPossibleValues = new ArrayList<String>();
				for (String pv : possibleValues) {
					newPossibleValues.add(pv + ALL_NODES);
					newPossibleValues.add(pv + SELECTED_NODES_ONLY);
				}
				possibleValues = newPossibleValues;
			}
		}

		submenuDef = new ListSingleSelection<String>(possibleValues);
		if (possibleValues.size() > 0) {
			submenuDef.setSelectedValue(possibleValues.get(0));
		}
		return submenuDef;
	}

	public void setSubmenuOptions(ListSingleSelection<String> opts) {
		configureLayoutFromSubmenuSelection(opts);
	}

	protected void configureLayoutFromSubmenuSelection(ListSingleSelection<String> opts) {
		String selectedMenu = opts.getSelectedValue();

		if (selectedMenu == null || selectedMenu == "")
			return;

		setSelectedOnly(selectedMenu.endsWith(SELECTED_NODES_ONLY));

		if (selectedMenu.endsWith(ALL_NODES))
			selectedMenu = selectedMenu.replaceFirst(ALL_NODES, "");
		if (selectedMenu.endsWith(SELECTED_NODES_ONLY))
			selectedMenu = selectedMenu.replaceFirst(SELECTED_NODES_ONLY, "");

		if (selectedMenu.startsWith(NODE_PREFIX))
			selectedMenu = selectedMenu.substring(NODE_PREFIX.length());
		if (selectedMenu.startsWith(EDGE_PREFIX))
			selectedMenu = selectedMenu.substring(EDGE_PREFIX.length());

		if (selectedMenu.length() > 0)
			setLayoutAttribute(selectedMenu);
	}

	public AbstractLayoutAlgorithmContext(boolean supportsSelectedOnly, Set<Class<?>> supportedNodeAttributes, Set<Class<?>> supportedEdgeAttributes) {
		this.supportsSelectedOnly = supportsSelectedOnly;
		this.supportedNodeAttributes = supportedNodeAttributes;
		this.supportedEdgeAttributes = supportedEdgeAttributes;
	}
	
	/**
	 * Sets the network view to be laid out.
	 * @param networkView the network view to be laid out.
	 */
	public void setNetworkView(final CyNetworkView networkView) {
		this.networkView = networkView;
		network = networkView.getModel();
		double node_count = (double) network.getNodeCount();
		node_count = Math.sqrt(node_count);
		node_count *= 100;
		
		if (useSelectedOnly()) {
			initStaticNodes(networkView);
		}
	}
	
	public CyNetworkView getNetworkView() {
		return networkView;
	}

	/**
	 * Set the name of the attribute to use for attribute
	 * dependent layout algorithms.
	 *
	 * @param attributeName The name of the attribute
	 */
	public void setLayoutAttribute(String attributeName) {
		if (supportedNodeAttributes.size() > 0) {
			nodeAttribute = attributeName;
		} else if (supportedEdgeAttributes.size() > 0) {
			edgeAttribute = attributeName;
		}
	}
	
	/**
	 * Set the flag that indicates that this algorithm
	 * should only operate on the currently selected nodes.
	 *
	 * @param selectedOnly set to "true" if the algorithm should
	 * only apply to selected nodes only
	 */
	public void setSelectedOnly(boolean selectedOnly) {
		this.selectedOnly = selectedOnly;
	}

	public boolean useSelectedOnly() {
		return selectedOnly;
	}
	
	/** 
	 * Descendants need to call this if they intend to use the "staticNodes" field.
	 */
	public final void initStaticNodes(CyNetworkView networkView) {
		staticNodes.clear();
		final Set<CyNode> selectedNodes =
			new HashSet<CyNode>(CyTableUtil.getNodesInState(networkView.getModel(),
									CyNetwork.SELECTED, true));
		for (final View<CyNode> nodeView : networkView.getNodeViews()) {
			if (!selectedNodes.contains(nodeView.getModel()))
				staticNodes.add(nodeView);
		}
	}

	public Set<View<CyNode>> getStaticNodes() {
		return staticNodes;
	}
}
