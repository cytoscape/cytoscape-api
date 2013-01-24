package org.cytoscape.view.model;

/*
 * #%L
 * Cytoscape View Model API (viewmodel-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
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

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;


public class DiscreteRangeTest {
	
	
	@Test
	public void testDiscreteRange() {
		
		final Set<String> rangeValues = new HashSet<String>();
		rangeValues.add("a");
		rangeValues.add("b");
		rangeValues.add("c");
		rangeValues.add("d");
		rangeValues.add("e");
		rangeValues.add("foo");
		
		final DiscreteRange<String> range1 = new DiscreteRange<String>(String.class, rangeValues);
		
		assertEquals(6, range1.values().size());
		assertTrue(range1.inRange("a"));
		assertTrue(range1.inRange("b"));
		assertTrue(range1.inRange("c"));
		assertTrue(range1.inRange("d"));
		assertTrue(range1.inRange("e"));
		assertFalse(range1.inRange("f"));
		assertTrue(range1.inRange("foo"));
		
		range1.addRangeValue("f");
		assertEquals(7, range1.values().size());
		assertTrue(range1.inRange("f"));
		
	}

}
