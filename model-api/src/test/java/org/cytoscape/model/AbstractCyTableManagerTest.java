package org.cytoscape.model;

/*
 * #%L
 * Cytoscape Model API (model-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2010 - 2013 The Cytoscape Consortium
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Set;

import org.junit.Test;

public abstract class AbstractCyTableManagerTest {
	/**
	 * Must be supplied by implementer.
	 */
	protected CyTableManager mgr;

	/**
	 * Must be supplied by implementer.
	 */
	protected CyNetwork goodNetwork;
	protected CyTable globalTable;

	protected CyNetworkTableManager networkTableMgr;
	protected CyNetworkManager networkManager;

	@Test
	public void testReset() {
		mgr.addTable(goodNetwork.getDefaultNodeTable());
		assertFalse(mgr.getAllTables(true).isEmpty());
		mgr.reset();
		assertTrue(mgr.getAllTables(true).isEmpty());
	}

	@Test
	public void testAddTable() {
		final Set<CyTable> allTables = mgr.getAllTables(true);
		int numberOfTables = allTables.size();
		CyTable newTable = mock(CyTable.class);
		when(newTable.getSUID()).thenReturn(Long.valueOf(200000));
		mgr.addTable(newTable);
		assertEquals(newTable, mgr.getTable(newTable.getSUID()));

		assertEquals(numberOfTables + 1, mgr.getAllTables(true).size());
	}

	@Test
	public void testGetGlobalTables() {
		assertNotNull(mgr.getGlobalTables());

		mgr.addTable(globalTable);
		mgr.addTable(goodNetwork.getDefaultNodeTable());

		assertEquals(1, mgr.getGlobalTables().size());
		assertEquals(globalTable, mgr.getGlobalTables().iterator().next());
	}

	@Test
	public void testGetLocalTables() {
		Set<CyTable> nodeTables = mgr.getLocalTables(CyNode.class);
		Set<CyTable> edgeTables = mgr.getLocalTables(CyEdge.class);
		Set<CyTable> networkTables = mgr.getLocalTables(CyNetwork.class);

		assertNotNull(nodeTables);
		assertNotNull(edgeTables);
		assertNotNull(networkTables);

		assertEquals(12, nodeTables.size() + edgeTables.size() + networkTables.size());
		assertEquals(4, networkTables.size());
		assertEquals(4, nodeTables.size());
		assertEquals(4, edgeTables.size());

		assertTrue(networkTables.contains(goodNetwork.getDefaultNetworkTable()));
		final CyTable hidden = networkTableMgr.getTable(goodNetwork, CyNetwork.class, CyNetwork.HIDDEN_ATTRS);
		assertTrue(networkTables.contains(hidden));
	}
}
