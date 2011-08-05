/*
  Copyright (c) 2010, The Cytoscape Consortium (www.cytoscape.org)

  The Cytoscape Consortium is:
  - Institute for Systems Biology
  - University of California San Diego
  - Memorial Sloan-Kettering Cancer Center
  - Institut Pasteur
  - Agilent Technologies

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


package org.cytoscape.task;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import org.cytoscape.model.CyTable;
import org.cytoscape.work.TaskIterator;
import org.junit.Before;
import org.junit.Test;

public class AbstractTableTaskFactoryTest {
	
	private class TableTaskFactory extends AbstractTableTaskFactory {
		public TaskIterator getTaskIterator() {
			return null;
		}
	}

	TableTaskFactory factory; 

	@Before
	public void setUp() {
		factory = new TableTaskFactory();
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullSetDataTable() throws Exception {
		factory.setTable(null);
	}

	@Test
	public void testGoodSetDataTable() throws Exception {
		factory.setTable(mock(CyTable.class));
		assertNotNull( factory.table );
	}

	@Test
	public void testNotFinal() throws Exception {
		factory.setTable(mock(CyTable.class));
		CyTable t1 = factory.table;
		factory.setTable(mock(CyTable.class));
		CyTable t2 = factory.table;
		assertFalse( (t1 == t2) );
	}
}
