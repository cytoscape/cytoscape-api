package org.cytoscape.work.util;

/*
 * #%L
 * Cytoscape Work API (work-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2010 - 2021 The Cytoscape Consortium
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
import org.junit.Before;
import org.junit.Test;


public class BoundedDoubleTest {
	private BoundedDouble bd;
	
	@Before
	public void init() {
		bd = new BoundedDouble(-1.0, 0.0, 1.0, /* lowerStrict = */ true, /* upperStrict = */ false);
	}

	@Test
	public final void testLowerBound() throws Exception {
		assertEquals("Lower bound not as expected!", Double.valueOf(-1.0), bd.getLowerBound());
	}

	@Test
	public final void testUpperBound() throws Exception {
		assertEquals("Upper bound not as expected!", Double.valueOf(1.0), bd.getUpperBound());
	}

	@Test
	public final void testIsLowerBoundStrict() throws Exception {
		assertEquals("Strictness of lower bound not as expected!", true, bd.isLowerBoundStrict());
	}

	@Test
	public final void testIsUpperBoundStrict() throws Exception {
		assertEquals("Strictness of upper bound not as expected!", false, bd.isUpperBoundStrict());
	}

	@Test
	public final void testSettingValidValue() throws Exception {
		bd.setValue(0.3);
		assertEquals("Value not as expected!", Double.valueOf(0.3), bd.getValue());
	}

	@Test
	public final void testSettingValidValueWithString() throws Exception {
		bd.setValue("0.3");
		assertEquals("Value not as expected!", Double.valueOf(0.3), bd.getValue());
	}

	@Test(expected=IllegalArgumentException.class)
		public final void testSettingInvalidValue() throws Exception {
		bd.setValue(-1.0);
	}

	@Test
	public final void testSettingValueEqualToUpperBound() throws Exception {
		bd.setValue(1.0);
		assertEquals("Value not as expected!", Double.valueOf(1.0), bd.getValue());
		
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testConstructionWithLowerBoundBeingGreaterThanUpperBound() throws Exception {
		new BoundedDouble(1.0, 0.0, -1.0, /* lowerStrict = */ true, /* upperStrict = */ false);
	}
}
