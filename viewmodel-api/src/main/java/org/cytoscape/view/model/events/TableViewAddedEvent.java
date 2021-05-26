package org.cytoscape.view.model.events;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.view.model.table.CyTableView;
import org.cytoscape.view.model.table.CyTableViewManager;


/**
 * When a {@link CyTableView} is added to a {@link CyTableViewManager}, this event will be fired.
 * @CyAPI.Final.Class
 * @CyAPI.InModule viewmodel-api
 * @since 3.9
 */
public final class TableViewAddedEvent extends AbstractCyEvent<CyTableViewManager> {

	private final CyTableView view;
	
	public TableViewAddedEvent(CyTableViewManager source, CyTableView view) {
		super(source, TableViewAddedListener.class);
		if(view == null)
			throw new NullPointerException("tableView is null");
		this.view = view;
	}
	
	public CyTableView getTableView() {
		return view;
	}

}
