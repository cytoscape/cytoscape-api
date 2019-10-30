package org.cytoscape.view.model;

import org.cytoscape.model.CyEdge;

/**
 * Contains additional info about a <code>View&lt;CyNode&gt;</code> obtained from a {@link CyNetworkViewSnapshot}.
 *
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule viewmodel-api
 * @since 3.8
 */ 
public interface SnapshotNodeInfo {

	/**
	 * Returns the view SUID of the node view.
	 * Equivalent to calling {@link View#getSUID()}
	 */
	Long getSUID();
	
	/**
	 * Returns the SUID of the underlying model object.
	 * Equivalent to calling {@link CyEdge#getSUID()}
	 */
	Long getModelSUID();
	
}
