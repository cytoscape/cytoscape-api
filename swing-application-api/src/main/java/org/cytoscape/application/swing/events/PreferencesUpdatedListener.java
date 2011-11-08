
package org.cytoscape.application.swing.events;

import org.cytoscape.event.CyListener;

/**
 * A listener for the {@link PreferencesUpdatedEvent}.
 * @CyAPI.Spi.Interface
 */
public interface PreferencesUpdatedListener extends CyListener {

	/**
	 * Process the specified event.
	 * @param e The even to be processed.
	 */
	public void handleEvent(PreferencesUpdatedEvent e);
}
