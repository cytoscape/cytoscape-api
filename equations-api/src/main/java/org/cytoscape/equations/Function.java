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

import java.util.List;

/** 
 * The basic description of a Function.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule equations-api
 */
public interface Function {
	/**
	 *  Used to parse the function string.  This name is treated in a case-insensitive manner!
	 *  @return the name by which you must call the function when used in an attribute equation.
	 */
	String getName();

	/**
	 *  Used to provide help for users.  Unlike getUsageDescription(), this is an informal English description,
	 *  like "Calculates the sine of its argument."
	 *
	 *  @return a description of what this function does
	 */
	String getFunctionSummary();

	/**
	 *  Used to provide help for users.  Unlike getFunctionSummary(), this describes how to call this function,
	 *  like "Call with SIN(number)."
	 *
	 *  @return a description of how to use this function
	 */
	String getUsageDescription();

	/**
	 *  @return the static return type of this function, Object.class, Double.cLass, String.class, or Boolean.class.
	 *           If the static return type is Object.class, the dynamic return type will be one of Double.cLass, String.class, or Boolean.class
	 *           and will depend on the arguments passed to the function!
	 *
	 *  Note, this is used by external tools used to filter a list of functions based on what a valid return type might be.
	 *  In Cytoscape it is used in the attribute browser's formula builder.
	 */
	Class<?> getReturnType();

	/**
	 *  @return the return type for this function (Double.cLass, String.class, or Boolean.class)
	 *           or null if the args passed in had the wrong arity or a type mismatch
	 *
	 *  Note that this is different from getReturnType() in that it will never return the wildcard Object.class.
	 *  It is used by the parser which knows the actual type of the arguments in any given call to this function.
	 */
	Class<?> validateArgTypes(final Class<?>[] argTypes);

	/**
	 *  Used to invoke this function.
	 *  @param args the function arguments which must correspond in type and number to what getParameterTypes() returns.
	 *  @return the result of the function evaluation.  The actual type of the returned object will be what getReturnType() returns.
	 *  @throws FunctionError 
	 *  @throws ArithmeticException thrown if a numeric error, e.g. a division by zero occurred.
	 *  @throws IllegalArgumentException thrown for any error that is not a numeric error, for example if a function only accepts positive numbers and a negative number was passed in.
	 */
	Object evaluateFunction(final Object[] args) throws FunctionError;

	/**
	 *  Used with the equation builder.
	 *
	 *  @param leadingArgs the types of the arguments that have already been selected by the user.
	 *  @return the set of arguments (must be a collection of String.class, Long.class, Double.class, Boolean.class and List.class) that are candidates for the next argument.  A null return indicates that no further arguments are valid.
	 *  Please note that if the returned set contains a null, this indicates an optional additional argument.
	 */
	List<Class<?>> getPossibleArgTypes(final Class<?>[] leadingArgs);
	
	
	/**
	 * Returns the usage descriptions for the function's arguments. Used to provide help for users.
	 * 
	 * This is an optional operation, may return null.
	 * 
	 * @since 3.9
	 */
	default List<ArgDescriptor> getArgumentDescriptors() { return null; };
}
