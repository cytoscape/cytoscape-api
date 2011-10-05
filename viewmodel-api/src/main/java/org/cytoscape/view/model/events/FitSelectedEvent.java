package org.cytoscape.view.model.events;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.view.model.CyNetworkView;


/**
 * Fit only selected graph object views to the container. 
 * <p>
 * This event should
 * be created and fired by the view being updated and not anyone else,
 * instead call the {@link CyNetworkView#fitSelected()} method.
 */
public final class FitSelectedEvent extends AbstractCyEvent<CyNetworkView> {
	
	/**
	 * Fit the selected part of network view to the container.  
	 * Usually this fires event to the presentation payer for redraw.
     * <p>
     * This event should
     * be created and fired by the view being updated and not anyone else,
     * instead call the {@link CyNetworkView#fitSelected()} method.
	 * 
	 * @param source target network view for fitting.
	 */
	public FitSelectedEvent(final CyNetworkView source) {
		super(source, FitSelectedEventListener.class);
	}
}
