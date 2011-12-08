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
package org.cytoscape.model;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import java.util.List;
import java.util.ArrayList;


public class CyTableUtilTest {
	CyNetwork net;

	CyRow row1;
	CyNode node1;
	CyEdge edge1;

	CyRow row2;
	CyNode node2;
	CyEdge edge2;

	final String columnName = "asdf";

	@Before
	public void setUp() {
		net = mock(CyNetwork.class);

		row1 = mock(CyRow.class);
		when(row1.get(columnName,Boolean.class)).thenReturn(true);

		node1 = mock(CyNode.class);
		when(net.getCyRow(node1)).thenReturn(row1);

		edge1 = mock(CyEdge.class);
		when(net.getCyRow(edge1)).thenReturn(row1);

		row2 = mock(CyRow.class);
		when(row2.get(columnName,Boolean.class)).thenReturn(false);

		node2 = mock(CyNode.class);
		when(net.getCyRow(node2)).thenReturn(row2);

		edge2 = mock(CyEdge.class);
		when(net.getCyRow(edge2)).thenReturn(row2);

		List<CyNode> nlist = new ArrayList<CyNode>();
		nlist.add(node1);
		nlist.add(node2);

		List<CyEdge> elist = new ArrayList<CyEdge>();
		elist.add(edge1);
		elist.add(edge2);

		when(net.getNodeList()).thenReturn(nlist);
		when(net.getEdgeList()).thenReturn(elist);
	}

	@Test
	public void testGetTrueNodes() {
		List<CyNode> nodes = CyTableUtil.getNodesInState(net,columnName,true);
		assertNotNull(nodes);
		assertTrue( nodes.contains(node1) );
		assertEquals( 1, nodes.size() );
	}

	@Test
	public void testGetFalseNodes() {
		List<CyNode> nodes = CyTableUtil.getNodesInState(net,columnName,false);
		assertNotNull(nodes);
		assertTrue( nodes.contains(node2) );
		assertEquals( 1, nodes.size() );
	}

	@Test
	public void testGetTrueEdges() {
		List<CyEdge> edges = CyTableUtil.getEdgesInState(net,columnName,true);
		assertNotNull(edges);
		assertTrue( edges.contains(edge1) );
		assertEquals( 1, edges.size() );
	}

	@Test
	public void testGetFalseEdges() {
		List<CyEdge> edges = CyTableUtil.getEdgesInState(net,columnName,false);
		assertNotNull(edges);
		assertTrue( edges.contains(edge2) );
		assertEquals( 1, edges.size() );
	}

	@Test
	public void testNullNetworkNodes() {
		try {
		List<CyNode> nodes = CyTableUtil.getNodesInState(null,columnName,false);
		} catch (NullPointerException npe) {
			return;
		}
		fail("didn't catch expected npe");
	}

	@Test
	public void testNullNetworkEdges() {
		try {
		List<CyEdge> nodes = CyTableUtil.getEdgesInState(null,columnName,false);
		} catch (NullPointerException npe) {
			return;
		}
		fail("didn't catch expected npe");
	}
}
