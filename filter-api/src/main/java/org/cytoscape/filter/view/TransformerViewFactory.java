package org.cytoscape.filter.view;

import javax.swing.JComponent;

import org.cytoscape.filter.model.Transformer;

/**
 * A factory for making UI panels to configure a specific {@link Transformer}.
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule filter-api
 */
public interface TransformerViewFactory {
	/**
	 * Returns the unique id of the {@link Transformer} this factory produces
	 * views for.
	 * @return the unique id of the {@link Transformer} this factory produces
	 *         views for.
	 */
	String getId();
	
	/**
	 * Creates a UI panel that configures the given {@code transformer}.
	 * 
	 * @param transformer the {@link Transformer} to be configured.
	 * @return a UI panel that configures the given {@code transformer}.
	 */
	JComponent createView(Transformer<?, ?> transformer);
}
