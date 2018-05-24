package org.cytoscape.util.color;

import java.awt.Color;

/*
 * #%L
 * Cytoscape Color API (utils-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2018 The Cytoscape Consortium
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
 * This is an interface for defining types of palettes.  It's main use is to provide a framework
 * for defining enums for types of palettes.
 */
public interface PaletteType {
	public String toString();
}
