package org.cytoscape.session.events;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.session.CyApplicationManager;
import org.cytoscape.view.presentation.RenderingEngine;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SetCurrentRenderingEngineEventTest {

	@Mock
	private RenderingEngine<CyNetwork> engine;
	@Mock
	private CyApplicationManager source;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGoodGetRenderingEngine() {
		final SetCurrentRenderingEngineEvent e = new SetCurrentRenderingEngineEvent(source, engine);
		assertNotNull(e.getRenderingEngine());
		assertEquals(engine, e.getRenderingEngine());
	}

	@Test
	public void testNullGetRenderingEngine() {
		final SetCurrentRenderingEngineEvent e = new SetCurrentRenderingEngineEvent(source, null);
		assertNull(e.getRenderingEngine());
	}
}
