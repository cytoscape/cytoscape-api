/*
  File: Equation.java

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


import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public final class Equation {
	private final String equation;
	private final Set<String> variableReferences;
	private final Object[] code;
	private final int[] sourceLocations;
	private final Class type;

	/**
	 *  Constructs an <code>Equation</code>.
	 *  @param equation            the string representing this equation
	 *  @param variableReferences  other variables that are referenced by this equation
	 *  @param code                the instruction sequence representing the compiled equation
	 *  @param sourceLocations     the starting points in the original equation for each node in the parse tree that resulted from
	 *                             the original equation
	 *  @param type                the type of the equation, String.class, Boolean.class or Double.class
	 */
	public Equation(final String equation, final Set<String> variableReferences, final Object[] code,
	               final int[] sourceLocations, final Class type)
	{
		this.equation           = equation;
		this.variableReferences = variableReferences;
		this.code               = code;
		this.sourceLocations    = sourceLocations;
		this.type               = type;
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
	public Class getType() { return type; }
}
