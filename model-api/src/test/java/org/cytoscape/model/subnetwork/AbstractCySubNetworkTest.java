/*
 Copyright (c) 2008, 2011, The Cytoscape Consortium (www.cytoscape.org)

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
package org.cytoscape.model.subnetwork;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import org.cytoscape.event.CyEvent;
import org.cytoscape.event.CyEventHelper;
import org.cytoscape.event.CyListener;

import org.cytoscape.model.CyTable;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyRow;
import org.cytoscape.model.DummyCyEdge;

import java.lang.RuntimeException;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;


/**
 * DOCUMENT ME!
 */
public abstract class AbstractCySubNetworkTest {
	protected CyRootNetwork root;
	protected CyRootNetwork root2;

	private CyNode n1;
	private CyNode n2;
	private CyNode n3;
	private CyNode n4;
	private CyNode n5;

	private CyNode nx1;
	private CyNode nx2;

	private CyEdge e1;
	private CyEdge e2;
	private CyEdge e3;

	private CyEdge ex1;
	private CySubNetwork sub;

	private void defaultSetup() {
		n1 = root.addNode();
		n2 = root.addNode();
		n3 = root.addNode();
		n4 = root.addNode();
		n5 = root.addNode();

		e1 = root.addEdge(n1,n2,true);
		e2 = root.addEdge(n3,n2,true);
		e3 = root.addEdge(n1,n3,false);

		sub = root.addSubNetwork();
		sub.addNode(n1);
		sub.addNode(n2);
		sub.addEdge(e1);

		nx1 = root2.addNode();
		nx2 = root2.addNode();
	}

	@Test
	public void testAddNode() {
		defaultSetup();

		assertNotNull("subnetwork is not null",sub);
		assertEquals("num nodes",2,sub.getNodeCount());
		assertEquals("num edges",1,sub.getEdgeCount());
		assertTrue("contains node1",sub.containsNode(n1));
		assertTrue("contains node2",sub.containsNode(n2));
		assertTrue("contains edge1",sub.containsEdge(e1));
		assertTrue("contains edge1",sub.containsEdge(n1,n2));

		sub.addNode(n4);

		assertEquals("num nodes",3,sub.getNodeCount());
		assertEquals("num edges",1,sub.getEdgeCount());
		assertTrue("contains node1",sub.containsNode(n1));
		assertTrue("contains node2",sub.containsNode(n2));
		assertTrue("contains node4",sub.containsNode(n4));
		assertTrue("contains edge1",sub.containsEdge(e1));
		assertTrue("contains edge1",sub.containsEdge(n1,n2));

		sub.addNode(n3);

		assertEquals("num nodes",4,sub.getNodeCount());
		assertEquals("num edges",1,sub.getEdgeCount());
		assertTrue("contains node1",sub.containsNode(n1));
		assertTrue("contains node2",sub.containsNode(n2));
		assertTrue("contains node3",sub.containsNode(n3));
		assertTrue("contains node4",sub.containsNode(n4));
		assertTrue("contains edge1",sub.containsEdge(e1));
		assertFalse("contains edge2",sub.containsEdge(e2));
		assertFalse("contains edge3",sub.containsEdge(e3));

	}

	@Test
	public void testNullAddNode() {
		defaultSetup();

		try {
			sub.addNode(null);
		} catch (Exception e) {
			noChangeChecks();
			return;
		}

		// if we don't get an exception
		fail();
	}

	@Test
	public void testAlreadyAddedAddNode() {
		defaultSetup();

		assertFalse("node already exists", sub.addNode( n1 ) );
		noChangeChecks();
	}

	@Test
	public void testNodeFromDifferntRootAddNode() {
		defaultSetup();

		try {
			sub.addNode(nx1);
		} catch (Exception e) {
			noChangeChecks();
			return;
		}

		// if we don't get an exception
		fail();
	}

	@Test
	public void testNullEdgeAdd() {
		defaultSetup();

		try {
			sub.addEdge(null);
		} catch (Exception e) {
			noChangeChecks();
			return;
		}

		// if we don't get an exception
		fail();
	}

	@Test
	public void testAlreadyAddedAddEdge() {
		defaultSetup();

		assertFalse("edge already exists", sub.addEdge( e1 ) );
		noChangeChecks();
	}

	@Test
	public void testEdgeFromDifferntRootAddEdge() {
		defaultSetup();

		ex1 = root2.addEdge(nx1,nx2,true);

		try {
			sub.addEdge(ex1);
		} catch (Exception e) {
			noChangeChecks();
			return;
		}

		// if we don't get an exception
		fail();
	}

	private void noChangeChecks() {
		assertNotNull("subnetwork is not null",sub);
		assertEquals("num nodes",2,sub.getNodeCount());
		assertEquals("num edges",1,sub.getEdgeCount());
		assertTrue("contains node1",sub.containsNode(n1));
		assertTrue("contains node2",sub.containsNode(n2));
		assertTrue("contains edge1",sub.containsEdge(e1));
	}


	@Test
	public void testRemoveNode() {
		defaultSetup();

		CySubNetwork sub2 = root.addSubNetwork();
		sub2.addNode(n1);
		sub2.addNode(n2);
		sub2.addNode(n4);
		sub2.addEdge(e1);

		assertNotNull("subnetwork is not null",sub2);
		assertEquals("num nodes",3,sub2.getNodeCount());
		assertEquals("num edges",1,sub2.getEdgeCount());
		assertTrue("contains node1",sub2.containsNode(n1));
		assertTrue("contains node2",sub2.containsNode(n2));
		assertTrue("contains node4",sub2.containsNode(n4));
		assertTrue("contains edge1",sub2.containsEdge(e1));
		assertTrue("contains edge1",sub2.containsEdge(n1,n2));

		sub2.removeNodes(Collections.singletonList(n4));

		assertEquals("num nodes",2,sub2.getNodeCount());
		assertEquals("num edges",1,sub2.getEdgeCount());
		assertTrue("contains node1",sub2.containsNode(n1));
		assertTrue("contains node2",sub2.containsNode(n2));
		assertFalse("contains node4",sub2.containsNode(n4));
		assertTrue("contains edge1",sub2.containsEdge(e1));
		assertTrue("contains edge1",sub2.containsEdge(n1,n2));

		sub2.removeNodes(Collections.singletonList(n1));

		assertEquals("num nodes",1,sub2.getNodeCount());
		assertEquals("num edges",0,sub2.getEdgeCount());
		assertFalse("contains node1",sub2.containsNode(n1));
		assertTrue("contains node2",sub2.containsNode(n2));
		assertFalse("contains edge1",sub2.containsEdge(e1));
		assertFalse("contains edge1",sub2.containsEdge(n1,n2));
	}

	@Test
	public void testInvalidRemoveFromNetwork() {
		defaultSetup();

		checkInvalidRemove(sub,nx1);
		checkInvalidRemove(sub,null);
	}

	private void checkInvalidRemove(CySubNetwork s, CyNode n) {
		try {
			s.addNode(n);
		} catch (Exception e) {
			assertNotNull("subnetwork is not null",s);
			assertEquals("num nodes",2,s.getNodeCount());
			assertEquals("num edges",1,s.getEdgeCount());
			assertTrue("contains node1",s.containsNode(n1));
			assertTrue("contains node2",s.containsNode(n2));
			assertTrue("contains edge1",s.containsEdge(e1));

			return;
		}

		// if we don't get an exception
		fail();
	}

	@Test
	public void testGetRootNetwork() {
		defaultSetup();

		CyRootNetwork r2 = sub.getRootNetwork();
		assertNotNull("root is not null",r2);
		assertTrue("r2 equals root",r2.equals(root));
		assertEquals("node list size",r2.getNodeList().size(),root.getNodeList().size());
		assertEquals("edge list size",r2.getEdgeList().size(),root.getEdgeList().size());
	}

	@Test
	public void testAddEdgeNotYetAddedSource() {
		defaultSetup();

		assertTrue(sub.addEdge(e3));
	}

	@Test
	public void testAddEdgeNotYetAddedTarget() {
		defaultSetup();

		CyEdge e4 = root.addEdge(n4,n1,true);
		assertTrue(sub.addEdge(e4));
	}

	@Test
	public void testAddEdgeBadEdge() {
		defaultSetup();

		CyEdge ex = new DummyCyEdge(null,null,true,100);
		try {
			sub.addEdge(ex);
		} catch (IllegalArgumentException e) {
			noChangeChecks();
			return;
		}
	}

	@Test
	public void testAddNodeInSubNetwork() {
		defaultSetup();

		CyNode nz1 = sub.addNode();

		assertEquals("num nodes in subnetwork",3,sub.getNodeCount());
		assertEquals("num nodes in root network",6,root.getNodeCount());
	}

	/**
	 * Checks that getNeighbors works as expected for CySubNetwork
	 */
	@Test
	public void testNodeAddedInSubnetworkSubGetNeighbors() {
		defaultSetup();

		CyNode nz1 = sub.addNode();
		CyEdge ez1 = sub.addEdge(nz1,n1,true);

		List<CyNode> nl2 = sub.getNeighborList(n1,CyEdge.Type.ANY);
		assertEquals(2,nl2.size());

		List<CyNode> nl3 = root.getNeighborList(n1,CyEdge.Type.ANY);
		assertEquals(3,nl3.size());

		List<CyNode> nl4 = root.getNeighborList(nz1,CyEdge.Type.ANY);
		assertEquals(1,nl4.size());

		CySubNetwork sub2 = root.addSubNetwork();
		sub2.addNode(n1);
		sub2.addNode(n2);
		// no edges in sub2, so no neighbors!

		List<CyNode> nl = sub2.getNeighborList(n1, CyEdge.Type.ANY);
		assertEquals(0,nl.size());
	}

	// TODO
	// add tests that are similar to getNeighbors for getAdjacentEdges and getConnectingEdges
	//
}
