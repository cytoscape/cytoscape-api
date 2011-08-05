/*
 Copyright (c) 2008, 2010, The Cytoscape Consortium (www.cytoscape.org)

 This library is free software; you can redistribute it and/or modify it
 under the terms of the GNU Lesser General Public License as published
 by the Free Software Foundation; either version 2.1 of the License, or
 any later version.

 This library is distributed in the hope that it will be useful, but
 WITHOUT ANY WARRANTY, WITHOUT EVEN THE IMPLIED WARRANTY OF
 MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.  The software and
 documentation provided hereunder is on an "as is" basis, and the
 Institute for Systems Biology and the Whitehead Institute
 have no obligations to provide maintenance, support,
 updates, enhancements or modifications.  In no event shall the
 Institute for Systems Biology and the Whitehead Institute
 be liable to any party for direct, indirect, special,
 incidental or consequential damages, including lost profits, arising
 out of the use of this software and its documentation, even if the
 Institute for Systems Biology and the Whitehead Institute
 have been advised of the possibility of such damage.  See
 the GNU Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation,
 Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
*/
package org.cytoscape.model;


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
		assertTrue("index >= 0", n1.getIndex() >= 0);
		assertTrue("index >= 0", n2.getIndex() >= 0);
	}

	@Test
	public void testDefaultAttributes() {
		CyNode n1 = net.addNode();
		assertEquals(String.class,
			     n1.getCyRow().getTable().getColumn("name").getType());
		assertEquals(Boolean.class,
			     n1.getCyRow().getTable().getColumn("selected").getType());
	}

	// by default a node should have a null nested network
	@Test
	public void testInitGetNestedNetwork() {
		CyNode n1 = net.addNode();
		assertNull(n1.getNetwork());
	}

	@Test
	public void testSetNestedNetwork() {
		CyNode n1 = net.addNode();
		CyNetwork net2 = mock(CyNetwork.class);
		n1.setNetwork( net2 );
		assertNotNull(n1.getNetwork());
		assertEquals(net2, n1.getNetwork());
	}

	// self nested networks are allowed
	@Test
	public void testSetSelfNestedNetwork() {
		CyNode n1 = net.addNode();
		n1.setNetwork( net );
		assertNotNull(n1.getNetwork());
		assertEquals(net, n1.getNetwork());
	}

	// null nested networks are allowed
	@Test
	public void testSetNullNestedNetwork() {
		CyNode n1 = net.addNode();

		// put a real network here first
		CyNetwork net2 = mock(CyNetwork.class);
		n1.setNetwork( net2 );
		assertNotNull(n1.getNetwork());
		assertEquals(net2, n1.getNetwork());

		// now put a null network to verify that we've "unset" things
		n1.setNetwork( null );
		assertNull(n1.getNetwork());
	}

	@Test
	public void testRemoveNodeIndexStaysConstant() {
		assertEquals(0,net.getNodeCount());
		CyNode n0 = net.addNode();
		CyNode n1 = net.addNode();
		int n0i = n0.getIndex();
		int n1i = n1.getIndex();
		assertEquals(0,n0i);
		assertEquals(1,n1i);
		net.removeNodes(Collections.singletonList(n0));
		assertEquals(1,n1i);
	}

	@Test
	public void testRemoveNodeGetNodeFromIndex() {
		assertEquals(0,net.getNodeCount());
		CyNode n0 = net.addNode();
		CyNode n1 = net.addNode();
		int n0i = n0.getIndex();
		int n1i = n1.getIndex();
		assertEquals(0,n0i);
		assertEquals(1,n1i);
		assertEquals(net.getNode(0),n0);
		assertEquals(net.getNode(1),n1);
		assertTrue( net.removeNodes(Collections.singletonList(n0)) );
		assertNull(net.getNode(0));
		assertEquals(n1,net.getNode(1));
	}
}
