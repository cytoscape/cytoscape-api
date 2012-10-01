package org.cytoscape.view.model.events;


import java.util.ArrayList;
import java.util.Collection;


import org.cytoscape.model.CyEdge;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class AddedEdgeViewsEventTest {
	
	Collection<View<CyEdge>> views;
	View<CyEdge> nodeView;
	View<CyEdge> nodeView2;
	CyNetworkView networkView;
	
	@Before
	public void setUp() throws Exception {
		views = new ArrayList<View<CyEdge>>();
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
		AddedEdgeViewsEvent ev1 = new AddedEdgeViewsEvent(networkView, views);
		for ( View<CyEdge> ev : ev1.getEdgeViews())
				assertTrue(views.contains(ev));
		assertEquals(ev1.getSource(),networkView);
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullPayload() {
		AddedEdgeViewsEvent ev1 = new AddedEdgeViewsEvent(networkView, null);
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullSource() {
		AddedEdgeViewsEvent ev1 = new AddedEdgeViewsEvent(null, views);
	}	

	@Test
	public void testEmptyPayload() {
		Collection<View<CyEdge>> vs = new ArrayList<View<CyEdge>>();
		AddedEdgeViewsEvent ev1 = new AddedEdgeViewsEvent(networkView, vs);
		assertEquals(0, ev1.getEdgeViews().size());
	}	
}

