package org.cytoscape.io.webservice.events;

/**
 * Listener for {@link SearchFinishedEvent}.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule webservice-api
 */
public interface SearchFinishedListener {
	/**
	 * The method that should handle the specified event.
	 * @param evt The event to be handled. 
	 */
	void handleEvent(final SearchFinishedEvent<?> evt);
}
