package org.cytoscape.io.webservice;

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

import org.cytoscape.work.TaskIterator;

/**
 * Thin wrapper for SOAP/REST web service clients.
 * 
 * All web service clients <strong>must</strong> implement this interface.
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule webservice-api
 */
public interface WebServiceClient {

	/**
	 * Returns resource location of this service, i.e., service URL. This is
	 * guaranteed to be globally unique and can be used as identifier.
	 * 
	 * @return URI of the service.
	 */
	URI getServiceLocation();

	/**
	 * Returns display name of this client. This is more human readable name for
	 * this client. This may not be unique.
	 * 
	 * @return display name for this client.
	 */
	String getDisplayName();

	/**
	 * Get human-readable description of this client.
	 * 
	 * @return Description as a string. Users should write parser for this
	 *         return value.
	 */
	String getDescription();

	/**
	 * Set query for the tasks to be executed.
	 * 
	 * @param query query object. This is client-dependent.
	 */
	TaskIterator createTaskIterator(Object query);

}
