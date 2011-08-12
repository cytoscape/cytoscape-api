package org.cytoscape.application.events;


import java.util.List;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.view.model.CyNetworkView;


/**
 * An event that indicates that a collection of network views has been selected.
 */
public final class SetSelectedNetworkViewsEvent extends AbstractCyEvent<CyApplicationManager> {
	private final List<CyNetworkView> views;

	/**
	 * Constructor.
	 * @param source The application manager firing the event.
	 * @param views A list of CyNetworkViews that have been selected. 
	 */
	public SetSelectedNetworkViewsEvent(final CyApplicationManager source, final List<CyNetworkView> views) {
		super(source, SetSelectedNetworkViewsListener.class);
		this.views = views;
	}

	/**
	 * Returns the list of network views that have been selected.
	 * @return The list of network views that have been selected.
	 */
	public List<CyNetworkView> getNetworkViews() {
		return views;
	}
}
