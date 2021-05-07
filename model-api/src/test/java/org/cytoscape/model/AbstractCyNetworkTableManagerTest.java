package org.cytoscape.model;

/*
 * #%L
 * Cytoscape Model API (model-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2021 The Cytoscape Consortium
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
import static org.mockito.Mockito.mock;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for Network Table Manager.
 *
 */
public abstract class AbstractCyNetworkTableManagerTest {
	/**
	 * Must be supplied by implementer.
	 */
	protected CyNetworkTableManager mgr;

	/**
	 * Must be supplied by implementer.
	 */
	protected CyNetwork goodNetwork;

	private CyNetwork badNetwork;
	private CyTable table1;
	private CyTable table2;

	@Before
	public void setUp() {
		badNetwork = mock(CyNetwork.class);
		table1 = mock(CyTable.class);
		table2 = mock(CyTable.class);
	}

	@Test
	public void testGoodNetwork() {
		assertNotNull( mgr.getTables(goodNetwork, CyNetwork.class) );
		assertNotNull( mgr.getTables(goodNetwork, CyNode.class) );
		assertNotNull( mgr.getTables(goodNetwork, CyEdge.class) );
	}

	@Test
	public void testBadNetwork() {
		assertEquals( 0, mgr.getTables(badNetwork, CyNetwork.class).size() );
		assertEquals( 0, mgr.getTables(badNetwork, CyNode.class).size() );
		assertEquals( 0, mgr.getTables(badNetwork, CyEdge.class).size() );
	}

	@Test
	public void testNullNetwork() {
		try {
			mgr.getTables(null, CyNetwork.class);
			fail();
		} catch (IllegalArgumentException e) {
		}
		try {
			mgr.getTables(null, CyNode.class);
			fail();
		} catch (IllegalArgumentException e) {
		}
		try {
			mgr.getTables(null, CyEdge.class);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void testTableNetworkMapHasExpectedTables() throws Exception {
		checkTableMap( mgr.getTables(goodNetwork, CyNetwork.class) );
		checkTableMap( mgr.getTables(goodNetwork, CyNode.class) );
		checkTableMap( mgr.getTables(goodNetwork, CyEdge.class) );
	}

	private void checkTableMap(Map<String,CyTable> tableMap) {
		// we should have at least the two default, but others may exist
		assertTrue(tableMap.size() >= 2);
		assertTrue(tableMap.keySet().contains(CyNetwork.DEFAULT_ATTRS));
		assertTrue(tableMap.keySet().contains(CyNetwork.HIDDEN_ATTRS));
	}
	
	@Test
	public void testSetTable() throws Exception {
		String namespace = "foo";
		mgr.setTable(goodNetwork, CyNode.class, namespace, table1);
		CyTable other = mgr.getTable(goodNetwork, CyNode.class, namespace);
		assertSame(table1, other);
	}

	@Test
	public void testOverwriteTable() throws Exception {
		String namespace = "foo";
		mgr.setTable(goodNetwork, CyNode.class, namespace, table1);
		mgr.setTable(goodNetwork, CyNode.class, namespace, table2);
		CyTable other = mgr.getTable(goodNetwork, CyNode.class, namespace);
		assertSame(table2, other);
	}

	@Test
	public void testRemoveTable() throws Exception {
		String namespace = "foo";
		mgr.setTable(goodNetwork, CyNode.class, namespace, table1);
		mgr.removeTable(goodNetwork, CyNode.class, namespace);
		CyTable other = mgr.getTable(goodNetwork, CyNode.class, namespace);
		assertNull(other);
	}

	@Test
	public void testGetTables() throws Exception {
		String namespace = "foo";
		mgr.setTable(goodNetwork, CyNode.class, namespace, table1);
		Map<String, CyTable> tables = mgr.getTables(goodNetwork, CyNode.class);
		assertSame(table1, tables.get(namespace));
	}
	
	@Test
	public void testRemoveDefaultTables() throws Exception {
		for (Class<? extends CyIdentifiable> type : new Class[] { CyNetwork.class, CyNode.class, CyEdge.class }) {
			try {
				mgr.removeTable(goodNetwork, type, CyNetwork.DEFAULT_ATTRS);
				fail();
			} catch (IllegalArgumentException e) {
			}
		}
	}

	@Test
	public void testSetDefaultTables() throws Exception {
		for (Class<? extends CyIdentifiable> type : new Class[] { CyNetwork.class, CyNode.class, CyEdge.class }) {
			try {
				mgr.setTable(goodNetwork, type, CyNetwork.DEFAULT_ATTRS, table1);
				fail();
			} catch (IllegalArgumentException e) {
			}
		}
	}
}
