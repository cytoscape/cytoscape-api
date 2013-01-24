package org.cytoscape.view.vizmap.mappings;

/*
 * #%L
 * Cytoscape VizMap API (vizmap-api)
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

import java.awt.Paint;

import org.cytoscape.event.CyEventHelper;
import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.model.CyRow;
import org.cytoscape.view.model.View;
import org.cytoscape.view.model.VisualProperty;
import org.cytoscape.view.presentation.property.BasicVisualLexicon;
import org.cytoscape.view.vizmap.VisualMappingFunction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AbstractVisualMappingFunctionTest {
	
	private String attrName;
	private Class<Double> attrType;	
	private VisualProperty<Paint> vp;
	
	private VisualMappingFunction<Double, Paint> function;
	
	@Mock
	CyEventHelper eventHelper;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		attrName = "test mapping function";
		attrType = Double.class;
		vp = BasicVisualLexicon.NODE_FILL_COLOR;
		function = new SimpleMappingFunction<Double, Paint>(attrName, attrType, vp, eventHelper);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAbstractVisualMappingFunction() {
		assertNotNull(function);
	}

	@Test
	public void testGetMappingColumnName() {
		assertEquals(attrName, function.getMappingColumnName());
	}

	@Test
	public void testGetMappingColumnType() {
		assertEquals(attrType, function.getMappingColumnType());
	}

	@Test
	public void testGetVisualProperty() {
		assertEquals(vp, function.getVisualProperty());
	}


	private static final class SimpleMappingFunction<K, V> extends AbstractVisualMappingFunction<K, V> {

		public SimpleMappingFunction(String attrName, Class<K> attrType, VisualProperty<V> vp, CyEventHelper eventHelper) {
			super(attrName, attrType, vp, eventHelper);
		}

		@Override
		public void apply(CyRow row, View<? extends CyIdentifiable> view) {}

		@Override
		public V getMappedValue(CyRow row) { return null; }
	}
}
