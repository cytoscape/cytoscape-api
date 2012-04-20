package org.cytoscape.application.swing;

import java.util.ArrayList;
import java.util.Collection;

import org.cytoscape.model.CyRow;
import org.cytoscape.model.CyTable;
import org.cytoscape.model.events.RowSetRecord;
import org.cytoscape.model.events.RowsSetEvent;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

public abstract class AbstractViewUpdaterTest {

	protected AbstractViewUpdater<?> updater;
	@Mock private CyTable source;
	
	private Collection<RowSetRecord> rows;
	
	@Mock private CyRow row;
	@Mock private Object value;
	@Mock private Object rawValue;
	
	@Test
	public abstract void testAbstractViewUpdater();

	@Test
	public void testHandleEvent() {
		rows = new ArrayList<RowSetRecord>();
		RowSetRecord rec1 = new RowSetRecord(row, "test", value, rawValue);
		rows.add(rec1);
		RowsSetEvent event = new RowsSetEvent(source, rows);
		updater.handleEvent(event);
		
		verify(row, times(0)).getAllValues();

	}

}
