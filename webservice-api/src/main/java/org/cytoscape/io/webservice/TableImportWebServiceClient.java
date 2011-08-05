package org.cytoscape.io.webservice;

import java.util.Set;

import org.cytoscape.model.CyTable;

public interface TableImportWebServiceClient {
	Set<CyTable> getTables();
}
