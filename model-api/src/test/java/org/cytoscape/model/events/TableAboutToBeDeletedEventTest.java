package org.cytoscape.model.events;

/*
 * #%L
 * Cytoscape Model API (model-api)
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


import org.cytoscape.model.CyTable;
import org.cytoscape.model.CyTableManager;

import static org.junit.Assert.*;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class TableAboutToBeDeletedEventTest {
	@Test
	public final void testGetTable() {
		final CyTableManager tableManager = mock(CyTableManager.class);
		final CyTable table = mock(CyTable.class);
		final TableAboutToBeDeletedEvent event = new TableAboutToBeDeletedEvent(tableManager, table);
		assertEquals("TableManager returned by getSource() is *not* the one passed into the constructor!",
			     tableManager, event.getSource());
		assertEquals("Table returned by getTable() is *not* the one passed into the constructor!",
			     table, event.getTable());
	}
}