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
 * Object used as a Visual Property value.
 * In most cases, Visual Property uses basic objects such as Number or Color.
 * If developers want to use custom objects as a Visual Property value, they should implement this.
 * 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule presentation-api
 */
public interface VisualPropertyValue {
	
	/**
	 * Name of this value type.
	 * For example, Arrow Shape or Node Shape.
	 * 
	 * @return human readable name of this object type.
	 */
	String getDisplayName();
	
	
	/**
	 * Returns serializable representation of this value.
	 * 
	 * @return Value in serializable string form.
	 */
	String getSerializableString();
	
}
