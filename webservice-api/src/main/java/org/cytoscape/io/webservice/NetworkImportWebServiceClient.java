package org.cytoscape.io.webservice;

import java.util.Set;

import org.cytoscape.model.CyNetwork;

/**
 * #ASKMIKE
 * @CyAPI.Spi.Interface
 */
public interface NetworkImportWebServiceClient {
	Set<CyNetwork> getNetworks();
}
