package org.cytoscape.equations;

/*
 * #%L
 * Cytoscape Equations API (equations-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2021 The Cytoscape Consortium
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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

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
		eqn = new Equation("=$A+$B", variableReferences, new TreeMap<String, Object>(), code,
		                   sourceLocations, Long.class);
	}

	@Test
	public void testToString() {
		assertEquals("toString() failed.", "=$A+$B", eqn.toString());
	}

	@Test
	public void testGetVariableReferences() {
		assertEquals("getVariableReferences() failed.", variableReferences, eqn.getVariableReferences());
	}

	@Test
	public void testGetCode() {
		assertArrayEquals("getCode() failed.", code, eqn.getCode());
	}

	@Test
	public void testGetSourceLocations() {
		assertArrayEquals("getSourceLocations() failed.", sourceLocations, eqn.getSourceLocations());
	}

	@Test
	public void testGetType() {
		assertEquals("getType() failed.", Long.class, eqn.getType());
	}

	@Test
	public void testEqualsWithExpectedSuccess() {
		final Equation other =
			new Equation("=$A+$B", variableReferences, new TreeMap<String, Object>(),
			             code, sourceLocations, Long.class);
		assertTrue("equals() failed.", eqn.equals(other));
	}

	@Test
	public void testEqualsWithExpectedFailure() {
		final Object other = new Integer(12);
		assertFalse("equals() failed.", eqn.equals(other));
	}
}
