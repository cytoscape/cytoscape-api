package org.cytoscape.view.model.events;


import java.util.ArrayList;
import java.util.Collection;


import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class AddedNodeViewsEventTest {
	
	Collection<View<CyNode>> views;
	View<CyNode> nodeView;
	View<CyNode> nodeView2;
	CyNetworkView networkView;
	
	@Before
	public void setUp() throws Exception {
		views = new ArrayList<View<CyNode>>();
		nodeView = mock(View.class);
		nodeView2 = mock(View.class);
		views.add(nodeView);
		views.add(nodeView2);
		networkView = mock(CyNetworkView.class);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testEvents() {
		AddedNodeViewsEvent ev1 = new AddedNodeViewsEvent(networkView, views);
		for ( View<CyNode> nv : ev1.getNodeViews())
			assertTrue(views.contains(nv));
		assertEquals(ev1.getSource(),networkView);
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullPayload() {
		AddedNodeViewsEvent ev1 = new AddedNodeViewsEvent(networkView, null);
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullSource() {
		AddedNodeViewsEvent ev1 = new AddedNodeViewsEvent(null, views);
	}	

	@Test
	public void testEmptyPayload() {
		Collection<View<CyNode>> vs = new ArrayList<View<CyNode>>();
		AddedNodeViewsEvent ev1 = new AddedNodeViewsEvent(networkView, vs);
		assertEquals(0,ev1.getNodeViews().size());
	}	
}
