package org.cytoscape.model.events;

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
import org.junit.Before;
import org.junit.Test;

import org.cytoscape.model.CyTable;

import static org.mockito.Mockito.*;


public class ColumnDeletedEventTest {
	ColumnDeletedEvent event;
	CyTable table;
	final String columnName = "asdf";

	@Before
	public void setUp() {
		table = mock(CyTable.class);
		event = new ColumnDeletedEvent(table,columnName);
	}

	@Test
	public void testGetColumnName() {
		assertEquals( event.getColumnName(), columnName );
	}

	@Test
	public void testGetSource() {
		assertEquals( event.getSource(), table );
	}

	@Test
	public void testGetListenerClass() {
		assertEquals( event.getListenerClass(), ColumnDeletedListener.class );
	}

	@Test
	public void testNullColumn() {
		try {
			ColumnDeletedEvent ev = new ColumnDeletedEvent(table, null);
		} catch (NullPointerException npe) {
			return;
		}
		fail("didn't catch expected npe for column");
	}

	@Test
	public void testNullTable() {
		try {
			ColumnDeletedEvent ev = new ColumnDeletedEvent(null, columnName);
		} catch (NullPointerException npe) {
			return;
		}
		fail("didn't catch expected npe for table");
	}
}
