/*
  File: ArgType.java

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
 * An enum specifying a function argument type.
 * @CyAPI.Enum.Class
 * @CyAPI.InModule equations-api
 */
public enum ArgType {
	//                 opt.  mult.args.                          compatible arg. types
	//--------------------------------------------------------------------------------------------------------
	/** An argument that can be converted to an integer. */
	INT(              false,   false,  new Class[] { Long.class, Double.class, String.class, Boolean.class }),

	/** An argument that can be converted to a floating point value. */
	FLOAT(            false,   false,  new Class[] { Double.class, Long.class, String.class, Boolean.class }),

	/** An argument that can be converted to a string. */
	STRING(           false,   false,  new Class[] { String.class, Double.class, Long.class, Boolean.class }),

	/** An argument that can be converted to a boolean. */
	BOOL(             false,   false,  new Class[] { Boolean.class, Double.class, Long.class, String.class }),

	/** Any scalar argument. */
	ANY(              false,   false,  new Class[] { Boolean.class, Double.class, Long.class, String.class }),

	/** An non-empty sequence of arguments that consist of lists of integers and scalars that can be converted to an integer. */
	INTS(             false,   true,   new Class[] { List.class, Long.class, Double.class, String.class, Boolean.class }),

	/** An non-empty sequence of arguments that consist of lists of floating point numbers and scalars that can be converted
	    to a floating point number. */
	FLOATS(           false,   true,   new Class[] { List.class, Double.class, Long.class, String.class, Boolean.class }),

	/** An non-empty sequence of arguments that consist of lists of strings and scalars that can be converted to a string. */
	STRINGS(          false,   true,   new Class[] { List.class, String.class, Double.class, Long.class, Boolean.class }),

	/** An non-empty sequence of arguments that consist of lists of booleans and scalars that can be converted to a boolean. */
	BOOLS(            false,   true,   new Class[] { List.class, Boolean.class, Double.class, Long.class, String.class }),

	/** A singke integer argument. */
	STRICT_INT(       false,   false,  new Class[] { Long.class }),

	/** A single floating point number argument. */
	STRICT_FLOAT(     false,   false,  new Class[] { Double.class }),

	/** A single string argument. */
	STRICT_STRING(    false,   false,  new Class[] { String.class }),

	/** A single boolean argument. */
	STRICT_BOOL(      false,   false,  new Class[] { Boolean.class }),

	/** An optional integer argument. */
	OPT_INT(          true,    false,  new Class[] { Long.class, Double.class, String.class, Boolean.class}),

	/** An optional floating point argument. */
	OPT_FLOAT(        true,    false,  new Class[] { Double.class, Long.class, String.class, Boolean.class }),

	/** An optional string argument. */
	OPT_STRING(       true,    false,  new Class[] { String.class, Double.class, Long.class, Boolean.class }),

	/** An optional boolean argument. */
	OPT_BOOL(         true,    false,  new Class[] { Boolean.class, Double.class, Long.class, String.class }),

	/** Zero or more arguments that consist of lists of integers and scalars that can be converted to an integer. */
	OPT_INTS(         true,    true,   new Class[] { List.class, Long.class, Double.class, String.class, Boolean.class }),

	/** Zero or more arguments that consist of lists of floating point numbers and scalars that can be converted
	    to a floating point number. */
	OPT_FLOATS(       true,    true,   new Class[] { List.class, Double.class, Long.class, String.class, Boolean.class }),

	/** Zero or more arguments that consist of lists of strings and scalars that can be converted to a string. */
	OPT_STRINGS(      true,    true,   new Class[] { List.class, String.class, Double.class, Long.class, Boolean.class }),

	/** Zero or more arguments that consist of lists of booleans and scalars that can be converted to a boolean. */
	OPT_BOOLS(        true,    true,   new Class[] { List.class, Boolean.class, Double.class, Long.class, String.class }),

	/** An optional integer argument. */
	OPT_STRICT_INT(   true,    false,  new Class[] { Long.class }),

	/** An optional floating point number argument. */
	OPT_STRICT_FLOAT( true,    false,  new Class[] { Double.class }),

	/** An optional string argument. */
	OPT_STRICT_STRING(true,    false,  new Class[] { String.class }),

	/** An optional boolean argument. */
	OPT_STRICT_BOOL(  true,    false,  new Class[] { Boolean.class }),

	/** One or more lists with arbitrary member element types and/or one or more scalars. */
	ANY_LIST(         false,   true,  new Class[] { List.class, Double.class, Long.class, String.class, Boolean.class }),

	/** A list with arbitrary member element types. */
	STRICT_ANY_LIST(  false,   false,  new Class[] { List.class }),

	/** Zero or more lists with arbitrary member element types and/or zero or more scalars. */
	OPT_ANY_LIST(     true,    true,  new Class[] { List.class, Double.class, Long.class, String.class, Boolean.class });
	
	private boolean isOptional;
	private boolean acceptsMultipleArgs;
	private Class[] compatibleTypes;

	ArgType(final boolean isOptional, final boolean acceptsMultipleArgs, final Class[] compatibleTypes) {
		this.isOptional = isOptional;
		this.acceptsMultipleArgs = acceptsMultipleArgs;
		this.compatibleTypes = compatibleTypes;
	}

	/** 
	 * Returns true if this type is optional. 
	 * @return true if this type is optional. 
	 */

	public boolean isOptional() { return isOptional; }
	/** 
	 * Returns true if this type accepts multiple args.
	 * @return true if this type accepts multiple args.
	 */
	public boolean acceptsMultipleArgs() { return acceptsMultipleArgs; }

	/** 
	 * Returns an array of compatible Class types.
	 * @return an array of compatible Class types.
	 */
	public Class[] getCompatibleTypes() { return compatibleTypes; }
}
