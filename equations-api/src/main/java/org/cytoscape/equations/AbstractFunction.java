package org.cytoscape.equations;

import java.util.Arrays;
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
	 * {@inheritDoc}
	 */
	@Override
	public List<ArgDescriptor> getArgumentDescriptors() {
		return Arrays.asList(argDescriptors);
	}
	

	/**
	 * {@inheritDoc}
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
				usage.append(isFirst ? "" : ", ");
			usage.append(argDescriptor.getArgName());

			isFirst = false;
		}

		for (int i = 0; i < optArgCount; ++i)
			usage.append(']');

		usage.append(").");

		return usage.toString();
	}
	
	
	/**
	 * {@inheritDoc}
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
	 * {@inheritDoc}
	 */
	public Class validateArgTypes(final Class[] argTypes) {
		return argTypesAreValid(argTypes) ? getReturnType() : null;
	}

	/**
	 * {@inheritDoc}
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
