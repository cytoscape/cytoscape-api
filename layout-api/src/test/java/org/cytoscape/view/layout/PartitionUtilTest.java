package org.cytoscape.view.layout;

/*
 * #%L
 * Cytoscape Layout API (layout-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2021 The Cytoscape Consortium
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

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.CyNetworkView;
import org.junit.Test;

public class PartitionUtilTest {


	@Test
	public void testPartition1() {
		
		CyNetworkView networkView = mock(CyNetworkView.class);
		CyNetwork network = mock(CyNetwork.class);
		when(networkView.getModel()).thenReturn(network);
		Collection<CyNode> nodes = new ArrayList<CyNode>();
		EdgeWeighter edgeWeighter = new EdgeWeighter();
		List<LayoutPartition> result = PartitionUtil.partition(networkView, nodes, edgeWeighter);
		
		assertNotNull(result);
		assertEquals(0,result.size());
	}

	@Test
	public void testPartition2() {
		CyNetworkView networkView = mock(CyNetworkView.class);
		CyNetwork network = mock(CyNetwork.class);
		when(networkView.getModel()).thenReturn(network);
		EdgeWeighter edgeWeighter = new EdgeWeighter();
		
		List<LayoutPartition> result = PartitionUtil.partition(networkView, false, edgeWeighter);
		
		assertNotNull(result);
		assertEquals(0,result.size());
	}

}
