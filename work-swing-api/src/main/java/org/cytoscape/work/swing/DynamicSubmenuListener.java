

package org.cytoscape.work.swing;

import javax.swing.event.MenuListener;
import javax.swing.event.PopupMenuListener;

/**
 * An extension of {@link MenuListener} that is used to describe
 * dynamic submenus. This interface allows the top name of the
 * dynamic menu to be set and allows the menu to be enabled or
 * disabled based on the state of the system.
 */
public interface DynamicSubmenuListener extends MenuListener, PopupMenuListener {

	/**
	 * Sets the name of the top level menu.
	 * @param name The name to use for the top level menu.
	 */
	void setMenuTitle(String name);

	/**
	 * Enables or disables the dynamically created menus.
	 * @param enableState Whether to enable or disable the menu.
	 */
	void setEnabled(boolean enableState);
}
