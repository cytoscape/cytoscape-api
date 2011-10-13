package org.cytoscape.io.webservice.events;

/**
 * Listener for {@link DataImportFinishedEvent}.
 */
public interface DataImportFinishedListener {
	void handleEvent(DataImportFinishedEvent<?> evt);
}
