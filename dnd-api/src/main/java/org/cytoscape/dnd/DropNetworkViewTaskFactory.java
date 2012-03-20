
package org.cytoscape.dnd;


import java.awt.datatransfer.Transferable;
import java.awt.geom.Point2D;

import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.work.TaskIterator;

/**
 * An extension of TaskFactory that provides support for
 * tasks to deal with drag and drop.
 * @CyAPI.Spi.Interface
 */
public interface DropNetworkViewTaskFactory {

	/**
	 * Sets the drop information for a TaskFactory. 
	 * @param t The transferable object that was dropped.
	 * @param javaPt The raw Java point at which the object was dropped.
	 * @param xformPt The drop point transformed into Cytoscape coordinates.
	 */
	TaskIterator createTaskIterator(CyNetworkView networkView, Transferable t, Point2D javaPt, Point2D xformPt);
	
	boolean isReady(CyNetworkView networkView, Transferable t, Point2D javaPt, Point2D xformPt);
}
