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

import org.cytoscape.model.CyTable;

import static org.mockito.Mockito.*;


public class RowsSetEventTest {
	RowsSetEvent event;
	Collection<RowSetRecord> recordCollection;
	CyTable table;

	@Before
	public void setUp() {
		recordCollection = new ArrayList<RowSetRecord>();
		recordCollection.add( new RowSetRecord(null,null,null,null));
		recordCollection.add( new RowSetRecord(null,null,null,null));

		table = mock(CyTable.class);
		event = new RowsSetEvent(table,recordCollection);
	}

	@Test
	public void testGetNode() {
		for ( RowSetRecord n : event.getPayloadCollection() )
			assertTrue( recordCollection.contains(n));
	}

	@Test
	public void testGetSource() {
		assertEquals( event.getSource(), table );
	}

	@Test
	public void testGetListenerClass() {
		assertEquals( event.getListenerClass(), RowsSetListener.class );
	}

	@Test(expected=NullPointerException.class)
	public void testNullNode() {
		RowsSetEvent ev = new RowsSetEvent(table, null);
	}

	@Test(expected=NullPointerException.class)
	public void testNullTable() {
		RowsSetEvent ev = new RowsSetEvent(null, recordCollection);
	}
}
