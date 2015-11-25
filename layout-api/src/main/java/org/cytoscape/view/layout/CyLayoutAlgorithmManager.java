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

}
