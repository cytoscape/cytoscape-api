package org.cytoscape.equations;


import static org.junit.Assert.*;
import org.junit.Test;


public class NodeTest {
	@Test
	public void testGetSourceLocation() {
		final SimpleNode sn = new SimpleNode(10);
		assertEquals("getSourceLocation() is borked!", 10, sn.getSourceLocation());
	}
}
