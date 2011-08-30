/*
  File: AbstractCyAction.java

  Copyright (c) 2006, 2010-2011, The Cytoscape Consortium (www.cytoscape.org)

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


import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.event.MenuEvent;
import javax.swing.event.PopupMenuEvent;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.CyNetworkView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An abstract implementation of the CyAction interface. Instead of using this
 * class directly you should (strongly) consider implementing a
 * org.cytoscape.work.TaskFactory/org.cytoscape.work.Task pair. Doing so will
 * allow your action to be used outside of a Swing specific application (which
 * the CyAction interface binds you to)!
 */
public abstract class AbstractCyAction extends AbstractAction implements CyAction {
	private static final long serialVersionUID = -2245672104075936952L;
	private static final Logger logger = LoggerFactory.getLogger(AbstractCyAction.class);

	protected String preferredMenu = null;

	// Value 100.0 means end of menu/tool bar
	protected float menuGravity = 100.0f;
	protected float toolbarGravity = 100.0f;

	protected boolean acceleratorSet = false;
	protected KeyStroke acceleratorKeyStroke = null;

	protected boolean useCheckBoxMenuItem = false;
	protected boolean inToolBar = false;
	protected boolean inMenuBar = true;
	protected String enableFor = null;

	protected String name;
	protected final CyApplicationManager applicationManager;

	/**
	 * Creates a new AbstractCyAction object.
	 * 
	 * @param name
	 *            The name of the action.
	 * @param applicationManager
	 *            The application manager providing context for this action.
	 */
	public AbstractCyAction(final String name, final CyApplicationManager applicationManager) {
		super(name);
		this.name = name;
		this.applicationManager = applicationManager;
	}

	/**
	 * Creates a new AbstractCyAction object.
	 * 
	 * @param configProps
	 *            A String-String Map of configuration metadata. This will
	 *            usually be the Map provided by the Spring service
	 *            configuration. Available configuration keys include:
	 *            <ul>
	 *            <li>title</li>
	 *            <li>preferredMenu</li>
	 *            <li>iconName</li>
	 *            <li>tooltip</li>
	 *            <li>inToolBar</li>
	 *            <li>inMenuBar</li>
	 *            <li>enableFor</li>
	 *            <li>accelerator</li>
	 *            <li>menuGravity</li>
	 *            <li>toolBarGravity</li>
	 *            </ul>
	 * @param applicationManager
	 *            The application manager providing context for this action.
	 */
	public AbstractCyAction(final Map<String, String> configProps, final CyApplicationManager applicationManager) {
		this(configProps.get("title"), applicationManager);

		logger.debug("New CyAction with title: " + configProps.get("title"));

		final String prefMenu = configProps.get("preferredMenu");
		if (prefMenu != null)
			setPreferredMenu(prefMenu);

		final String iconName = configProps.get("iconName");
		if (iconName != null)
			putValue(SMALL_ICON, new ImageIcon(getClass().getResource(iconName)));

		final String tooltip = configProps.get("tooltip");
		if (tooltip != null)
			putValue(SHORT_DESCRIPTION, tooltip);

		final String foundInToolBar = configProps.get("inToolBar");
		if (foundInToolBar != null)
			inToolBar = true;

		final String foundInMenuBar = configProps.get("inMenuBar");
		if (foundInMenuBar != null)
			inMenuBar = true;

		this.enableFor = configProps.get("enableFor");

		final String keyComboString = configProps.get("accelerator");
		if (keyComboString != null) {
			final KeyStroke command = AcceleratorParser.parse(keyComboString);
			if (command != null)
				setAcceleratorKeyStroke(command);
		}

		final String menuGravityString = configProps.get("menuGravity");
		if (menuGravityString != null) {
			try {
				menuGravity = Float.valueOf(menuGravityString);
			} catch (NumberFormatException nfe) {
				logger.warn("failed to set menuGravity with: " + menuGravityString, nfe);
			}
		}

		final String toolbarGravityString = configProps.get("toolBarGravity");
		if (toolbarGravityString != null) {
			try {
				toolbarGravity = Float.valueOf(toolbarGravityString);
			} catch (NumberFormatException nfe) {
				logger.warn("failed to set toolBarGravity with: " + toolbarGravityString, nfe);
			}
		}

		logger.debug("New Action: enable for = " + this.enableFor);
		this.setEnabled(true);
	}

	/**
	 * Sets the name of the action.
	 * 
	 * @param name
	 *            The name of the action.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getName() {
		return name;
	}

	/**
	 * By default all CytoscapeActions wish to be included in the menu bar at
	 * the 'preferredMenuName' location is specified and the 'Tools' menu not.
	 * 
	 * @return true if this CyAction should be included in menu bar.
	 */
	public boolean isInMenuBar() {
		return inMenuBar;
	}

	/**
	 * By default no CytoscapeActions will be included in the toolbar.
	 * 
	 * @return true if this Action should be included in the toolbar.
	 */
	public boolean isInToolBar() {
		return inToolBar;
	}

	/**
	 * Sets the gravity used to order this action in the menu bar.
	 * 
	 * @param gravity
	 *            The gravity for ordering menu bar actions.
	 */
	public void setMenuGravity(float gravity) {
		menuGravity = gravity;
	}

	/**
	 * {@inheritDoc}
	 */
	public float getMenuGravity() {
		return menuGravity;
	}

	/**
	 * Sets the gravity used to order this action in the toolbar.
	 * 
	 * @param gravity
	 *            The gravity for ordering toolbar actions.
	 */
	public void setToolbarGravity(float gravity) {
		toolbarGravity = gravity;
	}

	/**
	 * {@inheritDoc}
	 */
	public float getToolbarGravity() {
		return toolbarGravity;
	}

	/**
	 * Sets the accelerator KeyStroke for this action.
	 * 
	 * @param ks
	 *            The KeyStroke to be used as an accelerator for this action.
	 *            This parameter may be null, in which case no accelerator is
	 *            defined.
	 */
	public void setAcceleratorKeyStroke(KeyStroke ks) {
		acceleratorKeyStroke = ks;
	}

	/**
	 * {@inheritDoc}
	 */
	public KeyStroke getAcceleratorKeyStroke() {
		return acceleratorKeyStroke;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getPreferredMenu() {
		return preferredMenu;
	}

	/**
	 * Sets the preferredMenuString. See the {@link #getPreferredMenu}
	 * description for formatting description.
	 * 
	 * @param new_preferred
	 *            The string describing the preferred menu name.
	 */
	public void setPreferredMenu(String new_preferred) {
		preferredMenu = new_preferred;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean useCheckBoxMenuItem() {
		return useCheckBoxMenuItem;
	}

	/**
	 * This method can be used at your discretion, but otherwise does nothing.
	 * 
	 * @param e
	 *            The triggering event.
	 */
	public void menuCanceled(MenuEvent e) {
		updateEnableState();
	}

	/**
	 * This method can be used at your discretion, but otherwise does nothing.
	 * 
	 * @param e
	 *            The triggering event.
	 */
	public void menuDeselected(MenuEvent e) {
		updateEnableState();
	}

	/**
	 * This method can be overridden by individual actions to set the state of
	 * menu items based on whatever unique circumstances that menu option cares
	 * about. By default it sets the state of the menu based on the "enableFor"
	 * property found in the properties used to construct the action. The valid
	 * options for "enableFor" are "network", "networkAndView", and
	 * "selectedNetworkObjs".
	 * 
	 * @param e
	 *            The triggering event.
	 */
	public void menuSelected(MenuEvent e) {
		updateEnableState();
	}

	/**
	 * This method can be overridden by individual actions to set the state of
	 * menu items based on whatever unique circumstances that menu option cares
	 * about. By default it sets the state of the menu based on the "enableFor"
	 * property found in the properties used to construct the action. The valid
	 * options for "enableFor" are "network", "networkAndView", and
	 * "selectedNetworkObjs".
	 * 
	 * @param e
	 *            The triggering event.
	 */
	public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
		updateEnableState();
	}

	/**
	 * This method can be used at your discretion, but otherwise does nothing.
	 * 
	 * @param e
	 *            The triggering event.
	 */
	public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
	}

	/**
	 * This method can be used at your discretion, but otherwise does nothing.
	 * 
	 * @param e
	 *            The triggering event.
	 */
	public void popupMenuCanceled(PopupMenuEvent e) {
	}

	/**
	 * {@inheritDoc}
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
	protected void enableForNetwork() {
		CyNetwork n = applicationManager.getCurrentNetwork();
		if (n == null)
			setEnabled(false);
		else
			setEnabled(true);
	}
    
	protected void enableForNetworkWithoutView() {
		final CyNetwork n = applicationManager.getCurrentNetwork();
		final CyNetworkView v = applicationManager.getCurrentNetworkView();
		if (n == null)
			setEnabled(false);
		else if(n != null && v == null)
			setEnabled(true);
		else
			setEnabled(false);
	}

	/**
	 * Enable the action if the current network and view exist and are not null.
	 */
	protected void enableForNetworkAndView() {
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
	protected void enableForSelectedNodesOrEdges() {
		if (applicationManager.getCurrentNetworkView() == null)
			return;
		
		final CyNetwork network = applicationManager.getCurrentNetwork();

		// Disable if there is no current network.
		if (network == null) {
			setEnabled(false);
			return;
		}

		// If any of nodes are selected, enable this.
		if (network.getDefaultNodeTable().getMatchingRows(CyNetwork.SELECTED, Boolean.TRUE).isEmpty() &&
		    network.getDefaultEdgeTable().getMatchingRows(CyNetwork.SELECTED, Boolean.TRUE).isEmpty()) 
			setEnabled(false);
		else 
			setEnabled(true);
		
	}

	/**
	 * Enable the action if at least one selected node is required to perform
	 * the action.
	 */
	protected void enableForSelectedNodes() {
		if (applicationManager.getCurrentNetworkView() == null)
			return;
		
		final CyNetwork network = applicationManager.getCurrentNetwork();
		if (network == null) {
			setEnabled(false);
			return;
		}

		setEnabled(!network.getDefaultNodeTable().getMatchingRows(CyNetwork.SELECTED, Boolean.TRUE).isEmpty());
	}

	/**
	 * Enable the action if at least one selected edge is required to perform
	 * the action.
	 */
	protected void enableForSelectedEdges() {
		if (applicationManager.getCurrentNetworkView() == null)
			return;
		
		final CyNetwork network = applicationManager.getCurrentNetwork();

		if (network == null) {
			setEnabled(false);
			return;
		}

		setEnabled(!network.getDefaultEdgeTable().getMatchingRows(CyNetwork.SELECTED, Boolean.TRUE).isEmpty());
	}

	private void enableForTable() {
		setEnabled(applicationManager.getCurrentTable() != null);
	}
}
