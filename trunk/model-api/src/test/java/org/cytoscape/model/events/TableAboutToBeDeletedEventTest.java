package org.cytoscape.model.events;


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