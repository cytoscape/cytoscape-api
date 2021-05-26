package org.cytoscape.view.model.events;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.model.CyColumn;
import org.cytoscape.view.model.View;
import org.cytoscape.view.model.table.CyTableView;

/**
 * When column {@link View} is added to a {@link CyTableView}, this event will be fired.
 * @CyAPI.Final.Class
 * @CyAPI.InModule viewmodel-api
 * @since 3.9
 */
public final class AddedColumnViewEvent extends AbstractCyEvent<CyTableView> {

	private final View<CyColumn> columnView;
	
	public AddedColumnViewEvent(CyTableView source, View<CyColumn> columnView) {
		super(source, AddedColumnViewListener.class);
		this.columnView = columnView;
	}
	
	public View<CyColumn> getColumnView() {
		return columnView;
	}

}
