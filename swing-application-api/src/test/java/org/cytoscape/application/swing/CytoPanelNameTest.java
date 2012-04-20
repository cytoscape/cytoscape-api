package org.cytoscape.application.swing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CytoPanelNameTest {

	

	@Test
	public void testGetTitle() {
		assertEquals("Table Panel", CytoPanelName.SOUTH.getTitle());
		assertEquals("Tool Panel", CytoPanelName.SOUTH_WEST.getTitle());
		assertEquals("Results Panel", CytoPanelName.EAST.getTitle());
		assertEquals("Control Panel", CytoPanelName.WEST.getTitle());
	}
	
	@Test
	public void checkNumber() {
		assertEquals(4, CytoPanelName.values().length);
	}
}
