package org.cytoscape.model;

/*
 * #%L
 * Cytoscape Model API (model-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2021 The Cytoscape Consortium
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
import org.junit.Test;

public class CyTableEnumTest {

	// verify that we don't add values
	@Test
	public void testMutabilityEnumSize() {
		assertEquals(3, CyTable.Mutability.values().length);
	}

	// verify that we don't change the enum values
	@Test
	public void testMutabilityEnumValues() {
		assertNotNull(CyTable.Mutability.valueOf("MUTABLE"));
		assertNotNull(CyTable.Mutability.valueOf("PERMANENTLY_IMMUTABLE"));
		assertNotNull(CyTable.Mutability.valueOf("IMMUTABLE_DUE_TO_VIRT_COLUMN_REFERENCES"));
	}

	// verify that we don't add values
	@Test
	public void testSavePolicyEnumSize() {
		assertEquals(2, SavePolicy.values().length);
	}

	// verify that we don't change the enum values
	@Test
	public void testSavePolicyEnumValues() {
		assertNotNull(SavePolicy.valueOf("DO_NOT_SAVE"));
		assertNotNull(SavePolicy.valueOf("SESSION_FILE"));
	}
}
