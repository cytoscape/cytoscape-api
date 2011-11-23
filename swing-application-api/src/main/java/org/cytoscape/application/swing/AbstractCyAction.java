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

import org.cytoscape.application.CyApplicationManager;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

import org.cytoscape.view.model.CyNetworkView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.event.MenuEvent;
import javax.swing.event.PopupMenuEvent;


/**
 * An abstract implementation of the CyAction interface. Instead of using this
 * class directly you should (strongly) consider implementing a
 * {@link org.cytoscape.work.TaskFactory}/{@link org.cytoscape.work.Task} pair. Doing so will
 * allow your action to be used outside of a Swing specific application (which
 * the CyAction interface binds you to)!
 * @CyAPI.Abstract.Class
 */
public abstract class AbstractCyAction extends AbstractAction implements CyAction {
	private static final long serialVersionUID = -2245672104075936952L;
	private static final Logger logger = LoggerFactory.getLogger(AbstractCyAction.class);

	/**
	 * The name describing the preferred menu for the action. 
	 */
	protected String preferredMenu = null;

	/**
	 * The float value placing the action within the menu.
	 * Value of 0.0 is the beginning and 100.0 means end of menu.
	 */
	protected float menuGravity = 100.0f;

	/**
	 * The float value placing the action within the toolbar.
	 * Value of 0.0 is the beginning and 100.0 means end of menu.
	 */
	protected float toolbarGravity = 100.0f;

	/**
	 * Indicates whether accelerator keys have been set for the action.
	 */
	protected boolean acceleratorSet = false;

	/**
	 * The accelerator keystroke, if set.
	 */
	protected KeyStroke acceleratorKeyStroke = null;

	/**
	 * Indicates whether to use a checkbox menu item.
	 */
	protected boolean useCheckBoxMenuItem = false;

	/**
	 * Indicates whether the action is in the toolbar.
	 */
	protected boolean inToolBar = false;

	/**
	 * Indicates whether the action is in a menu.
	 */
	protected boolean inMenuBar = true;

	/**
	 * The string defining the possible system states that the
	 * action is enabled for.
	 */
	protected String enableFor = null;

	/**
	 * The name of the action.
	 */
	protected String name;

	/**
	 * The application manager.
	 */
	protected final CyApplicationManager applicationManager;

	/**
	 * A support class for deciding whether the action should be enabled.
	 */
	protected MenuEnableSupport enabler; 

	/**
	 * Creates a new AbstractCyAction object.
	 * @param name The name of the action.
	 * @param applicationManager The application manager providing context for this action.
	 */
	public AbstractCyAction(final String name, final CyApplicationManager applicationManager) {
		this(name,applicationManager,null);
	}

	/**
	 * Creates a new AbstractCyAction object.
	 *
	 * @param name The name of the action.
	 * @param applicationManager The application manager providing context for this action.
	 * @param enableFor A string declaring which states this action should be enabled for. 
	 */
	public AbstractCyAction(final String name, final CyApplicationManager applicationManager, String enableFor) {
		super(name);
		this.name = name;
		this.applicationManager = applicationManager;
		this.enabler = new MenuEnableSupport(this,enableFor,applicationManager);
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
	public AbstractCyAction(final Map<String, String> configProps,
	                        final CyApplicationManager applicationManager) {
		this(configProps.get("title"), applicationManager, configProps.get("enableFor"));

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

		setEnabled(true);
	}

	/**
	 * Sets the name of the action.
	 *
	 * @param name The name of the action.
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
	 * @param gravity The gravity for ordering menu bar actions.
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
	 * @param gravity The gravity for ordering toolbar actions.
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
	 * @param ks The KeyStroke to be used as an accelerator for this action.
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
	 * @param new_preferred The string describing the preferred menu name.
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
	 * @param e The triggering event.
	 */
	public void menuCanceled(MenuEvent e) {
		enabler.updateEnableState();
	}

	/**
	 * This method can be used at your discretion, but otherwise does nothing.
	 *
	 * @param e The triggering event.
	 */
	public void menuDeselected(MenuEvent e) {
		enabler.updateEnableState();
	}

	/**
	 * This method can be overridden by individual actions to set the state of
	 * menu items based on whatever unique circumstances that menu option cares
	 * about. By default it sets the state of the menu based on the "enableFor"
	 * property found in the properties used to construct the action. The valid
	 * options for "enableFor" are "network", "networkAndView", and
	 * "selectedNetworkObjs".
	 *
	 * @param e The triggering event.
	 */
	public void menuSelected(MenuEvent e) {
		enabler.updateEnableState();
	}

	/**
	 * This method can be overridden by individual actions to set the state of
	 * menu items based on whatever unique circumstances that menu option cares
	 * about. By default it sets the state of the menu based on the "enableFor"
	 * property found in the properties used to construct the action. The valid
	 * options for "enableFor" are "network", "networkAndView", and
	 * "selectedNetworkObjs".
	 *
	 * @param e The triggering event.
	 */
	public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
		enabler.updateEnableState();
	}

	/**
	 * This method can be used at your discretion, but otherwise does nothing.
	 *
	 * @param e The triggering event.
	 */
	public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
	}

	/**
	 * This method can be used at your discretion, but otherwise does nothing.
	 *
	 * @param e The triggering event.
	 */
	public void popupMenuCanceled(PopupMenuEvent e) {
	}

	/**
	 * Triggers the enable state of the action to be updated based
	 * on the enableFor state of the action and the state of the
	 * system.
	 */
	public void updateEnableState() {
		enabler.updateEnableState();
	}

    /**
     * Enable the action if the current network exists and is not null.
     */
    protected void enableForNetwork() {
		enabler.enableForNetwork();
    }
    
	/**
	 *  Enables the action when a network without view is present. 
	 */
	protected void enableForNetworkWithoutView() {
		enabler.enableForNetworkWithoutView() ;
	}

	/**
	 *  Enables the action when a network view is present. 
	 */
    protected void enableForNetworkAndView() {
    	enabler.enableForNetworkAndView() ;
    }

	/**
	 *  Enables the action when selected nodes or edges are present. 
	 */
    protected void enableForSelectedNodesOrEdges() {
    	enabler.enableForSelectedNodesOrEdges() ;
    }

	/**
	 *  Enables the action when selected nodes are present. 
	 */
    protected void enableForSelectedNodes() {
    	enabler.enableForSelectedNodes() ;
    }

	/**
	 *  Enables the action when selected edges are present. 
	 */
    protected void enableForSelectedEdges() {
    	enabler.enableForSelectedEdges() ;
    }

    private void enableForTable() {
    	enabler.enableForTable() ;
	}
}
