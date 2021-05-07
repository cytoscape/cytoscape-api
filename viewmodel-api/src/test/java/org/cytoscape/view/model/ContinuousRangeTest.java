package org.cytoscape.view.model;

/*
 * #%L
 * Cytoscape View Model API (viewmodel-api)
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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.cytoscape.view.model.ContinuousRange;
import org.junit.Test;


public class ContinuousRangeTest {
	
	@Test
	public void testContinuousNumberRange() {
		final ContinuousRange<Double> range1 = new ContinuousRange<Double>(Double.class, 0.0d, 100.0d, true, true);
		assertTrue(range1.inRange(3.0));
		assertTrue(range1.inRange(0.0));
		assertTrue(range1.inRange(100.0));
		assertFalse(range1.inRange(200.0));
		assertFalse(range1.inRange(100.001));
		assertFalse(range1.inRange(-1.2));
		
		final ContinuousRange<Double> range2 = new ContinuousRange<Double>(Double.class, 0.0d, 100.0d, false, false);
		assertFalse(range2.inRange(0.0));
		assertFalse(range2.inRange(100.0));
		
		final ContinuousRange<Double> range3 = new ContinuousRange<Double>(Double.class, 0.0d, 100.0d, false, true);
		assertFalse(range3.inRange(0.0));
		assertTrue(range3.inRange(100.0));
		
		final ContinuousRange<Double> range4 = new ContinuousRange<Double>(Double.class, 0.0d, 100.0d, true, false);
		assertTrue(range4.inRange(0.0));
		assertFalse(range4.inRange(100.0));
		
		final ContinuousRange<Color> colorRange = new ContinuousRange<Color>(Color.class, Color.BLACK, Color.yellow, false, false);
		// Need to define its own validator
		assertTrue(colorRange.inRange(new Color(10, 100, 10)));
	}

}
