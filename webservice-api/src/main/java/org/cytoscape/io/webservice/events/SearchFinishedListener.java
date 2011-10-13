package org.cytoscape.io.webservice.events;

/**
 * Listener for {@link SearchFinishedEvent}.
 */
public interface SearchFinishedListener {
	void handleEvent(final SearchFinishedEvent evt);
}
