package org.cytoscape.util.swing;

/*
 * #%L
 * Cytoscape Swing Utility API (swing-util-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2010 - 2013 The Cytoscape Consortium
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
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;

import static org.junit.Assert.*;
import org.junit.Test;


public abstract class AbstractGravityTrackerTest {
	protected GravityTracker gravityTracker;

	@Test
	public void testAddMenuItem() {
		final JMenuItem menuItem1 = new JMenuItem();
		gravityTracker.addMenuItem(menuItem1, 10.0);
		final JMenuItem menuItem2 = new JMenuItem();
		gravityTracker.addMenuItem(menuItem2, 5.0);
		final Component someMenu = gravityTracker.getMenu();
		if (someMenu instanceof JMenu) {
			final JMenu menu = (JMenu)someMenu;
			assertEquals(menuItem2, menu.getItem(0));
			assertEquals(menuItem1, menu.getItem(1));
		} else {
			final JPopupMenu menu = (JPopupMenu)someMenu;
			assertEquals(menuItem2, menu.getComponent(0));
			assertEquals(menuItem1, menu.getComponent(1));
		}
	}

	@Test
	public void testAddMenu() {
		final JMenu menu1 = new JMenu();
		gravityTracker.addMenu(menu1, 10.0);
		final JMenu menu2 = new JMenu();
		gravityTracker.addMenu(menu2, 5.0);
		final Component someMenu = gravityTracker.getMenu();
		if (someMenu instanceof JMenu) {
			final JMenu menu = (JMenu)someMenu;
			assertEquals(menu2, menu.getMenuComponent(0));
			assertEquals(menu1, menu.getMenuComponent(1));
		} else {
			final JPopupMenu menu = (JPopupMenu)someMenu;
			assertEquals(menu2, menu.getComponent(0));
			assertEquals(menu1, menu.getComponent(1));
		}
	}

	@Test
	public void testAddMenuSeparator() {
		final JMenu menu1 = new JMenu();
		gravityTracker.addMenu(menu1, 10.0);
		final JMenu menu2 = new JMenu();
		gravityTracker.addMenu(menu2, 5.0);
		gravityTracker.addMenuSeparator(7.5);
		final Component someMenu = gravityTracker.getMenu();
		if (someMenu instanceof JMenu) {
			final JMenu menu = (JMenu)someMenu;
			assertEquals(menu2, menu.getMenuComponent(0));
			assertTrue(menu.getMenuComponent(1) instanceof JSeparator);
			assertEquals(menu1, menu.getMenuComponent(2));
		} else {
			final JPopupMenu menu = (JPopupMenu)someMenu;
			assertEquals(menu2, menu.getComponent(0));
			assertTrue(menu.getComponent(1) instanceof JSeparator);
			assertEquals(menu1, menu.getComponent(2));
		}
	}

	@Test
	public void testRemoveComponent() {
		final JMenu menu1 = new JMenu();
		gravityTracker.addMenu(menu1, 10.0);
		final JMenu menu2 = new JMenu();
		gravityTracker.addMenu(menu2, 5.0);

		gravityTracker.removeComponent(menu2);
		
		final Component someMenu = gravityTracker.getMenu();
		if (someMenu instanceof JMenu) {
			final JMenu menu = (JMenu)someMenu;
			assertEquals(menu1, menu.getMenuComponent(0));
		} else {
			final JPopupMenu menu = (JPopupMenu)someMenu;
			assertEquals(menu1, menu.getComponent(0));
		}
	}
}
