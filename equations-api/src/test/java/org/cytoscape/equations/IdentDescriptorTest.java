package org.cytoscape.equations;

/*
 * #%L
 * Cytoscape Equations API (equations-api)
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


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Test;


public class IdentDescriptorTest {
	@Test
	public void testIntegerToLongMapping() {
		final IdentDescriptor id = new IdentDescriptor(Integer.valueOf(-108));
		assertEquals("Integer to Long translation is broken!", -108L, id.getValue());
	}

	@Test(expected=NullPointerException.class)
	public void testConstructionWithNullArgument() {
		final IdentDescriptor id = new IdentDescriptor(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testConstructionBadArgument() {
		final IdentDescriptor id = new IdentDescriptor(Float.valueOf(10.3f));
	}

	@Test
	public void testConstructionWithLong() {
		final IdentDescriptor id = new IdentDescriptor(Long.valueOf(8L));
		assertEquals("Construction with Long is broken!", 8L, id.getValue());
	}

	@Test
	public void testConstructionWithBoolean() {
		final IdentDescriptor id = new IdentDescriptor(Boolean.valueOf(false));
		assertEquals("Construction with Boolean is broken!", false, id.getValue());
	}

	@Test
	public void testConstructionWithDouble() {
		final IdentDescriptor id = new IdentDescriptor(Double.valueOf(2e-13));
		assertEquals("Construction with Double is broken!", 2e-13, id.getValue());
	}

	@Test
	public void testConstructionWithString() {
		final IdentDescriptor id = new IdentDescriptor("abc");
		assertEquals("Construction with Long is broken!", "abc", id.getValue());
	}

	@Test
	public void testConstructionWithAListTypeAndGetType() {
		final IdentDescriptor id = new IdentDescriptor(new ArrayList<String>());
		assertEquals("Construction with a List type is broken!", List.class, id.getType());
	}
}
