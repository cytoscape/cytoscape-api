package org.cytoscape.io.webservice.events;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.io.webservice.WebServiceClient;

/**
 * An event that indicates a search has completed.
 * 
 * @param <T>
 *            The type of the search result.
 * @CyAPI.Final.Class
 */
public final class SearchFinishedEvent<T> extends AbstractCyEvent<WebServiceClient> {

	private final T searchResult;

	/**
	 * Constructs the event.
	 * 
	 * @param source
	 *            the source object firing the event.
	 * @param result
	 *            the search result object.
	 */
	public SearchFinishedEvent(final WebServiceClient source, final T result) {
		super(source, SearchFinishedListener.class);
		this.searchResult = result;
	}

	/**
	 * Returns The search result.
	 * 
	 * @return The search result.
	 */
	public T getSearchResult() {
		return this.searchResult;
	}

}
