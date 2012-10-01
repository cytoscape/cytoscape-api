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
