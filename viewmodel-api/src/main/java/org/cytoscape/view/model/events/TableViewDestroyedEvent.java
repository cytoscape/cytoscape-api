package org.cytoscape.view.model.events;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.view.model.table.CyTableViewManager;

public class TableViewDestroyedEvent extends AbstractCyEvent<CyTableViewManager> {

	
	public TableViewDestroyedEvent(CyTableViewManager source) {
		super(source, TableViewDestroyedListener.class);
	}
	

}
