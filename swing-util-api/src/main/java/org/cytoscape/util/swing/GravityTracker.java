/*
 File: MenuGravityTracker.java

 Copyright (c) 2011, The Cytoscape Consortium (www.cytoscape.org)

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
package org.cytoscape.util.swing;


import java.awt.Component;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


/** This interface sepecifies a tracker that will be used to insert items (submenus, menu items
 *  and separators) into a menu based on their "gravity" or "weight".  Items with higher gravity
 *  will appear further down the menu.
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
