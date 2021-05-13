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


public class BoundedLongTest {
	private BoundedLong bi;

	@Before
	public void init() {
		bi = new BoundedLong(-2L, 0L, 2L, /* lowerStrict = */ true, /* upperStrict = */ false);
	}

	@Test
	public final void testLowerBound() throws Exception {
		assertEquals("Lower bound not as expected!", Long.valueOf(-2L), bi.getLowerBound());
	}

	@Test
	public final void testUpperBound() throws Exception {
		assertEquals("Upper bound not as expected!", Long.valueOf(2L), bi.getUpperBound());
	}

	@Test
	public final void testIsLowerBoundStrict() throws Exception {
		assertEquals("Strictness of lower bound not as expected!", true, bi.isLowerBoundStrict());
	}

	@Test
	public final void testIsUpperBoundStrict() throws Exception {
		assertEquals("Strictness of upper bound not as expected!", false, bi.isUpperBoundStrict());
	}

	@Test
	public final void testSettingValidValue() throws Exception {
		bi.setValue(1L);
		assertEquals("Value not as expected!", Long.valueOf(1L), bi.getValue());
	}

	@Test
	public final void testSettingValidValueWithString() throws Exception {
		bi.setValue("1");
		assertEquals("Value not as expected!", Long.valueOf(1L), bi.getValue());
	}

	@Test(expected=IllegalArgumentException.class)
		public final void testSettingInvalidValue() throws Exception {
		bi.setValue(-2L);
	}

	@Test
	public final void testSettingValueEqualToUpperBound() throws Exception {
		bi.setValue(2L);
		assertEquals("Value not as expected!", Long.valueOf(2L), bi.getValue());

	}

	@Test(expected=IllegalArgumentException.class)
	public final void testConstructionWithLowerBoundBeingGreaterThanUpperBound() throws Exception {
		new BoundedLong(2L, 0L, -2L, /* lowerStrict = */ true, /* upperStrict = */ false);
	}
}
