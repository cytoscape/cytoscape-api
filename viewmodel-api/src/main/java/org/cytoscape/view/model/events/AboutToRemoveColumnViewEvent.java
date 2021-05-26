package org.cytoscape.view.model.events;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.model.CyColumn;
import org.cytoscape.view.model.View;
import org.cytoscape.view.model.table.CyTableView;


/**
 * When column {@link View}s are about to be removed from a {@link CyTableView}, this event will be fired.
 * @CyAPI.Final.Class 
 * @CyAPI.InModule viewmodel-api
 * @since 3.9
 */
public final class AboutToRemoveColumnViewEvent extends AbstractCyEvent<CyTableView> {

	private final View<CyColumn> columnView;
	
	public AboutToRemoveColumnViewEvent(CyTableView source, View<CyColumn> columnView) {
		super(source, AboutToRemoveColumnViewListener.class);
		this.columnView = columnView;
	}
	
	public View<CyColumn> getColumnView() {
		return columnView;
	}

}
