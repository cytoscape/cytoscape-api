/*
 File: PopupMenuGravityTracker.java

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
import java.util.HashMap;
import java.util.Map;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This class inserts menu items and separators in a menu based on their "gravity".
 * Items with higher gravity appear further down the menu.
 * @CyAPI.Final.Class
 * @CyAPI.InModule swing-util-api
 */
public final class PopupMenuGravityTracker implements GravityTracker {
	private final static Logger logger = LoggerFactory.getLogger(PopupMenuGravityTracker.class);
	private final JPopupMenu menu;
	private final Map<Component, Double> componentGravity;

	/**
	 * Constructor.
	 * @param menu The JPopupMenu to associate with this PopupMenuGravityTracker.
	 */
	public PopupMenuGravityTracker(final JPopupMenu menu) {
		this.menu = menu;
		this.componentGravity = new HashMap<Component, Double>();
	}

	@Override
	public JPopupMenu getMenu() { return menu; }

	@Override
	public void addMenuItem(final JMenuItem newMenuItem, final double gravity) {
                final int index = getInsertLocation(newMenuItem.getText(), gravity);
                menu.insert(newMenuItem, index);
                componentGravity.put(newMenuItem, gravity);
                logger.info("Inserted menu item: " + newMenuItem + " with gravity: " + gravity);
	}

	@Override
	public void addMenu(final JMenu newSubmenu, final double gravity) {
                final int index = getInsertLocation(newSubmenu.getText(), gravity);
                menu.insert(newSubmenu, index);
                componentGravity.put(newSubmenu, gravity);
                logger.info("Inserted menu: " + newSubmenu + " with gravity: " + gravity);
	}

	@Override
	public void addMenuSeparator(final double gravity) {
                final int index = getInsertLocation("-", gravity);
                menu.insert(new JSeparator(), index);
                final Component separator = menu.getComponent(index);
                componentGravity.put(separator, gravity);
                logger.info("Inserted menu separator with gravity: " + gravity);
	}

	@Override
	public void removeComponent(final Component component) {
		if (componentGravity.remove(component) != null)
			menu.remove(menu.getComponentIndex(component));
	}

	private int getInsertLocation(final String newMenuText, final double newGravity) {
		if (newGravity == GravityTracker.USE_ALPHABETIC_ORDER) {
			for (int i = 0; i < menu.getComponentCount(); ++i) {
				final Component item = menu.getComponent(i);
				if (item instanceof JMenu) {
					final JMenu subMenu = (JMenu)item;
					if (newMenuText.compareToIgnoreCase(subMenu.getText()) < 0)
						return i;
				} else if (item instanceof JMenuItem) {
					final JMenuItem menuItem = (JMenuItem)item;
					if (newMenuText.compareToIgnoreCase(menuItem.getText()) < 0)
						return i;
				}
			}
		} else {
			for (int i = 0; i < menu.getComponentCount(); ++i) {
				final Component item = menu.getComponent(i);
				Double gravity = componentGravity.get(item);
				if (gravity != null && newGravity < gravity) {
					return i;
				}
			}
		}

		return menu.getComponentCount();
	}
}
