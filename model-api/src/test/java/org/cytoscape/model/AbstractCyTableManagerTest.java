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

		// At this point, one network is added to the manager.
		// Root Network has 3 private facade shared tables and 3 private shared tables, 
		// and Sub Network has 3 hidden private tables, 3 private local tables and 3 public facade tables.
		assertEquals(15, mgr.getAllTables(true).size());
		assertEquals(0, mgr.getAllTables(false).size());

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
