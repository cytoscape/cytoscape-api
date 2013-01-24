package org.cytoscape.view.vizmap;

/*
 * #%L
 * Cytoscape VizMap API (vizmap-api)
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

import org.cytoscape.view.model.VisualProperty;

/**
 * Factory for {@linkplain VisualMappingFunction}s. Implementation of this will
 * be provided as an service. One mapping type should have one factory.
 * 
 * @CyAPI.Api.Interface
 */
public interface VisualMappingFunctionFactory {

	/**
	 * Create a new {@linkplain VisualMappingFunction}.
	 * 
	 * @param <K>
	 *            Data type of controlling attribute.
	 * @param <V>
	 *            Data type of {@linkplain VisualProperty}, such as
	 *            {@linkplain Double}, {@linkplain String}, etc.
	 * 
	 * @param attributeName
	 *            Controlling attribute name. This is a name of the column in a
	 *            data table.
	 * @param attrValueType
	 *            Data type of controlling attribute.
	 * @param vp
	 *            {@linkplain VisualProperty} used in the new mapping
	 * 
	 * @return new VisualMappingFunction.
	 */
	<K, V> VisualMappingFunction<K, V> createVisualMappingFunction(final String attributeName,
			final Class<K> attrValueType, final VisualProperty<V> vp);

	/**
	 * Returns the type of this VisualMappingFunctionFactory.
	 * 
	 * @return the type of this VisualMappingFunctionFactory.
	 */
	Class<?> getMappingFunctionType();
}
