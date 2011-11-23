package org.cytoscape.io.webservice;

import java.util.Set;

import org.cytoscape.model.CyTable;

/**
 * An interface that defines a web service client that will
 * return a set of tables.
 * @CyAPI.Spi.Interface
 */
public interface TableImportWebServiceClient {

	/** 
	 * Returns a set of tables from the web service.
	 * @return a set of tables from the web service.
	 */
	Set<CyTable> getTables();
}
