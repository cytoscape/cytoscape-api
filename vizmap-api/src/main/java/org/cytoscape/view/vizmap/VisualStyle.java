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

import java.util.Collection;
import java.util.Set;

import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.view.model.View;
import org.cytoscape.view.model.VisualProperty;


/**
 * A VisualStyle is a collection of {@linkplain VisualMappingFunction}s and default values
 * that define how a set of attributes is mapped to visual properties of View objects.
 * @CyAPI.Api.Interface
 */
public interface VisualStyle {
	
	/**
	 * Returns name of this visual style. This should NOT be used as the ID of this
	 * Visual Style. Just for GUI components and may not be unique.
	 * 
	 * <p>
	 * Title of Visual Style is a mutable field and may <strong>NOT</strong> be unique.
	 *
	 * @return title of this visual style
	 */
	String getTitle();

	
	/**
	 * Set new title for this VisualStyle.
	 *
	 * @param title New title of this VisualStyle.
	 */
	void setTitle(final String title);
	

	/**
	 * Add a new {@linkplain VisualMappingFunction} to this VisualStyle.
	 *
	 * @param mapping new VisualMappingFunction to be added.
	 */
	void addVisualMappingFunction(final VisualMappingFunction<?, ?> mapping);
	
	
	/**
	 *  Remove a VisualMappingFunction for the VisualProperty.
	 *  One visual property can be associated with only one mapping function, 
	 *  so this always removes correct one.
	 *
	 * @param vp VisualMappingFunction associated with this VisualProperty will be removed.
	 *
	 */
	void removeVisualMappingFunction(final VisualProperty<?> vp);

	
	/**
	 *  Get current {@linkplain VisualMappingFunction} for the VisualProperty.
	 *
	 * @param <V> Data type of VisualProperty.
	 * 
	 * @param vp visual property associated with the target mapping.
	 *
	 * @return mapping function for the VisualProperty.  If no mapping is available, this value is null.
	 * 
	 */
	<V> VisualMappingFunction<?, V> getVisualMappingFunction(final VisualProperty<V> vp);

	
	/**
	 *  Returns all {@linkplain VisualMappingFunction}s in this style.
	 *
	 * @return  All mappings for this style.
	 */
	Collection<VisualMappingFunction<?, ?>> getAllVisualMappingFunctions();

	
	/**
	 *  Returns default value for the VisualProperty.
	 *  This is style's default value, not same as VisualProperty default.
	 *  If VisualMappingFunction is not available for this VisualProperty, this default value will be used in the view model.
	 *
	 * @param <V> Data type of VisualProperty
	 * 
	 * @param vp target VisualProperty
	 *
	 * @return  Style's default value for the VisualProperty.
	 */
	<V> V getDefaultValue(final VisualProperty<V> vp);

	
	/**
	 *  Set default value for the VisualProperty.
	 *
	 * @param <V> Data type of VisualProperty
	 * @param <S> Data type of actual default value.  This can be same as V or its child classes.
	 * 
	 * @param vp target VisualProperty
	 * @param value Value to be set as default.  This can be child type of V.  For example, 
	 * 				if V is Number, S can be Double, Integer, etc.
	 */
	<V, S extends V> void setDefaultValue(final VisualProperty<V> vp, final S value);

	
	/**
	 * Apply visual only to a individual View Object (node/edge), 
	 * not the entire network view.
	 * 
	 * @param viewObject
	 */
	void apply(final View<? extends CyIdentifiable> view);
	
	/**
	 * Get all dependencies for this style.
	 * 
	 * @return set of dependencies associated with this style.
	 */
	Set<VisualPropertyDependency<?>> getAllVisualPropertyDependencies();


	/**
	 * Add a new {@linkplain VisualPropertyDependency}.
	 * 
	 * @param dependency new dependency to be added 
	 */
	void addVisualPropertyDependency(final VisualPropertyDependency<?> dependency);
	
	
	/**
	 * Remove a {@linkplain VisualPropertyDependency}.
	 * 
	 * @param dependency
	 */
	void removeVisualPropertyDependency(final VisualPropertyDependency<?> dependency);
}
