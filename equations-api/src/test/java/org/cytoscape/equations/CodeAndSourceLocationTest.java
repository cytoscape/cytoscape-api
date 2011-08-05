package org.cytoscape.equations;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class CodeAndSourceLocationTest {
	private Object code;
	private CodeAndSourceLocation casl;

	@Before
	public void init() {
		code = new Integer(12);
		casl = new CodeAndSourceLocation(code, 25);
	}

	@Test
	public void testGetCode() {
		assertEquals("getCode() is broken!", code, casl.getCode());
	}

	@Test
	public void testGetSourceLocation() {
		assertEquals("getSourceLocation() is broken!", 25, casl.getSourceLocation());
	}
}
