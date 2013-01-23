package org.cytoscape.application.swing;

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

import javax.swing.JMenuItem;

/**
 * A simple wrapper class that allows a JMenuItem to be associated with 
 * a gravity value which defines where in a menu the item should fall.
 * @CyAPI.Final.Class
 * @CyAPI.InModule swing-application-api
 */
public final class CyMenuItem {

	private final JMenuItem menuItem;
	private final float gravity;

	/**
	 * Constructor.
	 *
	 * @param menuItem to JMenuItem to be added to the menu.
	 * @param gravity the gravity of the menu item to be added. 
	 */
	public CyMenuItem(JMenuItem menuItem, float gravity) {
		if ( menuItem == null )
			throw new NullPointerException("menu item cannot be null");
		this.menuItem = menuItem;
		this.gravity = gravity;
	}

	/**
	 * Returns the menu item to be placed in a menu according to the specified menu gravity.
	 * @return the menu item to be placed in a menu according to the specified menu gravity.
	 */
	public JMenuItem getMenuItem() {
		return menuItem;
	}

	/**
	 * Returns the gravity used to place the menu item in a menu.
	 * Gravity is a numeric value associated with each menu item. MenuItems in the same menu pull-down
	 * are sorted in ascending order based on their gravity values.
	 * @return The gravity used to place the menu item in a menu.
	 */
	public float getMenuGravity() {
		return gravity;
	}
}
