package org.cytoscape.view.model.events;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.view.model.table.CyTableView;
import org.cytoscape.view.model.table.CyTableViewManager;

public class TableViewAddedEvent extends AbstractCyEvent<CyTableViewManager> {

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
