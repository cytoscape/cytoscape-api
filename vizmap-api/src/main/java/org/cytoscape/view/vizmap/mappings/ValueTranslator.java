package org.cytoscape.view.vizmap.mappings;

/*
 * #%L
 * Cytoscape VizMap API (vizmap-api)
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
 * Translate given table value to a Visual Property value.
 * This will be used by Passthrough Mapping.
 *
 * @param <V> Data type of the column used for mapping
 * @param <T> Type of Visual Property range value.  Such as Color, Number, String.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule vizmap-api
 */
public interface ValueTranslator<V, T> {
	
	/**
	 * Convert input value to Visual Property value.
	 * For example, if this is a translator from text representation of color to Color object, 
	 * inputValue is a String value and return value is a Color build from the given string.
	 * 
	 * @param inputValue table value of type V.
	 * @return translated Visual Property value.
	 */
	public T translate(final V inputValue);
	
	
	/**
	 * Returns compatible input data type.
	 * 
	 * @return data type of input value.
	 */
	public Class<T> getTranslatedValueType();
}
