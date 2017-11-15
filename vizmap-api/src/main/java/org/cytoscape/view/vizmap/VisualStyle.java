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

import java.util.Collection;
import java.util.Set;

import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.model.CyRow;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.cytoscape.view.model.VisualProperty;


/**
 * A VisualStyle is a collection of {@linkplain VisualMappingFunction}s and default values
 * that define how a set of attributes is mapped to visual properties of View objects.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule vizmap-api
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
	 * @throws NullPointerException if the specified mapping is null
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
	 * Apply Visual Style to the entire network view.
	 * 
	 * @param networkView The view that the visual property should be applied to.
	 * @throws NullPointerException if the specified networkView is null
	 */
	void apply(final CyNetworkView networkView);


	/**
	 * Apply Visual Property values only to the given View Object (node or edge).
	 * If you need to update only few set of node/edge views, then this is more efficient.
	 * 
	 * @param row Data table row for the view's model.  This is necessary for applying mapped values.
	 * @param view The view that the visual property should be applied to.
	 */
	void apply(final CyRow row, final View<? extends CyIdentifiable> view);
	
	/**
	 * Get all dependencies for this style.
	 * 
	 * @return set of dependencies associated with this style.
	 */
	Set<VisualPropertyDependency<?>> getAllVisualPropertyDependencies();


	/**
	 * Add a new {@linkplain VisualPropertyDependency}.
	 * If the VisualStyle already contains a dependency with the same id, the passed dependency is not added.
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
