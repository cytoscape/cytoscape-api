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

import org.cytoscape.view.model.View;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.model.CyEdge;
import org.cytoscape.work.TaskMonitor;

public class AbstractEdgeViewTaskTest {
	
	private class EdgeViewTask extends AbstractEdgeViewTask {
		EdgeViewTask(View<CyEdge> nv, CyNetworkView view) { super(nv, view); }
		public void run(TaskMonitor tm) { 
			assertNotNull(edgeView);
			assertNotNull(netView);
		}

		@Override
		public void cancel() {
		}
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullEdgeView() throws Exception {
		new EdgeViewTask(null,mock(CyNetworkView.class));
	}

	@Test(expected=NullPointerException.class)
	public void testNullNetworkView() throws Exception {
		new EdgeViewTask((View<CyEdge>)mock(View.class),null);
	}

	@Test
	public void testGoodEdgeView() throws Exception {
		EdgeViewTask rt = new EdgeViewTask((View<CyEdge>)mock(View.class), mock(CyNetworkView.class) );
		rt.run(mock(TaskMonitor.class));
	}
}
