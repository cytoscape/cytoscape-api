package org.cytoscape.io.webservice;

import java.util.Set;

import org.cytoscape.model.CyTable;

/**
 * @CyAPI.Spi.Interface
 */
public interface TableImportWebServiceClient {
	Set<CyTable> getTables();
}
