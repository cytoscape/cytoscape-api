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

/**
 * A class describing an error in a function. 
 * @CyAPI.Final.class
 * @CyAPI.InModule equations-api
 */
public final class FunctionError extends Exception {
	private static final long serialVersionUID = 0xDAEDEBECFAEF2134L;
	private final int argNumber; // Which argument this error is associated with.

	/**
	 * Constructor.
	 * @param message The error message.
	 * @param argNumber The argument number associated with the error. 
	 */
	public FunctionError(final String message, final int argNumber) {
		super(message);

		this.argNumber = argNumber;
	}

	/**
	 * Returns the argument number the error is associated with.
	 * @return the argument number the error is associated with.
	 */
	public int getArgNumber() { return argNumber; }
}
