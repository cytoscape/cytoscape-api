package org.cytoscape.view.model;

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


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class VisualLexiconNodeTest {
	
	private VisualProperty<DummyObject> vp1;
	private VisualProperty<Integer> vp2;
	private VisualProperty<String> vp3;
	
	private VisualLexiconNode node1;
	private VisualLexiconNode node2;
	private VisualLexiconNode node3;
		

	@Before
	public void setUp() throws Exception {
		final Set<DummyObject> dummySet = new HashSet<DummyObject>();
		dummySet.add(new DummyObject());
		DiscreteRange<DummyObject> range = new DiscreteRange<DummyObject>(DummyObject.class, dummySet);
		vp1 = new DummyVisualProperty(new DummyObject(), "dummy", "Dummy Visual Property", range);
		vp2 = new IntegerVisualProperty();
		vp3 = new StringVisualProperty();
		node1 = new VisualLexiconNode(vp1, null);
		node2 = new VisualLexiconNode(vp2, node1);
		node3 = new VisualLexiconNode(vp3, node2);
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullVP() {
		VisualLexiconNode dummyNode = new VisualLexiconNode(null, null);
	}


	@Test
	public void testVisualLexiconNodeImpl() {
		assertNotNull(node1);
		assertNotNull(node2);
		assertNotNull(node3);
	}

	@Test
	public void testGetVisualProperty() {
		assertEquals(vp1, node1.getVisualProperty());
		assertEquals(vp2, node2.getVisualProperty());
		assertEquals(vp3, node3.getVisualProperty());
	}

	@Test
	public void testGetParent() {
		assertNull(node1.getParent());
		assertEquals(node1, node2.getParent());
		assertEquals(node2, node3.getParent());
	}

	@Test
	public void testGetChildren() {
		assertEquals(1, node1.getChildren().size());
		assertEquals(1, node2.getChildren().size());
		assertEquals(0, node3.getChildren().size());
	}

}