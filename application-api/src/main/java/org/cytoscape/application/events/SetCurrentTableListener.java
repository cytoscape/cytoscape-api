package org.cytoscape.application.events;

import org.cytoscape.event.CyListener;


/**
 * A listener for {@link SetCurrentTableEvent}s.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule application-api
 * @since 3.9
 */
public interface SetCurrentTableListener extends CyListener {

	public void handleEvent(SetCurrentTableEvent e);
}
