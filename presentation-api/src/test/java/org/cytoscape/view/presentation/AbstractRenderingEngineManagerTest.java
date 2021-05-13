package org.cytoscape.view.presentation;

/*
 * #%L
 * Cytoscape Presentation API (presentation-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2021 The Cytoscape Consortium
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
