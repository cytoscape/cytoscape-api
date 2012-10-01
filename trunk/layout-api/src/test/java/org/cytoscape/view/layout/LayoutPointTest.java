package org.cytoscape.view.layout;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class LayoutPointTest {
	
	private LayoutPoint point;
	
	private double x = 10d;
	private double y = -21d;

	@Before
	public void setUp() throws Exception {
		point = new LayoutPoint(x, y);
	}


	@Test
	public void testLayoutPoint() {
		assertNotNull(point);
	}

	@Test
	public void testGetX() {
		assertTrue(point.getX() == x);
	}

	@Test
	public void testGetY() {
		assertTrue(point.getY() == y);
	}
}
