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


public class BoundedFloatTest {
	private BoundedFloat bf;
	
	@Before
	public void init() {
		bf = new BoundedFloat(-1.0f, 0.0f, 1.0f, /* lowerStrict = */ true, /* upperStrict = */ false);
	}

	@Test
	public final void testLowerBound() throws Exception {
		assertEquals("Lower bound not as expected!", Float.valueOf(-1.0f), bf.getLowerBound());
	}

	@Test
	public final void testUpperBound() throws Exception {
		assertEquals("Upper bound not as expected!", Float.valueOf(1.0f), bf.getUpperBound());
	}

	@Test
	public final void testIsLowerBoundStrict() throws Exception {
		assertEquals("Strictness of lower bound not as expected!", true, bf.isLowerBoundStrict());
	}

	@Test
	public final void testIsUpperBoundStrict() throws Exception {
		assertEquals("Strictness of upper bound not as expected!", false, bf.isUpperBoundStrict());
	}

	@Test
	public final void testSettingValidValue() throws Exception {
		bf.setValue(0.3f);
		assertEquals("Value not as expected!", Float.valueOf(0.3f), bf.getValue());
	}

	@Test
	public final void testSettingValidValueWithString() throws Exception {
		bf.setValue("0.3");
		assertEquals("Value not as expected!", Float.valueOf(0.3f), bf.getValue());
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testSettingInvalidValueAtLowerBound() throws Exception {
		bf.setValue(-1.0f);
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testSettingInvalidValueBelowLowerBound() throws Exception {
		final BoundedFloat bf3 = new BoundedFloat(-1.0f, 0.0f, 1.0f, /* lowerStrict = */ false, /* upperStrict = */ true);
		bf3.setValue(-1.5f);
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testSettingInvalidValueAtUpperBound() throws Exception {
		final BoundedFloat bf2 = new BoundedFloat(-1.0f, 0.0f, 1.0f, /* lowerStrict = */ false, /* upperStrict = */ true);
		bf2.setValue(1.0f);
	}

	@Test
	public final void testSettingValueEqualToUpperBound() throws Exception {
		bf.setValue(1.0f);
		assertEquals("Value not as expected!", Float.valueOf(1.0f), bf.getValue());
		
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testConstructionWithLowerBoundBeingGreaterThanUpperBound() throws Exception {
		new BoundedFloat(1.0f, 0.0f, -1.0f, /* lowerStrict = */ true, /* upperStrict = */ false);
	}
}
