package org.cytoscape.equations;

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

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/** 
 * The class representing an equation.
 * @CyAPI.Final.Class
 * @CyAPI.InModule equations-api
 */
public final class Equation {
	private final String equation;
	private final Set<String> variableReferences;
	private final Map<String, Object> defaultVariableValues;
	private final Object[] code;
	private final int[] sourceLocations;
	private final Class<?> type;

	/**
	 *  Constructs an <code>Equation</code>.
	 *  @param equation              the string representing this equation
	 *  @param variableReferences    other variables that are referenced by this equation
	 *  @param defaultVariableValues 
	 *  @param code                  the instruction sequence representing the compiled equation
	 *  @param sourceLocations       the starting points in the original equation for each node
	 *                               in the parse tree that resulted from the original equation
	 *  @param type                  the type of the equation, String.class, Boolean.class or Double.class
	 */
	public Equation(final String equation, final Set<String> variableReferences,
	                final Map<String, Object> defaultVariableValues, final Object[] code,
	                final int[] sourceLocations, final Class<?> type)
	{
		this.equation              = equation;
		this.variableReferences    = variableReferences;
		this.defaultVariableValues = defaultVariableValues;
		this.code                  = Arrays.copyOf(code, code.length);
		this.sourceLocations       = Arrays.copyOf(sourceLocations, sourceLocations.length);
		this.type                  = type;
	}

	/** Returns a textual representation of an <code>Equation</code>.
	 *  @return this <code>Equation</code> as a <code>String</code>
	 */
	@Override public String toString() { return equation; }

	/** Compares one <code>Equation</code> to another.
	 *  @return true if this <code>Equation</code> is equivalent to "other" and else returns false
	 */
	@Override public boolean equals(final Object other) {
		if (other == null || other.getClass() != Equation.class)
			return false;

		final Equation otherEquation = (Equation)other;
		return equation.equals(otherEquation.equation);
	}

	@Override public int hashCode() {
		return equation.hashCode();
	}

	/** Returns all the variable references that occur as part if this <code>Equation</code>.
	 *  @return a set of variables referenced by this <code>Equation</code>
	 */
	public Set<String> getVariableReferences() { return variableReferences; }

	/** Returns default values for variable references, if any.
	 *  @return a map of variable names to their default values, if any
	 */
	public Map<String, Object> getDefaultVariableValues() { return defaultVariableValues; }

	/** Returns the compiled code (not Java byte code!) for the VM representing this <code>Equation</code>.
	 *  @return the code representing this <code>Equation</code>
	 */
	public Object[] getCode() { return code; }

	/** Returns the starting points in the original equation for each node in the parse tree that
	 *  resulted from the original equation.  (Potentially useful for writing a debugger.)
	 *  @return all the starting points in the original equation that resulted in parse tree nodes
	 */
	public int[] getSourceLocations() { return sourceLocations; }

	/** The overall type of the Equation, e.g. Long.class, String.class etc.
	 *  @return the type of this 
	 */
	public Class<?> getType() { return type; }
}
