package org.cytoscape.view.presentation.property.values;

/*
 * #%L
 * Cytoscape Ding View/Presentation Impl (ding-presentation-impl)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
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

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * An ENUM that provides values for the various anchors that
 * may be used for text, customgraphics, etc.
 *
 * @CyAPI.Enum.Class
 * @CyAPI.InModule presentation-api
 */
public enum Position {

	NORTH_WEST("Northwest", "NW"), 
	NORTH("North", "N"), 
	NORTH_EAST("Northeast","NE"), 
	WEST("West", "W"), 
	CENTER("Center", "C"), 
	EAST("East", "E"), 
	NONE("None", "NONE"), 
	SOUTH_WEST("Southwest", "SW"), 
	SOUTH("South", "S"), 
	SOUTH_EAST("Southeast", "SE");

	private static SortedSet<String> displayNames;

	private final String displayName;
	private final String shortName;

	private Position(final String displayName, final String shortName) {
		this.displayName = displayName;
		this.shortName = shortName;
	}

	/**
	 * Return the display name of this anchor position.
	 *
	 * @return display name
	 */
	public String getName() {
		return this.displayName;
	}

	/**
	 * Return the short name of this anchor position.
	 *
	 * @return short name
	 */
	public String getShortName() {
		return this.shortName;
	}
	
	/**
	 * Parse a string that contains either the short name or
	 * the display name for an anchor and return the
	 * corresponding enum value
	 *
	 * @param value the String containing the text to be parsed
	 * @return Position corresponding to the string
	 */
	public static Position parse(final String value) {
		for (final Position p : Position.values()) {
			if (p.getName().equals(value) || p.getShortName().equals(value))
				return p;
		}

		// If not found, return center.
		return CENTER;
	}

	/**
	 * Return a sorted list containing all of the display
	 * names for all of the Position anchors.
	 *
	 * @return sorted list of display names
	 */
	public static SortedSet<String> getDisplayNames() {
		if(displayNames == null) {
			displayNames = new TreeSet<String>();
			for(Position p: values()) {
				displayNames.add(p.displayName);
			}
		}
		
		return displayNames;
	}
}
