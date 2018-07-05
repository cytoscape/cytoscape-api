package org.cytoscape.view.presentation.events;

import org.cytoscape.event.CyListener;

/**
 * Listener for {@linkplain AnnotationsAddedEvent}.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule presentation-api
 */
public interface AnnotationsAddedListener extends CyListener {
		
	/**
	 * Process the event.
	 * @param e the {@link AnnotationsAddedEvent} to be handled.
	 */
	void handleEvent(AnnotationsAddedEvent e);
}
