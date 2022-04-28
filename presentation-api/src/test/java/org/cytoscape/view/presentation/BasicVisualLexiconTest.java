package org.cytoscape.view.presentation;

/*
 * #%L
 * Cytoscape Presentation API (presentation-api)
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

import java.util.Collection;

import org.cytoscape.view.model.DiscreteRange;
import org.cytoscape.view.model.NullDataType;
import org.cytoscape.view.model.VisualLexiconNode;
import org.cytoscape.view.model.VisualProperty;
import org.cytoscape.view.presentation.property.AbstractVisualLexiconTest;
import org.cytoscape.view.presentation.property.BasicVisualLexicon;
import org.cytoscape.view.presentation.property.NullVisualProperty;
import org.cytoscape.view.presentation.property.values.NodeShape;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BasicVisualLexiconTest extends AbstractVisualLexiconTest {

	private BasicVisualLexicon richLex;
	private VisualProperty<NullDataType> richRoot;

	// Fake VPs
	@Mock
	private VisualProperty<Double> numberVP;
	@Mock
	private VisualProperty<String> textVP;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		// Create root node.
		richRoot = new NullVisualProperty("RICH_ROOT", "Rich Root Visual Property");
		richLex = new BasicVisualLexicon(richRoot);
	}

	@Test
	public void testLexiconInstances() {
		assertNotNull(richLex);
	}

	@Test
	public void test2DLexicon() throws Exception {
		assertEquals(78, richLex.getAllVisualProperties().size());
	}

	@Test
	public void testTree() throws Exception {
		testTree(richLex);
	}

	@Test
	public void testGetAllDecendents() throws Exception {
		final VisualProperty<NullDataType> root = richLex.getRootVisualProperty();
		Collection<VisualProperty<?>> allChildren = richLex.getAllDescendants(root);

		assertEquals(richLex.getAllVisualProperties().size() - 1, allChildren.size());

		Collection<VisualLexiconNode> nodeTextChild = richLex.getVisualLexiconNode(BasicVisualLexicon.NODE_LABEL).getChildren();
		assertEquals(0, nodeTextChild.size());

		Collection<VisualLexiconNode> nodePaintChild = richLex.getVisualLexiconNode(BasicVisualLexicon.NODE_PAINT).getChildren();
		assertEquals(4, nodePaintChild.size());
		assertEquals(richLex.getAllDescendants(BasicVisualLexicon.NODE_PAINT).size(), nodePaintChild.size());

		Collection<VisualProperty<?>> nodeChildren = richLex.getAllDescendants(BasicVisualLexicon.NODE);
		assertEquals(31, nodeChildren.size());

		Collection<VisualProperty<?>> edgeChildren = richLex.getAllDescendants(BasicVisualLexicon.EDGE);
		assertEquals(33, edgeChildren.size());

		Collection<VisualProperty<?>> leaf = richLex.getAllDescendants(BasicVisualLexicon.EDGE_LABEL_COLOR);
		assertEquals(0, leaf.size());
	}

	@Test
	public void testIsSupported() throws Exception {
		assertFalse(richLex.isSupported(this.numberVP));
		assertTrue(richLex.isSupported(BasicVisualLexicon.EDGE_PAINT));
		assertTrue(richLex.isSupported(BasicVisualLexicon.NODE_SIZE));
		assertTrue(richLex.isSupported(BasicVisualLexicon.NETWORK_CENTER_Y_LOCATION));
		assertTrue(richLex.isSupported(BasicVisualLexicon.EDGE_LINE_TYPE));
		assertFalse(richLex.isSupported(this.textVP));
	}
	
	@Test
	public void testGetSupportedValueRange() {
		DiscreteRange<NodeShape> range = (DiscreteRange<NodeShape>) BasicVisualLexicon.NODE_SHAPE.getRange();
		assertArrayEquals(range.values().toArray(), richLex.getSupportedValueRange(BasicVisualLexicon.NODE_SHAPE).toArray());
	}
}
