/*
 File: CyMenuItem.java

 Copyright (c) 2006, The Cytoscape Consortium (www.cytoscape.org)

 The Cytoscape Consortium is:
 - Institute for Systems Biology
 - University of California San Diego
 - Memorial Sloan-Kettering Cancer Center
 - Institut Pasteur
 - Agilent Technologies

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

import javax.swing.JMenuItem;

/**
 * A simple wrapper class that allows a JMenuItem to be associated with 
 * a gravity value which defines where in a menu the item should fall.
 * @CyAPI.Final.Class
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
