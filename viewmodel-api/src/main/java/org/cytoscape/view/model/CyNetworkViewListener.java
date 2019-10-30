package org.cytoscape.view.model;


/**
 * Callback interface for use with {@link CyNetworkView} instances that are created
 * from {@link CyNetworkViewFactoryFactory} factories.
 * 
 * @see CyNetworkView#addNetworkViewListener(CyNetworkViewListener)
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule viewmodel-api
 * @since 3.8
 */
public interface CyNetworkViewListener {

	/**
	 * Fired when {@link CyNetworkView#fitContent()} is called.
	 */
	default void handleFitContent() {};
	
	
	/**
	 * Fired when {@link CyNetworkView#fitSelected()} is called.
	 */
	default void handleFitSelected() {};
	
	
	/**
	 * Fired when {@link CyNetworkView#updateView()} is called.
	 */
	default void handleUpdateView() {};
	
	
	/**
	 * Fired when {@link CyNetworkView#dispose()} is called.
	 */
	default void handleDispose() {};
}
