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
import java.util.ArrayList;


/**
 *  A class describing a function argument.
 */
public final class ArgDescriptor {
	private ArgType argType;
	private String argName;
	private String description;

	public ArgDescriptor(final ArgType argType, final String argName, final String description) {
		this.argType = argType;
		this.argName = argName;
		this.description = description;
	}

	public ArgType getArgType() { return argType; }
	public String getArgName() { return argName; }
	public String getDescription() { return description; }
	public boolean isOptional() { return argType.isOptional(); }

	public boolean isCompatibleWith(final Class type) {
		if (FunctionUtil.isSomeKindOfList(type))
			return isCompatibleList(type);

		for (final Class compatibleType : argType.getCompatibleTypes()) {
			if (type == compatibleType)
				return true;
		}

		return false;
	}

	/**
	 *  @return true if "type" is a List type that specifies the element type otherwise we return false
	 */
	private boolean isSpecificList(final Class type) {
		return type == DoubleList.class || type == LongList.class || type == StringList.class || type == BooleanList.class;
	}

	/**
	 *  @return true if "listType", which must be some type of List is a type compatible with this argument descriptor
	 */
	private boolean isCompatibleList(final Class listType) {
		// First we handle the case where "listType" is highly specific...
		if (isSpecificList(listType)) {
			for (final Class compatibleType : argType.getCompatibleTypes()) {
				if (compatibleType == listType || compatibleType == List.class)
					return true;
			}
			return false;
		}

		// ...and here we handle the case where we don't have any information about the argument types of "listType":
		for (final Class compatibleType : argType.getCompatibleTypes()) {
                        if (FunctionUtil.isSomeKindOfList(compatibleType))
                                return true;
		}

                return false;
	}

	/**
	 *  @return the types that are compatible with this argument
	 */
	public Class[] getCompatibleTypes() {
		return argType.getCompatibleTypes();
	}

	public boolean acceptsMultipleArgs() {
		return argType.acceptsMultipleArgs();
	}
}
