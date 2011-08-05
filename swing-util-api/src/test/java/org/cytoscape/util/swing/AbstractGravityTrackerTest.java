/*
 Copyright (c) 2010, The Cytoscape Consortium (www.cytoscape.org)

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
