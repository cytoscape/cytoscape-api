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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;


public class ArgDescriptorTest {
	private final ArgDescriptor argDesc = new ArgDescriptor(ArgType.INT, "count", "A fine int.");

	@Test
	public void testGetArgType() {
		assertEquals("getArgType() is broken.", ArgType.INT, argDesc.getArgType());
	}

	@Test
	public void testGetDescription() {
		assertEquals("getDescription() is broken.", "A fine int.", argDesc.getDescription());
	}

	@Test
	public void testIsCompatibleWithWithACompatibleType() {
		assertTrue("isCompatibleWith() is broken.", argDesc.isCompatibleWith(Long.class));
	}

	@Test
	public void testIsCompatibleWithWithAnIncompatibleType() {
		assertFalse("isCompatibleWith() is broken.", argDesc.isCompatibleWith(List.class));
	}

	@Test
	public void testIsCompatibleWithWithAnIncompatibleListType() {
		assertFalse("isCompatibleWith() is broken.", argDesc.isCompatibleWith(List.class));
	}

	@Test
	public void testAnArgDescriptorThatTakesAList() {
		final ArgDescriptor listArgDesc = new ArgDescriptor(ArgType.STRINGS, "strings", "A list of strings.");
		assertTrue("isCompatibleList() is broken.", listArgDesc.isCompatibleList(String.class));
		assertFalse("isCompatibleList() is broken.", listArgDesc.isCompatibleList(Object.class));
	}
}
