package org.cytoscape.application.swing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.swing.JMenuItem;

import org.junit.Before;
import org.junit.Test;

public class CyMenuItemTest {

	private CyMenuItem menuItem;

	private JMenuItem item = new JMenuItem("Test");
	private float gravity = 10;

	@Before
	public void setUp() throws Exception {
		menuItem = new CyMenuItem(item, gravity);
	}

	@Test
	public void testCyMenuItem() {
		assertNotNull(menuItem);
	}

	@Test
	public void testGetMenuItem() {
		assertEquals(item, menuItem.getMenuItem());
	}

	@Test
	public void testGetMenuGravity() {
		assertTrue(gravity == menuItem.getMenuGravity());
	}

}
