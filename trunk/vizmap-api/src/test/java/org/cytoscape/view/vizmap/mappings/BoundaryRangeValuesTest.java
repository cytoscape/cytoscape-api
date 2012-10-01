package org.cytoscape.view.vizmap.mappings;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class BoundaryRangeValuesTest {

	private BoundaryRangeValues<?> brv1;
	private BoundaryRangeValues<?> brv2;

	@Before
	public void setUp() throws Exception {
		this.brv1 = new BoundaryRangeValues<Double>(-10d, 0d, 10d);
		this.brv2 = new BoundaryRangeValues<Color>(Color.RED, Color.white, Color.GREEN);
	}

	@Test
	public void testBoundaryRangeValues() {
		assertNotNull(brv1);
		assertNotNull(brv2);
	}

	@Test
	public void testBoundaryRangeValuesCopyConstructor() {
		final BoundaryRangeValues<Color> brvOriginal = new BoundaryRangeValues<Color>(Color.BLUE, Color.YELLOW,
				Color.PINK);
		final BoundaryRangeValues<Color> brvCopy = new BoundaryRangeValues<Color>(brvOriginal);

		assertNotNull(brvCopy);
		assertEquals(brvOriginal.lesserValue, brvCopy.lesserValue);
		assertEquals(brvOriginal.equalValue, brvCopy.equalValue);
		assertEquals(brvOriginal.greaterValue, brvCopy.greaterValue);
	}

	@Test
	public void testToString() {
		assertEquals("{-10.0,0.0,10.0}", brv1.toString());
		assertEquals("{" + Color.red.toString() + "," + Color.WHITE.toString() + "," + Color.GREEN.toString() + "}",
				brv2.toString());
	}

}
