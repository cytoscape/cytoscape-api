package org.cytoscape.work;

/*
 * #%L
 * Cytoscape Work API (work-api)
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

/**
 * Reserved keywords for OSGi service properties (meta data).
 * 
 * These properties will be used for {@link TaskFactory} services.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule work-api
 */
public interface ServiceProperties {
	
	/**
	 * Unique ID of an OSGi service.  If you need to filter a service from collection of services, 
	 * use this property as its unique identifier.
	 */
	public static final String ID = "id";
	
	/**
	 * Human-readable display name of a service
	 */
	public static final String TITLE = "title";
	
	/**
	 * Action should be enabled or disabled by states if this property is set
	 */
	public static final String ENABLE_FOR = "enableFor";
	
	/**
	 * Preferred menu item for an action.
	 */
	public static final String PREFERRED_MENU = "preferredMenu";
	
	/**
	 *  Name of command used by Command Executor.
	 */
	public static final String COMMAND = "command";
	
	/**
	 * Name space for a command.
	 */
	public static final String COMMAND_NAMESPACE = "commandNamespace";
	
	/**
	 * Description for a command.
	 */
	public static final String COMMAND_DESCRIPTION = "commandDescription";
	
	/**
	 * Prose description for a command.
	 */
	public static final String COMMAND_LONG_DESCRIPTION = "commandLongDescription";
	
	/**
	 * A valid JSON string representative of the expected JSON output of a command.
	 */
	public static final String COMMAND_EXAMPLE_JSON = "commandExampleJSON";
	
	/**
	 * Indicates if the command supports JSON output.
	 */
	public static final String COMMAND_SUPPORTS_JSON = "commandSupportsJSON";
	
	/**
	 * Location of large icon data for desktop application (see {@link #IN_TOOL_BAR}).
	 */
	public static final String LARGE_ICON_URL = "largeIconURL";
	
	
	/**
	 * Location of small icon data for desktop application (see {@link #IN_MENU_BAR}).
	 */
	public static final String SMALL_ICON_URL = "smallIconURL";
	
	/**
	 * The String ID of the Icon to be used in a swing component, usually a tool bar button (see {@link #IN_TOOL_BAR}).
	 * The icon must be registered with the same ID first -- see <code>IconManager.addIcon(String, Icon)</code>.
	 */
	public static final String LARGE_ICON_ID = "largeIconID";
	
	/**
	 * The String ID of the Icon to be used in a swing component, usually a menu item (see {@link #IN_MENU_BAR}).
	 * The icon must be registered with the same ID first -- see <code>IconManager.addIcon(String, Icon)</code>.
	 */
	public static final String SMALL_ICON_ID = "smallIconID";
	
	/**
	 * Short tool tip text for an action.
	 */
	public static final String TOOLTIP = "tooltip";
	
	/**
	 * Optional longer tool tip description for an action (usually used in tool bars).
	 */
	public static final String TOOLTIP_LONG_DESCRIPTION = "tooltipLongDescription";
	
	/**
	 * Optional tool tip image for an action (usually used in tool bars).
	 */
	public static final String TOOLTIP_IMAGE = "tooltipImage";
	
	/**
	 * The CyAction or TaskFactory will be added to menu bar if this flag is set to {@code "true"}.
	 * You can also set the menu item's icon through the properties {@link #SMALL_ICON_URL} or {@link #SMALL_ICON_ID}.
	 */
	public static final String IN_MENU_BAR = "inMenuBar";
	
	/**
	 * The CyAction (or TaskFactory) will be added to the main tool bar if this flag is set to {@code "true"}.
	 * You can also set the button's icon through the properties {@link #LARGE_ICON_URL} or {@link #LARGE_ICON_ID}.
	 */
	public static final String IN_TOOL_BAR = "inToolBar";
	
	/**
	 * The {@code CyAction}, {@link TaskFactory} or {@code TableTaskFactory} will be added to the
	 * <b>Node Table Panel</b>'s tool bar if this flag is set to {@code "true"}.
	 * You can also set the button's icon through the properties {@link #LARGE_ICON_URL} or {@link #LARGE_ICON_ID}.
	 */
	public static final String IN_NODE_TABLE_TOOL_BAR = "inNodeTableToolBar";
	
	/**
	 * The {@code CyAction}, {@link TaskFactory} or {@code TableTaskFactory} will be added to the
	 * <b>Edge Table Panel</b>'s tool bar if this flag is set to {@code "true"}.
	 * You can also set the button's icon through the properties {@link #LARGE_ICON_URL} or {@link #LARGE_ICON_ID}.
	 */
	public static final String IN_EDGE_TABLE_TOOL_BAR = "inEdgeTableToolBar";
	
	/**
	 * The {@code CyAction}, {@link TaskFactory} or {@code TableTaskFactory} will be added to the
	 * <b>Network Table Panel</b>'s tool bar if this flag is set to {@code "true"}.
	 * You can also set the button's icon through the properties {@link #LARGE_ICON_URL} or {@link #LARGE_ICON_ID}.
	 */
	public static final String IN_NETWORK_TABLE_TOOL_BAR = "inNetworkTableToolBar";
	
	/**
	 * The {@code CyAction}, {@link TaskFactory} or {@code TableTaskFactory} will be added to the
	 * <b>Unassigned Tables Panel</b>'s tool bar if this flag is set to {@code "true"}.
	 * You can also set the button's icon through the properties {@link #LARGE_ICON_URL} or {@link #LARGE_ICON_ID}.
	 */
	public static final String IN_UNASSIGNED_TABLE_TOOL_BAR = "inUnassignedTableToolBar";
	
	
    /**
     * Action will be added to the network view context menu if this flag is set to {@code "true"}.
     *
     * <p>
     *   This service property only affects:
     *   <ul>
     *     <li>{@code NodeViewTaskFactory}</li>
     *     <li>{@code EdgeViewTaskFactory}</li>
     *     <li>{@code NetworkViewTaskFactory}</li>
     *   </ul>
     * </p>
     *
     * <p>If no value is defined, the default value is {@code "true"}.</p>
     */
    public static final String IN_CONTEXT_MENU = "inContextMenu";

	/**
	 * Shortcut key combination for desktop app.
	 */
	public static final String ACCELERATOR = "accelerator";
	
	/**
	 * Specify relative location of the action in the menu.
	 */
	public static final String MENU_GRAVITY = "menuGravity";
	
	/**
	 * Specify relative location of the action in the tool bar.
	 */
	public static final String TOOL_BAR_GRAVITY = "toolBarGravity";
	
	/**
	 * Action will be added to network panel's context menu if this flag is set to {@code "true"}.
	 */
	public static final String IN_NETWORK_PANEL_CONTEXT_MENU = "inNetworkPanelContextMenu";
	
	/**
	 * Preferred action properties. 
	 */
	public static final String PREFERRED_ACTION = "preferredAction";

	/**
	 * Insert separator before this menu item.
	 */
	public static final String INSERT_SEPARATOR_BEFORE = "insertSeparatorBefore";

	/**
	 * Insert separator after this menu item.
	 */
	public static final String INSERT_SEPARATOR_AFTER = "insertSeparatorAfter";
	
	/**
	 * Insert separator before this toolbar item.
	 */
	public static final String INSERT_TOOLBAR_SEPARATOR_BEFORE = "insertToolbarSeparatorBefore";

	/**
	 * Insert separator after this toolbar item.
	 */
	public static final String INSERT_TOOLBAR_SEPARATOR_AFTER = "insertToolbarSeparatorAfter";

	/**
 	 * Define standard context menus.  This is done here so that they will
 	 * be consistenty used since this is the only way to define gravity for these...
 	 */
	public static final String APPS_MENU = "Apps[1]"; // Default menu if nothing else is specified

	// Network context menus
	public static final String NETWORK_ADD_MENU = "Add[-1100]";
	public static final String NETWORK_DELETE_MENU = "Delete[-1090]";
	public static final String NETWORK_EDIT_MENU = "Edit[-1080]";
	public static final String NETWORK_SELECT_MENU = "Select[-1070]";
	public static final String NETWORK_GROUP_MENU = "Group[-1060]";
	public static final String NETWORK_LAYOUT_MENU = "Layout[-1050]";
	public static final String NETWORK_APPS_MENU = "Apps[1]";
	public static final String NETWORK_PREFERENCES_MENU = "Preferences[1000]";

	// Node context menus
	public static final String NODE_ADD_MENU = "Add[-1110]";
	public static final String NODE_EDIT_MENU = "Edit[-1100]";
	public static final String NODE_SELECT_MENU = "Select[-1090]";
	public static final String NODE_GROUP_MENU = "Group[-1080]";
	public static final String NODE_NESTED_NETWORKS_MENU = "Nested Networks[-1070]";
	public static final String NODE_APPS_MENU = "Apps[1]";  // This is the default menu if no preferredMenu is defined
	public static final String NODE_LINKOUTS_MENU = "External Links[1001]";
	public static final String NODE_DYNAMIC_LINKOUTS_MENU = "Dynamic Linkouts[1050]";
	public static final String NODE_PREFERENCES_MENU = "Preferences[1100]";

	// Edge context menus
	public static final String EDGE_EDIT_MENU = "Edit[-1100]";
	public static final String EDGE_SELECT_MENU = "Select[-1100]";
	public static final String EDGE_APPS_MENU = "Apps[1]";
	public static final String EDGE_LINKOUTS_MENU = "External Links[1001]";
	public static final String EDGE_DYNAMIC_LINKOUTS_MENU = "Dynamic Linkouts[1050]";
	public static final String EDGE_PREFERENCES_MENU = "Preferences[1100]";
}
