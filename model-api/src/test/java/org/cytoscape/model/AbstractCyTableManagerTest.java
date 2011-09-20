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
import static org.junit.Assert.assertTrue;

import org.junit.Before;
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

	@Before
	public void setUp() {
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
