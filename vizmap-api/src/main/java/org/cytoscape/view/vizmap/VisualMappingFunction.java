package org.cytoscape.view.vizmap;

/*
 * #%L
 * Cytoscape VizMap API (vizmap-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2013 The Cytoscape Consortium
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

import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.model.CyRow;
import org.cytoscape.view.model.View;
import org.cytoscape.view.model.VisualProperty;

/**
 * This class defines how an attribute gets mapped to a visual property.<br />
 * 
 * It takes two values:
 * <ul>
 * <li>Attribute value: node name(Strings), expression values(Numbers), ...</li>
 * <li>Visual Property: node size(Numbers), edge color(Color), node
 * shape(NodeShape), ...</li>
 * </ul>
 * 
 * This provides the mapping function from converting the attribute to the
 * visual property. Essentially, this is a map using <K> as the key and <V> as
 * the value.
 * 
 * The direction of mapping is ALWAYS:
 * 
 * <p>
 * K(Attribute) ---> V(Visual Property)
 * </p>
 * 
 * K will be used in implementations.
 * 
 * @param <K>
 *            Attribute object type. This is the key of mapping (Can
 *            be any objects)
 * @param <V>
 *            Visual property value type. (can be any type)
 * @CyAPI.Api.Interface
 * @CyAPI.InModule vizmap-api
 */
public interface VisualMappingFunction<K, V> {

	/**
	 * Returns attribute name used in this mapping. This field is immutable.
	 * 
	 * @return name of attribute (a column name in data table) associated with
	 *         this mapping.
	 */
	String getMappingColumnName();

	/**
	 * Returns data type of mapping attribute.
	 * 
	 * @return data type of controlling attribute.
	 */
	Class<K> getMappingColumnType();

	/**
	 * Visual Property associated with this function. This field is immutable.
	 * 
	 * @return {@linkplain VisualProperty} used in this mapping.
	 */
	VisualProperty<V> getVisualProperty();
	
	/**
	 * Apply mapping to the view model. Once this method is called, Cytoscape
	 * updates the view model and fires proper events.
	 * 
	 * @param row The data row used to create the visual property value
	 * for the specified view.
	 * @param view target View model to be updated. View should be one of the
	 *            following: Node, Edge, or Network.
	 */
	void apply(final CyRow row, final View<? extends CyIdentifiable> view);
	
	/**
	 * Returns the mapped value for a {@linkplain CyRow}.
	 * 
	 * @param row The data row used to create the visual property value for the specified view.
	 * @return The mapped visual property value
	 */
	V getMappedValue(final CyRow row);
}
