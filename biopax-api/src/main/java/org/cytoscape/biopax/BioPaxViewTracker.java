package org.cytoscape.biopax;

import org.cytoscape.view.model.CyNetworkView;

/**
 * This API is provisional and is subject to change at any time.
 */
public interface BioPaxViewTracker {
	
	/**
	 * Registers a BioPAX or SIF network view (created by {@link BioPaxMapper})
	 * 
	 * @param view
	 */
	void registerBioPaxView(CyNetworkView view);
}
