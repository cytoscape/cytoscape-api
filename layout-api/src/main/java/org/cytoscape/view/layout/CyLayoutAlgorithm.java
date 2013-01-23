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


import java.util.Collections;
import java.util.Set;

import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.cytoscape.work.TaskIterator;

/**
 * A task factory specifically for layout algorithms.
 *
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule layout-api
 */
public interface CyLayoutAlgorithm {
	
	/**
	 * A convenience declaration for an empty set signifying that all node views
	 * should be laid out when creating the task iterator.
	 */
	Set<View<CyNode>> ALL_NODE_VIEWS = Collections.emptySet();
	
	/**
	 * Creates a task iterator containing the layout tasks.
	 * @param networkView The network view that the layout algorithm should be applied to.
	 * @param layoutContext The layout context for this layout algorithm.
	 * @param nodesToLayOut The set of node views to layout.
	 * @param layoutAttribute The possibly null name of the attribute to use for this layout.
	 * @return taskIterator contains layout tasks.
	 */
	TaskIterator createTaskIterator(CyNetworkView networkView,  Object layoutContext, Set<View<CyNode>> nodesToLayOut, String layoutAttribute);
	
	
	/**
	 * Returns true if the task factory is ready to produce a task iterator.
	 * @param networkView The network view that the layout algorithm should be applied to.
	 * @param layoutContext The layout context for this layout algorithm.
	 * @param nodesToLayOut The set of node views to layout.
	 * @param layoutAttribute The possibly null name of the attribute to use for this layout.
	 * @return true if the task factory is ready to produce a task iterator.
	 */
	boolean isReady(CyNetworkView networkView,  Object layoutContext, Set<View<CyNode>> nodesToLayOut, String layoutAttribute);
	
	/**
	 * Returns a new layout context object. This method can be used to create
	 * custom configurations for layouts.
	 * @return a new layout context object.
	 */
	Object createLayoutContext();
	
	/**
	 * Returns the default instance of the layout context. This is the default
	 * layout configuration used in most cases.
	 * @return the default instance of the layout context.
	 */
	Object getDefaultLayoutContext();
	
	/**
	 * Returns the set of node attribute types potentially used by this layout algorithm.
	 * May (and frequently will) return an empty set.
	 * @return types of allowable attribute types.
	 */
	Set<Class<?>> getSupportedNodeAttributeTypes();

	/**
	 * Returns the set of node attribute types potentially used by this layout algorithm.
	 * May (and frequently will) return an empty set.
	 * @return types of allowable attribute types.
	 */
	Set<Class<?>> getSupportedEdgeAttributeTypes();

	/**
	 * Returns true if this algorithm supports being applied to only the currently selected nodes.
	 * @return true if this algorithm supports being applied to only the currently selected nodes.
	 */
	boolean getSupportsSelectedOnly();

	/**
	 * Returns the computer-readable name of the layout.  To get
	 * a human readable name, use toString().
	 * @return The computer-readable name of the layout.
	 */
	String getName();
	
	/**
	 * Returns the human-readable name of the layout.
	 * @return The human-readable name of the layout.
	 */
	String toString();
}
