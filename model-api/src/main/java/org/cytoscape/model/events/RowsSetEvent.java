package org.cytoscape.model.events;

import java.util.Collection;

import org.cytoscape.event.AbstractCyPayloadEvent;
import org.cytoscape.model.CyTable;

/**
 * This event signals that rows have been set.
 * @CyAPI.Final.Class   #ASKMIKE : This class isn't declared as final.
 */
public class RowsSetEvent extends AbstractCyPayloadEvent<CyTable, RowSetRecord> {

	/**
	 * Constructs Event.
	 * @param source the table in which the rows have been set.
	 * @param rows the rows that have been set as a Collection of type {@link RowSetRecord}.
	 */
	public RowsSetEvent(CyTable source, Collection<RowSetRecord> rows) {
		super(source, RowsSetListener.class, rows);
	}
}
