package org.cytoscape.view.layout;

/*
 * #%L
 * Cytoscape Layout API (layout-api)
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
import static org.mockito.Mockito.*;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyRow;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class EdgeWeighterTest {

	private EdgeWeighter ew;

	@Before
	public void setUp() throws Exception {
		ew = new EdgeWeighter();
	}

	@Test
	public void testReset() {
		ew.reset();
	}

	@Test
	public void testSetWeightType() {
		ew.setWeightType(WeightTypes.WEIGHT);
		assertEquals(WeightTypes.WEIGHT, ew.type);
	}

	@Test
	public void testSetNormalizedBounds() {
		ew.setNormalizedBounds(0.2d, 0.7d);
	}

	@Test
	public void testSetWeight() {
		CyEdge edge = mock(CyEdge.class);
		CyRow row = mock(CyRow.class);
		LayoutEdge layoutEdge = new LayoutEdge(edge, row);
		ew.setWeight(layoutEdge);
	}

	@Test
	public void testNormalizeWeight() {
		CyEdge edge = mock(CyEdge.class);
		CyRow row = mock(CyRow.class);
		LayoutEdge layoutEdge = new LayoutEdge(edge, row);
		assertTrue(ew.normalizeWeight(layoutEdge));
	}

	@Test
	public void testSetMaxWeightCutoff() {
		ew.setMaxWeightCutoff(0.4d);
		assertTrue(ew.maxWeightCutoff == 0.4d);
	}

	@Test
	public void testSetMinWeightCutoff() {
		ew.setMinWeightCutoff(0.3d);
		assertTrue(ew.minWeightCutoff == 0.3d);
	}

}
