package org.cytoscape.filter.model;

/**
 * Listens for changes to {@code Transformer} parameters.  This listener should
 * be added to, or removed from a {@code Transformer} directly.  
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule filter-api
 */
public interface TransformerListener {
	/**
	 * Called by a {@code Transformer} to indicate that its settings have
	 * changed in some way.
	 */
	void handleSettingsChanged();
}
