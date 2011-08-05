package org.cytoscape.io.webservice;

import java.util.Set;

import org.cytoscape.model.CyNetwork;

public interface NetworkImportWebServiceClient {
	Set<CyNetwork> getNetworks();
}
