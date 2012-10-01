package org.cytoscape.view.presentation;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collection;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.view.model.CyNetworkView;
import org.junit.Test;

public abstract class AbstractRenderingEngineManagerTest {
	
	protected RenderingEngineManager manager;

	@Test
	public void testRenderingEngineManagerImpl() {
		assertNotNull(manager);
	}

	@Test
	public void testGetRendringEngines() throws Exception {
		
		// First, create mock view models.
		final CyNetworkView networkView1 = mock(CyNetworkView.class);
		final CyNetworkView networkView2 = mock(CyNetworkView.class);
				
		final RenderingEngine<CyNetwork> engine1 = mock(RenderingEngine.class);
		when(engine1.getViewModel()).thenReturn(networkView1);
		final RenderingEngine<CyNetwork> engine2 = mock(RenderingEngine.class);
		when(engine2.getViewModel()).thenReturn(networkView1);
		final RenderingEngine<CyNetwork> engine3 = mock(RenderingEngine.class);
		when(engine3.getViewModel()).thenReturn(networkView2);
		
		manager.addRenderingEngine(engine1);
		manager.addRenderingEngine(engine2);
		manager.addRenderingEngine(engine3);
		
		final Collection<RenderingEngine<?>> allEngines = manager.getAllRenderingEngines();
		assertNotNull(allEngines);
		
		final Collection<RenderingEngine<?>> engines = manager.getRenderingEngines(networkView1);
		assertNotNull(engines);
		assertEquals(2, engines.size());
		
		when(engine1.getViewModel()).thenReturn(networkView1);
		manager.addRenderingEngine(engine1);

		assertTrue(manager.getRenderingEngines(networkView1).contains(engine1));
		
		// Remove from manager
		manager.removeRenderingEngine(engine1);
		manager.removeRenderingEngine(engine3);
		
		assertEquals(1, manager.getRenderingEngines(networkView1).size());
		assertEquals(0, manager.getRenderingEngines(networkView2).size());
		
		manager.removeRenderingEngine(engine2);
		assertEquals(0, manager.getRenderingEngines(networkView1).size());
	}
}
