package org.cytoscape.view.vizmap;

/*
 * #%L
 * Cytoscape VizMap API (vizmap-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
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
