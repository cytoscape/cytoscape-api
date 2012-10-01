package org.cytoscape.view.model;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.junit.Test;
import static org.mockito.Mockito.*;
import java.util.List;
import java.util.ArrayList;

public abstract class AbstractCyNetworkViewTest {
	
	private static final int NODE_COUNT = 5;
	private static final int EDGE_COUNT = 8;
	
	protected CyNetwork network;
	protected CyNetworkView  view;
	
	protected  CyNode node1, node2, node3, node4, node5;
	protected  CyEdge edge1, edge2, edge3, edge4, edge5, edge6, edge7, edge8; 
	
	
	@Test
	public void testNetworkViewOnlyMethods() throws Exception {
		
		assertNotNull(view.getNodeViews());
		assertEquals(NODE_COUNT, view.getNodeViews().size());
		
		assertNotNull(view.getEdgeViews());
		assertEquals(EDGE_COUNT, view.getEdgeViews().size());
		
		assertNotNull(view.getAllViews());
		assertEquals(NODE_COUNT+EDGE_COUNT+1, view.getAllViews().size());
		
		assertNotNull(view.getNodeView(node1));
		assertNotNull(view.getNodeView(node2));
		assertNotNull(view.getNodeView(node3));
		assertNotNull(view.getNodeView(node4));
		assertNotNull(view.getNodeView(node5));
		
		assertNotNull(view.getEdgeView(edge1));
		assertNotNull(view.getEdgeView(edge2));
		assertNotNull(view.getEdgeView(edge3));
		assertNotNull(view.getEdgeView(edge4));
		assertNotNull(view.getEdgeView(edge5));
		assertNotNull(view.getEdgeView(edge6));
		assertNotNull(view.getEdgeView(edge7));
		assertNotNull(view.getEdgeView(edge8));
	}
	
	@Test
    public void testGetModel() {
		assertNotNull( view.getModel() );
		
		boolean modelTypeTest = false;
		if(view.getModel() instanceof CyNetwork)
			modelTypeTest = true;
		
		assertTrue(modelTypeTest);
	}
	
	
	/**
	 * Create a very small network for testing
	 */
	protected void buildNetwork() {
		
		network = mock(CyNetwork.class);

		node1 = mock(CyNode.class); 
		node2 = mock(CyNode.class); 
		node3 = mock(CyNode.class); 
		node4 = mock(CyNode.class); 
		node5 = mock(CyNode.class); 

		List<CyNode> nl = new ArrayList<CyNode>();
		nl.add(node1);
		nl.add(node2);
		nl.add(node3);
		nl.add(node4);
		nl.add(node5);

		when(network.getNodeList()).thenReturn(nl);

		edge1 = mock(CyEdge.class);
		edge2 = mock(CyEdge.class);
		edge3 = mock(CyEdge.class);
		edge4 = mock(CyEdge.class);
		edge5 = mock(CyEdge.class);
		edge6 = mock(CyEdge.class);
		edge7 = mock(CyEdge.class);
		edge8 = mock(CyEdge.class);

		List<CyEdge> el = new ArrayList<CyEdge>();
		el.add(edge1);
		el.add(edge2);
		el.add(edge3);
		el.add(edge4);
		el.add(edge5);
		el.add(edge6);
		el.add(edge7);
		el.add(edge8);

		when(network.getEdgeList()).thenReturn(el);
	}
}
