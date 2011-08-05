package org.cytoscape.view.presentation;


import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import javax.swing.JComponent;
import javax.swing.JPanel;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.view.presentation.events.RenderingEngineAddedEvent;
import org.cytoscape.view.presentation.events.RenderingEngineAboutToBeRemovedEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PresentationEventsTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testEvents() {
		
		final RenderingEngineManager manager = mock(RenderingEngineManager.class);
		final RenderingEngine<CyNetwork> engine = mock(RenderingEngine.class);
		final RenderingEngineAddedEvent createdEvent = new RenderingEngineAddedEvent(manager, engine);
		
		assertEquals(manager, createdEvent.getSource());
		assertEquals(engine, createdEvent.getRenderingEngine());
		
		final RenderingEngineAboutToBeRemovedEvent destroyedEvent = new RenderingEngineAboutToBeRemovedEvent(manager, engine);
		assertEquals(manager, destroyedEvent.getSource());
		assertEquals(engine, destroyedEvent.getRenderingEngine());
	}

}
