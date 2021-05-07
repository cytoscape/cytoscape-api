package org.cytoscape.application.swing;

/*
 * #%L
 * Cytoscape Swing Application API (swing-application-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2021 The Cytoscape Consortium
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

import java.util.Collection;
import java.util.List;

import javax.swing.Action;
import javax.swing.JMenuItem;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.work.swing.DynamicSubmenuListener;

/**
 * A class that allows the enabled state of an Action of JMenuItem to managed in 
 * a consistent way. 
 * <br/>
 * Recognized values for the "enableFor" description string are:
 * <ul>
 * <li>network</li>
 * <li>networkWithoutView</li>
 * <li>networkAndView</li>
 * <li>selectedNodesOrEdges</li>
 * <li>selectedNodes</li>
 * <li>selectedEdges</li>
 * <li>table</li>
 * <li>always</li>
 * </ul>
 * 
 * @CyAPI.Final.Class
 * @CyAPI.InModule swing-application-api
 */
public final class ActionEnableSupport extends AbstractEnableSupport {

	private final CyApplicationManager applicationManager;
	private final CyNetworkViewManager networkViewManager;
	private final String enableFor;

	/**
	 * Enable when at least one network exists.
	 */
	public static final String ENABLE_FOR_NETWORK = "network";
	
	/**
	 * Enable when only one network is selected.
	 */
	public static final String ENABLE_FOR_SINGLE_NETWORK = "singleNetwork";

	/**
	 * Enable when at least one network with NO view exists.
	 */
	public static final String ENABLE_FOR_NETWORK_WITHOUT_VIEW = "networkWithoutView";

	/**
	 * Enable when at least one network WITH view exists.
	 */
	public static final String ENABLE_FOR_NETWORK_AND_VIEW = "networkAndView";

	/**
	 * Enable when either nodes or edges have been selected in a network. 
	 */
	public static final String ENABLE_FOR_SELECTED_NODES_OR_EDGES = "selectedNodesOrEdges";

	/**
	 * Enable when nodes have been selected in a network. 
	 */
	public static final String ENABLE_FOR_SELECTED_NODES = "selectedNodes";

	/**
	 * Enable when edges have been selected in a network. 
	 */
	public static final String ENABLE_FOR_SELECTED_EDGES = "selectedEdges";

	/**
	 * Enable when at least one network exists.
	 */
	public static final String ENABLE_FOR_TABLE = "table";

	/**
	 * Enable always. 
	 */
	public static final String ENABLE_FOR_ALWAYS = "always";

	/**
	 * Constructor.
	 * @param submenuListener The submenu listener whose enabled state will be updated.
	 * @param enableFor The description of how the submenu should be enabled.
	 * See class documentation above for allowable values for this string.
	 * @param applicationManager The application manager.
	 */
	public ActionEnableSupport(DynamicSubmenuListener submenuListener, String enableFor,
			final CyApplicationManager applicationManager, final CyNetworkViewManager networkViewManager) {
		super(submenuListener);

		this.networkViewManager = networkViewManager;
		this.enableFor = enableFor;
		this.applicationManager = applicationManager;
	}

	/**
	 * Constructor.
	 * @param action The action whose enabled state will be updated.
	 * @param enableFor The description of how the action should be enabled.
	 * See class documentation above for allowable values for this string.
	 * @param applicationManager The application manager.
	 */
	public ActionEnableSupport(Action action, String enableFor, CyApplicationManager applicationManager, final CyNetworkViewManager networkViewManager) {
		super(action);
		this.enableFor = enableFor;
		this.applicationManager = applicationManager;
		this.networkViewManager = networkViewManager;
	}

	/**
	 * Constructor.
	 * @param menuItem The menuItem whose enabled state will be updated.
	 * @param enableFor The description of how the menuItem should be enabled.
	 * See class documentation above for allowable values for this string.
	 * @param applicationManager The application manager.
	 */
	public ActionEnableSupport(JMenuItem menuItem, String enableFor, CyApplicationManager applicationManager, final CyNetworkViewManager networkViewManager) {
		super(menuItem);
		this.enableFor = enableFor;
		this.applicationManager = applicationManager;
		this.networkViewManager = networkViewManager;
	}

	/**
	 * Updates the enable state for the specified action/menuListener/menuItem
	 * for the specified enableFor description and the state of the system.
	 */
	public void updateEnableState() {
		if (enableFor == null)
			setEnabled(true);
		else if (enableFor.equals(ENABLE_FOR_ALWAYS))
			setEnabled(true);
		else if (enableFor.equals(ENABLE_FOR_NETWORK))
			enableForNetwork();
		else if (enableFor.equals(ENABLE_FOR_SINGLE_NETWORK))
			enableForSingleNetwork();
		else if (enableFor.equals(ENABLE_FOR_NETWORK_WITHOUT_VIEW))
			enableForNetworkWithoutView();
		else if (enableFor.equals(ENABLE_FOR_NETWORK_AND_VIEW))
			enableForNetworkAndView();
		else if (enableFor.equals(ENABLE_FOR_SELECTED_NODES_OR_EDGES))
			enableForSelectedNodesOrEdges();
		else if (enableFor.equals(ENABLE_FOR_SELECTED_NODES))
			enableForSelectedNodes();
		else if (enableFor.equals(ENABLE_FOR_SELECTED_EDGES))
			enableForSelectedEdges();
		else if (enableFor.equals(ENABLE_FOR_TABLE))
			enableForTable();
		else
			setEnabled(true);
	}

	//
	// The following methods are utility methods that that enable or disable
	// the action based on the state of Cytoscape. These methods are meant to
	// reduce duplicate code since many actions demand the same state to be
	// functional (e.g. a network and network view must exist). These methods
	// are generally called from within implementations of {@link
	// #menuSelected},
	// but can be called from anywhere.
	//

	/**
	 * Enable the action if the current network exists and is not null.
	 */
	private void enableForNetwork() {
		final CyNetwork n = applicationManager.getCurrentNetwork();
		setEnabled(n != null);
	}
	
	private void enableForSingleNetwork() {
		final CyNetwork n = applicationManager.getCurrentNetwork();
		
		if (n == null) {
			setEnabled(false);
		} else {
			final List<CyNetwork> networks = applicationManager.getSelectedNetworks();
			setEnabled(networks.size() == 1 && networks.contains(n));
		}
	}

	/**
	 * Enable the action if the selected network exists, is not null,
	 * and no view is available for the network.
	 */
	private void enableForNetworkWithoutView() {
		final CyNetwork n = applicationManager.getCurrentNetwork();

		if (n == null) {
			setEnabled(false);
		} else {
			// Network exists.
			final Collection<CyNetworkView> views = networkViewManager.getNetworkViews(n);
			setEnabled(views.isEmpty());
		}
	}

	/**
	 * Enable the action if the current network and view exist and are not null.
	 */
	private void enableForNetworkAndView() {
		final CyNetworkView v = applicationManager.getCurrentNetworkView();
		setEnabled(v != null);
	}

	/**
	 * Enable the action if at least one selected node or edge is required to
	 * perform the action.
	 */
	private void enableForSelectedNodesOrEdges() {
		final CyNetwork n = applicationManager.getCurrentNetwork();

		// Disable if there is no current network.
		if (n == null)
			setEnabled(false);
		else
			setEnabled( ((n.getDefaultNodeTable().countMatchingRows(CyNetwork.SELECTED, true) > 0) ||
			             (n.getDefaultEdgeTable().countMatchingRows(CyNetwork.SELECTED, true) > 0)) ); 
	}

	/**
	 * Enable the action if at least one selected node is required to perform
	 * the action.
	 */
	private void enableForSelectedNodes() {
		CyNetwork n = applicationManager.getCurrentNetwork();

		if (n == null)
			setEnabled(false);
		else
			setEnabled( (n.getDefaultNodeTable().countMatchingRows(CyNetwork.SELECTED, true) > 0) );
	}

	/**
	 * Enable the action if at least one selected edge is required to perform
	 * the action.
	 */
	private void enableForSelectedEdges() {
		CyNetwork n = applicationManager.getCurrentNetwork();

		if (n == null)
			setEnabled(false);
		else
			setEnabled( (n.getDefaultEdgeTable().countMatchingRows(CyNetwork.SELECTED, true) > 0) );
	}

	/**
	 * Enables the action/menuListener/menuItem if a table is available and not null.
	 */
	private void enableForTable() {
		setEnabled(applicationManager.getCurrentTable() != null);
	}
}
