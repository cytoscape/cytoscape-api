package org.cytoscape.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/*
 * #%L
 * Cytoscape Model API (model-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2016 The Cytoscape Consortium
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

public abstract class AbstractCyColumnTest {
	
	private static final String COLUMN_1 = "test";
	private static final String COLUMN_2 = "test1";
	
	protected CyTable table;
	
	@Test (expected = NullPointerException.class)
	public void testSetNameNull() {
		createInitialColumns();
		table.getColumn(COLUMN_1).setName(null);
	}
	
	public void testSetName() {
		createInitialColumns();
		table.getColumn(COLUMN_1).setName("New Test"); // Must not throw any exception
		assertEquals("New Test", table.getColumn("new test").getName());
	}
	
	public void testSetNameTrimNewName() {
		createInitialColumns();
		table.getColumn(COLUMN_1).setName("   New Test   "); // Must not throw any exception
		assertEquals("New Test", table.getColumn("NEW TEST").getName());
	}
	
	public void testSetNameChangeCaseOnly() {
		createInitialColumns();
		table.getColumn(COLUMN_1).setName("TEST"); // Must not throw any exception
		assertEquals("TEST", table.getColumn(COLUMN_1).getName());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetExistingName() {
		createInitialColumns();
		table.getColumn("TEST").setName(COLUMN_2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetExistingNameIgnoreCase() {
		createInitialColumns();
		table.getColumn(COLUMN_1).setName(COLUMN_2.toUpperCase());
	}
	
	private void createInitialColumns() {
		table.createColumn(COLUMN_1, String.class, false);
		table.createColumn(COLUMN_2, String.class, false);
	}
}