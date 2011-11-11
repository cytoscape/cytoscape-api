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


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Arrays;

import org.cytoscape.event.CyEvent;
import org.cytoscape.event.CyEventHelper;
import org.cytoscape.event.CyListener;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyTable;
import org.cytoscape.model.DummyCyNode;
import org.cytoscape.model.DummyCyEdge;

import static org.junit.Assert.*;
import org.junit.Test;


public abstract class AbstractCyRootNetworkTest {
	protected CyRootNetwork root;
	protected CyRootNetwork root2;

	@Test
	public void testInitialRootNetworkState() {
		System.out.println("---> testInitialRootNetworkState");
		List<CySubNetwork> subnets = root.getSubNetworkList();
		CySubNetwork base = root.getBaseNetwork();
		assertNotNull(subnets);
		assertEquals("num subnetworks",1,subnets.size());
		assertNotNull(base);
		assertEquals("base and root num nodes", root.getNodeCount(), base.getNodeCount());
		assertEquals("base and root num edges", root.getEdgeCount(), base.getNodeCount());
		assertTrue("subnetwork list contains base",root.getSubNetworkList().contains(base));
	}

	@Test
	public void testAddSubNetwork() {
		System.out.println("---> testAddSubNetwork");
		CyNode n1 = root.addNode();
		CyNode n2 = root.addNode();
		CyNode n3 = root.addNode();
		CyNode n4 = root.addNode();

		CyEdge e1 = root.addEdge(n1,n2,true);
		CyEdge e2 = root.addEdge(n2,n3,false);
		CyEdge e3 = root.addEdge(n2,n4,false);

		assertEquals("num nodes", 4, root.getNodeList().size());
		assertEquals("num edges", 3, root.getEdgeList().size());

		CySubNetwork s1 = root.addSubNetwork();
		s1.addNode(n1);
		s1.addNode(n2);
		s1.addEdge(e1);

		assertNotNull("subnetwork is not null",s1);
		assertEquals("num nodes", 4, root.getNodeList().size());
		assertEquals("base nodes", 2, s1.getNodeList().size());

		assertEquals("num edges", 3, root.getEdgeList().size());
		assertEquals("base edges", 1, s1.getEdgeList().size());
	}

	@Test
	public void testRemoveSubNetwork() {
		System.out.println("---> testRemoveSubNetwork");

		CyNode n1 = root.getBaseNetwork().addNode();
		CyNode n2 = root.getBaseNetwork().addNode();
		CyNode n3 = root.getBaseNetwork().addNode();

		CyEdge e1 = root.getBaseNetwork().addEdge(n1,n2,true);
		CyEdge e2 = root.getBaseNetwork().addEdge(n2,n3,false);

		CySubNetwork s1 = root.addSubNetwork();
		s1.addNode(n1);
		s1.addNode(n2);
		s1.addEdge(e1);

		assertNotNull("subnetwork is not null",s1);
		assertEquals("node list size",3,root.getNodeList().size());
		assertEquals("subnetwork node list size",2,s1.getNodeList().size());
		assertEquals("edge list size",2,root.getEdgeList().size());
		assertEquals("subnetwork edge list size",1,s1.getEdgeList().size());
		assertTrue("subnetwork list contains s1",root.getSubNetworkList().contains(s1));

		CySubNetwork s2 = root.addSubNetwork();
		s2.addNode(n3);
		s2.addNode(n2);
		s2.addEdge(e2);

		assertNotNull("subnetwork is not null",s2);
		assertEquals("node list size",3,root.getNodeList().size());
		assertEquals("subnetwork node list size",2,s2.getNodeList().size());
		assertEquals("edge list size",2,root.getEdgeList().size());
		assertEquals("subnetwork edge list size",1,s2.getEdgeList().size());
		assertEquals("node count",3,root.getNodeCount());
		assertEquals("edge count",2,root.getEdgeCount());

		assertEquals("num subnetworks",3,root.getSubNetworkList().size());

		root.removeSubNetwork(s1);

		assertEquals("node list size",3,root.getNodeCount());
		assertEquals("base node list size",2,root.getEdgeCount());

		assertTrue("subnetwork list contains s2",root.getSubNetworkList().contains(s2));
		assertEquals("num subnetworks",2,root.getSubNetworkList().size());

		root.removeSubNetwork(s2);

		assertEquals("node list size",3,root.getNodeCount());
		assertEquals("base node list size",2,root.getEdgeCount());

		assertFalse("subnetwork list contains s2",root.getSubNetworkList().contains(s2));
		assertEquals("num subnetworks",1,root.getSubNetworkList().size());
	}

	@Test
	public void testGetAllNodes() {
		System.out.println("---> testGetAllNodes");
		CyNode n1 = root.getBaseNetwork().addNode();
		CyNode n2 = root.getBaseNetwork().addNode();
		CyNode n3 = root.getBaseNetwork().addNode();

		assertEquals("node count",3,root.getNodeCount());
		assertEquals("base node count",3,root.getBaseNetwork().getNodeCount());

		CySubNetwork s1 = root.addSubNetwork();
		CyNode n4 = s1.addNode();
		CyNode n5 = s1.addNode();

		assertEquals("node count",5,root.getNodeCount());
		assertEquals("base node count",3,root.getBaseNetwork().getNodeCount());
		assertEquals("s1 node count",2,s1.getNodeCount());

		CySubNetwork s2 = root.addSubNetwork();
		CyNode n6 = s2.addNode();
		CyNode n7 = s2.addNode();
		CyNode n8 = s2.addNode();

		assertEquals("node count",8,root.getNodeCount());
		assertEquals("base node count",3,root.getBaseNetwork().getNodeCount());
		assertEquals("s1 node count",2,s1.getNodeCount());
		assertEquals("s2 node count",3,s2.getNodeCount());
	}

	@Test
	public void testGetAllEdges() {
		System.out.println("---> testGetAllEdges");
		CyNode n1 = root.getBaseNetwork().addNode();
		CyNode n2 = root.getBaseNetwork().addNode();
		CyNode n3 = root.getBaseNetwork().addNode();
		CyNode n4 = root.getBaseNetwork().addNode();

		CyEdge e1 = root.getBaseNetwork().addEdge(n1,n2,true);
		CyEdge e2 = root.getBaseNetwork().addEdge(n2,n3,false);
		CyEdge e3 = root.getBaseNetwork().addEdge(n2,n4,false);

		assertEquals("edge count",3,root.getEdgeCount());
		assertEquals("base edge count",3,root.getBaseNetwork().getEdgeCount());

		CySubNetwork s1 = root.addSubNetwork();
		s1.addNode(n1);
		s1.addNode(n4);
		CyEdge e4 = s1.addEdge(n1,n4,true);

		assertEquals("edge count",4,root.getEdgeCount());
		assertEquals("base edge count",3,root.getBaseNetwork().getEdgeCount());
		assertEquals("s1 edge count",1,s1.getEdgeCount());
	}

	@Test
	public void testJustRootAddNode() {
		CyNode n1 = root.addNode();

		assertEquals("root node count", 1, root.getNodeCount());

		CyNode n2 = root.getBaseNetwork().addNode();

		assertEquals("root node count", 2, root.getNodeCount());
		assertEquals("base node count", 1, root.getBaseNetwork().getNodeCount());
	}

	@Test
	public void testJustRootAddEdge() {
		CyNode n1 = root.addNode();
		CyNode n2 = root.addNode();

		CyNode n3 = root.getBaseNetwork().addNode();
		CyNode n4 = root.getBaseNetwork().addNode();

		CyEdge e1 = root.addEdge(n1,n2,true);

		assertEquals("root edge count", 1, root.getEdgeCount());

		CyEdge e2 = root.getBaseNetwork().addEdge(n3,n4,false);

		assertEquals("root edge count", 2, root.getEdgeCount());
		assertEquals("base edge count", 1, root.getBaseNetwork().getEdgeCount());
	}

	@Test
	public void testEdgesCantCrossRootAndSubNetwork() {
		CyNode n1 = root.addNode();
		CyNode n2 = root.addNode();

		CyNode n3 = root.getBaseNetwork().addNode();

		CyEdge e1 = root.addEdge(n1,n3,true);

		try {
			CyEdge e2 = root.getBaseNetwork().addEdge(n2,n3,false);
		} catch (IllegalArgumentException e) {
			return;
		}

		fail("edges can't cross subnetworks");
	}

	@Test
	public void testEdgesCantCrossSubNetworks() {
		CySubNetwork s1 = root.addSubNetwork();
		CyNode n1 = s1.addNode();

		CySubNetwork s2 = root.addSubNetwork();
		CyNode n2 = s2.addNode();

		try {
			CyEdge e1 = root.getBaseNetwork().addEdge(n2,n1,false);
		} catch (IllegalArgumentException e) {
			return;
		}

		fail("edges can't cross subnetworks");
	}

	@Test
	public void testAddNode() {
		System.out.println("---> testAddNode");
		CyNode n1 = root.getBaseNetwork().addNode();
		CyNode n2 = root.getBaseNetwork().addNode();
		CyNode n3 = root.getBaseNetwork().addNode();
		CyNode n4 = root.getBaseNetwork().addNode();

		CyEdge e1 = root.getBaseNetwork().addEdge(n1,n2,true);
		CyEdge e2 = root.getBaseNetwork().addEdge(n2,n3,false);
		CyEdge e3 = root.getBaseNetwork().addEdge(n2,n4,false);

		assertEquals("node list size",4,root.getNodeList().size());


		CySubNetwork s1 = root.addSubNetwork();
		s1.addNode(n1);
		s1.addNode(n2);
		s1.addEdge(e1);


		assertEquals("base node list size",4,root.getBaseNetwork().getNodeList().size());
		assertEquals("node list size",4,root.getNodeList().size());

		CyNode n5 = root.getBaseNetwork().addNode();


		assertEquals("node list size",5,root.getNodeList().size());
		assertEquals("base node list size",5,root.getBaseNetwork().getNodeList().size());

		CyNode n6 = root.getBaseNetwork().addNode();


		assertEquals("node list size",6,root.getNodeList().size());
		assertEquals("base node list size",6,root.getBaseNetwork().getNodeList().size());
		assertTrue("root does contain node5",root.containsNode(n5));

		boolean ret = root.removeNodes(Collections.singletonList(n5));

		assertTrue("removed node5", ret);
		assertFalse("root doesn't contain node5",root.containsNode(n5));

		assertEquals("node list size",5,root.getNodeList().size());
		assertEquals("base node list size",5,root.getBaseNetwork().getNodeList().size());

		ret = root.removeNodes(Collections.singletonList(n6));
		assertTrue("removed node6", ret);

		assertEquals("node list size",4,root.getNodeList().size());
		assertEquals("base node list size",4,root.getBaseNetwork().getNodeList().size());
	}

	@Test
	public void testRemoveBaseNetwork() {
		CySubNetwork base = root.getBaseNetwork();
		try {
			root.removeSubNetwork(base);
		} catch (IllegalArgumentException e) {
			return;
		}
		fail("can't remove the base network from a root network");
	}

	@Test
	public void testGetSubNetworkList() {
		System.out.println("---> testGetSubNetworkList");
		CyNode n1 = root.addNode();
		CyNode n2 = root.addNode();
		CyNode n3 = root.addNode();
		CyNode n4 = root.addNode();

		CyEdge e1 = root.addEdge(n1,n2,true);
		CyEdge e2 = root.addEdge(n2,n3,false);
		CyEdge e3 = root.addEdge(n2,n4,false);

		// list of size 1
		List<CySubNetwork> mnodes = root.getSubNetworkList();
		assertNotNull(mnodes);
		assertEquals("subnet node list size",1,mnodes.size());

		CySubNetwork s1 = root.addSubNetwork();

		// after creating a subnetwork

		List<CySubNetwork> mnodesX = root.getSubNetworkList();
		assertNotNull(mnodesX);
		assertEquals("subnet node list size",2,mnodesX.size());
		assertTrue("contains s1",mnodesX.contains(s1));

		CySubNetwork s2 = root.addSubNetwork();

		// list of size 3
		List<CySubNetwork> mnodes2 = root.getSubNetworkList();
		assertNotNull(mnodes2);
		assertEquals("subnet node list size",3,mnodes2.size());
		assertTrue("contains s1",mnodes2.contains(s1));
		assertTrue("contains s2",mnodes2.contains(s2));

		root.removeSubNetwork(s1);

		// list of size 2 after removing a subnetwork
		List<CySubNetwork> mnodes3 = root.getSubNetworkList();
		assertNotNull(mnodes3);
		assertEquals("subnet node list size",2,mnodes3.size());
		assertTrue("contains s2",mnodes3.contains(s2));
		assertFalse("not contains s1",mnodes3.contains(s1));
	}

	@Test
	public void testGetBaseNetwork() {
		System.out.println("---> testGetBaseNetwork");

		// should already contain a base network
		CySubNetwork base1 = root.getBaseNetwork();
		assertNotNull(base1);
		assertEquals("num nodes",0,base1.getNodeCount());
		assertEquals("num edges",0,base1.getEdgeCount());

		// now modify root
		CyNode n1 = base1.addNode();
		CyNode n2 = base1.addNode();
		CyNode n3 = base1.addNode();
		CyNode n4 = base1.addNode();

		CyEdge e1 = base1.addEdge(n1,n2,true);
		CyEdge e2 = base1.addEdge(n2,n3,false);
		CyEdge e3 = base1.addEdge(n2,n4,false);

		CySubNetwork base2 = root.getBaseNetwork();
		assertNotNull(base2);
		assertEquals("num nodes",4,base2.getNodeCount());
		assertEquals("num edges",3,base2.getEdgeCount());

		// now add a subnetwork
		CySubNetwork s1 = root.addSubNetwork();

		CySubNetwork base3 = root.getBaseNetwork();
		assertNotNull(base3);
		assertEquals("num nodes",4,base3.getNodeCount());
		assertEquals("num edges",3,base3.getEdgeCount());
	}

	@Test
	public void testAddSubNetworkNodes() {
		System.out.println("---> testAddSubNetworkNodes");
		CyNode n1 = root.addNode();
		CyNode n2 = root.addNode();
		CyNode n3 = root.addNode();
		CyNode n4 = root.addNode();
		CyNode n5 = root.addNode();

		CyEdge e1 = root.addEdge(n1,n2,true);
		CyEdge e2 = root.addEdge(n2,n3,false);
		CyEdge e3 = root.addEdge(n2,n4,false);
		CyEdge e4 = root.addEdge(n3,n4,false);
		CyEdge e5 = root.addEdge(n1,n4,false);

		CySubNetwork s1 = root.addSubNetwork();
		s1.addNode(n1);
		s1.addNode(n2);
		s1.addEdge(e1);

		assertNotNull(s1);
		assertEquals("s1 num nodes",2,s1.getNodeCount());
		assertEquals("s1 node list size",2,s1.getNodeList().size());
		assertTrue("s1 contains n1",s1.containsNode(n1));
		assertTrue("s1 contains n2",s1.containsNode(n2));
		assertFalse("s1 contains n3",s1.containsNode(n3));
		assertFalse("s1 contains n4",s1.containsNode(n4));
		assertFalse("s1 contains n5",s1.containsNode(n5));

		assertEquals("s1 num edges",1,s1.getEdgeCount());
		assertEquals("s1 edge list size",1,s1.getEdgeList().size());
		assertTrue("s1 contains e1",s1.containsEdge(e1));
		assertFalse("s1 contains e2",s1.containsEdge(e2));
		assertFalse("s1 contains e3",s1.containsEdge(e3));
		assertFalse("s1 contains e4",s1.containsEdge(e4));
		assertFalse("s1 contains e5",s1.containsEdge(e5));

		CySubNetwork s2 = root.addSubNetwork();
		s2.addNode(n2);
		s2.addNode(n3);
		s2.addNode(n4);
		s2.addNode(n5);
		s2.addEdge(e2);
		s2.addEdge(e3);
		s2.addEdge(e4);

		assertEquals("s2 num nodes",4,s2.getNodeCount());
		assertEquals("s2 node list size",4,s2.getNodeList().size());
		assertFalse("s2 contains n1",s2.containsNode(n1));
		assertTrue("s2 contains n2",s2.containsNode(n2));
		assertTrue("s2 contains n3",s2.containsNode(n3));
		assertTrue("s2 contains n4",s2.containsNode(n4));
		assertTrue("s2 contains n5",s2.containsNode(n5));

		assertEquals("s2 num edges",3,s2.getEdgeCount());
		assertEquals("s2 edge list size",3,s2.getEdgeList().size());
		assertFalse("s2 contains e1",s2.containsEdge(e1));
		assertTrue("s2 contains e2",s2.containsEdge(e2));
		assertTrue("s2 contains e3",s2.containsEdge(e3));
		assertTrue("s2 contains e4",s2.containsEdge(e4));
		assertFalse("s2 contains e5",s2.containsEdge(e5));
	}

	@Test
	public void testRemoveSubNetwork2() {
		System.out.println("---> testRemoveSubNetwork");
		CyNode n1 = root.addNode();
		CyNode n2 = root.addNode();
		CyNode n3 = root.addNode();
		CyNode n4 = root.addNode();
		CyNode n5 = root.addNode();

		CyEdge e1 = root.addEdge(n1,n2,true);
		CyEdge e2 = root.addEdge(n2,n3,false);
		CyEdge e3 = root.addEdge(n2,n4,false);
		CyEdge e4 = root.addEdge(n3,n4,false);
		CyEdge e5 = root.addEdge(n1,n4,false);

		CySubNetwork base = root.getBaseNetwork();

		CySubNetwork s1 = root.addSubNetwork();
		s1.addNode(n2);
		s1.addNode(n3);
		s1.addNode(n4);

		List<CySubNetwork> subs = root.getSubNetworkList();
		assertNotNull(subs);
		assertEquals("subnetwork list size",2,subs.size());
		assertTrue("subnetwork contains s1",subs.contains(s1));

		root.removeSubNetwork(s1);

		subs = root.getSubNetworkList();
		assertNotNull(subs);
		assertEquals("subnetwork list size",1,subs.size());
		assertFalse("subnetwork contains s1",subs.contains(s1));
	}

	@Test
	public void testRemoveBadSubNetwork() {
		CySubNetwork s1 = root.addSubNetwork();
		CySubNetwork s2 = root2.addSubNetwork();

		assertNotNull(s1);
		assertNotNull(s2);

		try {
			root.removeSubNetwork(s2);
		} catch (IllegalArgumentException e) {
			return;
		}

		fail("shouldn't be able to remove subnetwork that isn't yours!");
	}

	@Test
	public void testRemoveNullSubNetwork() {
		try {
			root.removeSubNetwork(null);
		} catch (IllegalArgumentException e) {
			fail("shouldn't throw exception for trying to remove null network");
		}
	}

	@Test
	public void testContainsNetwork() {
		CySubNetwork s1 = root.addSubNetwork();
		assertTrue(root.containsNetwork(s1));
	}

	@Test
	public void testNotContainsNetwork() {
		CySubNetwork s2 = root2.addSubNetwork();
		assertFalse(root.containsNetwork(s2));
	}

	@Test
	public void testNullContainsNetwork() {
		assertFalse(root.containsNetwork(null));
	}

	@Test
	public void testAddSubNetworkWithGoodNodesAndGoodEdges() {
		CyNode n1 = root.addNode();
		CyNode n2 = root.addNode();
		CyNode n3 = root.addNode();
		CyEdge e1 = root.addEdge(n1,n2,false);

		List<CyNode> nodes = Arrays.asList(n1, n2, n3 );
		List<CyEdge> edges = Arrays.asList( e1 );

		CySubNetwork n = root.addSubNetwork(nodes, edges);

		assertTrue( n.containsNode(n1) );
		assertTrue( n.containsNode(n2) );
		assertTrue( n.containsNode(n3) );
		assertTrue( n.containsEdge(e1) );
	}

	@Test
	public void testAddSubNetworkWithNullNodesAndGoodEdges() {
		CyNode n1 = root.addNode();
		CyNode n2 = root.addNode();
		CyEdge e1 = root.addEdge(n1,n2,false);

		List<CyEdge> edges = Arrays.asList( e1 );

		CySubNetwork n = root.addSubNetwork(null, edges);

		assertTrue(n.containsNode(n1) );
		assertTrue(n.containsNode(n2) );
		assertTrue(n.containsEdge(e1) );
	}

	@Test
	public void testAddSubNetworkWithGoodNodesAndNullEdges() {
		CyNode n1 = root.addNode();
		CyNode n2 = root.addNode();
		CyNode n3 = root.addNode();

		List<CyNode> nodes = Arrays.asList( n1, n2, n3 );

		CySubNetwork n = root.addSubNetwork(nodes, null);

		assertTrue( n.containsNode(n1) );
		assertTrue( n.containsNode(n2) );
		assertTrue( n.containsNode(n3) );
	}

	@Test
	public void testAddSubNetworkWithEmptyNodesAndGoodEdges() {
		CyNode n1 = root.addNode();
		CyNode n2 = root.addNode();
		CyEdge e1 = root.addEdge(n1,n2,false);

		List<CyNode> nodes = new ArrayList<CyNode>();
		List<CyEdge> edges = Arrays.asList( e1 );

		CySubNetwork n = root.addSubNetwork(nodes, edges);

		assertTrue( n.containsNode(n1) );
		assertTrue( n.containsNode(n2) );
		assertTrue( n.containsEdge(e1) );
	}

	@Test
	public void testAddSubNetworkWithGoodNodesAndEmptyEdges() {
		CyNode n1 = root.addNode();
		CyNode n2 = root.addNode();
		CyNode n3 = root.addNode();

		List<CyNode> nodes = Arrays.asList( n1, n2, n3 );
		List<CyEdge> edges = new ArrayList<CyEdge>();

		CySubNetwork n = root.addSubNetwork(nodes, edges);

		assertTrue( n.containsNode(n1) );
		assertTrue( n.containsNode(n2) );
		assertTrue( n.containsNode(n3) );
	}

	@Test(expected=IllegalArgumentException.class)
	public void testAddSubNetworkWithInvalidNodesAndGoodEdges() {
		CyNode n1 = root.addNode();
		CyNode n2 = root.addNode();
		CyEdge e1 = root.addEdge(n1,n2,false);

		CyNode b1 = new DummyCyNode(74);
		CyNode b2 = new DummyCyNode(75);
		List<CyNode> nodes = Arrays.asList( b1, b2 );
		List<CyEdge> edges = Arrays.asList( e1 );

		CySubNetwork n = root.addSubNetwork(nodes, edges);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testAddSubNetworkWithGoodNodesAndInvalidEdges() {
		CyNode n1 = root.addNode();
		CyNode n2 = root.addNode();
		CyNode n3 = root.addNode();

		CyEdge ex = new DummyCyEdge(null,null,false,50);
		List<CyNode> nodes = Arrays.asList( n1, n2, n3 );
		List<CyEdge> edges = Arrays.asList( ex );

		CySubNetwork n = root.addSubNetwork(nodes, edges);
	}

    @Test
    public void testGetSharedNetworkTable() {
		CyTable shared = root.getSharedNetworkTable();
        assertNotNull(shared);
		shared.createColumn("homer",String.class,false);
		CySubNetwork sub = root.addSubNetwork();
		assertNotNull( sub.getDefaultNetworkTable().getColumn("homer") );	
    }

    @Test
    public void testGetSharedNodeTable() {
		CyTable shared = root.getSharedNodeTable();
        assertNotNull(shared);
		shared.createColumn("homer",String.class,false);
		CySubNetwork sub = root.addSubNetwork();
		assertNotNull( sub.getDefaultNodeTable().getColumn("homer") );	
    }

    @Test
    public void testGetSharedEdgeTable() {
		CyTable shared = root.getSharedEdgeTable();
        assertNotNull(shared);
		shared.createColumn("homer",String.class,false);
		CySubNetwork sub = root.addSubNetwork();
		assertNotNull( sub.getDefaultEdgeTable().getColumn("homer") );	
    }
}
