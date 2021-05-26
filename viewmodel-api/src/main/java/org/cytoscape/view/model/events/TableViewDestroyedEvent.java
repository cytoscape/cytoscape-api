package org.cytoscape.view.model.events;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.view.model.table.CyTableView;
import org.cytoscape.view.model.table.CyTableViewManager;


/**
 * When a {@link CyTableView} is destroyed, this event will be fired.
 * @CyAPI.Final.Class
 * @CyAPI.InModule viewmodel-api
 * @since 3.9
 */
public final class TableViewDestroyedEvent extends AbstractCyEvent<CyTableViewManager> {

	public TableViewDestroyedEvent(CyTableViewManager source) {
		super(source, TableViewDestroyedListener.class);
	}
	
}
