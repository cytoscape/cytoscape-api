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
import java.util.Set;

/** 
 * Parser for a string representing an equation. 
 * @CyAPI.Api.Interface 
 * @CyAPI.InModule equations-api
 */
public interface EquationParser {
	/**
	 *  @deprecated Functions registered using this method will work, but will not update properly 
	 *  if the containing bundle is updated. To register a Function, please use CyActivator or CyServiceRegistrar's
	 *  registerService method to register as a service so updates can be handled properly.
	 *  @param func  the function that will be registered
	 *  @throws IllegalArgumentException will be thrown if "func" is null or the function has previously been registered
	 */
	@Deprecated
	void registerFunction(final Function func) throws IllegalArgumentException;

	/**
	 * Returns the function associated with the name "functionName" or null if no such function exists.
	 *  @param functionName the name of the function to get.
	 * @return the function associated with the name "functionName" or null if no such function exists.
	 */
	Function getFunction(final String functionName);

	/**
	 * Returns the set of currently registered functions.
	 *  @return the set of currently registered functions
	 */
	Set<Function> getRegisteredFunctions();

	/**
	 * Returns true if the parse succeeded otherwise false
	 *  @param eqn                  a valid attribute equation which must start with an equal sign
	 *  @param attribNameToTypeMap  a list of existing attribute names and their types
	 *  @return true if the parse succeeded otherwise false
	 */
	boolean parse(final String eqn, final Map<String, Class<?>> attribNameToTypeMap);

	/**
	 * Returns the result type of the parsed equation if the parse succeeded, otherwise null.
	 *  @return the result type of the parsed equation if the parse succeeded, otherwise null.
	 */
	Class<?> getType();

	/**
	 *  If parse() failed, this will return the last error messages.
	 *  @return the last error message of null
	 */
	String getErrorMsg();

	/**
	 * Returns all the variable names that have been detected in the most recently parsed equation.
	 *  @return all the variable names that have been detected in the most recently parsed equation.
	 */
	Set<String> getVariableReferences();

	/**
	 *  Returns a map of variable names to their default values, if any.
	 *  @return a map of variable names to their default values, if any.
	 */
	Map<String, Object> getDefaultVariableValues();

	/**
	 * Returns the parse tree. Must only be called if parse() returns true!
	 *  @return the parse tree.
	 */
	TreeNode getParseTree();
}
