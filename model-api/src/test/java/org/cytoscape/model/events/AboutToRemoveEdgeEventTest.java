package org.cytoscape.model.events;

/*
 * #%L
 * Cytoscape Model API (model-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2021 The Cytoscape Consortium
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

import java.util.ArrayList;
import java.util.Collection;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyEdge;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

/**
 * DOCUMENT ME!
 */
public class AboutToRemoveEdgeEventTest {

	AboutToRemoveEdgesEvent event;
	Collection<CyEdge> edgeCollection;
	CyNetwork net;

	@Before
	public void setUp() {
		edgeCollection = new ArrayList<CyEdge>();
		edgeCollection.add( mock(CyEdge.class) );
		edgeCollection.add( mock(CyEdge.class) );
		
		net = mock(CyNetwork.class); 
		event = new AboutToRemoveEdgesEvent(net,edgeCollection);
	}

	@Test
	public void testGetEdge() {
		assertEquals( event.getEdges(), edgeCollection );
	}

	@Test
	public void testGetSource() {
		assertEquals( event.getSource(), net );
	}

	@Test
	public void testGetListenerClass() {
		assertEquals( event.getListenerClass(), AboutToRemoveEdgesListener.class );
	}

	@Test(expected=NullPointerException.class)
	public void testNullEdge() {
		AboutToRemoveEdgesEvent ev = new AboutToRemoveEdgesEvent(net, null);
	}

	@Test(expected=NullPointerException.class)
	public void testNullNetwork() {
		AboutToRemoveEdgesEvent ev = new AboutToRemoveEdgesEvent(null, edgeCollection);
	}
}
