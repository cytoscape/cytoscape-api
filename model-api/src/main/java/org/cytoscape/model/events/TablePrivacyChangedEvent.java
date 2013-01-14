package org.cytoscape.model.events;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.model.CyTable;

/**
 * This event is fired when a table privacy is changed from public
 * to private or vice versa.  A public CyTable is a table that is accessible to the user through the user
 * interface.  Private or non-public CyTables will not be visible to the user from the
 * normal user interface, although they will be accessible to app writers through the API.
 * @CyAPI.Final.Class
 * @CyAPI.InModule model-api
 */
public final class TablePrivacyChangedEvent extends AbstractCyEvent<CyTable> {

	/**
	 * Constructor method for TablePrivacyChangedEvent.
	 * @param source the CyTable that the privacy is updated for.
	 */
	public TablePrivacyChangedEvent(CyTable source) {
		super(source, TablePrivacyChangedListener.class);
	}

}
