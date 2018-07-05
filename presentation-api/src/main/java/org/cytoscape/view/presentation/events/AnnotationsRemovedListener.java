package org.cytoscape.view.presentation.events;

import org.cytoscape.event.CyListener;

/**
 * Listener for {@linkplain AnnotationsRemovedEvent}.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule presentation-api
 */
public interface AnnotationsRemovedListener extends CyListener {

	/**
	 * Process the event.
	 * @param e the {@link AnnotationsRemovedEvent} to be handled.
	 */
	void handleEvent(AnnotationsRemovedEvent e);
}
