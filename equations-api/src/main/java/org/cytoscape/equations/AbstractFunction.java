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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


/**
 * Base class for all {@link Function} implementations. 
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule equations-api
 */
public abstract class AbstractFunction implements Function {
	private final ArgDescriptor[] argDescriptors;

	/** 
	 * Base class constructor for easy creation of <code>Function</code>s from function argument descriptors.
	 * @param argDescriptors  an array describing the <code>Function</code> argument list
	 */
	protected AbstractFunction(final ArgDescriptor[] argDescriptors) {
		this.argDescriptors = argDescriptors;

		// Ensure that optional args are never followed by nonoptional args:
		boolean lastArgWasOptional = false;
		for (int i = 0; i < argDescriptors.length; ++i) {
			final boolean currentArgIsOptional = argDescriptors[i].isOptional();
			if (lastArgWasOptional && !currentArgIsOptional)
				throw new IllegalArgumentException("invalid argument specification for "
				                                   + getName() + "() optional argument "
				                                   + argDescriptors[i - 1].getArgName()
				                                   + " is followed by non-optional argument "
				                                   + argDescriptors[i].getArgName() + ".");
			lastArgWasOptional = currentArgIsOptional;
		}

		// Ensure that no two args have the same name:
		final Set<String> alreadySeen = new TreeSet<String>();
		for (final ArgDescriptor argDescriptor : argDescriptors) {
			final String argName = argDescriptor.getArgName();
			if (alreadySeen.contains(argName))
				throw new IllegalArgumentException("duplicate argument name "
				                                   + argName + " for " + getName()
				                                   + "() specified multiple times.");
			alreadySeen.add(argName);
		}
	}

	/**
	 *  Used to parse the function string.  This name is treated in a case-insensitive manner!
	 *  @return the name by which you must call the function when used in an attribute equation.
	 */
	public abstract String getName();

	/**
	 *  Used to provide help for users.  Unlike getUsageDescription(), this is an informal English description,
	 *  like "Calculates the sine of its argument."
	 *
	 *  @return a description of what this function does
	 */
	public abstract String getFunctionSummary();

	/**
	 *  Used to provide help for users.  Unlike getFunctionSummary(), this describes how to call this function,
	 *  like "Call with SIN(number)."
	 *
	 *  @return a description of how to use this function
	 */
	public final String getUsageDescription() {
		final StringBuilder usage = new StringBuilder("Call with ");
		usage.append(getName());
		usage.append('(');

		int optArgCount = 0;
		boolean isFirst = true;
		for (final ArgDescriptor argDescriptor : argDescriptors) {
			if (argDescriptor.isOptional()) {
				usage.append(isFirst ? "[" : " [,");
				++optArgCount;
			} else
				usage.append(isFirst ? "" : " ,");
			usage.append(argDescriptor.getArgName());

			isFirst = false;
		}

		for (int i = 0; i < optArgCount; ++i)
			usage.append(']');

		usage.append(").");

		return usage.toString();
	}

	/**
	 * Returns the static return type of this function.
	 * If the static return type is Object.class, the dynamic return type will be one of Double.cLass, 
	 * String.class, or Boolean.class and will depend on the arguments passed to the function!
	 * <br/>
	 * Note, this is used by external tools used to filter a list of functions based on what a valid 
	 * return type might be. In Cytoscape it is used in the attribute browser's formula builder.
	 *
	 * @return the static return type of this function, Object.class, Double.cLass, String.class, or Boolean.class.
	 */
	public abstract Class getReturnType();

	/**
	 * Returns true of false if the args passed in had arity of a type mismatch.
	 * Note that this is different from getReturnType() in that it will never return the wildcard Object.class.
	 * It is used by the parser which knows the actual type of the arguments in any given call to this function.
	 *
	 * @param argTypes the args to check for wrong arity or a type mismatch.
	 * @return true or false if the args passed in had the wrong arity or a type mismatch
	 */
	protected final boolean argTypesAreValid(final Class[] argTypes) {
		int i = 0;

		int currentArgCount = 0;
		ArgDescriptor currentArgDescriptor = null;
		for (final Class argType : argTypes) {
			if (currentArgCount == 0) {
				// Too many actual arguments?
				if (i == argDescriptors.length)
					return false;

				currentArgDescriptor = argDescriptors[i++];
			}

			if (!currentArgDescriptor.isCompatibleWith(argType))
				return false;
			else if (currentArgDescriptor.acceptsMultipleArgs())
				++currentArgCount;
			else // We have a single matching argument and need to move on to the next arg descriptor.
				currentArgCount = 0;
			
		}

		// Everything was ok if we either used up all the arg descriptors or if the
		// current arg descriptor represents an optional argument:
		return i == argDescriptors.length || argDescriptors[i].isOptional();
	}

	/**
	 * Returns the return type of this function.
	 * Note that this is different from getReturnType() in that it will never return the wildcard Object.class.
	 * It is used by the parser which knows the actual type of the arguments in any given call to this function.
	 *
	 * @param argTypes the args to check for wrong arity or a type mismatch.
	 * @return the return type for this function (Double.class, String.class, or Boolean.class)
	 *          or null if the args passed in had the wrong arity or a type mismatch
	 */
	public final Class validateArgTypes(final Class[] argTypes) {
		return argTypesAreValid(argTypes) ? getReturnType() : null;
	}

	/**
	 * Used to invoke this function.
	 * @param args the function arguments which must correspond in type and number to what getParameterTypes() returns.
	 * @return the result of the function evaluation.  The actual type of the returned object will be what 
	 * getReturnType() returns.
	 * @throws ArithmeticException thrown if a numeric error, e.g. a division by zero occurred.
	 * @throws IllegalArgumentException thrown for any error that is not a numeric error, 
	 * for example if a function only accepts positive numbers and a negative number was passed in.
	 */
	public abstract Object evaluateFunction(final Object[] args) throws FunctionError;

	/**
	 *  Used with the equation builder.
	 *
	 *  @param leadingArgs the types of the arguments that have already been selected by the user.
	 *  @return the set of arguments (must be a collection of String.class, Long.class, Double.class,
	 *          Boolean.class and List.class) that are candidates for the next argument.  A null return
	 *          indicates that no further arguments are valid.  Please note that if the returned set
	 *          contains a null, this indicates an optional additional argument.
	 */
	public final List<Class<?>> getPossibleArgTypes(final Class[] leadingArgs) {
		int i = 0;

		int currentArgCount = 0;
		ArgDescriptor currentArgDescriptor = null;
		for (final Class leadingArg : leadingArgs) {
			if (currentArgCount == 0) {
				// Too many actual arguments?
				if (i == argDescriptors.length)
					throw new IllegalStateException("number of arguments is too large.");

				currentArgDescriptor = argDescriptors[i++];
			}

			if (!currentArgDescriptor.isCompatibleWith(leadingArg)) {
				throw new IllegalStateException("incompatible argument type.");
			} else if (currentArgDescriptor.acceptsMultipleArgs())
				++currentArgCount;
			else // We have a single matching argument and need to move on to the next arg descriptor.
				currentArgCount = 0;
		}

		if (currentArgCount == 0 && i == argDescriptors.length)
			return null;

		final List<Class<?>> possibleNextArgs = new LinkedList<Class<?>>();
		if (currentArgCount > 0) { // => We're dealing w/ an argument descriptor that can take multiple actual arguments.
			for (final Class type : currentArgDescriptor.getCompatibleTypes())
				possibleNextArgs.add(type);
			if (i == argDescriptors.length)
				possibleNextArgs.add(null);
		}

		if (i < argDescriptors.length) {
			final ArgDescriptor nextArgDesc = argDescriptors[i];
			for (final Class type : nextArgDesc.getCompatibleTypes())
				possibleNextArgs.add(type);
			if (nextArgDesc.isOptional())
				possibleNextArgs.add(null);
		}

		return possibleNextArgs;
	}
}
