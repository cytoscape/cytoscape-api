package org.cytoscape.io.webservice.client;

/*
 * #%L
 * Cytoscape Webservice API (webservice-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
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

import java.net.URI;
import java.net.URISyntaxException;

import org.cytoscape.io.webservice.WebServiceClient;

/**
 * Abstract class for all web service clients. All clients MUST extend this
 * class.
 *
 * @CyAPI.Abstract.Class
 */
public abstract class AbstractWebServiceClient implements WebServiceClient {

	// Globally-unique service location.
	private final URI serviceURI;

	// Display Name for this client.
	private final String displayName;
	private final String description;

	/**
	 * Constructs this AbstractWebServiceClient.
	 *
	 * @param uri
	 *            Service Location.
	 */
	public AbstractWebServiceClient(final String uri, final String displayName, final String description) {
		try {
			this.serviceURI = new URI(uri);
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException("Given URI String is invalid: " + uri, e);
		}

		this.displayName = displayName;
		this.description = description;
	}

	@Override
	public final String getDisplayName() {
		return displayName;
	}

	@Override
	public final String getDescription() {
		return description;
	}

	@Override
	public final String toString() {
		return this.displayName;
	}

	@Override
	public final URI getServiceLocation() {
		return this.serviceURI;
	}
}
