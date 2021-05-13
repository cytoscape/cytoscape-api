package org.cytoscape.view.vizmap.gui.util;

/*
 * #%L
 * Cytoscape VizMap GUI API (vizmap-gui-api)
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

import java.util.Map;
import java.util.Set;

/**
 * Generates Discrete mapping for a set of attribute values and a Visual
 * Property.
 * 
 * @param <V> the type of the Visual Property.
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule vizmap-gui-api
 */
public interface DiscreteMappingGenerator<V> {

	/**
	 * For a given set of attribute values, create discrete mapping.
	 * 
	 * @param <T>
	 *            the type of the attribute values.
	 * @param attributeSet
	 *            the set of attribute values.
	 * @return a map between an attribute type and a visual property type.
	 */
	<T> Map<T, V> generateMap(final Set<T> attributeSet);

	/**
	 * Returns the type of the visual property.
	 * 
	 * @return the type of the visual property.
	 */
	Class<V> getDataType();
}
