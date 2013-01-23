package org.cytoscape.view.presentation.property.values;

/*
 * #%L
 * Cytoscape Presentation API (presentation-api)
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
 * Factory to create an instance of {@linkplain Bend}.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule presentation-api
 */
public interface BendFactory {
	
	/**
	 * Creates an instance of edge bend.
	 * Usually, this creates Bend object without any {@linkplain Handle}s.
	 * 
	 * @return new instance of Bend object.
	 */
	Bend createBend();

	/**
	 * Creates an instance of edge bend from serializable string.
	 * 
	 * @param serializedString string representation of edge bend.
	 * 
	 * @return New instance of Bend (interpretation of the given string).
	 */
	Bend parseSerializableString(final String serializedString);
}
