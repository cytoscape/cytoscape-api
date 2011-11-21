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
package org.cytoscape.view.vizmap;

import org.cytoscape.model.CyTable;
import org.cytoscape.model.CyTableEntry;
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
	 * Returns table used for this mapping.
	 * 
	 * @return CyTable associated with this mapping.
	 */
	CyTable getMappingTable();

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
	 * @param view
	 *            target View model to be updated. View should be one of the
	 *            following: Node, Edge, or Network.
	 */
	void apply(final View<? extends CyTableEntry> view);
}
