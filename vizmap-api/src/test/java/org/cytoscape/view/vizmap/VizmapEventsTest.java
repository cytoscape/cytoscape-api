package org.cytoscape.view.vizmap;


import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.cytoscape.view.vizmap.events.VisualStyleAddedEvent;
import org.cytoscape.view.vizmap.events.VisualStyleAboutToBeRemovedEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VizmapEventsTest {

	private VisualStyle style;
	private VisualMappingManager manager;
	
	@Before
	public void setUp() throws Exception {
		style = mock(VisualStyle.class);
		manager = mock(VisualMappingManager.class);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testVisualStyleCreatedEvent() {
		final VisualStyleAddedEvent event = new VisualStyleAddedEvent(manager, style);
		assertNotNull(event);
		assertEquals(style, event.getVisualStyleAdded());
	}
	
	@Test
	public void testVisualStyleDestroyedEvent() {
		final VisualStyleAboutToBeRemovedEvent event = new VisualStyleAboutToBeRemovedEvent(manager, style);
		assertNotNull(event);
		assertEquals(style, event.getVisualStyleToBeRemoved());
	}

}
