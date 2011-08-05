package org.cytoscape.equations;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.management.AttributeList;
import javax.management.relation.RoleList;
import javax.management.relation.RoleUnresolvedList;

import static org.junit.Assert.*;
import org.junit.Test;


public class FunctionUtilTest {
	@Test
	public void testGetArgAsDoubleWithADoubleArgument() {
		assertEquals("getArgAsDouble() failed!", 1e-23, FunctionUtil.getArgAsDouble(1e-23), 1e-30);
	}

	@Test
	public void testGetArgAsDoubleWithALongArgument() {
		assertEquals("getArgAsDouble() failed!", -13.0, FunctionUtil.getArgAsDouble(-13L), 0.001);
	}

	@Test
	public void testGetArgAsDoubleWithValidStringArgument() {
		assertEquals("getArgAsDouble() failed!", 1.3, FunctionUtil.getArgAsDouble("1.3"), 0.0001);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testGetArgAsDoubleWithBadStringArgument() {
		FunctionUtil.getArgAsDouble("abc");
	}

	@Test
	public void testGetArgAsDoubleWithBooleanArgument() {
		assertEquals("getArgAsDouble() failed!", 1.0, FunctionUtil.getArgAsDouble(Boolean.valueOf(true)), 0.00001);
	}

	@Test
	public void testGetArgAsDoubleWithBooleanArgument2() {
		assertEquals("getArgAsDouble() failed!", 0.0, FunctionUtil.getArgAsDouble(Boolean.valueOf(false)), 0.00001);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testGetArgAsDoubleWithBadArgumentType() {
		FunctionUtil.getArgAsDouble(new Exception(""));
	}

	@Test
	public void testGetArgAsStringFromBoolean() {
		assertEquals("getArgAsString failed!", "TRUE", FunctionUtil.getArgAsString(Boolean.valueOf(true)));
	}

	@Test
	public void testGetArgAsStringFromBoolean2() {
		assertEquals("getArgAsString failed!", "FALSE", FunctionUtil.getArgAsString(Boolean.valueOf(false)));
	}

	@Test
	public void testGetArgAsStringFromDouble() {
		assertEquals("getArgAsString failed!", "1.3", FunctionUtil.getArgAsString(Double.valueOf(1.3)));
	}

	@Test
	public void testGetArgAsStringFromString() {
		assertEquals("getArgAsString failed!", "abc", FunctionUtil.getArgAsString("abc"));
	}

	@Test
	public void testGetArgAsLongFromDouble() {
		assertEquals("getArgAsLong() failed!", 11L, FunctionUtil.getArgAsLong(Double.valueOf(11.3)));
	}

	@Test
	public void testGetArgAsLongFromLong() {
		assertEquals("getArgAsLong() failed!", 1001L, FunctionUtil.getArgAsLong(Long.valueOf(1001L)));
	}

	@Test
	public void testGetArgAsLongFromBoolean() {
		assertEquals("getArgAsLong() failed!", 1L, FunctionUtil.getArgAsLong(Boolean.valueOf(true)));
	}

	@Test
	public void testGetArgAsLongFromValidString() {
		assertEquals("getArgAsLong() failed!", 23L, FunctionUtil.getArgAsLong("23"));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testGetArgAsLongFromInvalidString() {
		FunctionUtil.getArgAsLong("abc");
	}

	@Test
	public void testGetArgAsLongFromBoolean2() {
		assertEquals("getArgAsLong() failed!", 0L, FunctionUtil.getArgAsLong(Boolean.valueOf(false)));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testGetArgAsLongWithInvalidTypeAsArgument() {
		FunctionUtil.getArgAsLong(new Exception("abc"));
	}

	@Test
	public void testGetArgAsBooleanWithNonZeroDouble() {
		assertEquals("getArgAsBoolean() failed!", true, FunctionUtil.getArgAsBoolean(-1.3));
	}

	@Test
	public void testGetArgAsBooleanWithZeroDouble() {
		assertEquals("getArgAsBoolean() failed!", false, FunctionUtil.getArgAsBoolean(0.0));
	}

	@Test
	public void testGetArgAsBooleanWithNonZeroLong() {
		assertEquals("getArgAsBoolean() failed!", true, FunctionUtil.getArgAsBoolean(12L));
	}

	@Test
	public void testGetArgAsBooleanWithZeroLong() {
		assertEquals("getArgAsBoolean() failed!", false, FunctionUtil.getArgAsBoolean(0L));
	}

	@Test
	public void testGetArgAsBooleanWithTrueValuedString() {
		assertEquals("getArgAsBoolean() failed!", true, FunctionUtil.getArgAsBoolean("tRUE"));
	}

	@Test
	public void testGetArgAsBooleanWithFalseValuedString() {
		assertEquals("getArgAsBoolean() failed!", false, FunctionUtil.getArgAsBoolean("FaLsE"));
	}

	@Test
	public void testGetArgAsBooleanWithBooleanArgument() {
		assertEquals("getArgAsBoolean() failed!", false, FunctionUtil.getArgAsBoolean(Boolean.valueOf(false)));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testGetArgAsBooleanWithInvalidTypeAsArgument() {
			FunctionUtil.getArgAsBoolean(Integer.valueOf(2));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testGetArgAsBooleanWithInvalidString() {
		FunctionUtil.getArgAsBoolean("abc");
	}

	@Test
	public void testNumericallySafeSum() {
		final double[] numbers = { 10.3, 1e-2, -10.3, 140.5, -1e-2 };
		assertEquals("numericallySafeSum() failed!", 140.5, FunctionUtil.numericallySafeSum(numbers), 0.00001);
	}

	@Test
	public void testGetOrdinal1() {
		assertEquals("getOrdinal() failed!", "11th", FunctionUtil.getOrdinal(11));
	}

	@Test
	public void testGetOrdinal2() {
		assertEquals("getOrdinal() failed!", "31st", FunctionUtil.getOrdinal(31));
	}

	@Test
	public void testGetOrdinal3() {
		assertEquals("getOrdinal() failed!", "122nd", FunctionUtil.getOrdinal(122));
	}

	@Test
	public void testGetOrdinal4() {
		assertEquals("getOrdinal() failed!", "43rd", FunctionUtil.getOrdinal(43));
	}

	@Test
	public void testGetOrdinal5() {
		assertEquals("getOrdinal() failed!", "44th", FunctionUtil.getOrdinal(44));
	}

	@Test
	public void testCalcSampleVariance() {
		final double[] x = { 2.2, 2.2 };
		assertEquals("calcSampleVariance() failed!", 0.0, FunctionUtil.calcSampleVariance(x), 0.0001);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCalcSampleVarianceWithIllegalArgument() {
		final double[] x = { 1.0 };
		FunctionUtil.calcSampleVariance(x);
	}

	@Test
	public void testListToArray() {
		final List<Double> dl = new ArrayList<Double>();
		dl.add(-1.0);
		dl.add(2.0);
		final double[] expectedArray = { -1.0, 2.0 };
		assertArrayEquals("listToArray() failed!", expectedArray, FunctionUtil.listToArray(dl), 0.0001);
	}

	@Test
	public void testIsScalarArgTypeWithADoubleArgument() {
		assertTrue("isScalarArgType() failed!", FunctionUtil.isScalarArgType(Double.class));
	}

	@Test
	public void testIsScalarArgTypeWithALongArgument() {
		assertTrue("isScalarArgType() failed!", FunctionUtil.isScalarArgType(Long.class));
	}

	@Test
	public void testIsScalarArgTypeWithAStringArgument() {
		assertTrue("isScalarArgType() failed!", FunctionUtil.isScalarArgType(String.class));
	}

	@Test
	public void testIsScalarArgTypeWithABooleanArgument() {
		assertTrue("isScalarArgType() failed!", FunctionUtil.isScalarArgType(Boolean.class));
	}

	@Test
	public void testIsScalarArgTypeWithAnIntegerArgument() {
		assertFalse("isScalarArgType() failed!", FunctionUtil.isScalarArgType(Integer.class));
	}

	@Test
	public void testIsScalarArgTypeWithAListArgument() {
		assertFalse("isScalarArgType() failed!", FunctionUtil.isScalarArgType(ArrayList.class));
	}

	@Test
	public void testIsSomeKindOfListWithAListArg() {
		assertTrue("isSomeKindOfList() failed!", FunctionUtil.isSomeKindOfList(List.class));
	}

	@Test
	public void testIsSomeKindOfListWithAnArrayListArg() {
		assertTrue("isSomeKindOfList() failed!", FunctionUtil.isSomeKindOfList(ArrayList.class));
	}

	@Test
	public void testIsSomeKindOfListWithADoubleListArg() {
		assertTrue("isSomeKindOfList() failed!", FunctionUtil.isSomeKindOfList(DoubleList.class));
	}

	@Test
	public void testIsSomeKindOfListWithAStringListArg() {
		assertTrue("isSomeKindOfList() failed!", FunctionUtil.isSomeKindOfList(StringList.class));
	}

	@Test
	public void testIsSomeKindOfListWithALongListArg() {
		assertTrue("isSomeKindOfList() failed!", FunctionUtil.isSomeKindOfList(LongList.class));
	}

	@Test
	public void testIsSomeKindOfListWithABooleanListArg() {
		assertTrue("isSomeKindOfList() failed!", FunctionUtil.isSomeKindOfList(BooleanList.class));
	}

	@Test
	public void testIsSomeKindOfListWithAVectorArg() {
		assertTrue("isSomeKindOfList() failed!", FunctionUtil.isSomeKindOfList(Vector.class));
	}

	@Test
	public void testIsSomeKindOfListWithAStackArg() {
		assertTrue("isSomeKindOfList() failed!", FunctionUtil.isSomeKindOfList(Stack.class));
	}

	@Test
	public void testIsSomeKindOfListWithAnAttributeListArg() {
		assertTrue("isSomeKindOfList() failed!", FunctionUtil.isSomeKindOfList(AttributeList.class));
	}

	@Test
	public void testIsSomeKindOfListWithACopyOnWriteArrayListArg() {
		assertTrue("isSomeKindOfList() failed!", FunctionUtil.isSomeKindOfList(CopyOnWriteArrayList.class));
	}

	@Test
	public void testIsSomeKindOfListWithALinkedListArg() {
		assertTrue("isSomeKindOfList() failed!", FunctionUtil.isSomeKindOfList(LinkedList.class));
	}

	@Test
	public void testIsSomeKindOfListWithARoleListArg() {
		assertTrue("isSomeKindOfList() failed!", FunctionUtil.isSomeKindOfList(RoleList.class));
	}

	@Test
	public void testIsSomeKindOfListWithARoleUnresolvedListArg() {
		assertTrue("isSomeKindOfList() failed!", FunctionUtil.isSomeKindOfList(RoleUnresolvedList.class));
	}

	@Test
	public void testIsSomeKindOfListWithAScalarArg() {
		assertFalse("isSomeKindOfList() failed!", FunctionUtil.isSomeKindOfList(Long.class));
	}

	@Test
	public void testIsSomeKindOfListWithAnArrayArg() {
		assertFalse("isSomeKindOfList() failed!", FunctionUtil.isSomeKindOfList(Long[].class));
	}

	@Test
	public void testAddScalarArgumentTypes() {
		final List<Class> argTypes = new ArrayList<Class>();
		argTypes.add(Integer.class);
		FunctionUtil.addScalarArgumentTypes(argTypes);
		assertTrue("addScalarArgumentTypes() failed!", argTypes.contains(Double.class));
		assertTrue("addScalarArgumentTypes() failed!", argTypes.contains(Long.class));
		assertTrue("addScalarArgumentTypes() failed!", argTypes.contains(String.class));
		assertTrue("addScalarArgumentTypes() failed!", argTypes.contains(Boolean.class));
	}

	@Test
	public void testGetDoubles() {
		final String[] strings = { "1.0", "-0.3" };
		final double[] expectedValues = { 1.0, -0.3 };
		try {
			assertArrayEquals("getDoubles() failed!", expectedValues, FunctionUtil.getDoubles(strings), 0.00001);
		} catch (final Exception e) {
			fail("getDoubles() failed!");
		}
	}

	@Test
	public void testGetDoubles2() {
		final List<Object> misc = new ArrayList<Object>();
		misc.add(Long.valueOf(1L));
		misc.add("-0.3");
		final Object[] args = { misc, Boolean.valueOf(false) };
		final double[] expectedValues = { 1.0, -0.3, 0.0 };
		try {
			assertArrayEquals("getDoubles() failed!", expectedValues, FunctionUtil.getDoubles(args), 0.00001);
		} catch (final Exception e) {
			fail("getDoubles() failed!");
		}
	}

	@Test
	public void testGetStrings() {
		final List<Object> misc = new ArrayList<Object>();
		misc.add(Long.valueOf(1L));
		misc.add(-0.3);
		final Object[] args = { misc, Boolean.valueOf(false) };
		final String[] expectedValues = { "1", "-0.3", "FALSE" };
		try {
			assertArrayEquals("getStrings() failed!", expectedValues, FunctionUtil.getStrings(args));
		} catch (final Exception e) {
			fail("getStrings() failed!");
		}
	}

	@Test
	public void testGetLongs() {
		final List<Object> misc = new ArrayList<Object>();
		misc.add(Double.valueOf(1.0));
		misc.add(-0.3);
		final Object[] args = { misc, Boolean.valueOf(false), -33L, 42, "501" };
		final long[] expectedValues = { 1L, -1L, 0L, -33L, 42L, 501L };
		try {
			assertArrayEquals("getLongs() failed!", expectedValues, FunctionUtil.getLongs(args));
		} catch (final Exception e) {
			fail("getLongs() failed!");
		}
	}

	@Test
	public void testGetBooleans() {
		final List<Object> misc = new ArrayList<Object>();
		misc.add(Long.valueOf(1L));
		misc.add(-0.3);
		final Object[] args = { misc, "false", 42, "true", true, false };
		final boolean[] expectedValues = { true, true, false, true, true, true, false };
		try {
			final boolean[] actualValues = FunctionUtil.getBooleans(args);
			assertEquals("getBooleans() failed!", expectedValues.length, actualValues.length);
			for (int i = 0; i < actualValues.length; ++i)
				assertEquals("getBooleans() failed!", expectedValues[i], actualValues[i]);
		} catch (final Exception e) {
			fail("getBooleans() failed!");
		}
	}

	@Test
	public void testTranslateObjectType1() {
		assertEquals("translateObjectType() failed!", 3.0, FunctionUtil.translateObjectType(Double.valueOf(3.0)));
	}

	@Test
	public void testTranslateObjectType2() {
		assertEquals("translateObjectType() failed!", 2L, FunctionUtil.translateObjectType(Long.valueOf(2L)));
	}

	@Test
	public void testTranslateObjectType3() {
		assertEquals("translateObjectType() failed!", "abc", FunctionUtil.translateObjectType("abc"));
	}

	@Test
	public void testTranslateObjectType4() {
		assertEquals("translateObjectType() failed!", true, FunctionUtil.translateObjectType(Boolean.valueOf(true)));
	}

	@Test
	public void testTranslateObjectType5() {
		assertEquals("translateObjectType() failed!", 3.3, (Double)FunctionUtil.translateObjectType(Float.valueOf(3.3f)), 0.00001);
	}

	@Test
	public void testTranslateObjectType6() {
		assertEquals("translateObjectType() failed!", -11L, FunctionUtil.translateObjectType(Integer.valueOf(-11)));
	}

	@Test
	public void testTranslateObjectType7() {
		assertEquals("translateObjectType() failed!", 2L, FunctionUtil.translateObjectType(Short.valueOf("2")));
	}

	@Test
	public void testTranslateObjectType8() {
		assertEquals("translateObjectType() failed!", 10L, FunctionUtil.translateObjectType(Byte.valueOf("10")));
	}

	@Test
	public void testTranslateObjectType9() {
		assertEquals("translateObjectType() failed!", 20L, FunctionUtil.translateObjectType((char)20));
	}

	@Test
	public void testTranslateObjectType10() {
		assertNull("translateObjectType() failed!", FunctionUtil.translateObjectType(new ArrayList<Double>()));
	}
}
