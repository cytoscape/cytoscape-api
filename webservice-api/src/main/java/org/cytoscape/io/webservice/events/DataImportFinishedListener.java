package org.cytoscape.io.webservice.events;

/**
 * Listener for {@link DataImportFinishedEvent}.
 * @CyAPI.Spi.Interface
 */
public interface DataImportFinishedListener {
	/**
	 * The method that should handle the specified event.
	 * @param evt The event to be handled.
	 */
	void handleEvent(DataImportFinishedEvent<?> evt);
}
