package org.cytoscape.view.model.events;

import java.util.Collection;

import org.cytoscape.event.AbstractCyPayloadEvent;
import org.cytoscape.model.CyRow;
import org.cytoscape.view.model.View;
import org.cytoscape.view.model.table.CyTableView;

/**
 * When row {@link View} is added to a {@link CyTableView}, this event will be fired.
 * @CyAPI.Final.Class
 * @CyAPI.InModule viewmodel-api
 * @since 3.9
 */
public final class AddedRowViewsEvent extends AbstractCyPayloadEvent<CyTableView,View<CyRow>> {

	public AddedRowViewsEvent(CyTableView source, Collection<View<CyRow>> payload) {
		super(source, AddedRowViewsListener.class, payload);
	}
	
}
