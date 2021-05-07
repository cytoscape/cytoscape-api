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
