package org.cytoscape.view.model.events;

import org.cytoscape.event.CyListener;

/**
 * Listener for {@linkplain UpdateNetworkPresentationEvent}. Usually,
 * presentation layer objects implements this event handler and redraw the
 * presentation (visualization) once it catches this event.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule viewmodel-api
 */
public interface UpdateNetworkPresentationListener extends CyListener {

	/**
	 * Listener implementing this method will redraw the presentation if necessary.
	 * 
	 * @param e
	 *            Event containing target network view-model.
	 */
	public void handleEvent(UpdateNetworkPresentationEvent e);
}
