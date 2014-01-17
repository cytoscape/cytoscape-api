package org.cytoscape.view.model;

/*
 * #%L
 * Cytoscape View Model API (viewmodel-api)
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


/**
 * The base interface that defines methods used to set visual properties
 * for nodes, edges, and networks.
 *
 * Think of it as a row in the view-model table.
 *
 * @param <S> the base (model-level) object for which this is a View. For example, CyNode or CyEdge.
 * In the future versions, Attributes can be this parameter.
 * @CyAPI.Api.Interface 
 * @CyAPI.InModule viewmodel-api
 */
public interface View<S> extends CyIdentifiable {
	
	/**
	 * Assign a value to the given Visual Property of this view.
	 * 
	 * @param <T> Data type of the visual property.  This can be subclasses of type T.
	 * @param <V> Value of the visual property.  This can be subclasses of T. 
	 * 
	 * @param vp the VisualProperty (Node Color, Edge Width, etc.)
	 * @param value actual value stored in this visual property.
	 */
	<T, V extends T> void setVisualProperty(VisualProperty<? extends T> vp, V value);
	

	/**
	 * Get the actual value for the given visual property.
	 * 
	 * @param <T> Object type of the Visual Property (Color, Double, ...)
	 * 
	 * @param vp the VisualProperty.
	 * @return Value associated with this View and Visual Property pair.
	 */
	<T> T getVisualProperty(VisualProperty<T> vp);
	

	/**
	 * Indicates whether or not the passed visual property is set with a non-null value.
	 * @param vp the VisualProperty.
	 * @return true if the view has a non-null value for the VisualProperty.
	 */
	boolean isSet(VisualProperty<?> vp);
	
	
	/**
	 * Set locked value.  This value will be used to bypass the style.
	 * 
	 * @param <T> Data type of the visual property.  This can be subclasses of type T.
	 * @param <V> Value of the visual property.  This can be subclasses of T. 
	 * @param vp the VisualProperty
	 * @param value the value that will bypass the style
	 */
	<T, V extends T> void setLockedValue(VisualProperty<? extends T> vp, V value);

	
	/**
	 * Returns true if the value of the given VisualProperty or one of its
	 * ancestors is locked.
	 * @param vp the VisualProperty
	 * @return true if value of the given VisualProperty value or one of its
	 * ancestors is locked.
	 */
	boolean isValueLocked(VisualProperty<?> vp);

	
	/**
	 * Clear value lock for given VisualProperty.
	 *
	 * @param vp the VisualProperty.
	 */
	void clearValueLock(VisualProperty<?> vp);

	
	/**
	 *  Get source data structure, such as CyNode, CyEdge, etc.
	 *
	 * @return Data object of this view.
	 */
	S getModel();


	/**
	 * Returns true if the given VisualProperty's value has been locked.
	 * directly.
	 * @param vp the VisualProperty
	 * @return true if the given VisualProperty's value has been locked.
	 */
	boolean isDirectlyLocked(VisualProperty<?> vp);


	/**
	 * Clear all VisualProperty values previously set to this view.
	 */
	void clearVisualProperties();
	
}
