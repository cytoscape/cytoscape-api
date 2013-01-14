package org.cytoscape.task;

import java.awt.geom.Point2D;

import org.cytoscape.view.model.CyNetworkView;

/**
 * A base class for task factories that need to add a functionality to the network view 
 * considering a given location (for example adding a node where a mouse was clicked).
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule core-task-api
 */
public abstract class AbstractNetworkViewLocationTaskFactory implements NetworkViewLocationTaskFactory{
	
	/**
	 * Returns true if the supplied view and coordinates are not null.
	 * @param networkView The network view
	 * @param javaPt the point on the network view in java coordinates
	 * @param xformPt the point on the network view transformed into Cytoscape coordinates 
	 * @return true if the supplied view and coordinates are not null.
	 */
	@Override
	public boolean isReady(CyNetworkView networkView, Point2D javaPt, Point2D xformPt) {
		return networkView != null && javaPt != null && xformPt != null;
	}
	
}
