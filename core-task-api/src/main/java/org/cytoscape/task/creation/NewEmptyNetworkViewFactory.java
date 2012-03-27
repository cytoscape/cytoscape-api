package org.cytoscape.task.creation;

import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.work.TaskFactory;

/**
 * An interface for creating new, empty network views.
 * @CyAPI.Spi.Interface
 */
public interface NewEmptyNetworkViewFactory{

	/**
	 * Returns a new, empty CyNetworkView.
	 * @return A new, empty CyNetworkView.
	 */
	CyNetworkView createNewEmptyNetworkView();
}
