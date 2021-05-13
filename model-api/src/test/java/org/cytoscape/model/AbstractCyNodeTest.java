package org.cytoscape.model;

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


import static org.junit.Assert.*;
import org.junit.Test;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

import java.lang.RuntimeException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;


/** Base class for CyNode tests. */
public abstract class AbstractCyNodeTest {
	protected CyNetwork net;

	@Test
	public void testGetIndex() {
		CyNode n1 = net.addNode();
		CyNode n2 = net.addNode();
		assertTrue("index >= 0", n1.getSUID() >= 0);
		assertTrue("index >= 0", n2.getSUID() >= 0);
	}

	@Test
	public void testDefaultAttributes() {
		CyNode n1 = net.addNode();
		assertEquals(String.class,
			     net.getRow(n1).getTable().getColumn("name").getType());
		assertEquals(Boolean.class,
			     net.getRow(n1).getTable().getColumn("selected").getType());
	}

	// by default a node should have a null nested network
	@Test
	public void testInitGetNestedNetwork() {
		CyNode n1 = net.addNode();
		assertNull(n1.getNetworkPointer());
	}

	@Test
	public void testSetNestedNetwork() {
		CyNode n1 = net.addNode();
		CyNetwork net2 = mock(CyNetwork.class);
		n1.setNetworkPointer( net2 );
		assertNotNull(n1.getNetworkPointer());
		assertEquals(net2, n1.getNetworkPointer());
	}

	// self nested networks are allowed
	@Test
	public void testSetSelfNestedNetwork() {
		CyNode n1 = net.addNode();
		n1.setNetworkPointer( net );
		assertNotNull(n1.getNetworkPointer());
		assertEquals(net, n1.getNetworkPointer());
	}

	// null nested networks are allowed
	@Test
	public void testSetNullNestedNetwork() {
		CyNode n1 = net.addNode();

		// put a real network here first
		CyNetwork net2 = mock(CyNetwork.class);
		n1.setNetworkPointer( net2 );
		assertNotNull(n1.getNetworkPointer());
		assertEquals(net2, n1.getNetworkPointer());

		// now put a null network to verify that we've "unset" things
		n1.setNetworkPointer( null );
		assertNull(n1.getNetworkPointer());
	}

	@Test
	public void testRemoveNodeIndexStaysConstant() {
		assertEquals(0,net.getNodeCount());
		CyNode n0 = net.addNode();
		CyNode n1 = net.addNode();
		long n0i = n0.getSUID();
		long n1i = n1.getSUID();
		net.removeNodes(Collections.singletonList(n0));
		long nnli = n1.getSUID();
		assertEquals(nnli,n1i);
	}

	@Test
	public void testRemoveNodeGetNodeFromIndex() {
		assertEquals(0,net.getNodeCount());
		CyNode n0 = net.addNode();
		CyNode n1 = net.addNode();
		long n0i = n0.getSUID();
		long n1i = n1.getSUID();
		assertEquals(net.getNode(n0i),n0);
		assertEquals(net.getNode(n1i),n1);
		assertTrue( net.removeNodes(Collections.singletonList(n0)) );
		assertNull(net.getNode(n0i));
		assertEquals(n1,net.getNode(n1i));
	}
}
