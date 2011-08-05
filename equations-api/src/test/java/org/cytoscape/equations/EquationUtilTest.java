package org.cytoscape.equations;


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
