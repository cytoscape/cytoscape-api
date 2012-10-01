package org.cytoscape.equations;


import static org.junit.Assert.*;
import org.junit.Test;


public class FunctionErrorTest {
	@Test
	public void testGetArgNumber() {
		final FunctionError fe = new FunctionError("some message", 17);
		assertEquals("getArgNumber() failed!", 17, fe.getArgNumber());
	}
}
