package org.cytoscape.view.model;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNode;

/**
 * Contains additional info about a <code>View&lt;CyEdge&gt;</code> obtained from a {@link CyNetworkViewSnapshot}.
 * 
 * @see CyNetworkViewSnapshot#getEdgeInfo(View)
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule viewmodel-api
 * @since 3.8
 */
public interface SnapshotEdgeInfo {
	
	/**
	 * Returns the view SUID of the edge view.
	 * Equivalent to calling {@link View#getSUID()}
	 */
	Long getSUID();
	
	/**
	 * Returns the SUID of the underlying model object.
	 * Equivalent to calling {@link CyEdge#getSUID()}
	 */
	Long getModelSUID();
	
	/**
	 * Returns the SUID of the source node view.
	 */
	long getSourceViewSUID();
	
	/**
	 * Returns the source node view;
	 */
	View<CyNode> getSourceNodeView();
	
	/**
	 * Returns the SUID of the target node view.
	 */
	long getTargetViewSUID();
	
	/**
	 * Returns the target node view;
	 */
	View<CyNode> getTargetNodeView();

	/**
	 * Returns true if the edge is directed.
	 */
	boolean isDirected();
	
}
