/*
 Copyright (c) 2008, 2010-2011, The Cytoscape Consortium (www.cytoscape.org)

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
package org.cytoscape.group;


import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import org.cytoscape.group.CyGroup;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

import org.junit.Before;
import org.junit.Test;


public abstract class AbstractCyGroupTest {
	
	protected CyNetwork net;
	protected CyGroupFactory groupFactory;

	private CyGroup group;
	private CyEdge edge1;
	private CyEdge edge2;
	private CyEdge edge3;
	private CyEdge edge4;
	private CyEdge edge5;

	protected void defaultSetUp() {
		CyNode node1 = net.addNode();
    CyNode node2 = net.addNode();
    CyNode node3 = net.addNode();
    CyNode node4 = net.addNode();
    CyNode node5 = net.addNode();
    List<CyNode> groupNodes = new ArrayList<CyNode>();
    groupNodes.add(node1);
    groupNodes.add(node2);
    groupNodes.add(node3);

    edge1 = net.addEdge(node1, node2, false);
    edge2 = net.addEdge(node2, node3, false);
    edge3 = net.addEdge(node2, node4, false);
    edge4 = net.addEdge(node2, node5, false);
    edge5 = net.addEdge(node3, node5, false);

    List<CyEdge> groupEdges = new ArrayList<CyEdge>();
    groupEdges.add(edge1);
    groupEdges.add(edge2);
    groupEdges.add(edge3);
    groupEdges.add(edge4);

		// Create a group
		group = groupFactory.createGroup(net, groupNodes, groupEdges);
	}

	@Test
	public void testNetworkCreation() {
		assertTrue("net has 5 nodes", net.getNodeCount() == 5);
		assertTrue("net has 5 edges", net.getEdgeCount() == 5);
	}

	@Test
	public void testGroupCreated() {
    assertTrue("group node count = 3", group.getNodeList().size() == 3);
    assertTrue("group internal edge count = 2", group.getInteriorEdgeList().size() == 2);
    assertTrue("group external edge count = 2", group.getExteriorEdgeList().size() == 2);
	}

	@Test
	public void testAddExternalEdge() {
		group.addExternalEdge(edge5);
    assertTrue("group external edge count = 3", group.getExteriorEdgeList().size() == 3);
	}

	@Test
	public void testCollapse() {
    assertTrue("group is not collapsed", !group.isCollapsed(net));

		group.collapse(net);
    assertTrue("group is collapsed", group.isCollapsed(net));

		assertTrue("net has 3 nodes", net.getNodeCount() == 3);

		assertTrue("net has group node", net.containsNode(group.getGroupNode()));
	}

	@Test
	public void testExpand() {
		group.expand(net);
    assertTrue("group is expanded", !group.isCollapsed(net));

		assertTrue("net has 5 nodes", net.getNodeCount() == 5);

		assertTrue("net does not have group node", !net.containsNode(group.getGroupNode()));
	}

}
