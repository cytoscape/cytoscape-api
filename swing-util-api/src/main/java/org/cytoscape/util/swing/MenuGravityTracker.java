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
import java.util.HashMap;
import java.util.Map;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This class inserts menu items and separators in a menu based on their "gravity".
 * Items with higher gravity appear further down the menu.
 * @CyAPI.Final.Class
 * @CyAPI.InModule swing-util-api
 */
public final class MenuGravityTracker implements GravityTracker {
	
	private final static Logger logger = LoggerFactory.getLogger("org.cytoscape.application.userlog");
	
	private final JMenu menu;
	private final Map<Component, Double> componentGravity;

	public MenuGravityTracker(JMenu menu) {
		this.menu = menu;
		this.componentGravity = new HashMap<>();
	}

	@Override
	public JMenu getMenu() {
		return menu;
	}

	@Override
	public void addMenuItem(JMenuItem newMenuItem, double gravity) {
		//newMenuItem.setText(newMenuItem.getText() + " (" + gravity + ")");
		int index = getInsertLocation(newMenuItem.getText(), gravity);
		menu.insert(newMenuItem, index);
		componentGravity.put(newMenuItem, gravity);
		logger.debug("Inserted menu item: " + newMenuItem + " with gravity: " + gravity);
	}

	@Override
	public void addMenu(JMenu newSubmenu, double gravity) {
		//newSubmenu.setText(newSubmenu.getText() + " (" + gravity + ")");
		int index = getInsertLocation(newSubmenu.getText(), gravity);
		menu.insert(newSubmenu, index);
		componentGravity.put(newSubmenu, gravity);
		logger.debug("Inserted menu: " + newSubmenu + " with gravity: " + gravity);
	}

	@Override
	public void addMenuSeparator(double gravity) {
		int index = getInsertLocation("-", gravity);
		menu.insertSeparator(index);
		Component separator = menu.getMenuComponent(index);
		componentGravity.put(separator, gravity);
		logger.debug("Inserted menu separator with gravity: " + gravity);
	}

	@Override
	public void removeComponent(Component component) {
		if (componentGravity.remove(component) != null)
			menu.remove(component);
	}

	private int getInsertLocation(String text, double gravity) {
		int count = menu.getMenuComponentCount();
		
		if (gravity == USE_ALPHABETIC_ORDER) {
			for (int i = 0; i < count; ++i) {
				var item = menu.getMenuComponent(i);
				var itemGrav = componentGravity.getOrDefault(item, USE_ALPHABETIC_ORDER);
				
				if(itemGrav != USE_ALPHABETIC_ORDER) {
					continue;
				} else if (item instanceof JMenu subMenu) {
					if (text.compareToIgnoreCase(subMenu.getText()) < 0) {
						return i;
					}
				} else if (item instanceof JMenuItem menuItem) {
					if (text.compareToIgnoreCase(menuItem.getText()) < 0) {
						return i;
					}
				}
			}
		} else {
			for (int i = 0; i < count; ++i) {
				var item = menu.getMenuComponent(i);
				var itemGrav = componentGravity.getOrDefault(item, USE_ALPHABETIC_ORDER);
				if (itemGrav == USE_ALPHABETIC_ORDER) {
					return i;
				}
				if (itemGrav != null && gravity < itemGrav) {
					return i;
				}
			}
		}

		return count;
	}
	
}
