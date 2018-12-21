package org.cytoscape.view.model;

public interface CyNetworkViewListener {

	/**
	 * Utility method to fit content to the presentation container (usually a Swing Window).
	 * This fires event to the presentation layer for updating presentation.
	 */
	default void handleFitContent() {};
	
	
	/**
	 * Utility method to fit selected graph objects to the presentation container.
	 * This fires event to the presentation layer for updating presentation.
	 */
	default void handleFitSelected() {};
	
	
	/**
	 * Cascading event for the presentation layer for updating presentation.
	 */
	default void handleUpdateView() {};
	
	
	/**
	 * Called on dispose
	 */
	default void handleDispose() {};
}
