/*
 Copyright (c) 2008, The Cytoscape Consortium (www.cytoscape.org)

 The Cytoscape Consortium is:
 - Institute for Systems Biology
 - University of California San Diego
 - Memorial Sloan-Kettering Cancer Center
 - Institut Pasteur
 - Agilent Technologies

 This library is free software; you can redistribute it and/or modify it
 under the terms of the GNU Lesser General Public License as published
 by the Free Software Foundation; either version 2.1 of the License, or
 any later version.

 This library is distributed in the hope that it will be useful, but
 WITHOUT ANY WARRANTY, WITHOUT EVEN THE IMPLIED WARRANTY OF
 MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.  The software and
 documentation provided hereunder is on an "as is" basis, and the
 Institute for Systems Biology and the Whitehead Institute
 have no obligations to provide maintenance, support,
 updates, enhancements or modifications.  In no event shall the
 Institute for Systems Biology and the Whitehead Institute
 be liable to any party for direct, indirect, special,
 incidental or consequential damages, including lost profits, arising
 out of the use of this software and its documentation, even if the
 Institute for Systems Biology and the Whitehead Institute
 have been advised of the possibility of such damage.  See
 the GNU Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation,
 Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
*/
package org.cytoscape.view.model;



/**
 * An object which represents a type of visual entity, such as node color, size, etc.
 * 
 * Visual Property itself does NOT have any hierarchy/dependency.  It will be implemented in VisualLexicon.
 * 
 * @param <T> the dataType of the VisualProperty, ie. what kind of objects are the values
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
	 * A short string used to identify this visual property and suitable for
	 * serializing to XML and other text formats.
	 * @return A short string used to identify this visual property and suitable for serialization.
	 */
	String getIdString();

	
	/**
	 * A short string suitable for presentation to humans.  Should not be used
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
	Class<?> getTargetDataType();

}
