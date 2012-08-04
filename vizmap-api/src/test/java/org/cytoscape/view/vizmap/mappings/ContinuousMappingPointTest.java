package org.cytoscape.view.vizmap.mappings;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.Color;
import java.awt.Paint;

import org.cytoscape.event.CyEventHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ContinuousMappingPointTest {

	private ContinuousMappingPoint<Double, Paint> point;
	private BoundaryRangeValues<Paint> brv1;
	private BoundaryRangeValues<Paint> brv2;

	private Double val1 = 100d;
	private Double val2 = 0d;

	@Mock
	CyEventHelper eventHelper;

	@Mock
	ContinuousMapping<Double, Paint> continuousMapping;

	@Mock
	ContinuousMapping<String, Paint> continuousMapping2;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.brv1 = new BoundaryRangeValues<Paint>(Color.RED, Color.white, Color.GREEN);
		this.brv2 = new BoundaryRangeValues<Paint>(Color.BLACK, Color.YELLOW, Color.PINK);
		point = new ContinuousMappingPoint<Double, Paint>(val1, brv1, continuousMapping, eventHelper);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = IllegalArgumentException.class)
	public void testContinuousMappingPoint() {
		assertNotNull(point);
		// This throws exception
		final ContinuousMappingPoint<String, Paint> badPoint = new ContinuousMappingPoint<String, Paint>("Test", brv1,
				continuousMapping2, eventHelper);
	}

	@Test
	public void testGetValue() {
		final Double value = point.getValue();
		assertEquals(val1, value);
	}

	@Test
	public void testSetValue() {
		point.setValue(val2);
		assertEquals(val2, point.getValue());
	}

	@Test
	public void testGetRange() {
		assertEquals(brv1, point.getRange());
	}

	@Test
	public void testSetRange() {
		point.setRange(brv2);
		assertEquals(brv2, point.getRange());
	}

}
