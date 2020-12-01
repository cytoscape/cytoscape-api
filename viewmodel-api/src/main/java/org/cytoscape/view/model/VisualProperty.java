package org.cytoscape.view.model;

import java.util.Comparator;

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
 * An object which represents a type of visual entity, such as node color, size, etc.
 * 
 * Visual Property itself does NOT have any hierarchy/dependency.  It will be implemented in VisualLexicon.
 * 
 * @param <T> the dataType of the VisualProperty, i.e. what kind of objects are the values
 * @CyAPI.Api.Interface
 * @CyAPI.InModule viewmodel-api
 */
public interface VisualProperty<T> {
	
	/**
	 * Returns the {@link Range} of this VisualProperty.
	 * @return the {@link Range} of this VisualProperty.
	 */
	Range<T> getRange();
	

	/**
	 * The default value of this property.  This value is immutable.
	 * @return  The default value of this property.  
	 */
	T getDefault();

	
	/**
	 * Returns a short string used to identify this visual property and suitable for
	 * serializing to XML and other text formats.
	 * @return A short string used to identify this visual property and suitable for serialization.
	 */
	String getIdString();

	
	/**
	 * Returns a short string suitable for presentation to humans.  Should not be used
	 * for serialization.
	 * @return  A short string suitable for presentation to humans.
	 */
	String getDisplayName();

	

	/**
	 * Returns a string of the specified value suitable for serializing to XML
	 * other text output.
	 * @param value the specified value.
	 * @return a string of the specified value suitable for serializing to XML
	 * other text output.
	 */
	String toSerializableString(final T value);

	
	/**
	 * Returns an object of type T given a string serialized from the getSerializableString(T value)
	 * method.
	 * @param value a string serialized from the getSerializableString(T value) method.
	 * @return an object of type T given a string serialized from the getSerializableString(T value)
	 * method.
	 */
	T parseSerializableString(final String value);
	
	
	/**
	 * In some cases, default value from visual style is not suitable, such as x, y, z location of nodes.
	 * If this flag is on, it will be ignored and it will be controlled by mapping only.
	 * @return true if the default value should be ignored (as with x,y,z locations) and false otherwise.
	 */
	boolean shouldIgnoreDefault();
	
	
	/**
	 * VisualProperty is always associated with a data type.  For example, EDGE_COLOR is associated with 
	 * {@link org.cytoscape.model.CyEdge} data object.  In that case, this returns Class&lt;CyEdge&gt;.  
	 * For now, return data types are CyNode, CyEdge, and CyNetwork.
	 * 
	 * @return target data type of this visual property.  CyNode, CyEdge, or CyNetwork.
	 * 
	 */
	Class<? extends CyIdentifiable> getTargetDataType();
	
	
	/**
	 * Utility method for sorting collections of Views according to the value of a VisualProperty.
	 * The VisualProperty value type must implement {@link Comparable}.
	 * Null values are sorted first.
	 * <br><br>
	 * Example:
	 * <pre>
	 * // sort node views by z-order
	 * List&lt;View&lt;CyNode&gt;&gt; nodeViews = new ArrayList<>(networkView.getNodeViews());
	 * nodeViews.sort(VisualProperty.comparing(BasicVisualLexicon.NODE_Z_LOCATION));
	 * </pre>
	 * 
	 * @since 3.9
	 */
	public static <T, V extends Comparable<V>> Comparator<View<T>> comparing(VisualProperty<V> vp) {
		return Comparator.comparing(view -> view.getVisualProperty(vp), Comparator.nullsFirst(Comparator.naturalOrder()));
	}

}
