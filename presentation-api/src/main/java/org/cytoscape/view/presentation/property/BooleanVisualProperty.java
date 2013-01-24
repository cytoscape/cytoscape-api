
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
package org.cytoscape.view.presentation.property; 

import java.util.HashSet;
import java.util.Set;

import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.view.model.AbstractVisualProperty;
import org.cytoscape.view.model.DiscreteRange;
import org.cytoscape.view.model.Range;

/**
 * Visual Property for {@link Boolean} values.
 * 
 * @CyAPI.Final.Class
 * @CyAPI.InModule presentation-api
 */
public final class BooleanVisualProperty extends AbstractVisualProperty<Boolean> { 
	
	private static final Range<Boolean> BOOLEAN_RANGE;
	
	static {
		final Set<Boolean> bRange = new HashSet<Boolean>();
		bRange.add(true);
		bRange.add(false);
		BOOLEAN_RANGE = new DiscreteRange<Boolean>(Boolean.class, bRange); 
	}
	
	/**
	 * Constructor.
	 * @param def The boolean value. 
	 * @param id A machine readable string identifying this visual property used for XML serialization. 
	 * @param displayName A human readable string used for displays and user interfaces. 
	 * @param modelDataType The model data type associated with this visual property, e.g. CyNode, CyEdge, or CyNetwork. 
	 */
	public BooleanVisualProperty(final Boolean def, final String id, final String displayName, final Class<? extends CyIdentifiable> modelDataType) {
		this(def, id, displayName, false, modelDataType);
	}
	
	/**
	 * Constructor.
	 * @param def The boolean value. 
	 * @param id A machine readable string identifying this visual property used for XML serialization. 
	 * @param displayName A human readable string used for displays and user interfaces. 
	 * @param ignoreDefault Whether the default value should be ignored. 
	 * @param modelDataType The model data type associated with this visual property, e.g. CyNode, CyEdge, or CyNetwork. 
	 */
	public BooleanVisualProperty(final Boolean def, final String id, final String displayName, final Boolean ignoreDefault, final Class<? extends CyIdentifiable> modelDataType) {
		super(def, BOOLEAN_RANGE, id, displayName, modelDataType);
		this.shouldIgnoreDefault = ignoreDefault;
	}
	
	
	@Override
	public String toSerializableString(final Boolean value) {
		return value.toString();
	}

	@Override
	public Boolean parseSerializableString(final String text) {
		return Boolean.valueOf(text);
	}
}
