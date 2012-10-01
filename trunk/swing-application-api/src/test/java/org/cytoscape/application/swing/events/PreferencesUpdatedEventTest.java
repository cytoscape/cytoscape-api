package org.cytoscape.application.swing.events;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PreferencesUpdatedEventTest {

	private PreferencesUpdatedEvent event;

	@Mock
	private Object source;
	@Mock
	private Properties oldProps;
	@Mock
	private Properties newProps;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		event = new PreferencesUpdatedEvent(source, oldProps, newProps);
	}

	@Test
	public void testPreferencesUpdatedEvent() {
		assertNotNull(event);
	}

	@Test
	public void testGetOldProperties() {
		assertEquals(oldProps, event.getOldProperties());
	}

	@Test
	public void testGetNewProperties() {
		assertEquals(newProps, event.getNewProperties());
	}
}
