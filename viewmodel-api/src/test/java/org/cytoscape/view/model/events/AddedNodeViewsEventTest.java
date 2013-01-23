package org.cytoscape.view.model.events;

/*
 * #%L
 * Cytoscape View Model API (viewmodel-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
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
