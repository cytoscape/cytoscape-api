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
package org.cytoscape.model.events;


import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

import static org.mockito.Mockito.*;


public class AddedNodeEventTest {
	AddedNodesEvent event;
	Collection<CyNode> nodeCollection;
	CyNetwork net;

	@Before
	public void setUp() {
		nodeCollection = new ArrayList<CyNode>();
		nodeCollection.add( mock(CyNode.class));
		nodeCollection.add( mock(CyNode.class));

		net = mock(CyNetwork.class);
		event = new AddedNodesEvent(net,nodeCollection);
	}

	@Test
	public void testGetNode() {
		for ( CyNode n : event.getPayloadCollection() )
			assertTrue( nodeCollection.contains(n));
	}

	@Test
	public void testGetSource() {
		assertEquals( event.getSource(), net );
	}

	@Test
	public void testGetListenerClass() {
		assertEquals( event.getListenerClass(), AddedNodesListener.class );
	}

	@Test
	public void testNullNode() {
		try {
			AddedNodesEvent ev = new AddedNodesEvent(net, null);
		} catch (NullPointerException npe) {
			return;
		}
		fail("didn't catch expected npe for node");
	}

	@Test
	public void testNullNetwork() {
		try {
			AddedNodesEvent ev = new AddedNodesEvent(null, nodeCollection);
		} catch (NullPointerException npe) {
			return;
		}
		fail("didn't catch expected npe for network");
	}
}
