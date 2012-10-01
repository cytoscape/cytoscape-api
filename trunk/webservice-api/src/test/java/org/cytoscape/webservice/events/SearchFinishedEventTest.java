package org.cytoscape.webservice.events;

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
