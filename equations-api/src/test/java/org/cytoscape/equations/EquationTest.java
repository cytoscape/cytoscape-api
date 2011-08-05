package org.cytoscape.equations;


import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class EquationTest {
	private Equation eqn;
	private Set<String> variableReferences;
	private Object[] code;
	private int[] sourceLocations;

	@Before
	public void init() {
		variableReferences = new HashSet<String>();
		variableReferences.add("A");
		variableReferences.add("B");
		code = new Object[2];
		sourceLocations = new int[] { 12, 117 };
		eqn = new Equation("=$A+$B", variableReferences, code, sourceLocations, Long.class);
	}

	@Test
	public void testToString() {
		assertEquals("toString() failed!", "=$A+$B", eqn.toString());
	}

	@Test
	public void testGetVariableReferences() {
		assertEquals("getVariableReferences() failed!", variableReferences, eqn.getVariableReferences());
	}

	@Test
	public void testGetCode() {
		assertArrayEquals("getCode() failed!", code, eqn.getCode());
	}

	@Test
	public void testGetSourceLocations() {
		assertEquals("getSourceLocations() failed!", sourceLocations, eqn.getSourceLocations());
	}

	@Test
	public void testGetType() {
		assertEquals("getType() failed!", Long.class, eqn.getType());
	}

	@Test
	public void testEqualsWithExpectedSuccess() {
		final Equation other = new Equation("=$A+$B", variableReferences, code, sourceLocations, Long.class);
		assertTrue("equals() failed!", eqn.equals(other));
	}

	@Test
	public void testEqualsWithExpectedFailure() {
		final Object other = new Integer(12);
		assertFalse("equals() failed!", eqn.equals(other));
	}
}
