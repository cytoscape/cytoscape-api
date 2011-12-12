/*
 File: MenuEnableSupport.java

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
package org.cytoscape.application.swing;

import org.cytoscape.application.CyApplicationManager;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.work.swing.DynamicSubmenuListener;

import javax.swing.Action;
import javax.swing.JMenuItem;

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
 * </ul>
 * 
 * @CyAPI.Final.Class
 */
public final class MenuEnableSupport {

	private final Action action;
	private final JMenuItem menuItem;
	private final DynamicSubmenuListener submenuListener; 
	private final CyApplicationManager applicationManager;
	private final String enableFor;
	private boolean enableState;

	/**
	 * Constructor.
	 * @param submenuListener The submenu listener whose enabled state will be updated.
	 * @param enableFor The description of how the submenu should be enabled.
	 * See class documentation above for allowable values for this string.
	 * @param applicationManager The application manager.
	 */
	public MenuEnableSupport(DynamicSubmenuListener submenuListener, String enableFor, CyApplicationManager applicationManager) {
		this.submenuListener = submenuListener;
		this.action = null;
		this.menuItem = null;
		this.enableFor = enableFor;
		this.applicationManager = applicationManager;
		this.enableState = true;
	}

	/**
	 * Constructor.
	 * @param action The action whose enabled state will be updated.
	 * @param enableFor The description of how the action should be enabled.
	 * See class documentation above for allowable values for this string.
	 * @param applicationManager The application manager.
	 */
	public MenuEnableSupport(Action action, String enableFor, CyApplicationManager applicationManager) {
		this.submenuListener = null;
		this.action = action;
		this.menuItem = null;
		this.enableFor = enableFor;
		this.applicationManager = applicationManager;
	}

	/**
	 * Constructor.
	 * @param menuItem The menuItem whose enabled state will be updated.
	 * @param enableFor The description of how the menuItem should be enabled.
	 * See class documentation above for allowable values for this string.
	 * @param applicationManager The application manager.
	 */
	public MenuEnableSupport(JMenuItem menuItem, String enableFor, CyApplicationManager applicationManager) {
		this.submenuListener = null;
		this.action = null;
		this.menuItem = menuItem;
		this.enableFor = enableFor;
		this.applicationManager = applicationManager;
	}

	/**
	 * Updates the enable state for the specified action/menuListener/menuItem
	 * for the specified enableFor description and the state of the system.
	 */
	public void updateEnableState() {
		if (enableFor == null)
			setEnabled(true);
		else if (enableFor.equals("network"))
			enableForNetwork();
		else if (enableFor.equals("networkWithoutView"))
			enableForNetworkWithoutView();
		else if (enableFor.equals("networkAndView"))
			enableForNetworkAndView();
		else if (enableFor.equals("selectedNodesOrEdges"))
			enableForSelectedNodesOrEdges();
		else if (enableFor.equals("selectedNodes"))
			enableForSelectedNodes();
		else if (enableFor.equals("selectedEdges"))
			enableForSelectedEdges();
		else if (enableFor.equals("table"))
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
	public void enableForNetwork() {
		CyNetwork n = applicationManager.getCurrentNetwork();

		if (n == null)
			setEnabled(false);
		else
			setEnabled(true);
	}

	/**
	 * Enable the action if the current network exists, is not null,
	 * and no view is available for the network.
	 */
	public void enableForNetworkWithoutView() {
		final CyNetwork n = applicationManager.getCurrentNetwork();
		final CyNetworkView v = applicationManager.getCurrentNetworkView();

		if (n == null)
			setEnabled(false);
		else if ((n != null) && (v == null))
			setEnabled(true);
		else
			setEnabled(false);
	}

	/**
	 * Enable the action if the current network and view exist and are not null.
	 */
	public void enableForNetworkAndView() {
		CyNetworkView v = applicationManager.getCurrentNetworkView();

		if (v == null)
			setEnabled(false);
		else
			setEnabled(true);
	}

	/**
	 * Enable the action if at least one selected node or edge is required to
	 * perform the action.
	 */
	public void enableForSelectedNodesOrEdges() {
		final CyNetwork curNetwork = applicationManager.getCurrentNetwork();

		// Disable if there is no current network.
		if (curNetwork == null) {
			setEnabled(false);

			return;
		}

		// If any of nodes are selected, enable this.
		for (CyNode node : curNetwork.getNodeList()) {
			if (curNetwork.getRow(node).get(CyNetwork.SELECTED, Boolean.class)) {
				setEnabled(true);

				return;
			}
		}

		for (CyEdge edge : curNetwork.getEdgeList()) {
			if (curNetwork.getRow(edge).get(CyNetwork.SELECTED, Boolean.class)) {
				setEnabled(true);

				return;
			}
		}

		setEnabled(false);
	}

	/**
	 * Enable the action if at least one selected node is required to perform
	 * the action.
	 */
	public void enableForSelectedNodes() {
		CyNetwork n = applicationManager.getCurrentNetwork();

		if (n == null) {
			setEnabled(false);

			return;
		}

		for (CyNode node : n.getNodeList()) {
			if (n.getRow(node).get(CyNetwork.SELECTED, Boolean.class)) {
				setEnabled(true);

				return;
			}
		}

		setEnabled(false);
	}

	/**
	 * Enable the action if at least one selected edge is required to perform
	 * the action.
	 */
	public void enableForSelectedEdges() {
		CyNetwork n = applicationManager.getCurrentNetwork();

		if (n == null) {
			setEnabled(false);

			return;
		}

		for (CyEdge edge : n.getEdgeList()) {
			if (n.getRow(edge).get(CyNetwork.SELECTED, Boolean.class)) {
				setEnabled(true);

				return;
			}
		}

		setEnabled(false);
	}

	/**
	 * Enables the action/menuListener/menuItem if a table is available and not null.
	 */
	public void enableForTable() {
		setEnabled(applicationManager.getCurrentTable() != null);
	}

	private synchronized void setEnabled(boolean b) {
		enableState = b;
		if ( submenuListener != null )
			submenuListener.setEnabled(enableState);
		if ( action != null )
			action.setEnabled(enableState);
		if ( menuItem != null )
			menuItem.setEnabled(enableState);
	}

	/**
	 * Returns true if the action/menuListener/menuItem is enabled, false otherwise.
	 * @return true if the action/menuListener/menuItem is enabled, false otherwise.
	 */
	public synchronized boolean isCurrentlyEnabled() {
		return enableState;
	}
}
