package org.cytoscape.task;

import org.cytoscape.model.CyEdge;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.Togglable;

/*
 * #%L
 * Cytoscape Core Task API (core-task-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2021 The Cytoscape Consortium
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

/**
 * A task factory that creates one or more tasks that operate on the specified View&lt;CyEdge&gt; within
 * the specified CyNetworkView.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule core-task-api
 */
public interface EdgeViewTaskFactory {
	
	/**
	 * Creates a new TaskIterator using the given edge view and network view.
	 * @param edgeView  a non-null edge view
	 * @param networkView  a non-null network view
	 * @return A TaskIterator object containing one or more {@link org.cytoscape.work.Task} objects to execute.
	 */
	TaskIterator createTaskIterator(View<CyEdge> edgeView, CyNetworkView networkView);
	
	/**
	 * Returns true if this task factory is ready to produce a TaskIterator.
	 * @param edgeView  a non-null edge view
	 * @param networkView  a non-null network view
	 * @return true if this task factory is ready to produce a TaskIterator.
	 */
	boolean isReady(View<CyEdge> edgeView, CyNetworkView networkView);
	
	/**
     * If this task factory implements the {@link Togglable} interface then 
     * this method determines if the button or check box is on or off.
     * 
     * @since 3.9
     */
    default boolean isOn(View<CyEdge> edgeView, CyNetworkView networkView) {
    	return false;
    }
}
