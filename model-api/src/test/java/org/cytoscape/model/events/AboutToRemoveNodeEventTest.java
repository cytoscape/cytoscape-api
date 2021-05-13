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
import org.cytoscape.model.CyNode;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

/**
 * DOCUMENT ME!
 */
public class AboutToRemoveNodeEventTest  {

	AboutToRemoveNodesEvent event;
	Collection<CyNode> nodeCollection;
	CyNetwork net;

	@Before
	public void setUp() {
		nodeCollection = new ArrayList<CyNode>();
		nodeCollection.add( mock(CyNode.class));
		nodeCollection.add( mock(CyNode.class));

		net = mock(CyNetwork.class); 
		event = new AboutToRemoveNodesEvent(net,nodeCollection);
	}

	@Test
	public void testGetNode() {
		assertEquals( event.getNodes(), nodeCollection );
	}

	@Test
	public void testGetSource() {
		assertEquals( event.getSource(), net );
	}

	@Test
	public void testGetListenerClass() {
		assertEquals( event.getListenerClass(), AboutToRemoveNodesListener.class );
	}

	@Test(expected=NullPointerException.class)
	public void testNullNode() {
		AboutToRemoveNodesEvent ev = new AboutToRemoveNodesEvent(net, null);
	}

	@Test(expected=NullPointerException.class)
	public void testNullNetwork() {
		AboutToRemoveNodesEvent ev = new AboutToRemoveNodesEvent(null, nodeCollection);
	}
}
