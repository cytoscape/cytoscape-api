package org.cytoscape.view.layout;

/*
 * #%L
 * Cytoscape Layout API (layout-api)
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


import java.util.Collection;

import org.cytoscape.view.model.CyNetworkView;

/**
 * This class provides access to the available layout algorithms.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule layout-api
 */
public interface CyLayoutAlgorithmManager {

	/** The name of the default layout. */
	String DEFAULT_LAYOUT_NAME = "force-directed";
	
	/** The name of the default layout property. */
	String DEFAULT_LAYOUT_PROPERTY_NAME = "layout.default";

	/**
	 * Returns a layout algorithm of the specified name and null if no
	 * algorithm exists with that name.
	 * @param name The name of the algorithm.
	 * @return a layout algorithm of the specified name and null if no
	 * algorithm exists with that name.
	 */
	CyLayoutAlgorithm getLayout(String name);

	/**
	 * Returns a collection of all available layouts.
	 * @return a collection of all available layouts.
	 */
	Collection<CyLayoutAlgorithm> getAllLayouts();

	/**
	 * Returns Cytoscape's default layout.
	 * @return the default layout.
	 */
	CyLayoutAlgorithm getDefaultLayout();
	
	/**
	 * Changes Cytoscape's default layout.
	 * @param layout The new default algorithm
	 */
	CyLayoutAlgorithm setDefaultLayout(CyLayoutAlgorithm layout);
	
	/**
	 * Returns the attribute name that has been associated with the layout, or the empty
	 * string if there is no valid attribute associated to the layout for the given view.
	 * 
	 * The attribute name may be a node or edge column name.
	 * 
	 * Note, if an attribute was associated with the given layout using the 
	 * {@link CyLayoutAlgorithmManager#setLayoutAttribute(CyLayoutAlgorithm, String)}
	 * method, but the given CyNetworkView does not support that attribute, then the empty string will be returned instead. 
	 * 
	 * @see CyLayoutAlgorithm#getSupportedEdgeAttributeTypes()
	 * @see CyLayoutAlgorithm#getSupportedNodeAttributeTypes()
	 * @see CyLayoutAlgorithmManager#setLayoutAttribute(CyLayoutAlgorithm, String)
	 * 
	 * @param view If null returns the value set using {@link CyLayoutAlgorithmManager#setLayoutAttribute(CyLayoutAlgorithm, String)}.
	 * If view is not null then returns the attribute if it is valid for the given view. If the attribute is not valid for the given view then
	 * the empty string is returned.
	 * @return May return null if no attribute has been associated with the layout.
	 */
	String getLayoutAttribute(CyLayoutAlgorithm layout, CyNetworkView view);
	
	/**
	 * Associates an attribute name with the layout.
	 * This attribute may be passed as the <b>layoutAttribute</b> parameter to the
	 * {@link CyLayoutAlgorithm#createTaskIterator(org.cytoscape.view.model.CyNetworkView, Object, java.util.Set, String)} 
	 * method when running the layout.
	 * The attribute name may be a node or edge column name.
	 * 
	 * @see CyLayoutAlgorithm#getSupportedEdgeAttributeTypes()
	 * @see CyLayoutAlgorithm#getSupportedNodeAttributeTypes()
	 * @param layoutAttribute May be null.
	 */
	void setLayoutAttribute(CyLayoutAlgorithm layout, String layoutAttribute);

}
