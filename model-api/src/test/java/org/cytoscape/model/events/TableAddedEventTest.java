package org.cytoscape.model.events;

import static org.junit.Assert.assertEquals;

import org.cytoscape.model.CyTable;
import org.cytoscape.model.CyTableManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TableAddedEventTest {

	@Mock private CyTableManager tableManager;
	@Mock private CyTable table;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public final void testGetTable() {
		
		final TableAddedEvent event = new TableAddedEvent(tableManager, table);
		
		assertEquals("TableManager returned by getSource() is *not* the one passed into the constructor!",
				tableManager, event.getSource());
		assertEquals("Table returned by getTable() is *not* the one passed into the constructor!", table,
				event.getTable());
	}

}
