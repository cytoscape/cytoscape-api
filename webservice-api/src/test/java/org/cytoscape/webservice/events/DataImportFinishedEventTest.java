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
import org.cytoscape.io.webservice.events.DataImportFinishedEvent;
import org.cytoscape.io.webservice.events.DataImportFinishedListener;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class DataImportFinishedEventTest {
	
	private DataImportFinishedEvent<?> event;

	@Mock private WebServiceClient source;
	@Mock private Object importedData;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		event = new DataImportFinishedEvent<Object>(source, importedData);
	}

	@Test
	public void testDataImportFinishedEvent() {
		assertNotNull(event);
		assertEquals(source, event.getSource());
		assertEquals(DataImportFinishedListener.class, event.getListenerClass());
	}

	@Test
	public void testGetImportedObject() {
		assertEquals(importedData, event.getImportedObject());
	}

}
