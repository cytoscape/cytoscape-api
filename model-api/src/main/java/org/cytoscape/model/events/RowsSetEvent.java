package org.cytoscape.model.events;

import java.util.Collection;

import org.cytoscape.event.AbstractCyPayloadEvent;
import org.cytoscape.model.CyTable;

public class RowsSetEvent extends AbstractCyPayloadEvent<CyTable, RowSetRecord> {

	public RowsSetEvent(CyTable source, Collection<RowSetRecord> rows) {
		super(source, RowsSetListener.class, rows);
	}
}
