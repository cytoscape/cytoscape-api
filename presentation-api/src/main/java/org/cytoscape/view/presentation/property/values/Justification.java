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

/**
 * An ENUM that provides information about text justification.
 * @CyAPI.Enum.Class
 * @CyAPI.InModule presentation-api
 */
public enum Justification {
	JUSTIFY_CENTER("Center Justified", "c"), 
	JUSTIFY_LEFT("Left Justified", "l"), 
	JUSTIFY_RIGHT("Right Justified", "r");
	
	private static String[] JUSTIFY;

	private final String displayName;
	private final String shortName;

	private Justification(final String displayName, final String shortName) {
		this.displayName = displayName;
		this.shortName = shortName;
	}

	/**
	 * Return the display name of this justification type.
	 *
	 * @return display name
	 */
	public String getName() {
		return this.displayName;
	}

	/**
	 * Return the short name of this justification type
	 *
	 * @return short name
	 */
	public String getShortName() {
		return this.shortName;
	}

	/**
	 * Parse a string that contains either the short name or
	 * the display name for a justifcation and return the
	 * corresponding enum value
	 *
	 * @param value the String containing the text to be parsed
	 * @return Justification corresponding to the string
	 */
	public static Justification parse(final String value) {
		for (final Justification j : values()) {
			if (j.getName().equals(value) || j.getShortName().equals(value))
				return j;
		}
		return null;
	}


	/**
	 * Return the display names for all of the Justifcation values
	 *
	 * @return array of display names
	 */
	public static String[] getNames() {
		// Lazy instantiation
		if(JUSTIFY == null) {
			JUSTIFY = new String[values().length];
			int i = 0;
			for(Justification j: values()) {
				JUSTIFY[i] = j.displayName;
				i++;
			}
		}
		
		return JUSTIFY;
	}

}
