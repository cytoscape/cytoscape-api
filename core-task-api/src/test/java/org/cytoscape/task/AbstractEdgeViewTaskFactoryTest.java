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

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import org.cytoscape.view.model.View;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.model.CyEdge;

import org.cytoscape.work.TaskIterator;

public class AbstractEdgeViewTaskFactoryTest {
	
	private class EdgeViewTaskFactory extends AbstractEdgeViewTaskFactory {
		public TaskIterator createTaskIterator() {
			return null;
		}
	}

	EdgeViewTaskFactory factory; 

	@Before
	public void setUp() {
		factory = new EdgeViewTaskFactory();
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullSetEdgeView() throws Exception {
		factory.setEdgeView(null,mock(CyNetworkView.class));
	}

	@Test(expected=NullPointerException.class)
	public void testNullSetNetworkView() throws Exception {
		factory.setEdgeView((View<CyEdge>)mock(View.class),null);
	}

	@Test
	public void testGoodSetEdgeView() throws Exception {
		factory.setEdgeView((View<CyEdge>)mock(View.class),mock(CyNetworkView.class));
		assertNotNull( factory.edgeView );
		assertNotNull( factory.netView );
	}

	@Test
	public void testEdgeViewNotFinal() throws Exception {
		factory.setEdgeView((View<CyEdge>)mock(View.class),mock(CyNetworkView.class));
		View<CyEdge> t1 = factory.edgeView;
		factory.setEdgeView((View<CyEdge>)mock(View.class),mock(CyNetworkView.class));
		View<CyEdge> t2 = factory.edgeView;
		assertFalse( (t1 == t2) );
	}

	@Test
	public void testNetworkViewNotFinal() throws Exception {
		factory.setEdgeView((View<CyEdge>)mock(View.class),mock(CyNetworkView.class));
		CyNetworkView t1 = factory.netView;
		factory.setEdgeView((View<CyEdge>)mock(View.class),mock(CyNetworkView.class));
		CyNetworkView t2 = factory.netView;
		assertFalse( (t1 == t2) );
	}
}
