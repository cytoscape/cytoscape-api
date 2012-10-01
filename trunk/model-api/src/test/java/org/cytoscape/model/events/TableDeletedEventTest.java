package org.cytoscape.model.events;


import org.cytoscape.model.CyTableManager;

import static org.junit.Assert.*;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class TableDeletedEventTest {
	@Test
	public final void testGetTable() {
		final CyTableManager tableManager = mock(CyTableManager.class);
		final TableDeletedEvent event = new TableDeletedEvent(tableManager);
		assertEquals("TableManager returned by getSource() is *not* the one passed into the constructor!",
			     tableManager, event.getSource());
	}
}