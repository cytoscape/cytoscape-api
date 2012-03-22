package org.cytoscape.task;

import java.awt.datatransfer.Transferable;
import java.awt.geom.Point2D;

import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;

/**
 * An extension of TaskFactory that provides support for
 * tasks to deal with adding node and endge by right click menue.
 * @CyAPI.Spi.Interface
 */
public interface NetworkViewLocationTaskFactory{

	/**
	 * Sets the drop information for a TaskFactory. 
	 * @param t The transferable object that was dropped.
	 * @param javaPt The raw Java point at which the object was dropped.
	 * @param xformPt The drop point transformed into Cytoscape coordinates.
	 */
	TaskIterator createTaskIterator(CyNetworkView networkView, Point2D javaPt, Point2D xformPt);
	
	boolean isReady(CyNetworkView networkView, Point2D javaPt, Point2D xformPt);
}