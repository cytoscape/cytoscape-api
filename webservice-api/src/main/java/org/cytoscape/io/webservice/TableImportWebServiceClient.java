package org.cytoscape.io.webservice;

import java.util.Set;

import org.cytoscape.model.CyTable;

/**
 * #ASKMIKE
 * @CyAPI.Spi.Interface
 */
public interface TableImportWebServiceClient {
	Set<CyTable> getTables();
}
