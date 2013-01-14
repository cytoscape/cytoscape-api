package org.cytoscape.view.model.events;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.view.model.CyNetworkView;

/**
 * Fit network visualization to the given container. 
 * <p>
 * This event should
 * be created and fired by the view being updated and not anyone else,
 * instead call the {@link CyNetworkView#fitContent()} method.
 * @CyAPI.Final.Class
 * @CyAPI.InModule viewmodel-api
 */
public final class FitContentEvent extends AbstractCyEvent<CyNetworkView> {
	
	/**
	 * Fit the size of network view to the current container.
     * <p>
     * This event should
     * be created and fired by the view being updated and not anyone else,
     * instead call the {@link CyNetworkView#fitContent()} method.
	 * 
	 * @param source network view to be fitted to the container.
	 */
	public FitContentEvent(final CyNetworkView source) {
		super(source, FitContentListener.class);
	}
}
