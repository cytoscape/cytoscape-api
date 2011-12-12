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
import org.junit.Test;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

import java.lang.RuntimeException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class AbstractCyTableEntryTest {
	protected CyNetwork net;

	@Test
	public void testGetSUID() {
		CyNode n1 = net.addNode();
		assertTrue("suid >= 0", n1.getSUID() >= 0);

		CyNode n2 = net.addNode();
		assertTrue("suid >= 0", n2.getSUID() >= 0);

		CyEdge e1 = net.addEdge(n1, n2, true);
		assertTrue("suid >= 0", e1.getSUID() >= 0);

		CyEdge e2 = net.addEdge(n1, n2, false);
		assertTrue("suid >= 0", e2.getSUID() >= 0);

		assertTrue("suid >= 0", net.getSUID() >= 0);
	}
}
