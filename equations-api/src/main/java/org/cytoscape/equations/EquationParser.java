/*
  File: EquationParser.java

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


import java.util.Map;
import java.util.Set;


public interface EquationParser {
	/**
	 *  After registering an attribute function "func" it can be used in attribute equations.
	 *  @param func  the function that will be registered
	 *  @throws IllegalArgumentException will be thrown if "func" is null or the function has previously been registered
	 */
	public void registerFunction(final Function func) throws IllegalArgumentException;

	/**
	 *  @return the function associated with the name "functionName" or null if no such function exists
	 */
	public Function getFunction(final String functionName);

	/**
	 *  @return the set of currently registered functions
	 */
	public Set<Function> getRegisteredFunctions();

	/**
	 *  @param eqn                  a valid attribute equation which must start with an equal sign
	 *  @param attribNameToTypeMap  a list of existing attribute names and their types
	 *  @return true if the parse succeeded otherwise false
	 */
	public boolean parse(final String eqn, final Map<String, Class<?>> attribNameToTypeMap);

	/**
	 *  @return the result type of the parsed equation if the parse succeeded, otherwise null
	 */
	public Class<?> getType();

	/**
	 *  If parse() failed, this will return the last error messages.
	 *  @return the last error message of null
	 */
	public String getErrorMsg();

	/**
	 *  @return all the variable names that have been detected in the most recently parsed equation
	 */
	public Set<String> getVariableReferences();

	/**
	 *  @return the parse tree.  Must only be called if parse() returns true!
	 */
	public Node getParseTree();
}
