package org.cytoscape.model.events;

/*
 * #%L
 * Cytoscape Model API (model-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2010 - 2021 The Cytoscape Consortium
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


import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class SetNetworkPointerEventTest {
	private SetNetworkPointerEvent event;
	private CyNode node;
	private CyNetwork net;

	@Before
	public void init() {
		node = mock(CyNode.class); 
		net = mock(CyNetwork.class); 
		event = new SetNetworkPointerEvent(node, net);
	}

	@Test
	public void testGetNode() {
		assertEquals("getNode() failed!", event.getNode(), node);
	}

	@Test
	public void testGetNetwork() {
		assertEquals("getNode() failed!", event.getNetwork(), net);
	}

	@Test
	public void testGetSource() {
		assertEquals("getSource() failed!", event.getSource(), node);
	}

	@Test
	public void testGetListenerClass() {
		assertEquals("Invalid listener class!", event.getListenerClass(), SetNetworkPointerListener.class);
	}

	@Test
	public void testNullNode() {
		try {
			SetNetworkPointerEvent ev = new SetNetworkPointerEvent(null, net);
		} catch (NullPointerException npe) {
			return;
		}
		fail("didn't catch expected npe for node");
	}

	@Test
	public void testNullNetwork() {
		try {
			SetNetworkPointerEvent ev = new SetNetworkPointerEvent(node, null);
		} catch (NullPointerException npe) {
			return;
		}
		fail("didn't catch expected npe for network");
	}
}
