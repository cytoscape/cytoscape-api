/*
 Copyright (c) 2010-2011, The Cytoscape Consortium (www.cytoscape.org)

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


import static org.mockito.Mockito.mock;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;


public abstract class AbstractCyTableManagerTest {
	/**
	 * Must be supplied by implementer.
	 */
	protected CyTableManager mgr;

	/**
	 * Must be supplied by implementer.
	 */
	protected CyNetwork goodNetwork;

	private CyNetwork badNetwork;

	@Before
	public void setUp() {
		badNetwork = mock(CyNetwork.class);
	}

	@Test
	public void testGoodNetwork() {
		assertNotNull( mgr.getTableMap(CyNetwork.class, goodNetwork) );
		assertNotNull( mgr.getTableMap(CyNode.class, goodNetwork) );
		assertNotNull( mgr.getTableMap(CyEdge.class, goodNetwork) );
	}

	@Test
	public void testBadNetwork() {
		assertNull( mgr.getTableMap(CyNetwork.class,badNetwork) );
		assertNull( mgr.getTableMap(CyNode.class,badNetwork) );
		assertNull( mgr.getTableMap(CyEdge.class,badNetwork) );
	}

	@Test
	public void testNullNetwork() {
		assertNull( mgr.getTableMap(CyNetwork.class,null) );
		assertNull( mgr.getTableMap(CyNode.class,null) );
		assertNull( mgr.getTableMap(CyEdge.class,null) );
	}

	public void testTableNetworkMapHasExpectedTables() throws Exception {
		checkTableMap( mgr.getTableMap(CyNetwork.class, goodNetwork) );
		checkTableMap( mgr.getTableMap(CyNode.class, goodNetwork) );
		checkTableMap( mgr.getTableMap(CyEdge.class, goodNetwork) );
	}

	private void checkTableMap(Map<String,CyTable> tableMap) {
		// we should have at least the two default, but others may exist
		assertTrue(tableMap.size() >= 2);
		assertTrue(tableMap.keySet().contains(CyNetwork.DEFAULT_ATTRS));
		assertTrue(tableMap.keySet().contains(CyNetwork.HIDDEN_ATTRS));
	}

	@Test
	public void testReset() {
		mgr.addTable(goodNetwork.getDefaultNodeTable());
		assertFalse(mgr.getAllTables(true).isEmpty());
		mgr.reset();
		assertTrue(mgr.getAllTables(true).isEmpty());
	}

	@Test
	public void testAddTable() {
		mgr.addTable(goodNetwork.getDefaultNodeTable());
		assertEquals(goodNetwork.getDefaultNodeTable(),
		             mgr.getTable(goodNetwork.getDefaultNodeTable().getSUID()));
	}
}
