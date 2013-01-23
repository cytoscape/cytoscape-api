package org.cytoscape.equations;

/*
 * #%L
 * Cytoscape Equations API (equations-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2010 - 2013 The Cytoscape Consortium
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
 * Executes the code represented by an equation.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule equations-api
 */
public interface Interpreter {
	/** Executes the code represented by "equation".
	 *  @param equation             compiled code and metadata
	 *  @param variableNameToDescriptorMap  a mapping of equation variable references to their types and current values
	 *  @return a Double, Boolean or String object that is the result of a successful execution.
	 *  @throws NullPointerException thrown if either "equation" or "variableNameToDescriptorMap" are null
	 *  @throws ArithmeticException thrown if an arithmetic error was detected like a division by zero etc.
	 *  @throws IllegalArgumentException thrown if a function invocation resulted in a function detecting an invalid argument
	 *  @throws IllegalStateException thrown if an invalid interpreter internal state was reached
	 *
	 *   Please note that for each variable reference in "equation" there needs to be an entry in "variableNameToDescriptorMap"
	 */
	public Object execute(final Equation equation, final Map<String, IdentDescriptor> variableNameToDescriptorMap)
		throws NullPointerException, ArithmeticException, IllegalArgumentException, IllegalStateException;
}
