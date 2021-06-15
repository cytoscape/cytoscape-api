package org.cytoscape.task;

import java.util.Collection;

import org.cytoscape.view.model.CyNetworkView;
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
 * A task factory that creates one or more tasks that operate on the specified 
 * collection of network views.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule core-task-api
 */
public interface NetworkViewCollectionTaskFactory {

	/** 
     * Provisions this factory with the collection of network views that will be passed into
	 * any task created by it.
	 * @param networkViews  a non-null collection of {@link CyNetworkView}s
	 * @return A TaskIterator object containing one or more {@link org.cytoscape.work.Task} objects to execute.
	 */
	TaskIterator createTaskIterator(Collection<CyNetworkView> networkViews);

    /**
     * Returns true if this task factory is ready to produce a TaskIterator.
	 * @param networkViews  a non-null collection of {@link CyNetworkView}s
     * @return true if this task factory is ready to produce a TaskIterator.
     */
	boolean isReady(Collection<CyNetworkView> networkViews);
	
	/**
     * If this task factory implements the {@link Togglable} interface then 
     * this method determines if the button or check box is on or off.
     * 
     * @since 3.9
     */
    default boolean isOn(Collection<CyNetworkView> networkViews) {
    	return false;
    }
}
