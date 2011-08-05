package org.cytoscape.model.events;

import java.util.Collection;

import org.cytoscape.event.AbstractCyPayloadEvent;
import org.cytoscape.model.CyTable;

public class RowsCreatedEvent extends AbstractCyPayloadEvent<CyTable, Object> {

	public RowsCreatedEvent(CyTable source, Collection<Object> primaryKeys) {
		super(source, RowsCreatedListener.class, primaryKeys);
	}
}
