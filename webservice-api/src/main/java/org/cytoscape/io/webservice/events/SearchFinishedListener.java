package org.cytoscape.io.webservice.events;

/**
 * Listener for {@link SearchFinishedEvent}.
 * @CyAPI.Spi.Interface
 */
public interface SearchFinishedListener {
	/**
	 * The method that should handle the specified event.
	 * @param evt The event to be handled. #ASKMIKE shouldn't it have <?>
	 */
	void handleEvent(final SearchFinishedEvent evt);
}
