package org.cytoscape.application.swing;

/*
 * #%L
 * Cytoscape Swing Application API (swing-application-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2021 The Cytoscape Consortium
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
 * The different display states available for a {@link CytoPanel}.
 * @CyAPI.Enum.Class
 * @CyAPI.InModule swing-application-api
 */
public enum CytoPanelState {
	/**
	 * The {@link CytoPanel} will be hidden and only appear as a menu item.
	 */
	HIDE,

	/**
	 * The {@link CytoPanel} will be open and appear as a separate frame 
	 * independent of the application.
	 */
	FLOAT,

	/**
	 * The {@link CytoPanel} will be open and appear as a nested frame 
	 * within the application.
	 */
	DOCK,
	;
}
