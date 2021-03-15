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
 * A class describing a function argument.
 * @CyAPI.Final.Class
 * @CyAPI.InModule equations-api
 */
public final class ArgDescriptor {
	private final ArgType argType;
	private final String argName;
	private final String description;

	/**
	 * @param argType The type of the argument.
	 * @param argName The name of the argument.
	 * @param description The description of the argument.
	 */
	public ArgDescriptor(ArgType argType, String argName, String description) {
		this.argType = argType;
		this.argName = argName;
		this.description = description;
	}

	/**
	 * @return The type of the argument.
	 */
	public ArgType getArgType() { return argType; }

	/**
	 * Returns the name of the argument.
	 * @return The name of the argument.
	 */
	public String getArgName() { return argName; }

	/**
	 * Returns the description of the argument.
	 * @return The description of the argument.
	 */
	public String getDescription() { return description; }

	/**
	 * Returns whether the ArgType is optional. 
	 * @return whether the ArgType is optional. 
	 */
	public boolean isOptional() { return argType.isOptional(); }

	/**
	 * Tests to see if the specified class is compatible with this ArgType.
	 * @param type the Class to test against this ArgType.
	 * @return true if the specified class is compatible with this ArgType.
	 */
	public boolean isCompatibleWith(final Class type) {
		for (final Class compatibleType : argType.getCompatibleTypes()) {
			if (type == compatibleType)
				return true;
			if (List.class.equals(compatibleType) && List.class.isAssignableFrom(type))
				return true;
		}

		return false;
	}

	/**
	 * Returns true if "listType", which must be some type of List is a type compatible with this argument descriptor.
	 * @param listType A type that is a subclass of List.
	 * @return true if "listType", which must be some type of List is a type compatible with this argument descriptor.
	 */
	public boolean isCompatibleList(final Class listElementType) {
		// First we handle the case where "listType" is highly specific...
		if (listElementType != null) {
			for (final Class compatibleType : argType.getCompatibleTypes()) {
				if (compatibleType == listElementType)
					return true;
			}
			return false;
		}

		// ...and here we handle the case where we don't have any information about the argument types of "listType":
		for (final Class compatibleType : argType.getCompatibleTypes()) {
			if (FunctionUtil.isTypeOfList(compatibleType))
				return true;
		}

		return false;
	}

	/**
	 * Returns the types that are compatible with this argument.
	 * @return the types that are compatible with this argument.
	 */
	public Class[] getCompatibleTypes() {
		return argType.getCompatibleTypes();
	}

	/**
	 * Returns true if this ArgDescriptor accepts multiple arguments, otherwise false.
	 * @return true if this ArgDescriptor accepts multiple arguments, otherwise false.
	 */
	public boolean acceptsMultipleArgs() {
		return argType.acceptsMultipleArgs();
	}
}
