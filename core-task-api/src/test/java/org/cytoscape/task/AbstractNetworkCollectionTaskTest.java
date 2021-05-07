package org.cytoscape.task;

/*
 * #%L
 * Cytoscape Core Task API (core-task-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2010 - 2021 The Cytoscape Consortium
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

import org.cytoscape.model.CyNetwork;
import org.cytoscape.work.TaskMonitor;

import java.util.Collection;


public class AbstractNetworkCollectionTaskTest {
	
	private class NetworkCollectionTask extends AbstractNetworkCollectionTask {
		NetworkCollectionTask(Collection<CyNetwork> nets) { super(nets); }
		public void run(TaskMonitor tm) { 
			assertNotNull(networks);
		}

		@Override
		public void cancel() {
		}
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullNetwork() throws Exception {
		new NetworkCollectionTask(null);
	}

	@Test
	public void testGoodNetworkCollection() throws Exception {
		NetworkCollectionTask rt = new NetworkCollectionTask( (Collection<CyNetwork>)mock(Collection.class) );
		rt.run(mock(TaskMonitor.class));
	}
}
