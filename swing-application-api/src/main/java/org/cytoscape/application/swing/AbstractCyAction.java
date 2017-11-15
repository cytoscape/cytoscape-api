package org.cytoscape.application.swing;

import static org.cytoscape.work.ServiceProperties.ACCELERATOR;
import static org.cytoscape.work.ServiceProperties.ENABLE_FOR;
import static org.cytoscape.work.ServiceProperties.INSERT_SEPARATOR_AFTER;
import static org.cytoscape.work.ServiceProperties.INSERT_SEPARATOR_BEFORE;
import static org.cytoscape.work.ServiceProperties.INSERT_TOOLBAR_SEPARATOR_AFTER;
import static org.cytoscape.work.ServiceProperties.INSERT_TOOLBAR_SEPARATOR_BEFORE;
import static org.cytoscape.work.ServiceProperties.IN_MENU_BAR;
import static org.cytoscape.work.ServiceProperties.IN_TOOL_BAR;
import static org.cytoscape.work.ServiceProperties.LARGE_ICON_URL;
import static org.cytoscape.work.ServiceProperties.MENU_GRAVITY;
import static org.cytoscape.work.ServiceProperties.PREFERRED_MENU;
import static org.cytoscape.work.ServiceProperties.SMALL_ICON_URL;
import static org.cytoscape.work.ServiceProperties.TITLE;
import static org.cytoscape.work.ServiceProperties.TOOLTIP;
import static org.cytoscape.work.ServiceProperties.TOOL_BAR_GRAVITY;

/*
 * #%L
 * Cytoscape Swing Application API (swing-application-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.event.MenuEvent;
import javax.swing.event.PopupMenuEvent;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.work.TaskFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An abstract implementation of the CyAction interface. Instead of using this
 * class directly you should (strongly) consider implementing a
 * {@link org.cytoscape.work.TaskFactory}/{@link org.cytoscape.work.Task} pair. Doing so will
 * allow your action to be used outside of a Swing specific application (which
 * the CyAction interface binds you to)!
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule swing-application-api
 */
public abstract class AbstractCyAction extends AbstractAction implements CyAction {
	
	private static final long serialVersionUID = -2245672104075936952L;
	
	private static final Logger logger = LoggerFactory.getLogger("org.cytoscape.application.userlog");

	/**
	 * The name describing the preferred menu for the action. 
	 */
	protected String preferredMenu = null;

	/**
	 * The float value placing the action within the menu.
	 * Value of 0.0 is the beginning and -1.0 means to sort alphabetically.
	 * App developers are strongly encouraged to set specific gravities
	 * within your own menus.
	 */
	protected float menuGravity = 100.0f;

	/**
	 * The float value placing the action within the toolbar.
	 * Value of 0.0 is the beginning and -1.0 means to sort alphabetically.
	 * App developers are strongly encouraged to set specific gravities
	 * within your own menus.
	 */
	protected float toolbarGravity = -1.0f;

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
	 * Indicates whether a menu separator should be inserted before this item
	 */
	protected boolean insertSeparatorBefore = false;

	/**
	 * Indicates whether a menu separator should be inserted after this item
	 */
	protected boolean insertSeparatorAfter = false;
	
	/**
	 * Indicates whether a toolbar separator should be inserted before this item
	 */
	protected boolean insertToolbarSeparatorBefore = false;
	
	/**
	 * Indicates whether a toolbar separator should be inserted after this item
	 */
	protected boolean insertToolbarSeparatorAfter = false;

	/**
	 * The name of the action.
	 */
	protected String name;

	/**
 	 * The configuration properties.  Adding it here allows extensions of
 	 * this class to pass their own properties.
 	 */
	protected Map<String, String> configurationProperties; 

	/**
	 * A support class for deciding whether the action should be enabled.
	 */
	private final AbstractEnableSupport enabler;

	/**
	 * Creates a new AbstractCyAction object that is
	 * always enabled.
	 * @param name The name of the action.
	 */
	public AbstractCyAction(final String name) {
		super(name);
		this.enabler = new AlwaysEnabledEnableSupport(this);
		addNameChangeListener();
	}

	private void addNameChangeListener() {
		name = (String) getValue(Action.NAME);
		
		addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent event) {
				if (!Action.NAME.equals(event.getPropertyName())) {
					return;
				}
				
				name = (String) event.getNewValue();
			}
		});
	}

	/**
	 * Creates a new AbstractCyAction object that is enabled for the
	 * specific "enableFor" state.
	 *
	 * @param name The name of the action.
	 * @param applicationManager The application manager providing context for this action.
	 * @param enableFor A string declaring which states this action should be enabled for. 
	 * @param networkViewManager The network view manager that provides context for this action. 
	 */
	public AbstractCyAction(final String name, final CyApplicationManager applicationManager, final String enableFor,
			final CyNetworkViewManager networkViewManager) {
		super(name);
		this.enabler = new ActionEnableSupport(this, enableFor, applicationManager, networkViewManager);
		addNameChangeListener();
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
	 *            <li>insertSeparatorBefore - (Whether a separator should be inserted before this menu item.)</li>
	 *            <li>insertSeparatorAfter - (Whether a separator should be inserted after this menu item.)</li>
	 *            <li>insertToolbarSeparatorBefore - (Whether a separator should be inserted before this toolbar item.)</li>
	 *            <li>insertToolbarSeparatorAfter - (Whether a separator should be inserted after this toolbar item.)</li>
	 *            <li>enableFor - (System state that the action should be enabled for. See {@link ActionEnableSupport} for more detail.)</li>
	 *            <li>accelerator - (Accelerator key bindings.)</li>
	 *            <li>menuGravity - (Float value with 0.0 representing the top and larger values moving towards the bottom of the menu.)</li>
	 *            <li>toolBarGravity - (Float value with 0.0 representing the top and larger values moving towards the bottom of the toolbar.)</li>
	 *            </ul>
	 * @param applicationManager
	 *            The application manager providing context for this action.
	 */
	public AbstractCyAction(final Map<String, String> configProps, final CyApplicationManager applicationManager,
			final CyNetworkViewManager networkViewManager) {
		this(configProps.get(TITLE), applicationManager, configProps.get(ENABLE_FOR), networkViewManager);

		configFromProps(configProps);
		addNameChangeListener();
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
	 *            <li>insertSeparatorBefore - (Whether a separator should be inserted before this menu item.)</li>
	 *            <li>insertSeparatorAfter - (Whether a separator should be inserted after this menu item.)</li>
	 *            <li>insertToolbarSeparatorBefore - (Whether a separator should be inserted before this toolbar item.)</li>
	 *            <li>insertToolbarSeparatorAfter - (Whether a separator should be inserted after this toolbar item.)</li>
	 *            <li>enableFor - (<i>Ingored in this constructor and TaskFactoryPredicate is used instead!</i>)</li>
	 *            <li>accelerator - (Accelerator key bindings.)</li>
	 *            <li>menuGravity - (Float value with 0.0 representing the top and larger values moving towards the bottom of the menu.)</li>
	 *            <li>toolBarGravity - (Float value with 0.0 representing the top and larger values moving towards the bottom of the toolbar.)</li>
	 *            </ul>
	 * @param predicate
	 *            The task factory predicate that indicates whether or not this 
	 *            action should be enabled.
	 */
	public AbstractCyAction(final Map<String, String> configProps, final TaskFactory predicate) {
		super(configProps.get(TITLE));
		
		this.enabler = new TaskFactoryEnableSupport(this, predicate);
		configFromProps(configProps);
		addNameChangeListener();
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
	 *            <li>insertSeparatorBefore - (Whether a separator should be inserted before this menu item.)</li>
	 *            <li>insertSeparatorAfter - (Whether a separator should be inserted after this menu item.)</li>
	 *            <li>insertToolbarSeparatorBefore - (Whether a separator should be inserted before this toolbar item.)</li>
	 *            <li>insertToolbarSeparatorAfter - (Whether a separator should be inserted after this toolbar item.)</li>
	 *            <li>enableFor - (<i>Will only use this value if the TaskFactory is not a TaskFactoryPredicate!</i> 
	 *                             See {@link ActionEnableSupport} for more detail.)</li>
	 *            <li>accelerator - (Accelerator key bindings.)</li>
	 *            <li>menuGravity - (Float value with 0.0 representing the top and larger values moving towards the bottom of the menu.)</li>
	 *            <li>toolBarGravity - (Float value with 0.0 representing the top and larger values moving towards the bottom of the toolbar.)</li>
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
		else {
			TaskFactoryEnableSupport taskFactoryEnabler = new TaskFactoryEnableSupport((CyAction) null, factory);
			ActionEnableSupport actionEnabler = new ActionEnableSupport((CyAction) null, enableFor, applicationManager, networkViewManager);
			this.enabler = new ConjunctionEnableSupport(this, actionEnabler, taskFactoryEnabler);
		}
		configFromProps(configProps);
		addNameChangeListener();
	}

	private void configFromProps(final Map<String, String> configProps) {

		logger.debug("New CyAction with title: " + configProps.get(TITLE));

		configurationProperties = configProps;

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

		final String foundInsertSeparatorBefore = configProps.get(INSERT_SEPARATOR_BEFORE);

		if (foundInsertSeparatorBefore != null  && Boolean.parseBoolean(foundInsertSeparatorBefore))
			insertSeparatorBefore = true;

		final String foundInsertSeparatorAfter = configProps.get(INSERT_SEPARATOR_AFTER);

		if (foundInsertSeparatorAfter != null  && Boolean.parseBoolean(foundInsertSeparatorAfter))
			insertSeparatorAfter = true;
		
		final String foundInsertToolbarSeparatorBefore = configProps.get(INSERT_TOOLBAR_SEPARATOR_BEFORE);

		if (foundInsertToolbarSeparatorBefore != null  && Boolean.parseBoolean(foundInsertToolbarSeparatorBefore))
			insertToolbarSeparatorBefore = true;

		final String foundInsertToolbarSeparatorAfter = configProps.get(INSERT_TOOLBAR_SEPARATOR_AFTER);

		if (foundInsertToolbarSeparatorAfter != null  && Boolean.parseBoolean(foundInsertToolbarSeparatorAfter))
			insertToolbarSeparatorAfter = true;

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
		putValue(Action.NAME, name);
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

	
	@Override
	public void setIsInMenuBar(boolean b) {	inMenuBar = b;	}

	/**
	 * By default no CytoscapeActions will be included in the toolbar.
	 *
	 * @return true if this Action should be included in the toolbar.
	 */
	@Override
	public boolean isInToolBar() {
		return inToolBar;
	}

	@Override
	public void setIsInToolBar(boolean b) {	inToolBar = b;	}

	/**
	 * Insert a separator before this menu item.  
	 *
	 * @return true if this Action should have a separator before it
	 */
	@Override
	public boolean insertSeparatorBefore() {
		return insertSeparatorBefore;
	}

	/**
	 * Insert a separator after this menu item. 
	 *
	 * @return true if this Action should have a separator after it
	 */
	@Override
	public boolean insertSeparatorAfter() {
		return insertSeparatorAfter;
	}
	
	/**
	 * Insert a separator before this toolbar item.  
	 *
	 * @return true if this Action should have a separator before it
	 */
	public boolean insertToolbarSeparatorBefore() {
		return insertToolbarSeparatorBefore;
	}
	
	/**
	 * Insert a separator after this menu item. 
	 *
	 * @return true if this Action should have a separator after it
	 */
	public boolean insertToolbarSeparatorAfter() {
		return insertToolbarSeparatorAfter;
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
	@Override public void setAcceleratorKeyStroke(KeyStroke ks) {
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

	/**
 	 * Return the config props. 
 	 */
	@Override
	public Map<String,String> getProperties() {
		return configurationProperties;
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
	
	private static class ConjunctionEnableSupport extends AbstractEnableSupport {
		private AbstractEnableSupport[] enableSupports;

		public ConjunctionEnableSupport(CyAction action, AbstractEnableSupport... enableSupports) {
			super(action);
			this.enableSupports = enableSupports;
		}
		
		@Override
		public void updateEnableState() {
			boolean enabled = true;
			for (AbstractEnableSupport enableSupport : enableSupports) {
				enableSupport.updateEnableState();
				enabled &= enableSupport.isCurrentlyEnabled();
				if (!enabled) {
					break;
				}
			}
			setEnabled(enabled);
		}
	}
}
