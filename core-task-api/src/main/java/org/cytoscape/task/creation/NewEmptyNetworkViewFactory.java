package org.cytoscape.task.creation;

import org.cytoscape.view.model.CyNetworkView;

/**
 * An interface for creating new, empty network views.
 * @CyAPI.Spi.Interface
 */
public interface NewEmptyNetworkViewFactory {

	/**
	 * Return a new, empty CyNetworkView.
	 * @return A new, empty CyNetworkView.
	 */
	CyNetworkView createNewEmptyNetworkView();
}
