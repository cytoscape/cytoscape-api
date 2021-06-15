package org.cytoscape.task;

import java.awt.geom.Point2D;

import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.Togglable;

/*
 * #%L
 * Cytoscape Core Task API (core-task-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2021 The Cytoscape Consortium
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
 * An extension of TaskFactory that provides support for
 * tasks that need to know the a location within a CyNetworkView to
 * perform their task.  An example would be task that adds a
 * node to a network in a specific location.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule core-task-api
 */
public interface NetworkViewLocationTaskFactory{

	/**
	 * Creates a task iterator using the specified network view and points. 
	 * @param networkView The network view. 
	 * @param javaPt The raw Java point within the network view. 
	 * @param xformPt The raw Java point transformed into Cytoscape coordinates.
     * @return A TaskIterator object containing one or more {@link org.cytoscape.work.Task} objects to execute.
	 */
	TaskIterator createTaskIterator(CyNetworkView networkView, Point2D javaPt, Point2D xformPt);
	
	/**
	 * Returns true if this task factory is ready to produce a TaskIterator.
	 * @param networkView The network view. 
	 * @param javaPt The raw Java point within the network view. 
	 * @param xformPt The raw Java point transformed into Cytoscape coordinates.
	 * @return true if this task factory is ready to produce a TaskIterator.
	 */
	boolean isReady(CyNetworkView networkView, Point2D javaPt, Point2D xformPt);
	
	/**
     * If this task factory implements the {@link Togglable} interface then 
     * this method determines if the button or check box is on or off.
     * 
     * @since 3.9
     */
    default boolean isOn(CyNetworkView networkView, Point2D javaPt, Point2D xformPt) {
    	return false;
    }
}
