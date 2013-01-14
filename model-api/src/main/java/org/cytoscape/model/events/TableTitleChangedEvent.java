package org.cytoscape.model.events;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.model.CyTable;

/**
 * TODO: Missing documentation
 * @CyAPI.Final.Class
 * @CyAPI.InModule model-api
 */
public final class TableTitleChangedEvent extends AbstractCyEvent<CyTable> {

	private String oldTitle;
	
	public TableTitleChangedEvent(CyTable source, String oldTitle) {
		super(source, TableTitleChangedListener.class);
		this.oldTitle = oldTitle;
	}

	public String getOldTitle(){
		return oldTitle;
	}
}
