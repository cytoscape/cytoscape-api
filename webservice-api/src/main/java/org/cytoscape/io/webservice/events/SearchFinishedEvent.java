package org.cytoscape.io.webservice.events;

/*
 * #%L
 * Cytoscape Webservice API (webservice-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2021 The Cytoscape Consortium
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.io.webservice.WebServiceClient;

/**
 * An event that indicates a search has completed.
 * 
 * @param <T>
 *            The type of the search result.
 * @CyAPI.Final.Class
 * @CyAPI.InModule webservice-api
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
