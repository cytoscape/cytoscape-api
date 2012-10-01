/*
 Copyright (c) 2010, The Cytoscape Consortium (www.cytoscape.org)

 This library is free software; you can redistribute it and/or modify it
 under the terms of the GNU Lesser General Public License as published
 by the Free Software Foundation; either version 2.1 of the License, or
 any later version.

 This library is distributed in the hope that it will be useful, but
 WITHOUT ANY WARRANTY, WITHOUT EVEN THE IMPLIED WARRANTY OF
 MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.  The software and
 documentation provided hereunder is on an "as is" basis, and the
 Institute for Systems Biology and the Whitehead Institute
 have no obligations to provide maintenance, support,
 updates, enhancements or modifications.  In no event shall the
 Institute for Systems Biology and the Whitehead Institute
 be liable to any party for direct, indirect, special,
 incidental or consequential damages, including lost profits, arising
 out of the use of this software and its documentation, even if the
 Institute for Systems Biology and the Whitehead Institute
 have been advised of the possibility of such damage.  See
 the GNU Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation,
 Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
*/
package org.cytoscape.work.util;


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
