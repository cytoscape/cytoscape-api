package org.cytoscape.view.layout;

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
