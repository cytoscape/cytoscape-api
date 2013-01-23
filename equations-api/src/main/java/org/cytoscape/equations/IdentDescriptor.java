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
 * Used to hold a current value for an equation's variable reference.
 * @CyAPI.Final.Class
 * @CyAPI.InModule equations-api
 */
public final class IdentDescriptor {
	private final Class type;
	private final Object value;

	/** Initializes a new <code>IdentDescriptor</code> and provides minimal type translation
	 *  (from <code>Integer</code> to <code>Long</code>).
	 *  @param o  an object that represents a value for a variable reference
	 *  @throws NullPointerException  if "o" is null
	 *  @throws IllegalArgumentException  if "o" is not a <code>Long</code>, <code>Integer</code>,
	 *          <code>Double</code>, <code>Boolean</code> nor a <code>String</code>
	 */
	public IdentDescriptor(final Object o) {
		if (o == null)
			throw new NullPointerException("argument must not be null.");

		if (o.getClass() == Integer.class) { // Need to map Integer to Long!
			final Integer i = (Integer)o;
			this.type = Long.class;
			this.value = new Long(i);
			return;
		}
		else if (o instanceof List)
			this.type = List.class;
		else if (o.getClass() != Long.class && o.getClass() != Double.class && o.getClass() != Boolean.class
			 && o.getClass() != String.class)
			throw new IllegalArgumentException("argument is of an unsupported type (" + o.getClass() + ").");
		else
			this.type = o.getClass();

		this.value = o;
	}

	/** Returns the, possibly adjusted, type of the descriptor as one of the types internally supported by equations.
	 *  @return the (translated) type of the descriptor
	 */
	public Class getType() { return type; }

	/** Returns the value of the descriptor.
	 *  @return the (translated) value of the descriptor
	 */
	public Object getValue() { return value; }
}
