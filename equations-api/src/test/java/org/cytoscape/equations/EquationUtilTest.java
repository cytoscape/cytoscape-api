package org.cytoscape.equations;

/*
 * #%L
 * Cytoscape Equations API (equations-api)
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

import static org.junit.Assert.*;
import org.junit.Test;


public class EquationUtilTest {
	@Test
	public void testAttribNameAsReferenceWithSimpleName() {
		assertEquals("attribNameAsReference failed!", "$simple",
			     EquationUtil.attribNameAsReference("simple"));
	}

	@Test
	public void testAttribNameAsReferenceWithComplexName() {
		assertEquals("attribNameAsReference failed!", "${not\\ so\\ simple}",
			     EquationUtil.attribNameAsReference("not so simple"));
	}

	@Test(expected=Exception.class)
	public void testAttribNameAsReferenceWithEmptyName() {
		EquationUtil.attribNameAsReference("");
	}

	@Test
	public void testDoubleToLongWithValidArgument() {
		assertEquals("doubleToLong() failed!", 12L, EquationUtil.doubleToLong(12.3));
	}

	@Test
	public void testDoubleToLongWithNegativeValidArgument() {
		assertEquals("doubleToLong() failed!", -13L, EquationUtil.doubleToLong(-12.3));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testDoubleToLongWithInvalidArgument() {
		EquationUtil.doubleToLong(Double.MAX_VALUE);
	}
}
