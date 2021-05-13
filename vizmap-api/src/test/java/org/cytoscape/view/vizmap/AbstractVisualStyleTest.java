package org.cytoscape.view.vizmap;

/*
 * #%L
 * Cytoscape VizMap API (vizmap-api)
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


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.Color;
import java.awt.Paint;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyRow;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.cytoscape.view.model.VisualLexicon;
import org.cytoscape.view.presentation.property.BasicVisualLexicon;
import org.junit.Test;

public abstract class AbstractVisualStyleTest {
	
	protected static final String attrName = "Sample attr 1";
	
	protected static final Color RED1 = new Color(200, 0, 0);
	protected static final Color GREEN1 = new Color(0, 200, 0);
	protected static final Color RED2 = new Color(100, 0, 0);
	protected static final Color GREEN2 = new Color(0, 100, 0);
	
	protected VisualStyle style;
	
	protected String originalTitle;
	protected String newTitle;
	
	protected VisualLexicon lexicon;
	
	// Simple test network
	protected CyNode node1;
	protected CyNode node2;
	protected CyNode node3;
	protected CyEdge edge;
	protected CyNetwork network;
	protected CyNetworkView networkView;
	
	
	@Test
	public void testVisualStyle() {
		// Make sure all required o
		assertNotNull(style);
		assertNotNull(originalTitle);
		assertNotNull(newTitle);
		assertNotNull(network);
		assertNotNull(networkView);
		assertNotNull(networkView.getNodeView(node1));
		assertNotNull(networkView.getNodeView(node2));
		assertNotNull(networkView.getNodeView(node3));
		
		assertEquals("red", networkView.getModel().getDefaultNodeTable().getRow(node1.getSUID()).get(attrName, String.class));
		assertEquals("green", networkView.getModel().getDefaultNodeTable().getRow(node2.getSUID()).get(attrName, String.class));
		assertEquals("foo", networkView.getModel().getDefaultNodeTable().getRow(node3.getSUID()).get(attrName, String.class));

		// Test title
		assertEquals(originalTitle, style.getTitle());
		style.setTitle(newTitle);
		assertEquals(newTitle, style.getTitle());
		
		final Paint defaultNodeColor = BasicVisualLexicon.NODE_FILL_COLOR.getDefault();
		final Paint defaultNodePaint = BasicVisualLexicon.NODE_PAINT.getDefault();
		final View<CyNode> nodeView1 = networkView.getNodeView(node1);
		final View<CyNode> nodeView2 = networkView.getNodeView(node2);
		final View<CyNode> nodeView3 = networkView.getNodeView(node3);
		
		// Before apply call, all node views should have same color (Visual Property default).
		assertEquals(defaultNodeColor, nodeView1.getVisualProperty(BasicVisualLexicon.NODE_FILL_COLOR));
		assertEquals(defaultNodeColor, nodeView2.getVisualProperty(BasicVisualLexicon.NODE_FILL_COLOR));
		assertEquals(defaultNodeColor, nodeView3.getVisualProperty(BasicVisualLexicon.NODE_FILL_COLOR));
		assertEquals(defaultNodePaint, nodeView1.getVisualProperty(BasicVisualLexicon.NODE_PAINT));
		assertEquals(defaultNodePaint, nodeView2.getVisualProperty(BasicVisualLexicon.NODE_PAINT));
		assertEquals(defaultNodePaint, nodeView3.getVisualProperty(BasicVisualLexicon.NODE_PAINT));
		
		// Set some default values for the VS
		style.setDefaultValue(BasicVisualLexicon.NODE_FILL_COLOR, Color.PINK);
		style.setDefaultValue(BasicVisualLexicon.NODE_WIDTH, 60d);
		style.setDefaultValue(BasicVisualLexicon.NODE_BORDER_WIDTH, 7d);
		
		assertEquals(Color.PINK, style.getDefaultValue(BasicVisualLexicon.NODE_FILL_COLOR));
		assertEquals(Double.valueOf(60d), style.getDefaultValue(BasicVisualLexicon.NODE_WIDTH));
		assertEquals(Double.valueOf(7d), style.getDefaultValue(BasicVisualLexicon.NODE_BORDER_WIDTH));
		
		// Apply default values.
		style.apply(networkView);
		
		// Check defaults are correct
		assertEquals(Color.PINK, nodeView1.getVisualProperty(BasicVisualLexicon.NODE_FILL_COLOR));
		assertEquals(Double.valueOf(60d), nodeView1.getVisualProperty(BasicVisualLexicon.NODE_WIDTH));
		assertEquals(Double.valueOf(7d), nodeView1.getVisualProperty(BasicVisualLexicon.NODE_BORDER_WIDTH));
		
		// Check others
		assertEquals(Color.PINK, nodeView2.getVisualProperty(BasicVisualLexicon.NODE_FILL_COLOR));
		assertEquals(Color.PINK, nodeView3.getVisualProperty(BasicVisualLexicon.NODE_FILL_COLOR));

		// Apply to individual values
		style.setDefaultValue(BasicVisualLexicon.NODE_FILL_COLOR, Color.YELLOW);
		
		final CyRow row = networkView.getModel().getRow(nodeView3.getModel());
		
		// Apply only to node 3.
		style.apply(row, nodeView3);
		
		// Make sure only node 3 has different color.
		assertEquals(Color.PINK, nodeView1.getVisualProperty(BasicVisualLexicon.NODE_FILL_COLOR));
		assertEquals(Color.PINK, nodeView2.getVisualProperty(BasicVisualLexicon.NODE_FILL_COLOR));
		assertEquals(Color.YELLOW, nodeView3.getVisualProperty(BasicVisualLexicon.NODE_FILL_COLOR));
	}
	
	@Test
	public void testDependency() {
		// TODO add tests
	}
	
}
