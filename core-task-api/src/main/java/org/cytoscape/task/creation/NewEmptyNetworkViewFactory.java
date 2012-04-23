package org.cytoscape.task.creation;

import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.work.TaskFactory;

/**
 * An interface for creating new, empty network views.
 * @CyAPI.Api.Interface
 */
public interface NewEmptyNetworkViewFactory{

	/**
	 * Returns a new, empty CyNetworkView. Runs synchronously.
	 * @return A new, empty CyNetworkView.
	 */
	CyNetworkView createNewEmptyNetworkView();
}
