package org.cytoscape.webservice;

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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.net.URI;

import org.cytoscape.io.webservice.WebServiceClient;
import org.cytoscape.work.TaskIterator;
import org.junit.Test;

public abstract class AbstractWebServiceClientTest {

	protected WebServiceClient client;
	protected URI locationUri;
	protected String displayName;
	protected String description;
	
	protected Object queryObject;
	
	@Test
	public void testGetServiceLocation() {
		final URI serviceLocation = client.getServiceLocation();
		assertNotNull(serviceLocation);
		assertEquals(locationUri, serviceLocation);
	}


	@Test
	public void testGetDisplayName() {
		final String displayName = client.getDisplayName();
		assertEquals(this.displayName, displayName);
	}

	@Test
	public void testGetDescription() {
		final String description = client.getDescription();
		assertEquals(this.description, description);
	}

	@Test
	public void testCreateTaskIterator() {
		final TaskIterator itr = client.createTaskIterator(queryObject);
		assertNotNull(itr);
		assertFalse(itr.getNumTasks() == 0);
	}

}
