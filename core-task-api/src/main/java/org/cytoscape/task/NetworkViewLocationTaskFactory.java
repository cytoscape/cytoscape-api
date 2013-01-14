package org.cytoscape.task;

import java.awt.geom.Point2D;

import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.work.TaskIterator;

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
}
