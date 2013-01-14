package org.cytoscape.view.vizmap.gui.event;

import java.beans.PropertyChangeEvent;

/**
 * Handler for Vizmap-GUI-local {@link PropertyChangeEvent}.
 * 
 * Once GUI-local {@link PropertyChangeEvent} is fired,
 * {@link java.beans.PropertyChangeListener} catches the event and one of these
 * handlers will be called and processes the event.
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule vizmap-gui-api
 */
public interface VizMapEventHandler {
	
	/**
	 * Process event.
	 * 
	 * @param e GUI-local event to be processed.
	 */
	void processEvent(final PropertyChangeEvent e);
}