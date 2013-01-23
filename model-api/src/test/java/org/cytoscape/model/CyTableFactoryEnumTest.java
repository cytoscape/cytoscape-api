package org.cytoscape.model;

/*
 * #%L
 * Cytoscape Model API (model-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2013 The Cytoscape Consortium
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

public class CyTableFactoryEnumTest {

	// verify that we don't add values
	@Test
	public void testEnumSize() {
		assertEquals(3, CyTableFactory.InitialTableSize.values().length);
	}

	// verify that we don't change the enum values
	@Test
	public void testEnumValues() {
		assertNotNull(CyTableFactory.InitialTableSize.valueOf("SMALL"));
		assertNotNull(CyTableFactory.InitialTableSize.valueOf("MEDIUM"));
		assertNotNull(CyTableFactory.InitialTableSize.valueOf("LARGE"));
	}

	// verify that the sizes don't change 
	@Test
	public void testEnumValueSizes() {
		assertEquals(100,CyTableFactory.InitialTableSize.SMALL.getSize());
		assertEquals(1000,CyTableFactory.InitialTableSize.MEDIUM.getSize());
		assertEquals(10000,CyTableFactory.InitialTableSize.LARGE.getSize());
	}
}
