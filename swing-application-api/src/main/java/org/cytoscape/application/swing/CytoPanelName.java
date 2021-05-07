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
 * An enum that describes {@link CytoPanel}s uses compass directions
 * to describe their location within the application.
 * 
 * Fields may be added to this enum in the future.
 * 
 * @CyAPI.Enum.Class
 * @CyAPI.InModule swing-application-api
 */
public enum CytoPanelName {
	/**
	 * The south or bottom panel.
	 */
	SOUTH("Table Panel"),

	/**
	 * The east or right-hand panel.
	 */
	EAST("Results Panel"),

	/**
	 * The west or left-hand panel.
	 */
	WEST("Control Panel"),

	/**
	 * The south-west or bottom left panel.
	 */
	SOUTH_WEST("Tool Panel");
	

	private final String title;

	private CytoPanelName(final String title) {
		this.title = title;
	}

	/**
	 * Returns the human readable title used for labeling this CytoPanel.
	 * @return The human readable title used for labeling this CytoPanel.
	 */
	public String getTitle() {
		return title;
	}
}
