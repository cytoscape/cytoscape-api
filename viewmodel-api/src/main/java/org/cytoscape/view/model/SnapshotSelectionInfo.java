package org.cytoscape.view.model;


/**
 * Contains additional info about selection state obtained from a {@link CyNetworkViewSnapshot}.
 * Used to optimize rendering performance. App writers typically will not have a use for this object.
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule viewmodel-api
 * @since 3.9
 * @see CyNetworkViewSnapshot#getSelectionInfo()
 */
public interface SnapshotSelectionInfo {

	boolean isSelectionIncreased();

	int getSelectedNodes();

	int getSelectedEdges();
	
}
