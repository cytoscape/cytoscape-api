package org.cytoscape.property;


import org.cytoscape.event.CyListener;

/**
 * Listener interface for {@link PropertyUpdatedEvent}.
 * @author rozagh
 *
 */
public interface PropertyUpdatedListener extends CyListener {
	/**
	 * Method for handling the event.
	 * @param e the fired {@link PropertyUpdatedEvent}.
	 */
	void handleEvent(PropertyUpdatedEvent e);

}
