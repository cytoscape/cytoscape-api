
package org.cytoscape.application.swing.events;

import org.cytoscape.event.CyListener;

/**
 * A listener for the PreferencesUpdatedEvent.
 */
public interface PreferencesUpdatedListener extends CyListener {

	/**
	 * Proces the specified event.
	 * @param e The even to be processed.
	 */
	public void handleEvent(PreferencesUpdatedEvent e);
}
