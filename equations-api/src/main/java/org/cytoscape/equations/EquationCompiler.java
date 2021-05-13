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

import java.util.Map;


/**
 * Compiler that compiles equations to byte (non-Java) code. 
 * @CyAPI.Api.Interface 
 * @CyAPI.InModule equations-api
 */
public interface EquationCompiler {
	/** Compiles a string to byte code plus some metadata as encapsulated by the <code>Equation</code> class
	 *  @param equation               a textual representation of an equation
	 *  @param variableNameToTypeMap  a mapping from variable names occurring in "equation" to their respective types
	 * @return true if the compiler successfully compiles the equation. 
	 */
	boolean compile(final String equation, final Map<String, Class<?>> variableNameToTypeMap);

	/** Returns an error message for an error encountered during a call to compile, if any.
	 *  @return a human readable error message
	 *  Please note that it is probably a good idea to only call this method if compile() returned false.
	 */
	String getLastErrorMsg();
	
	/**
	 * If compile() failed, this will return the index in the equation where the error was encountered.
	 * The value -1 is returned if the compile did not fail or if the error location could not be determined.
	 * @return the error location 
	 */
	int getErrorLocation();

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
