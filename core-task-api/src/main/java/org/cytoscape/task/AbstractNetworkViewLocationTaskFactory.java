package org.cytoscape.task;

import java.awt.datatransfer.Transferable;
import java.awt.geom.Point2D;

import org.cytoscape.event.CyEventHelper;
import org.cytoscape.model.subnetwork.CyRootNetworkManager;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.vizmap.VisualMappingManager;
import org.cytoscape.work.TaskIterator;

/**
 * The base class for all of the task factories that need to add a functionality to the network view 
 * considering a give location (e.g. adding the node in the mouse clicked location).
 * @author rozagh
 *
 */
public abstract class AbstractNetworkViewLocationTaskFactory implements NetworkViewLocationTaskFactory{
	
	@Override
	public boolean isReady(CyNetworkView networkView, Point2D javaPt, Point2D xformPt) {
		return true;
	}
	
}
