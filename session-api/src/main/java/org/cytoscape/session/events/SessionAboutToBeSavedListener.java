
package org.cytoscape.session.events;

import org.cytoscape.event.CyListener;

/**
 * Any object that needs to know that a {@link org.cytoscape.session.CySession} is about to be
 * created listen to this event.  Additionally, apps can set
 * a list of files to be saved in the CySession using the
 * appropriate method in the {@link SessionAboutToBeSavedEvent}.
 * @CyAPI.Spi.Interface
 */
public interface SessionAboutToBeSavedListener extends CyListener {
	
	/**
	 * Processes the specified event when fired.
	 * @param e The event that the listener is listening for.
	 */
	public void handleEvent(SessionAboutToBeSavedEvent e);
}
