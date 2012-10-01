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
import org.cytoscape.model.CyEdge;

import static org.mockito.Mockito.*;


public class AddedEdgeEventTest {
	AddedEdgesEvent event;
	Collection<CyEdge> edgeCollection;
	CyNetwork net;

	@Before
	public void setUp() {
		edgeCollection = new ArrayList<CyEdge>();
		edgeCollection.add( mock(CyEdge.class));
		edgeCollection.add( mock(CyEdge.class));

		net = mock(CyNetwork.class);
		event = new AddedEdgesEvent(net,edgeCollection);
	}

	@Test
	public void testGetEdge() {
		for ( CyEdge e : event.getPayloadCollection())
			assertTrue( edgeCollection.contains(e) );
	}

	@Test
	public void testGetSource() {
		assertEquals( event.getSource(), net );
	}

	@Test
	public void testGetListenerClass() {
		assertEquals( event.getListenerClass(), AddedEdgesListener.class );
	}

	@Test
	public void testNullEdge() {
		try {
			AddedEdgesEvent ev = new AddedEdgesEvent(net, null);
		} catch (NullPointerException npe) {
			return;
		}
		fail("didn't catch expected npe for edge");
	}

	@Test
	public void testNullNetwork() {
		try {
			AddedEdgesEvent ev = new AddedEdgesEvent(null, edgeCollection);
		} catch (NullPointerException npe) {
			return;
		}
		fail("didn't catch expected npe for network");
	}
}
