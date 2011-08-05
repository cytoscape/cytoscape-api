package org.cytoscape.io.webservice.events;

public interface DataImportFinishedListener {
	void handleEvent(DataImportFinishedEvent<?> evt);
}
