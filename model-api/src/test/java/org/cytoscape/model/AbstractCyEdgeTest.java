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
package org.cytoscape.model;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public abstract class AbstractCyEdgeTest {
	
	protected CyNetwork net;

	private CyEdge eDir;
	private CyEdge eUndir;
	private CyNode n1;
	private CyNode n2;
	private CyNode n3;

	protected void defaultSetUp() {
		n1 = net.addNode();
		n2 = net.addNode();
		n3 = net.addNode();

		// TODO should we be instantiating these objects independent of CyNetwork?
		eDir = net.addEdge(n1, n2, true);
		eUndir = net.addEdge(n2, n3, false);
	}

	@Test
	public void testIsDirected() {
		assertTrue("eDir is directed", eDir.isDirected());
		assertFalse("eUndir is undirected", eUndir.isDirected());
	}

	@Test
	public void testGetIndex() {
		assertTrue("edge index >= 0", eDir.getIndex() >= 0);
		assertTrue("edge index >= 0", eUndir.getIndex() >= 0);
	}

	@Test
	public void testGetSource() {
		assertNotNull("source exists", eDir.getSource());
		assertTrue("source is a CyNode", eDir.getSource() instanceof CyNode);

		assertNotNull("source exists", eUndir.getSource());
		assertTrue("source is a CyNode", eUndir.getSource() instanceof CyNode);

		assertTrue("source for eDir", eDir.getSource() == n1);

		// TODO what should the policy be here?
		// Which node should be returned? Is either legal?
		assertTrue("source for eUndir", ((eUndir.getSource() == n3) || (eUndir.getSource() == n2)));
	}

	@Test
	public void testGetTarget() {
		assertNotNull("target exists", eDir.getTarget());
		assertTrue("target is a CyNode", eDir.getTarget() instanceof CyNode);

		assertNotNull("target exists", eUndir.getTarget());
		assertTrue("target is a CyNode", eUndir.getTarget() instanceof CyNode);

		assertTrue("target for eDir", eDir.getTarget() == n2);

		// TODO what should the policy be here?
		// Which node should be returned? Is either legal?
		assertTrue("target for eUndir", ((eUndir.getTarget() == n3) || (eUndir.getTarget() == n2)));
	}

	@Test
	public void testToString() {
		assertNotNull("string is not null", eDir.toString());
		assertNotNull("string is not null", eUndir.toString());
		assertTrue("string has non zero length", eDir.toString().length() > 0);
		assertTrue("string has non zero length", eUndir.toString().length() > 0);
	}

	@Test
	public void testDefaultAttributes() {
		CyNode n1 = net.addNode();
		CyNode n2 = net.addNode();
		CyEdge e1 = net.addEdge(n1,n2,true);

		assertEquals(String.class, e1.getCyRow().getTable().getColumn(CyTableEntry.NAME).getType());
		assertEquals(Boolean.class,
			     e1.getCyRow().getTable().getColumn(CyNetwork.SELECTED).getType());
		assertEquals(String.class,
			     e1.getCyRow().getTable().getColumn(CyEdge.INTERACTION).getType());
	}
}
