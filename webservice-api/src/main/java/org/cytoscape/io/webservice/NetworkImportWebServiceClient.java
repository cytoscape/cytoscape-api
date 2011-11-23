package org.cytoscape.io.webservice;

import java.util.Set;

import org.cytoscape.model.CyNetwork;

/**
 * An interface that defines a web service client that will
 * return a set of networks.
 * @CyAPI.Spi.Interface
 */
public interface NetworkImportWebServiceClient {
	/** 
	 * Returns the set of networks returned from the webservice.
	 * @return The set of networks returned from the webservice.
	 */
	Set<CyNetwork> getNetworks();
}
