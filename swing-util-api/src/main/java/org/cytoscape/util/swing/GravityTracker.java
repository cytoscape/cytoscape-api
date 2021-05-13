package org.cytoscape.util.swing;

/*
 * #%L
 * Cytoscape Swing Utility API (swing-util-api)
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


import java.awt.Component;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


/**
 * This interface sepecifies a tracker that will be used to insert items (submenus, menu items
 * and separators) into a menu based on their "gravity" or "weight".  Items with higher gravity
 * will appear further down the menu.
 * @CyAPI.Api.Interface 
 * @CyAPI.InModule swing-util-api
 */
public interface GravityTracker {
	final static double USE_ALPHABETIC_ORDER = -1.0;

	/** Returns the JPopupMenu or JMenu that this gravity tracker tracks.
	 *  @return the JPopupMenu or JMenu that this gravity tracker tracks
	 */
	Component getMenu();

	/** Adds a new menu item into a position based on "gravity" of the new item.
	 *  @param newMenuItem  the new item to insert into the menu
	 *  @param gravity      the weight determining the insert position
	 *         (use USE_ALPHABETIC_ORDER if you want alphabetic ordering)
	 */
	void addMenuItem(final JMenuItem newMenuItem, final double gravity);

	/** Adds a new submenu into a position based on "gravity" of the new item.
	 *  @param newSubmenu  the new submenu to insert into the menu
	 *  @param gravity     the weight determining the insert position
	 */
	void addMenu(final JMenu newSubmenu, final double gravity);

	/** Adds a separator into a position based on "gravity" of the new item.
	 *  @param gravity  the weight determining the insert position
	 */
	void addMenuSeparator(final double gravity);

	/** Removes an item from this tracker and the corresponding menu.
	 *  @param component  the item that is to be removed
	 */
	void removeComponent(final Component component);
}
