package org.cytoscape.webservice.client;

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

import org.cytoscape.io.webservice.client.AbstractWebServiceClient;
import org.cytoscape.webservice.AbstractWebServiceClientTest;
import org.cytoscape.work.Task;
import org.cytoscape.work.TaskIterator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AbstractClientTest extends AbstractWebServiceClientTest {

	@Before
	public void setUp() throws Exception {
		final String uriString = "http://www.ebi.ac.uk/Tools/webservices/psicquic/registry/registry";
		this.locationUri = new URI(uriString);
		this.description = "dummy client";
		this.displayName = "dummy";
		this.queryObject = "test query";
		client = new DummyClient(uriString, displayName, description);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructor() throws Exception {
		client = new DummyClient("123()    69&&&6 *!", displayName, description);
	}
	
	@Test
	public void testToString() {
		assertEquals(this.displayName, client.toString());
	}
	
	private final class DummyClient extends AbstractWebServiceClient {

		private Task mockTask = mock(Task.class);
		
		public DummyClient(String uri, String displayName, String description) {
			super(uri, displayName, description);
		}

		@Override
		public TaskIterator createTaskIterator(Object query) {
			return new TaskIterator(mockTask);
		}
		
	}
}
