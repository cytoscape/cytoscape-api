package org.cytoscape.task;

import java.awt.datatransfer.Transferable;
import java.awt.geom.Point2D;

import org.cytoscape.event.CyEventHelper;
import org.cytoscape.model.subnetwork.CyRootNetworkManager;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.vizmap.VisualMappingManager;
import org.cytoscape.work.TaskIterator;

/**
 * A base class for task factories that need to add a functionality to the network view 
 * considering a given location (for example adding a node where a mouse was clicked).
 */
public abstract class AbstractNetworkViewLocationTaskFactory implements NetworkViewLocationTaskFactory{
	
	/**
	 * Always returns true.
	 * @param networkView The network view
	 * @param javaPt the point on the network view in java coordinates
	 * @param xformPt the point on the network view transformed into Cytoscape coordinates 
	 * @return always returns true.
	 */
	@Override
	public boolean isReady(CyNetworkView networkView, Point2D javaPt, Point2D xformPt) {
		return true;
	}
	
}
