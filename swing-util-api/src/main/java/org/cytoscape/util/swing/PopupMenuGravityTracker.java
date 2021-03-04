package org.cytoscape.util.swing;

/*
 * #%L
 * Cytoscape Swing Utility API (swing-util-api)
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
	private final static Logger logger = LoggerFactory.getLogger("org.cytoscape.application.userlog");
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
                logger.debug("Inserted menu item: " + newMenuItem + " with gravity: " + gravity);
	}

	@Override
	public void addMenu(final JMenu newSubmenu, final double gravity) {
                final int index = getInsertLocation(newSubmenu.getText(), gravity);
                menu.insert(newSubmenu, index);
                componentGravity.put(newSubmenu, gravity);
                logger.debug("Inserted menu: " + newSubmenu + " with gravity: " + gravity);
	}

	@Override
	public void addMenuSeparator(final double gravity) {
                final int index = getInsertLocation("-", gravity);
                menu.insert(new JSeparator(), index);
                final Component separator = menu.getComponent(index);
                componentGravity.put(separator, gravity);
                logger.debug("Inserted menu separator with gravity: " + gravity);
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
