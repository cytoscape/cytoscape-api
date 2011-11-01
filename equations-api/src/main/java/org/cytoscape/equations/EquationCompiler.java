/*
  File: EquationCompiler.java

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


/** Compiler that compiles equations to byte (non-Java) code. 
 * @CyAPI.Api.Interface #ASKMIKE
 */
public interface EquationCompiler {
	/** Compiles a string to byte code plus some metadata as encapsulated by the <code>Equation</code> class
	 *  @param equation               a textual representation of an equation
	 *  @param variableNameToTypeMap  a mapping from variable names occurring in "equation" to their respective types
	 * @return #ASKMIKE
	 */
	boolean compile(final String equation, final Map<String, Class<?>> variableNameToTypeMap);

	/** Returns an error message for an error encountered during a call to compile, if any.
	 *  @return a human readable error message
	 *  Please note that it is probably a good idea to only call this method if compile() returned false.
	 */
	String getLastErrorMsg();

	/** If compile() returns true, this returns the result of the compilation.
	 *  @return an <code>Equation</code> representing the code compiled from the string passed into compile()
	 */
	Equation getEquation();

	/**
	 *  A factory method that returns an Equation that always fails at runtime.  The reason for
	 *  why this exists is that the returned error equations can substitute for real equations as
	 *  dummies.
	 *
	 *  @param equation      an arbitrary string that is usually a syntactically invalid equation
	 *  @param type          the return type of the error equation
	 *  @param errorMessage  the runtime error message that the returned equation will produce
	 *
	 *  @return the error equation
	 */
	public Equation getErrorEquation(final String equation, final Class<?> type, final String errorMessage);

	/** Returns the parser which can be used for checking the syntax of equations.
	 *  @return the parser used by the compiler
	 */
	public EquationParser getParser();
}
