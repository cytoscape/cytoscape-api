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

import static org.junit.Assert.*;

import java.awt.Paint;
import java.util.HashSet;
import java.util.Set;

import org.cytoscape.view.model.NullDataType;
import org.cytoscape.view.model.VisualLexicon;
import org.cytoscape.view.model.VisualProperty;
import org.cytoscape.view.presentation.property.BasicVisualLexicon;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class VisualPropertyDependencyTest {
	
	private VisualPropertyDependency<Paint> dependency;
	
	private String id = "TEST_DEPENDENCY";
	private String displayName = "test dependency";
	private Set<VisualProperty<Paint>> vpSet;

	@Mock private VisualProperty<NullDataType> rootVisualProperty;
	
	private VisualLexicon lexicon;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		lexicon = new BasicVisualLexicon(rootVisualProperty);
		vpSet = new HashSet<VisualProperty<Paint>>();
		vpSet.add(BasicVisualLexicon.NODE_BORDER_PAINT);
		vpSet.add(BasicVisualLexicon.NODE_FILL_COLOR);
		
		dependency = new VisualPropertyDependency<Paint>(id, displayName, vpSet, lexicon);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected=IllegalArgumentException.class)
	public void testVisualPropertyDependency() {
		assertNotNull(dependency);
		
		final Set<VisualProperty<Paint>> badSet = new HashSet<VisualProperty<Paint>>();
		badSet.add(BasicVisualLexicon.EDGE_LABEL_COLOR);
		badSet.add(BasicVisualLexicon.NODE_FILL_COLOR);
		
		// This throws exception
		final VisualPropertyDependency<Paint> badDependency = new VisualPropertyDependency<Paint>(id, displayName, badSet, lexicon);
	}

	@Test
	public void testGetDisplayName() {
		assertEquals(displayName, dependency.getDisplayName());
	}

	@Test
	public void testGetVisualProperties() {
		assertEquals(vpSet, dependency.getVisualProperties());
	}

	@Test
	public void testSetDependency() {
		dependency.setDependency(true);
		assertTrue(dependency.isDependencyEnabled());
	}

	@Test
	public void testIsDependencyEnabled() {
		assertFalse(dependency.isDependencyEnabled());
		dependency.setDependency(true);
		assertTrue(dependency.isDependencyEnabled());
	}

	@Test
	public void testGetParentVisualProperty() {
		VisualProperty<Paint> parent = dependency.getParentVisualProperty();
		assertNotNull(parent);
		assertEquals(BasicVisualLexicon.NODE_PAINT, parent);
	}

	@Test
	public void testToString() {
		assertEquals(displayName, dependency.toString());
	}
}
