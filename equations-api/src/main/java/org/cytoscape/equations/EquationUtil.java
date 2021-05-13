package org.cytoscape.equations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyRow;
import org.cytoscape.model.CyTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * #%L
 * Cytoscape Equations API (equations-api)
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

/**
 * Various static utility methods relating to equations. 
 * @CyAPI.Static.Class
 * @CyAPI.InModule equations-api
 */
public final class EquationUtil {
	private EquationUtil() { } // Exists to prevent creating instances of this class!
	
	private static final Logger logger = LoggerFactory.getLogger("org.cytoscape.application.userlog");

	/** Converts a bare name to a variable reference.
	 *  @param attribName  the bare name that will be converted
	 *  @return "attribName" written as an attribute reference with a leading $-sign
	 */
	public static String attribNameAsReference(final String attribName) {
		if (isSimpleAttribName(attribName))
			return "$" + attribName;
		else
			return "${" + escapeAttribName(attribName) + "}";
	}

	/** Checked conversion of a double to a long following the Excel™ rules.
	 *  @param d  the number that will be converted
	 *  @return "d" converted to a Long using Excel™ rules
	 *  @throws IllegalArgumentException if "d" is outside the range of a long
	 */
	public static long doubleToLong(final double d) {
		if (d > Long.MAX_VALUE || d < Long.MIN_VALUE)
			throw new IllegalArgumentException("floating point value is too large to be converted to a Long.");

		double x = ((Double)d).longValue();
		if (x != d && d < 0.0)
			--x;

		return (long)x;
	}

	/** Test for whether the argument consists only of letters and digits and starts with a letter
	 *  @param attribName the name to test
	 *  @return true if "attribName" start with a letter and consists of only letters and digits, else false
	 */
	private static boolean isSimpleAttribName(final String attribName) {
		final int length = attribName.length();
		if (length == 0)
			throw new IllegalStateException("empty column names should never happen.");

		if (!Character.isLetter(attribName.charAt(0)))
			return false;

		for (int i = 1; i < length; ++i) {
			final char ch = attribName.charAt(i);
			if (!Character.isLetter(ch) && !Character.isDigit(ch))
				return false;
		}

		return true;
	}

	/** Returns "attribName" with characters that need to be backslash-escaped when written as
	 *           part of an attribute reference, escaped.
	 *  @return "attribName" with characters that need to be backslash-escaped when written as
	 *           part of an attribute reference, escaped.
	 */
	private static String escapeAttribName(final String attribName) {
		final int length = attribName.length();
		final StringBuilder escapedAttribName = new StringBuilder(length * 2);
		for (int i = 0; i < length; ++i) {
			final char ch = attribName.charAt(i);
			switch (ch) {
			case ' ':
			case '\\':
			case '{':
			case '}':
			case ':':
			case ',':
			case '(':
			case ')':
				escapedAttribName.append('\\');
			}
			escapedAttribName.append(ch);
		}

		return escapedAttribName.toString();
	}
	
	/** Refreshes all the equations in the given CyTable
	 *  @param table the CyTable
	 *  @param compiler an EquationCompiler
	 */
	public static void refreshEquations(CyTable table, EquationCompiler compiler) {
		Map<String, Class<?>> variableNameToTypeMap = new HashMap<String, Class<?>>();
		for (CyColumn column : table.getColumns()) {
			variableNameToTypeMap.put(column.getName(), column.getType() == Integer.class ? Long.class : column.getType());
		}

		for (CyRow row : table.getAllRows()) {
			for (CyColumn column : table.getColumns()) {
				String name = column.getName();
				Object value = row.getRaw(name);
				if (!(value instanceof Equation)) {
					continue;
				}
				Equation equation = (Equation) value;
				final Class<?> columnType = column.getType();
				final Class<?> listElementType = column.getListElementType();
				final Class<?> expectedType = variableNameToTypeMap.remove(name);
				try {
					if (compiler.compile(equation.toString(), variableNameToTypeMap)) {
						final Class<?> eqnType = compiler.getEquation().getType();
						if(eqnTypeIsCompatible(columnType, listElementType, eqnType))
							equation = compiler.getEquation();
						else {
							final String errorMsg = "Equation result type is "
									+ getUnqualifiedName(eqnType) + ", column type is "
									+ getUnqualifiedName(columnType) + ".";
							equation = compiler.getErrorEquation(equation.toString(), expectedType, errorMsg);
						}
						row.set(name, compiler.getEquation());
					}
				} catch (Exception e) {
					logger.error("Unexpected error while restoring equation: " + equation.toString(), e);
				}
				variableNameToTypeMap.put(name, expectedType);
			}
		}
	}
	
	/** Returns whether the given column type is compatible with an equation type
	 *  @param columnType the column type
	 *  @param listElementType the list element type, if columnType is List
	 *  @param eqnType the equation type
	 *  @return true if the types are compatible, false if incompatible
	 */
	public static boolean eqnTypeIsCompatible(final Class<?> columnType, final Class<?> listElementType, final Class<?> eqnType) {
		if (columnType == eqnType)
			return true;
		if (columnType == String.class) // Anything can be trivially converted to a string.
			return true;
		if (columnType == Integer.class && (eqnType == Long.class || eqnType == Double.class))
			return true;
		if (columnType == Long.class && (eqnType == Integer.class || eqnType == Double.class))
			return true;
		if (columnType == Double.class && eqnType == Long.class)
			return true;
		if (columnType == Boolean.class && (eqnType == Long.class || eqnType == Double.class))
			return true;
		if (columnType != List.class || !columnType.isAssignableFrom(eqnType))
			return false;

		// HACK!!!!!!  We don't know the type of the List, but we can do some type checking
		// for our own builtins.  We need to do this as a negative evaluation in case
		// an App wants to add a new List function
		if (eqnType.getSimpleName().equals("DoubleList") && listElementType != Double.class)
			return false;

		if (eqnType.getSimpleName().equals("LongList") && 
				(listElementType != Integer.class && listElementType != Long.class))
			return false;

		if (eqnType.getSimpleName().equals("BooleanList") && listElementType != Boolean.class)
			return false;

		if (eqnType.getSimpleName().equals("StringList") && listElementType != String.class)
			return false;

		return true;
	}
	
	/** Returns the unqualified name of the given class
	 *  @param type a Class object
	 *  @return the unqualified name of the class as a String
	 */
	public static String getUnqualifiedName(final Class<?> type) {
		final String typeName = type.getName();
		final int lastDotPos = typeName.lastIndexOf('.');
		return lastDotPos == -1 ? typeName : typeName.substring(lastDotPos + 1);
	}
}
