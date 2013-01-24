/*
  File: ArgDescriptor.java

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


import java.util.List;


/** 
 * A class describing a function argument.
 * @CyAPI.Final.Class
 * @CyAPI.InModule equations-api
 */
public final class ArgDescriptor {
	private ArgType argType;
	private String argName;
	private String description;

	/**
	 * @param argType The type of the argument.
	 * @param argName The name of the argument.
	 * @param description The description of the argument.
	 */
	public ArgDescriptor(final ArgType argType, final String argName, final String description) {
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
