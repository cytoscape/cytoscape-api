package org.cytoscape.webservice.events;

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
