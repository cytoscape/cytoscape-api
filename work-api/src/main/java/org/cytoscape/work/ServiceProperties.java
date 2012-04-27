package org.cytoscape.work;

/**
 * Reserved keywords for OSGi service properties (meta data).
 * 
 * These properties will be used for {@linkplain TaskFactory} services.
 */
public interface ServiceProperties {
	
	// Reserved Key Words for CyActions
	
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
	 * Preferred menu item for an action
	 */
	public static final String PREFERRED_MENU = "preferredMenu";
	
	/**
	 *  Name of command used by Command Executor
	 */
	public static final String COMMAND = "command";
	
	/**
	 * Name space for a command
	 */
	public static final String COMMAND_NAMESPACE = "commandNamespace";
	
	/**
	 * Location of large icon data for desktop application
	 */
	public static final String LARGE_ICON_URL = "largeIconURL";
	
	/**
	 * Location of small icon data for desktop application
	 */
	public static final String SMALL_ICON_URL = "smallIconURL";
	
	/**
	 * Tool tip text for an action
	 */
	public static final String TOOLTIP = "tooltip";
	
	/**
	 * Action will be added to tool bar if this flag is set to true
	 */
	public static final String IN_TOOL_BAR = "inToolBar";
	
	/**
	 * Action will be added to menu bar if this flag is set to true
	 */
	public static final String IN_MENU_BAR = "inMenuBar";
	
	/**
	 * Shortcut key combination for desktop app.
	 */
	public static final String ACCELERATOR = "accelerator";
	
	/**
	 * Specify relative location of the action in the menu
	 */
	public static final String MENU_GRAVITY = "menuGravity";
	
	/**
	 * Specify relative location of the action in the tool bar
	 */
	public static final String TOOL_BAR_GRAVITY = "toolBarGravity";
	
	/**
	 * Action will be added to network panel's context menu if this
	 * flag is set to true
	 */
	public static final String IN_NETWORK_PANEL_CONTEXT_MENU = "inNetworkPanelContextMenu";
	
	/**
	 * Preferred action properties. 
	 */
	public static final String PREFERRED_ACTION = "preferredAction";
}
