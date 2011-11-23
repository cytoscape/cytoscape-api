package org.cytoscape.io.webservice;

/**
 * An interface that defines a queryable web service
 * that returns a search result.
 * @param <T> The type of the search result.
 * @CyAPI.Spi.Interface
 */
public interface SearchWebServiceClient<T> {
	
	/**
	 * Returns the search result object.
	 * @return the search result object.
	 */
	T getSearchResult();
}
