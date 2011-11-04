package org.cytoscape.io.webservice;

/**
 * @CyAPI.Spi.Interface
 */
public interface SearchWebServiceClient<T> {
	T getSearchResult();
}
