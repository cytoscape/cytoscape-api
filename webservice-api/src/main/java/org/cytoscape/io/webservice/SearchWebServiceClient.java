package org.cytoscape.io.webservice;

/**
 * #ASKMIKE
 * @CyAPI.Spi.Interface
 */
public interface SearchWebServiceClient<T> {
	T getSearchResult();
}
