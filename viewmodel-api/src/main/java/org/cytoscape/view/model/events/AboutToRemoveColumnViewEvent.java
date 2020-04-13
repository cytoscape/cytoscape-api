package org.cytoscape.view.model.events;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.model.CyColumn;
import org.cytoscape.view.model.View;
import org.cytoscape.view.model.table.CyTableView;

public class AboutToRemoveColumnViewEvent extends AbstractCyEvent<CyTableView> {

	private final View<CyColumn> columnView;
	
	public AboutToRemoveColumnViewEvent(CyTableView source, View<CyColumn> columnView) {
		super(source, AboutToRemoveColumnViewListener.class);
		this.columnView = columnView;
	}
	
	public View<CyColumn> getColumnView() {
		return columnView;
	}

}
