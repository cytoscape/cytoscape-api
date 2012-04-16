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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.event.MenuEvent;
import javax.swing.event.PopupMenuEvent;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.work.TaskFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.cytoscape.work.ServiceProperties.*;

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
	 * A support class for deciding whether the action should be enabled.
	 */
	private final AbstractEnableSupport enabler; 

	/**
	 * Creates a new AbstractCyAction object.
	 * @param name The name of the action.
	 */
	public AbstractCyAction(final String name) {
		super(name);
		this.enabler = new AlwaysEnabledEnableSupport(this);
	}

	/**
	 * Creates a new AbstractCyAction object.
	 *
	 * @param name The name of the action.
	 * @param applicationManager The application manager providing context for this action.
	 * @param enableFor A string declaring which states this action should be enabled for. 
	 */
	public AbstractCyAction(final String name, final CyApplicationManager applicationManager, final String enableFor,
			final CyNetworkViewManager networkViewManager) {
		super(name);
		this.enabler = new StringEnableSupport(this, enableFor, applicationManager, networkViewManager);
	}

	/**
	 * Creates a new AbstractCyAction object.
	 *
	 * @param configProps
	 *            A String-String Map of configuration metadata. This will
	 *            usually be the Map provided by the OSGi service
	 *            configuration. Available configuration keys include:
	 *            <ul>
	 *            <li>title - (The title of the menu.)</li>
	 *            <li>preferredMenu - (The preferred menu for the action.)</li>
	 *            <li>largeIconURL - (The icon to be used for the toolbar.)</li>
	 *            <li>smallIconURL - (The icon to be used for the menu.)</li>
	 *            <li>tooltip - (The toolbar or menu tooltip.)</li>
	 *            <li>inToolBar - (Whether the action should be in the toolbar.)</li>
	 *            <li>inMenuBar - (Whether the action should be in a menu.)</li>
	 *            <li>enableFor - (System state that the action should be enabled for. See {@link StringEnableSupport} for more detail.)</li>
	 *            <li>accelerator - (Accelerator key bindings.)</li>
	 *            <li>menuGravity - (Float value between 0.0 [top] and 100.0 [bottom] placing the action in the menu.)</li>
	 *            <li>toolBarGravity - (Float value between 0.0 [top] and 100.0 [bottom] placing the action in the toolbar.)</li>
	 *            </ul>
	 * @param applicationManager
	 *            The application manager providing context for this action.
	 */
	public AbstractCyAction(final Map<String, String> configProps, final CyApplicationManager applicationManager,
			final CyNetworkViewManager networkViewManager) {
		this(configProps.get(TITLE), applicationManager, configProps.get(ENABLE_FOR), networkViewManager);

		configFromProps(configProps);
	}

	/**
	 * Creates a new AbstractCyAction object.
	 *
	 * @param configProps
	 *            A String-String Map of configuration metadata. This will
	 *            usually be the Map provided by the OSGi service
	 *            configuration. Available configuration keys include:
	 *            <ul>
	 *            <li>title - (The title of the menu.)</li>
	 *            <li>preferredMenu - (The preferred menu for the action.)</li>
	 *            <li>largeIconURL - (The icon to be used for the toolbar.)</li>
	 *            <li>smallIconURL - (The icon to be used for the menu.)</li>
	 *            <li>tooltip - (The toolbar or menu tooltip.)</li>
	 *            <li>inToolBar - (Whether the action should be in the toolbar.)</li>
	 *            <li>inMenuBar - (Whether the action should be in a menu.)</li>
	 *            <li>enableFor - (<i>Ingored in this constructor and TaskFactoryPredicate is used instead!</i>)</li>
	 *            <li>accelerator - (Accelerator key bindings.)</li>
	 *            <li>menuGravity - (Float value between 0.0 [top] and 100.0 [bottom] placing the action in the menu.)</li>
	 *            <li>toolBarGravity - (Float value between 0.0 [top] and 100.0 [bottom] placing the action in the toolbar.)</li>
	 *            </ul>
	 * @param predicate
	 *            The task factory predicate that indicates whether or not this 
	 *            action should be enabled.
	 */
	public AbstractCyAction(final Map<String, String> configProps, final TaskFactory predicate) {
		super(configProps.get(TITLE));
		
		this.enabler = new TaskFactoryEnableSupport(this, predicate);
		configFromProps(configProps);
	}

	/**
	 * Creates a new AbstractCyAction object.
	 *
	 * @param configProps
	 *            A String-String Map of configuration metadata. This will
	 *            usually be the Map provided by the OSGi service
	 *            configuration. Available configuration keys include:
	 *            <ul>
	 *            <li>title - (The title of the menu.)</li>
	 *            <li>preferredMenu - (The preferred menu for the action.)</li>
	 *            <li>largeIconURL - (The icon to be used for the toolbar.)</li>
	 *            <li>smallIconURL - (The icon to be used for the menu.)</li>
	 *            <li>tooltip - (The toolbar or menu tooltip.)</li>
	 *            <li>inToolBar - (Whether the action should be in the toolbar.)</li>
	 *            <li>inMenuBar - (Whether the action should be in a menu.)</li>
	 *            <li>enableFor - (<i>Will only use this value if the TaskFactory is not a TaskFactoryPredicate!</i> 
	 *                             See {@link StringEnableSupport} for more detail.)</li>
	 *            <li>accelerator - (Accelerator key bindings.)</li>
	 *            <li>menuGravity - (Float value between 0.0 [top] and 100.0 [bottom] placing the action in the menu.)</li>
	 *            <li>toolBarGravity - (Float value between 0.0 [top] and 100.0 [bottom] placing the action in the toolbar.)</li>
	 *            </ul>
	 * @param applicationManager
	 *            The application manager providing context for this action.
	 * @param factory
	 *            The task factory that may or may not be a TaskFactoryPredicate. If it is a predicate,
	 *            it will be used to indicate whether or not this action should be enabled.  This
	 *            TaskFactory is not used by the AbstractCyAction in any other way.  Any execution of tasks
	 *            from this TaskFactory must be handled by a subclass.
	 */
	public AbstractCyAction(final Map<String, String> configProps, final CyApplicationManager applicationManager,
			final CyNetworkViewManager networkViewManager, final TaskFactory factory) {
		super(configProps.get(TITLE));
		
		final String enableFor = configProps.get(ENABLE_FOR);
		if (enableFor == null)
			this.enabler = new TaskFactoryEnableSupport(this, factory);
		else
			this.enabler = new StringEnableSupport(this, enableFor, applicationManager, networkViewManager);

		configFromProps(configProps);
	}

	private void configFromProps(final Map<String, String> configProps) {

		logger.debug("New CyAction with title: " + configProps.get(TITLE));

		final String prefMenu = configProps.get(PREFERRED_MENU);

		if (prefMenu != null)
			setPreferredMenu(prefMenu);

		final URL largeIconURL = getURL( configProps.get(LARGE_ICON_URL) );
		if ( largeIconURL != null ) 
			putValue(LARGE_ICON_KEY, new ImageIcon(largeIconURL));

		final URL smallIconURL = getURL( configProps.get(SMALL_ICON_URL) );
		if ( smallIconURL != null ) 
			putValue(SMALL_ICON, new ImageIcon(smallIconURL));

		final String tooltip = configProps.get(TOOLTIP);

		if (tooltip != null)
			putValue(SHORT_DESCRIPTION, tooltip);

		final String foundInToolBar = configProps.get(IN_TOOL_BAR);

		if (foundInToolBar != null && Boolean.parseBoolean(foundInToolBar))
			inToolBar = true;

		final String foundInMenuBar = configProps.get(IN_MENU_BAR);

		if (foundInMenuBar != null  && Boolean.parseBoolean(foundInMenuBar))
			inMenuBar = true;

		final String keyComboString = configProps.get(ACCELERATOR);

		if (keyComboString != null) {
			final KeyStroke command = AcceleratorParser.parse(keyComboString);

			if (command != null)
				setAcceleratorKeyStroke(command);
		}

		final String menuGravityString = configProps.get(MENU_GRAVITY);

		if (menuGravityString != null) {
			try {
				menuGravity = Float.valueOf(menuGravityString);
			} catch (NumberFormatException nfe) {
				logger.warn("failed to set menuGravity with: " + menuGravityString, nfe);
			}
		}

		final String toolbarGravityString = configProps.get(TOOL_BAR_GRAVITY);

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
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * By default all CytoscapeActions wish to be included in the menu bar at
	 * the 'preferredMenuName' location is specified and the 'Tools' menu not.
	 *
	 * @return true if this CyAction should be included in menu bar.
	 */
	@Override
	public boolean isInMenuBar() {
		return inMenuBar;
	}

	/**
	 * By default no CytoscapeActions will be included in the toolbar.
	 *
	 * @return true if this Action should be included in the toolbar.
	 */
	@Override
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
	@Override
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
	@Override
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
	@Override
	public KeyStroke getAcceleratorKeyStroke() {
		return acceleratorKeyStroke;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPreferredMenu() {
		return preferredMenu;
	}

	/**
	 * Sets the preferredMenuString. See the {@link #getPreferredMenu}
	 * description for formatting description.
	 *
	 * @param newPreferredMenu The string describing the preferred menu name.
	 */
	public void setPreferredMenu(String newPreferredMenu) {
		preferredMenu = newPreferredMenu;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean useCheckBoxMenuItem() {
		return useCheckBoxMenuItem;
	}

	/**
	 * This method can be used at your discretion, but otherwise does nothing.
	 *
	 * @param e The triggering event.
	 */
	@Override
	public void menuCanceled(MenuEvent e) {
		enabler.updateEnableState();
	}

	/**
	 * This method can be used at your discretion, but otherwise does nothing.
	 *
	 * @param e The triggering event.
	 */
	@Override
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
	@Override
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
	@Override
	public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
		enabler.updateEnableState();
	}

	/**
	 * This method can be used at your discretion, but otherwise does nothing.
	 *
	 * @param e The triggering event.
	 */
	@Override
	public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
	}

	/**
	 * This method can be used at your discretion, but otherwise does nothing.
	 *
	 * @param e The triggering event.
	 */
	@Override
	public void popupMenuCanceled(PopupMenuEvent e) {
	}

	/**
	 * Triggers the enable state of the action to be updated based
	 * on the enableFor state of the action and the state of the
	 * system.
	 */
	@Override
	public void updateEnableState() {
		enabler.updateEnableState();
	}

	
	private URL getURL(final String s) {
		if ( s == null )
			return null;
		
		try {
			return new URL(s);
		} catch (MalformedURLException e) {
			logger.warn("Incorrectly formatted URL string: '" + s +"'",e);
			return null;
		}
	}
}
