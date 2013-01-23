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
