/*
  File: FunctionUtil.java

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
package org.cytoscape.equations;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 *  A collection of static methods that may be useful for the implementation of built-in functions.
 *  @CyAPI.Static.Class
 */
public final class FunctionUtil {
	private FunctionUtil() { } // Exists to prevent creating instances of this class!

	/** Assumes that "arg" is a "String", "Boolean", "Long" or a "Double and converts it to "double".
	 *  @param arg  the object instance that will be converted to a <code>double</code>, if possible
	 *  @return the converted argument as a "double"
	 *  @throws IllegalArgumentException if the argument cannot be converted to a "double"
	 */
	public static double getArgAsDouble(final Object arg) throws IllegalArgumentException {
		if (arg.getClass() == Double.class)
			return (Double)arg;
		if (arg.getClass() == Long.class)
			return (double)(Long)arg;
		if (arg.getClass() == String.class) {
			try {
				return Double.parseDouble((String)arg);
			} catch (final Exception e) {
				throw new IllegalArgumentException("can't convert \"" + arg + "\" to a floating point number.", e);
			}
		}
		if (arg.getClass() == Boolean.class)
			return (Boolean)arg ? 1.0 : 0.0;

		throw new IllegalArgumentException("can't convert argument to a floating point number.");
	}

	/** Assumes that "arg" is a "String", "Boolean", "Long" or a "Double and converts it to "String".
	 *  @param arg  the object instance that will be converted to a <code>String</code>
	 *  @return the converted argument as a "double"
	 *  @throws IllegalArgumentException if the argument cannot be converted to a "double"
	 */
	public static String getArgAsString(final Object arg) throws IllegalArgumentException {
		if (arg.getClass() == Boolean.class)
			return (Boolean)arg ? "TRUE" : "FALSE";
		if (arg.getClass() == Double.class) {
			final String asString = arg.toString();
			int length = asString.length();

			// Strip trailing zeros:
			while (length > 1 && asString.charAt(length - 1) == '0')
				--length;

			// Strip trailing period or comma:
			if (asString.charAt(length - 1) == '.' || asString.charAt(length - 1) == ',')
				--length;

			return asString.substring(0, length);
		}

		return arg.toString();
	}

	/** Assumes that "arg" is a "String", "Boolean", "Long" or a "Double and converts it to "long".
	 *  @param arg  the object instance that will be converted to a <code>long</code>, if possible
	 *  @return the converted argument as a "long"
	 *  @throws IllegalArgumentException if the argument cannot be converted to a "long"
	 */
	public static long getArgAsLong(final Object arg) throws IllegalArgumentException {
		if (arg.getClass() == Double.class)
			return EquationUtil.doubleToLong((Double)arg);
		if (arg.getClass() == Long.class)
			return (Long)arg;
		if (arg.getClass() == String.class) {
			try {
				return Long.parseLong((String)arg);
			} catch (final Exception e) {
				throw new IllegalArgumentException("can't convert \"" + arg + "\" to a whole number.");
			}
		}
		if (arg.getClass() == Boolean.class)
			return (Boolean)arg ? 1L : 0L;

		throw new IllegalArgumentException("can't convert argument to a whole number.");
	}

	/** Assumes that "arg" is a "String", "Boolean", "Long" or a "Double and converts it to "boolean".
	 *  @param arg  the object instance that will be converted to a <code>boolean</code>, if possible
	 *  @return the converted argument as a "boolean"
	 *  @throws IllegalArgumentException if the argument cannot be converted to a "boolean"
	 */
	public static boolean getArgAsBoolean(final Object arg) throws IllegalArgumentException {
		if (arg.getClass() == Double.class) {
			final double d = (Double)arg;
			return d == 0.0 ? false : true;
		}
		if (arg.getClass() == Long.class) {
			final long l = (Long)arg;
			return l == 0L ? false : true;
		}
		if (arg.getClass() == String.class) {
			final String argAsString = (String)arg;

			if (argAsString.equalsIgnoreCase(Boolean.TRUE.toString()))
				return true;
			if (argAsString.equalsIgnoreCase(Boolean.FALSE.toString()))
				return false;
			throw new IllegalArgumentException("can't convert \"" + argAsString + "\" to a boolean.");
		}
		if (arg.getClass() == Boolean.class)
			return (Boolean)arg;

		throw new IllegalArgumentException("can't convert argument to a boolean.");
	}

	/** Carefully adds the numbers in "a" minimising loss of precision.
	 *  @param a  the numbers that will be summed up
	 *  @return the sum of the elements of "a"
	 */
	public static double numericallySafeSum(final double a[]) {
		int positiveCount = 0;
		for (double d : a) {
			if (d >= 0.0)
				++positiveCount;
		}

		// Separate positive and negative values:
		final double[] positiveValues = new double[positiveCount];
		final double[] negativeValues = new double[a.length - positiveCount];
		int positiveIndex = 0;
		int negativeIndex = 0;
		for (double d : a) {
			if (d >= 0.0)
				positiveValues[positiveIndex++] = d;
			else
				negativeValues[negativeIndex++] = d;
		}

		double positiveSum = 0.0;
		if (positiveValues.length > 0) {
			// Add values in increasing order of magnitude:
			Arrays.sort(positiveValues);
			for (double d : positiveValues)
				positiveSum += d;
		}

		double negativeSum = 0.0;
		if (negativeValues.length > 0) {
			// Add values in increasing order of magnitude:
			Arrays.sort(negativeValues);
			for (int i = negativeValues.length - 1; i >= 0; --i)
				negativeSum += negativeValues[i];
		}

		return positiveSum + negativeSum;
	}

	/** Converts an integer to an ordinal string.
	 *  @param  i  the integer that will be converted
	 *  @return the String representation of the ith ordinal
	 */
	public static String getOrdinal(final int i) {
		switch (i % 100) {
		case 11:
		case 12:
		case 13:
			return Integer.toString(i) + "th";
		}
		
		switch (i % 10) {
		case 1:
			return Integer.toString(i) + "st";
		case 2:
			return Integer.toString(i) + "nd";
		case 3:
			return Integer.toString(i) + "rd";
		default:
			return Integer.toString(i) + "th";
		}
	}

	/** Calculates the sample variance of some numbers.
	 *  @param x  the numbers whose sample variance will be calculated
	 *  @return the sample variance of the numbers in x[]
	 */
	public static double calcSampleVariance(final double[] x) {
		final int n = x.length;
		if (n < 2)
			throw new IllegalArgumentException("can't calculate a variance with fewer than 2 values.");

		final double[] xSquared = new double[n];
		for (int i = 0; i < n; ++i)
			xSquared[i] = x[i] * x[i];

		final double sumOfX = numericallySafeSum(x);
		final double sumOfXSquared = numericallySafeSum(xSquared);

		return (sumOfXSquared - (sumOfX * sumOfX) / (double)n) / (double)(n - 1);
	}

	/** Converts a List<Double> to a regular double[]
	 *  @param  a  the list that will be converted
	 *  @return the converted array
	 */
	public static double[] listToArray(final List<Double> a) {
		final double[] x = new double[a.size()];
		int i = 0;
		for (double d : a)
			x[i++] = d;

		return x;
	}

	/** Tests whether the argument is one of the scalar types used by equations.
	 *  @param type  the type that will be tested
	 *  @return true, if type is Double.class, Long.class, String.class or Boolean.class, else false
	 */
	public static boolean isScalarArgType(final Class type) {
		return type == Double.class || type == Long.class || type == String.class || type == Boolean.class;
	}

	/** Tests whether the argument is some kind of <code>List</code>.
	 *  @param listClassCandidate  the type that will be tested
	 *  @return true if "listClassCandidate" is an implementer of interface List, else false
	 */
	public static boolean isTypeOfList(final Class listClassCandidate) {
		return List.class.isAssignableFrom(listClassCandidate);
	}

	/** Adds the scalar types that equations use internally to the argument, i.e. adds
	 *  <code>Double.class</code>, <code>Long.class</code>, <code>String.class</code> and
	 *  <code>Boolean.class</code> to "argTypes".
	 *  @param argTypes  the list that will be augmented
	 */
	public static void addScalarArgumentTypes(final List<Class> argTypes) {
		argTypes.add(Double.class);
		argTypes.add(Long.class);
		argTypes.add(String.class);
		argTypes.add(Boolean.class);
	}

	/** Attempts to convert all arguments, including Lists to a uniform array of doubles.
	 *  @param args  the objects that will be converted to doubles, if possible
	 *  @return the converted array
	 *  @throws FunctionError 
	 *  @throws IllegalArgumentException if any scalar argument cannot be converted to a double or any list
	 *          argument contains an element that cannot be converted to a number.
	 */
	public static double[] getDoubles(final Object[] args) throws FunctionError {
		final List<Double> numbers = new ArrayList<Double>();

		for (int i = 0; i < args.length; ++i) {
			final Object arg = args[i];
			if (arg instanceof List) {
				final List list = (List)arg;
				for (final Object listElement : list) {
					final Double d = convertToDouble(listElement);
					if (d == null)
						throw new FunctionError("can't convert list element \"" + listElement
						                        + "\" to a number.", i);
					numbers.add(d);
				}
			} else {
				final Double d = convertToDouble(arg);
				if (d == null)
					throw new FunctionError("can't convert \"" + arg + "\" to a number.", i);
				numbers.add(d);
			}
		}

		final double[] doubles = new double[numbers.size()];
		int index = 0;
		for (final Double d : numbers)
			doubles[index++] = d;

		return doubles;
	}

	/** Attempts to convert all arguments, including Lists to a uniform array of longs.
	 *  @param args  the objects that will be converted to longs, if possible
	 *  @return the converted array
	 *  @throws FunctionError 
	 *  @throws IllegalArgumentException if any scalar argument cannot be converted to a long or any list
	 *          argument contains an element that cannot be converted to a number.
	 */
	public static long[] getLongs(final Object[] args) throws FunctionError {
		final List<Long> numbers = new ArrayList<Long>();

		for (int i = 0; i < args.length; ++i) {
			final Object arg = args[i];
			if (arg instanceof List) {
				final List list = (List)arg;
				for (final Object listElement : list) {
					final Long l = convertToLong(listElement);
					if (l == null)
						throw new FunctionError("can't convert list element \"" + listElement
						                        + "\" to an integer.", i);
					numbers.add(l);
				}
			} else {
				final Long l = convertToLong(arg);
				if (l == null)
					throw new FunctionError("can't convert \"" + arg + "\" to an integer.", i);
				numbers.add(l);
			}
		}

		final long[] longs = new long[numbers.size()];
		int index = 0;
		for (final Long l : numbers)
			longs[index++] = l;

		return longs;
	}

	/** Converts all arguments, including Lists to a uniform array of strings.
	 *  @param args  the objects that will be converted to Strings
	 *  @return the converted array
	 */
	public static String[] getStrings(final Object[] args) {
		final List<String> strings = new ArrayList<String>();

		for (final Object arg : args) {
			if (arg instanceof List) {
				final List list = (List)arg;
				for (final Object listElement : list)
					strings.add(getArgAsString(listElement));
			} else
				strings.add(getArgAsString(arg));
		}

		final String[] retVal = new String[strings.size()];
		return strings.toArray(retVal);
	}

	/** Attempts to convert all arguments, including Lists to a uniform array of booleans.
	 *  @param args  the objects that will be converted to booleans, if possible
	 *  @return the converted array
	 *  @throws FunctionError 
	 *  @throws IllegalArgumentException if any scalar argument cannot be converted to a boolean or any list
	 *          argument contains an element that cannot be converted to a number.
	 */
	public static boolean[] getBooleans(final Object[] args) throws FunctionError {
		final List<Boolean> booleans = new ArrayList<Boolean>();

		for (int i = 0; i < args.length; ++i) {
			final Object arg = args[i];
			if (arg instanceof List) {
				final List list = (List)arg;
				for (final Object listElement : list) {
					final Boolean b = convertToBoolean(listElement);
					if (b == null)
						throw new FunctionError("can't convert list element \"" + listElement
						                        + "\" to a boolean value.", i);
					booleans.add(b);
				}
			} else {
				final Boolean b = convertToBoolean(arg);
				if (b == null)
					throw new FunctionError("can't convert \"" + arg + "\" to a boolean number.", i);
				booleans.add(b);
			}
		}

		final boolean[] retval = new boolean[booleans.size()];
		int index = 0;
		for (final Boolean b : booleans)
			retval[index++] = b;

		return retval;
	}

	/**
	 *  Returns "arg" converted to a Double, if possible, else null
	 *  @return "arg" converted to a Double, if possible, else null
	 */
	static private Double convertToDouble(final Object arg) {
		if (arg.getClass() == Double.class)
			return (Double)arg;
		if (arg.getClass() == Long.class)
			return (double)(Long)arg;
		if (arg.getClass() == Integer.class)
			return (double)(Integer)arg;
		if (arg.getClass() == String.class) {
			try {
				return Double.valueOf((String)arg);
			} catch (final Exception e) {
				return null;
			}
		}
		if (arg.getClass() == Boolean.class)
			return Double.valueOf((Boolean)arg ? 1.0 : 0.0);

		return null;
	}

	/**
	 * Returns "arg" converted to a Long, if possible, else null.
	 *  @return "arg" converted to a Long, if possible, else null.
	 */
	static private Long convertToLong(final Object arg) {
		if (arg.getClass() == Double.class)
			return EquationUtil.doubleToLong((Double)arg);
		if (arg.getClass() == Long.class)
			return (Long)arg;
		if (arg.getClass() == Integer.class)
			return (long)(Integer)arg;
		if (arg.getClass() == String.class) {
			try {
				return Long.valueOf((String)arg);
			} catch (final Exception e) {
				return null;
			}
		}
		if (arg.getClass() == Boolean.class)
			return Long.valueOf((Boolean)arg ? 1L : 0L);

		return null;
	}

	/**
	 *  @return "arg" converted to a Boolean, if possible. else null
	 */
	static private Boolean convertToBoolean(final Object arg) {
		if (arg.getClass() == Double.class)
			return (Double)arg == 0.0 ? Boolean.FALSE : Boolean.TRUE;
		if (arg.getClass() == Long.class)
			return (Long)arg == 0L ? Boolean.FALSE : Boolean.TRUE;
		if (arg.getClass() == Integer.class)
			return (Integer)arg == 0 ? Boolean.FALSE : Boolean.TRUE;
		if (arg.getClass() == String.class) {
			final String s = (String)arg;
			if (s.equalsIgnoreCase("true"))
				return Boolean.TRUE;
			if (s.equalsIgnoreCase("false"))
				return Boolean.FALSE;
			return null;
		}
		if (arg.getClass() == Boolean.class)
			return (Boolean)arg;

		return null;
	}

	/** Tries to map "input" to one of <code>Double</code>, <code>Long</code>, <code>Boolean</code> or <code>String<code>.
	 *  @param input  the object that will be mapped to one of the scalar types that are internally supported by equations
	 *  @return null if the translation failed, the input object if no translation was necessary or a new object if a successful translation was possible
	 */
	public static Object translateObjectType(final Object input) {
		final Class<?> type = input.getClass();
		if (type == Double.class || type == Long.class || type == Boolean.class || type == String.class)
			return input;

		if (type == Float.class)
			return Double.valueOf((Float)input);

		if (type == Integer.class)
			return Long.valueOf((Integer)input);

		if (type == Short.class)
			return Long.valueOf((Short)input);

		if (type == Byte.class)
			return Long.valueOf((Byte)input);

		if (type == Character.class)
			return Long.valueOf((Character)input);

		// Couldn't map the input to anything useful:
		return null;
	}
}
