package org.cytoscape.io.webservice.events;

import org.cytoscape.event.AbstractCyEvent;

public final class SearchFinishedEvent<T> extends AbstractCyEvent<Object> {

	private final T searchResult;
	
	public SearchFinishedEvent(final Object source,
			final T result) {
		super(source, SearchFinishedListener.class);
		
		this.searchResult = result;
	}

	public T getSearchResult() {
		return this.searchResult;
	}

}