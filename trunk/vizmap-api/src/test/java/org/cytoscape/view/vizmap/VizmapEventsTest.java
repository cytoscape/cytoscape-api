package org.cytoscape.view.vizmap;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.vizmap.events.VisualStyleAboutToBeRemovedEvent;
import org.cytoscape.view.vizmap.events.VisualStyleAddedEvent;
import org.cytoscape.view.vizmap.events.VisualStyleSetEvent;
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
		assertEquals(style, event.getVisualStyleAdded());
	}
	
	@Test
	public void testVisualStyleDestroyedEvent() {
		final VisualStyleAboutToBeRemovedEvent event = new VisualStyleAboutToBeRemovedEvent(manager, style);
		assertEquals(style, event.getVisualStyleToBeRemoved());
	}
	
	@Test
	public void testVisualStyleSetEvent() {
		final CyNetworkView view = mock(CyNetworkView.class);
		final VisualStyleSetEvent event = new VisualStyleSetEvent(manager, style, view);
		assertEquals(style, event.getVisualStyle());
		assertEquals(view, event.getNetworkView());
	}

}
