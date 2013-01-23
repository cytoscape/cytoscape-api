package org.cytoscape.webservice.events;

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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.cytoscape.io.webservice.WebServiceClient;
import org.cytoscape.io.webservice.events.SearchFinishedEvent;
import org.cytoscape.io.webservice.events.SearchFinishedListener;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SearchFinishedEventTest {
	
	private SearchFinishedEvent<?> event;

	@Mock private WebServiceClient source;
	@Mock private Object result;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		event = new SearchFinishedEvent<Object>(source, result);
	}

	@Test
	public void testSearchFinishedEvent() {
		assertNotNull(event);
		assertEquals(source, event.getSource());
		assertEquals(SearchFinishedListener.class, event.getListenerClass());
	}

	@Test
	public void testGetSearchResult() {
		assertEquals(result, event.getSearchResult());
	}
}
