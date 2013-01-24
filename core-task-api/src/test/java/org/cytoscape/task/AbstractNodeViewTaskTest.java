package org.cytoscape.task;

/*
 * #%L
 * Cytoscape Core Task API (core-task-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2010 - 2013 The Cytoscape Consortium
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Test;

import org.cytoscape.view.model.View;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.model.CyNode;
import org.cytoscape.work.TaskMonitor;

public class AbstractNodeViewTaskTest {
	
	private class NodeViewTask extends AbstractNodeViewTask {
		NodeViewTask(View<CyNode> nv, CyNetworkView view) { super(nv, view); }
		public void run(TaskMonitor tm) { 
			assertNotNull(nodeView);
			assertNotNull(netView);
		}

		@Override
		public void cancel() {
		}
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullNodeView() throws Exception {
		new NodeViewTask(null,mock(CyNetworkView.class));
	}

	@Test(expected=NullPointerException.class)
	public void testNullNetworkView() throws Exception {
		new NodeViewTask((View<CyNode>)mock(View.class),null);
	}

	@Test
	public void testGoodNodeView() throws Exception {
		NodeViewTask rt = new NodeViewTask((View<CyNode>)mock(View.class), mock(CyNetworkView.class) );
		rt.run(mock(TaskMonitor.class));
	}
}
