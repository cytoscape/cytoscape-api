package org.cytoscape.model.events;

import org.cytoscape.event.CyListener;

/**
 * Listener interface for handling {@link TablePrivacyChangedEvent}.
 * This interface can be implemented by the classes effected by setting
 * a table public or private.
 * @author rozagh
 *
 */
public interface TablePrivacyChangedListener extends CyListener{
	
	/**
	 * Handler method for {@link TablePrivacyChangedListener}.
	 * @param e the fired event of type {@link TablePrivacyChangedEvent}. 
	 */
	void handleEvent(TablePrivacyChangedEvent e);
}
