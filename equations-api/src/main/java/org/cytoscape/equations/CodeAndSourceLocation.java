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
 * Encapsulates compiled code with the location in the original source equation.
 * @CyAPI.Final.Class
 * @CyAPI.InModule equations-api
 */
public final class CodeAndSourceLocation {
	private final Object code;
	private final int sourceLocation;

	public CodeAndSourceLocation(final Object code, final int sourceLocation) {
		this.code            = code;
		this.sourceLocation = sourceLocation;
	}

	public Object getCode() { return code; }
	public int getSourceLocation() { return sourceLocation; }
}
