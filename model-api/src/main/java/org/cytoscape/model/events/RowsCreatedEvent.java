package org.cytoscape.model.events;

import java.util.Collection;

import org.cytoscape.event.AbstractCyPayloadEvent;
import org.cytoscape.model.CyTable;

/**
 * This event signals that rows have been created.
 * @CyAPI.Final.Class
 */
public final class RowsCreatedEvent extends AbstractCyPayloadEvent<CyTable, Object> {

	/**
	 * Constructs event.
	 * @param source the table in which the rows have been created.
	 * @param primaryKeys the primary keys of the rows that have been created.
	 */
	public RowsCreatedEvent(CyTable source, Collection<Object> primaryKeys) {
		super(source, RowsCreatedListener.class, primaryKeys);
	}
}
