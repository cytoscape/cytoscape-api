/*
  File: AbstractLayoutAlgorithm.java

  Copyright (c) 2006, 2010, The Cytoscape Consortium (www.cytoscape.org)

  This library is free software; you can redistribute it and/or modify it
  under the terms of the GNU Lesser General Public License as published
  by the Free Software Foundation; either version 2.1 of the License, or
  any later version.

  This library is distributed in the hope that it will be useful, but
  WITHOUT ANY WARRANTY, WITHOUT EVEN THE IMPLIED WARRANTY OF
  MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.  The software and
  documentation provided hereunder is on an "as is" basis, and the
  Institute for Systems Biology and the Whitehead Institute
  have no obligations to provide maintenance, support,
  updates, enhancements or modifications.  In no event shall the
  Institute for Systems Biology and the Whitehead Institute
  be liable to any party for direct, indirect, special,
  incidental or consequential damages, including lost profits, arising
  out of the use of this software and its documentation, even if the
  Institute for Systems Biology and the Whitehead Institute
  have been advised of the possibility of such damage.  See
  the GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with this library; if not, write to the Free Software Foundation,
  Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
*/
package org.cytoscape.view.layout;


import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyRow;
import org.cytoscape.model.CyTableUtil;
import org.cytoscape.model.CyColumn;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.cytoscape.work.TaskMonitor;
import org.cytoscape.work.undo.UndoSupport;
import org.cytoscape.work.Tunable;
import org.cytoscape.work.util.ListSingleSelection;


/**
 * The AbstractLayoutAlgorithm provides a basic implementation of a layout TaskFactory.
 * @CyAPI.Abstract.Class
 */
abstract public class AbstractLayoutAlgorithm implements CyLayoutAlgorithm {

	private ListSingleSelection<String> submenuDef;

	private static final String ALL_NODES = " All Nodes";
	private static final String SELECTED_NODES_ONLY = " Selected Nodes Only";
	private static final String NODE_PREFIX = "(Node) "; 
	private static final String EDGE_PREFIX = "(Edge) "; 
	

	/**
	 * Never use this method from within a layout to access the submenu options,
     * instead call the configureLayoutFromSubmenuSelection() method to configure
	 * the layout based on menu selection. 
	 * @return The list single selection object that specifies the submenu
	 * names to be used for generating selection submenus. 
	 */
	@Tunable(description="Apply to")
	public ListSingleSelection<String> getSubmenuOptions() {

		List<String> possibleValues = new ArrayList<String>();

		Set<Class<?>> nodeAttrTypes = supportsNodeAttributes();
		Set<Class<?>> edgeAttrTypes = supportsEdgeAttributes();

		if ( nodeAttrTypes != null && !nodeAttrTypes.isEmpty() ) {
	        for (final CyColumn column : network.getDefaultNodeTable().getColumns()) 
	            if (nodeAttrTypes.contains(column.getType()))
					possibleValues.add(NODE_PREFIX + column.getName());
		} else if ( edgeAttrTypes != null && !edgeAttrTypes.isEmpty() ) {
	        for (final CyColumn column : network.getDefaultEdgeTable().getColumns()) 
	            if (edgeAttrTypes.contains(column.getType()))
					possibleValues.add(EDGE_PREFIX + column.getName());
		}
	
		int numSelected = CyTableUtil.getNodesInState(network, CyNetwork.SELECTED, true).size();
		if (supportsSelectedOnly() && numSelected > 0) {

			if ( possibleValues.isEmpty() ) {
				possibleValues.add(ALL_NODES);
				possibleValues.add(SELECTED_NODES_ONLY);
			} else {
				List<String> newPossibleValues = new ArrayList<String>();
				for ( String pv : possibleValues ) {
					newPossibleValues.add(pv + ALL_NODES);
					newPossibleValues.add(pv + SELECTED_NODES_ONLY);
				}		
				possibleValues = newPossibleValues;
			}
		} 

//		if ( possibleValues.isEmpty() )
//			possibleValues.add( humanName );
		
		submenuDef = new ListSingleSelection<String>( possibleValues );
		if (possibleValues.size() > 0) {
			submenuDef.setSelectedValue(possibleValues.get(0));
		}
		return submenuDef;
	}

	/**
	 * This method is a no-op.  Don't use it.
	 */	
	public void setSubmenuOptions(ListSingleSelection<String> opts) {
		// no-op
	}

	/**
	 *
	 */
	protected void configureLayoutFromSubmenuSelection() {
		String selectedMenu = submenuDef.getSelectedValue(); 

		if ( selectedMenu == null || selectedMenu == "" )
			return;

		setSelectedOnly( selectedMenu.endsWith(SELECTED_NODES_ONLY) );

		if ( selectedMenu.endsWith( ALL_NODES ) ) 
			selectedMenu = selectedMenu.replaceFirst( ALL_NODES, "" );
		if ( selectedMenu.endsWith( SELECTED_NODES_ONLY ) ) 
			selectedMenu = selectedMenu.replaceFirst( SELECTED_NODES_ONLY, "" );


		if ( selectedMenu.startsWith( NODE_PREFIX ) ) 
			selectedMenu = selectedMenu.replaceFirst( NODE_PREFIX, "" );
		if ( selectedMenu.startsWith( EDGE_PREFIX ) ) 
			selectedMenu = selectedMenu.replaceFirst( EDGE_PREFIX, "" );

		if ( selectedMenu.length() > 0 )
			setLayoutAttribute(selectedMenu);	
	}
	
	/**
	 * The network view that the layout will be applied to.
	 */
	protected CyNetworkView networkView;
	
	/**
	 * The network model underlying the networkView.  This shouldn't be set directly
	 * by extending classes.
	 */
	protected CyNetwork network;

	/**
	 * The UndoSupport object use for allowing undo of layouts.
	 */
	protected final UndoSupport undo;
//	private ViewChangeEdit undoableEdit;
	
	/**
	 * The set of nodes that are  
	 */
	protected Set<View<CyNode>> staticNodes = new HashSet<View<CyNode>>();
	
	/**
	 * Indicates that only selected nodes should be laid out.
	 */
	protected boolean selectedOnly;
	
	private final boolean supportsSelectedOnly;
	private String edgeAttribute = null;
	private String nodeAttribute = null;
	private Dimension currentSize = new Dimension(20, 20);
	private final String humanName;
	private final String computerName;

	/**
	 * The Constructor.
	 * @param undo the UndoSupport object used for allowing undo of layouts.
	 * @param computerName a computer readable name used to construct property strings.
	 * @param humanName a user visible name of the layout.
	 * @param supportsSelectedOnly indicates whether only selected nodes should be laid out.
	 */
	public AbstractLayoutAlgorithm(final UndoSupport undo, final String computerName, final String humanName, boolean supportsSelectedOnly) {
		this.undo = undo;
		this.computerName = computerName;
		this.humanName = humanName;
		this.supportsSelectedOnly = supportsSelectedOnly;
	}

	/**
	 * Sets the network view to be laid out.
	 * @param networkView the network view to be laid out.
	 */
	@Override
	public void setNetworkView(final CyNetworkView networkView) {
		this.networkView = networkView;
		this.network = networkView.getModel();
		double node_count = (double) network.getNodeCount();
		node_count = Math.sqrt(node_count);
		node_count *= 100;
		currentSize = new Dimension((int) node_count, (int) node_count);
	}
	
	/**
	 * Set the name of the attribute to use for attribute
	 * dependent layout algorithms.
	 *
	 * @param attributeName The name of the attribute
	 */
	public void setLayoutAttribute(String attributeName) {
		if (supportsNodeAttributes().size() > 0) {
			nodeAttribute = attributeName;
		} else if (supportsEdgeAttributes().size() > 0) {
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

	/**
	 * A computer readable name used to construct property strings.
	 * @return a computer readable name used to construct property strings.
	 */
	public String getName() {
		return computerName;
	}

	/**
	 * Used to get the user-visible name of the layout.
	 * @return the user-visible name of the layout.
	 */
	public String toString() {
		return humanName;
	}

	/**
	 * Indicates whether this algorithm supports applying the layout 
	 * only to selected nodes.
	 */
	public final boolean supportsSelectedOnly() {
		return supportsSelectedOnly;
	}

	/**
	 * Returns the types of node attributes supported by
	 * this algorithm.  This should be overridden by the
	 * specific algorithm.
	 *
	 * @return the list of supported attribute types, or null
	 * if node attributes are not supported
	 */
	public Set<Class<?>> supportsNodeAttributes() {
		return new HashSet<Class<?>>();
	}

	/**
	 * Returns the types of edge attributes supported by
	 * this algorithm.  This should be overridden by the
	 * specific algorithm.
	 *
	 * @return the list of supported attribute types, or null
	 * if edge attributes are not supported
	 */
	public Set<Class<?>> supportsEdgeAttributes() {
		return new HashSet<Class<?>>();
	}

	/**
	 * This returns the list of "attributes" that are provided
	 * by an algorithm for internal purposes.  For example,
	 * an edge-weighted algorithm might seed the list of
	 * attributes with "unweighted".  This should be overloaded
	 * by algorithms that intend to return custom attributes.
	 *
	 * @return A (possibly empty) list of attributes
	 */
	public List<String> getInitialAttributeList() {
		return new ArrayList<String>();
	}

	/** 
	 * Descendants need to call this if they intend to use the "staticNodes" field.
	 */
	public final void initStaticNodes() {
		staticNodes.clear();
		final Set<CyNode> selectedNodes =
			new HashSet<CyNode>(CyTableUtil.getNodesInState(networkView.getModel(),
									CyNetwork.SELECTED, true));
		for (final View<CyNode> nodeView : networkView.getNodeViews()) {
			if (!selectedNodes.contains(nodeView.getModel()))
				staticNodes.add(nodeView);
		}
	}
}
