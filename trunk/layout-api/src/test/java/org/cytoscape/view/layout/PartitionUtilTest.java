package org.cytoscape.view.layout;

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
