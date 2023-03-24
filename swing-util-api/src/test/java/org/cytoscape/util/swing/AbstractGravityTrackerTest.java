package org.cytoscape.util.swing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/*
 * #%L
 * Cytoscape Swing Utility API (swing-util-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2010 - 2021 The Cytoscape Consortium
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
	
	@Test
	public void testAlphabeticOrdering1() {
		var menuItemA = new JMenuItem("A");
		var menuItemB = new JMenuItem("B");
		var menuItemC = new JMenuItem("C");
		var menuItemD = new JMenuItem("D");
		var menuItemE = new JMenuItem("E");
		
		gravityTracker.addMenuItem(menuItemC, GravityTracker.USE_ALPHABETIC_ORDER);
		gravityTracker.addMenuItem(menuItemA, GravityTracker.USE_ALPHABETIC_ORDER);
		gravityTracker.addMenuItem(menuItemE, GravityTracker.USE_ALPHABETIC_ORDER);
		gravityTracker.addMenuItem(menuItemD, GravityTracker.USE_ALPHABETIC_ORDER);
		gravityTracker.addMenuItem(menuItemB, GravityTracker.USE_ALPHABETIC_ORDER);
		
		var comp = gravityTracker.getMenu();
		if (comp instanceof JMenu menu) {
			assertSame(menuItemA, menu.getMenuComponent(0));
			assertSame(menuItemB, menu.getMenuComponent(1));
			assertSame(menuItemC, menu.getMenuComponent(2));
			assertSame(menuItemD, menu.getMenuComponent(3));
			assertSame(menuItemE, menu.getMenuComponent(4));
		} else if(comp instanceof JPopupMenu menu) {
			assertSame(menuItemA, menu.getComponent(0));
			assertSame(menuItemB, menu.getComponent(1));
			assertSame(menuItemC, menu.getComponent(2));
			assertSame(menuItemD, menu.getComponent(3));
			assertSame(menuItemE, menu.getComponent(4));
		} else {
			fail("Not a menu!");
		}
	}
	
	@Test
	public void testAlphabeticOrdering2() {
		var menuItem1 = new JMenuItem("Normal 1");
		var menuItem2 = new JMenuItem("Normal 2");
		var menuItem3 = new JMenuItem("Normal 3");
		var menuItemA = new JMenuItem("A");
		var menuItemB = new JMenuItem("B");
		var menuItemC = new JMenuItem("C");
		
		gravityTracker.addMenuItem(menuItemC, GravityTracker.USE_ALPHABETIC_ORDER);
		gravityTracker.addMenuItem(menuItem2, 2.0);
		gravityTracker.addMenuItem(menuItemA, GravityTracker.USE_ALPHABETIC_ORDER);
		gravityTracker.addMenuItem(menuItem1, 1.0);
		gravityTracker.addMenuItem(menuItem3, 3.0);
		gravityTracker.addMenuItem(menuItemB, GravityTracker.USE_ALPHABETIC_ORDER);
		
		var comp = gravityTracker.getMenu();
		if (comp instanceof JMenu menu) {
			assertSame(menuItem1, menu.getMenuComponent(0));
			assertSame(menuItem2, menu.getMenuComponent(1));
			assertSame(menuItem3, menu.getMenuComponent(2));
			assertSame(menuItemA, menu.getMenuComponent(3));
			assertSame(menuItemB, menu.getMenuComponent(4));
			assertSame(menuItemC, menu.getMenuComponent(5));
		} else if(comp instanceof JPopupMenu menu) {
			assertSame(menuItem1, menu.getComponent(0));
			assertSame(menuItem2, menu.getComponent(1));
			assertSame(menuItem3, menu.getComponent(2));
			assertSame(menuItemA, menu.getComponent(3));
			assertSame(menuItemB, menu.getComponent(4));
			assertSame(menuItemC, menu.getComponent(5));
		} else {
			fail("Not a menu!");
		}
	}
	
	@Test
	public void testAlphabeticOrdering3() {
		var menuItemA = new JMenuItem("A");
		var menuItemB = new JMenuItem("B");
		var menuItemC = new JMenuItem("C");
		var menuItem1 = new JMenuItem("Normal 1");
		var menuItem2 = new JMenuItem("Normal 2");
		var menuItem3 = new JMenuItem("Normal 3");
		
		gravityTracker.addMenuItem(menuItem1, 1.0);
		gravityTracker.addMenuItem(menuItem3, 3.0);
		gravityTracker.addMenuItem(menuItemC, GravityTracker.USE_ALPHABETIC_ORDER);
		gravityTracker.addMenuItem(menuItem2, 2.0);
		gravityTracker.addMenuItem(menuItemA, GravityTracker.USE_ALPHABETIC_ORDER);
		gravityTracker.addMenuItem(menuItemB, GravityTracker.USE_ALPHABETIC_ORDER);
		
		var comp = gravityTracker.getMenu();
		if (comp instanceof JMenu menu) {
			assertSame(menuItem1, menu.getMenuComponent(0));
			assertSame(menuItem2, menu.getMenuComponent(1));
			assertSame(menuItem3, menu.getMenuComponent(2));
			assertSame(menuItemA, menu.getMenuComponent(3));
			assertSame(menuItemB, menu.getMenuComponent(4));
			assertSame(menuItemC, menu.getMenuComponent(5));
		} else if(comp instanceof JPopupMenu menu) {
			assertSame(menuItem1, menu.getComponent(0));
			assertSame(menuItem2, menu.getComponent(1));
			assertSame(menuItem3, menu.getComponent(2));
			assertSame(menuItemA, menu.getComponent(3));
			assertSame(menuItemB, menu.getComponent(4));
			assertSame(menuItemC, menu.getComponent(5));
		} else {
			fail("Not a menu!");
		}
	}
}
