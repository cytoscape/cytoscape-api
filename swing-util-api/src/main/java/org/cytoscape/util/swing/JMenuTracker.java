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


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 * A class that creates and manages hierarchies of JMenu objects.
 * @CyAPI.Final.Class
 * @CyAPI.InModule swing-util-api
 */
public final class JMenuTracker {
	
	private final Map<String, MenuGravityTracker> menuMap;
	private final JMenuBar rootMenuBar;
	private final PopupMenuGravityTracker rootPopupGravityTracker;

	/**
	 * This constructor allows you to specify a root JPopupMenu that all parent-less
	 * menus will be added to.  For example, if you call getMenu("File.New") then two
	 * menus will be created, "File" and it's submenu "New".  In this situation the
	 * "File" menu will be added to the JPopupMenu, while "New" will not.
	 *
	 * @param rootPopupMenu  the popup menu that all parent-less items will be added to based on
	 *        their gravity
	 */
	public JMenuTracker(final JPopupMenu rootPopupMenu) {
		if (rootPopupMenu == null)
			throw new NullPointerException("root popupmenu for menus is null.");

		this.rootPopupGravityTracker = new PopupMenuGravityTracker(rootPopupMenu);
		this.rootMenuBar = null;
		menuMap = new HashMap<String, MenuGravityTracker>();
	}

	/**
	 * This constructor allows you to specify a root menubar that all parent-less
	 * menus will be added to.  For example, if you call getMenu("File.New") then two
	 * menus will be created, "File" and it's submenu "New".  In this situation the
	 * "File" menu will be added to the JPopupMenu, while "New" will not.
	 *
	 * @param rootMenuBar  the popup menu that all parent-less items will be added to based on
	 *        their gravity
	 */
	public JMenuTracker(final JMenuBar rootMenuBar) {
		if (rootMenuBar == null)
			throw new NullPointerException("root menubar for menus is null.");

		this.rootPopupGravityTracker = null;
		this.rootMenuBar = rootMenuBar;
		menuMap = new HashMap<String, MenuGravityTracker>();
	}

	/**
	 * This method will fetch an existing menu or create a new one if a menu
	 * with the specified name does not exist. The menu name is specified
	 * with a '.' delimited string, such that each token creates a new child
	 * menu.  Insertion locations are determined in two possible ways: 1) a "gravity" or "weight"
	 * may be specified in square brackets after each item name.  If the gravity has been
	 * omitted, the insertion location will be based on a case-insensitive alphanumeric
	 * ordering.   An example of a string using weights is "File[10].New[40]".
	 *
	 * @param menuString A '.' delimited string identifying menu names.
	 * @return The last child JMenu object specified by the menu_string parameter.
	 */
	public GravityTracker getGravityTracker(final String menuString) {
		if (menuString == null)
			throw new NullPointerException("menu string is null");

		if (menuString.isEmpty())
			throw new IllegalArgumentException("menu string has zero length");

		// Special case: if we have a popup menu and the menu is "." return the rootGravityTracker
		if (menuString.equals(".") && rootPopupGravityTracker != null)
			return rootPopupGravityTracker;

		final List<MenuNameAndGravity> namesAndGravities = parseMenuString(menuString);
		MenuGravityTracker parentGravityTracker = null;
		MenuGravityTracker gravityTracker = null;
		String menu_key = null;

		for (final MenuNameAndGravity nameAndGravity : namesAndGravities) {
			final String menu_token = nameAndGravity.getMenuName();
			menu_key = menu_key == null ? menu_token : menu_key + "." + menu_token;
			gravityTracker = menuMap.get(menu_key);
			
			if (gravityTracker == null) {
				final JMenu menu = new JMenu(menu_token);
				
				menu.addMenuListener(new MenuListener() {
					@Override
					public void menuSelected(MenuEvent e) {
						// Force update of enabled state of all of its direct menu items
						final int itemCount = menu.getItemCount();
						
						for (int i = 0; i < itemCount; i++) {
							final JMenuItem item = menu.getItem(i);
							
							if (item != null && item.getAction() != null)
								item.setEnabled(item.getAction().isEnabled());
						}
					}
					
					@Override
					public void menuDeselected(MenuEvent e) {
					}
					
					@Override
					public void menuCanceled(MenuEvent e) {
					}
				});

				// if there is a JMenu parent, use that
				if (parentGravityTracker != null)
					parentGravityTracker.addMenu(menu, nameAndGravity.getGravity());
				// otherwise use add the menu to the root component
				else if (rootMenuBar != null && rootPopupGravityTracker == null)
					rootMenuBar.add(menu);
				else if (rootMenuBar == null && rootPopupGravityTracker != null)
					rootPopupGravityTracker.addMenu(menu, nameAndGravity.getGravity());
				else
					throw new IllegalStateException("we have no root popup menu or menu bar.");

				gravityTracker = new MenuGravityTracker(menu);
				menuMap.put(menu_key, gravityTracker);
			}

			parentGravityTracker = gravityTracker;
		}

		return gravityTracker;
	}

	enum ParseState {
		LOOKING_FOR_OPENING_BRACKET, LOOKING_FOR_CLOSING_BRACKET, LOOKING_FOR_PERIOD;
	}

	final static class MenuNameAndGravity {
		private final String menuName;
		private final double gravity;

		MenuNameAndGravity(final String menuName, final double gravity) {
			this.menuName = menuName;
			this.gravity  = gravity;
		}

		String getMenuName() {
			return menuName;
		}

		double getGravity() {
			return gravity;
		}
	}

	static List<MenuNameAndGravity> parseMenuString(final String menuString) {
		final List<MenuNameAndGravity> namesAndGravities = new ArrayList<MenuNameAndGravity>();
		ParseState state = ParseState.LOOKING_FOR_OPENING_BRACKET;

		StringBuilder menuName = new StringBuilder();
		StringBuilder gravityAsString = null;
		for (int i = 0; i < menuString.length(); ++i) {
			final char ch = menuString.charAt(i);
			switch (state) {
			case LOOKING_FOR_OPENING_BRACKET:
				if (ch == '.') {
					if (menuName.length() == 0)
						throw new IllegalArgumentException("zero-length menu name found.");
					namesAndGravities.add(new MenuNameAndGravity(menuName.toString(),
										     GravityTracker.USE_ALPHABETIC_ORDER));
					menuName = new StringBuilder();
				} else if (ch == '[') {
					gravityAsString =  new StringBuilder();
					state = ParseState.LOOKING_FOR_CLOSING_BRACKET;
				} else
					menuName.append(ch);
				break;
			case LOOKING_FOR_CLOSING_BRACKET:
				if (ch != ']')
					gravityAsString.append(ch);
				else {
					double gravity;
					try {
						gravity = Double.parseDouble(gravityAsString.toString());
					} catch (NumberFormatException e) {
						throw new IllegalArgumentException("bad \"gravity\" in menu string. ("
										   + menuString + ")");
					}
					namesAndGravities.add(new MenuNameAndGravity(menuName.toString(),
										     gravity));
					menuName = new StringBuilder();
					state = ParseState.LOOKING_FOR_PERIOD;
				}
				break;
			case LOOKING_FOR_PERIOD:
				if (ch != '.')
					throw new IllegalArgumentException("period expected in menu string. ("
									   + menuString + ")");
				state = ParseState.LOOKING_FOR_OPENING_BRACKET;
				break;
			}
		}

		if (state == ParseState.LOOKING_FOR_OPENING_BRACKET)
			namesAndGravities.add(new MenuNameAndGravity(menuName.toString(),
								     GravityTracker.USE_ALPHABETIC_ORDER));
		else if (state != ParseState.LOOKING_FOR_PERIOD)
			throw new IllegalArgumentException("incomplete \"gravity\" specification in menu string ("
							   + menuName + "). ("
							   + menuString + ")");

		return namesAndGravities;
	}
}
