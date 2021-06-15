package org.cytoscape.task;

import java.util.Collection;

import org.cytoscape.model.CyNetwork;
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
 * Produces a task iterator for the specified collection of networks. 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule core-task-api
 */
public interface NetworkCollectionTaskFactory {
	
	/** 
	 * Provisions this factory with the collection of networks that will be passed into any task
	 * created by it.
	 * @param networks a non-null collection of {@link CyNetwork}s
	 * @return A TaskIterator object containing one or more {@link org.cytoscape.work.Task} objects to execute.
	 */
	TaskIterator createTaskIterator(Collection<CyNetwork> networks);
	
	/** 
	 * Returns true if this task factory is ready to produce a task iterator.
	 * @param networks a non-null collection of {@link CyNetwork}s
	 * @return true if this task factory is ready to produce a task iterator.
	 */
	boolean isReady(Collection<CyNetwork> networks);
	
	/**
     * If this task factory implements the {@link Togglable} interface then 
     * this method determines if the button or check box is on or off.
     * 
     * @since 3.9
     */
    default boolean isOn(Collection<CyNetwork> networks) {
    	return false;
    }
}
