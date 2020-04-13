package org.cytoscape.view.model.events;

import java.util.Collection;

import org.cytoscape.event.AbstractCyPayloadEvent;
import org.cytoscape.model.CyRow;
import org.cytoscape.view.model.View;
import org.cytoscape.view.model.table.CyTableView;

public class AddedRowViewsEvent extends AbstractCyPayloadEvent<CyTableView,View<CyRow>> {

	public AddedRowViewsEvent(CyTableView source, Collection<View<CyRow>> payload) {
		super(source, AddedRowViewsListener.class, payload);
	}
	
}
