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
import java.util.Set;


public abstract class AbstractCyTableUtilTest {
	protected CyNetwork net;

	CyRow row1;
	CyNode node1;
	CyEdge edge1;

	CyRow row2;
	CyNode node2;
	CyEdge edge2;

	final String columnName = "asdf";

	@Before
	public void internalSetUp() {
		net = createNetwork();
		node1 = net.addNode();
		node2 = net.addNode();
		edge1 = net.addEdge(node1, node2, true);
		edge2 = net.addEdge(node1, node2, true);
		
		net.getDefaultNodeTable().createColumn(columnName, Boolean.class, false);
		net.getDefaultEdgeTable().createColumn(columnName, Boolean.class, false);
		
		net.getRow(node1).set(columnName, true);
		net.getRow(node2).set(columnName, false);
		net.getRow(edge1).set(columnName, true);
		net.getRow(edge2).set(columnName, false);
	}

	protected abstract CyNetwork createNetwork();

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

	@Test(expected=NullPointerException.class)
	public void testNullNetworkNodes() {
		List<CyNode> nodes = CyTableUtil.getNodesInState(null,columnName,false);
	}

	@Test(expected=NullPointerException.class)
	public void testNullNetworkEdges() {
		List<CyEdge> nodes = CyTableUtil.getEdgesInState(null,columnName,false);
	}

	@Test
	public void testGetColumnNames() {
		CyTable t = mock(CyTable.class);

		CyColumn c1 = mock(CyColumn.class);
		when(c1.getName()).thenReturn("c1");

		CyColumn c2 = mock(CyColumn.class);
		when(c2.getName()).thenReturn("c2");

		List<CyColumn> cols = new ArrayList<CyColumn>();
		cols.add(c1);
		cols.add(c2);

		when(t.getColumns()).thenReturn(cols);

		Set<String> names = CyTableUtil.getColumnNames(t);

		assertNotNull(names);
		assertEquals(2,names.size());
		assertTrue(names.contains("c1"));
		assertTrue(names.contains("c2"));
	}

}
