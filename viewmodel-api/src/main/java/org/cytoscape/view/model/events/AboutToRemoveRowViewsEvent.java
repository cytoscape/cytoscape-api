package org.cytoscape.view.model.events;

import java.util.Collection;

import org.cytoscape.event.AbstractCyPayloadEvent;
import org.cytoscape.model.CyRow;
import org.cytoscape.view.model.View;
import org.cytoscape.view.model.table.CyTableView;

public class AboutToRemoveRowViewsEvent extends AbstractCyPayloadEvent<CyTableView,View<CyRow>> {

	public AboutToRemoveRowViewsEvent(CyTableView source, Class<?> listenerClass, Collection<View<CyRow>> payload) {
		super(source, AboutToRemoveRowViewsListener.class, payload);
	}

}
