package org.cytoscape.view.presentation.gradients;

import javax.swing.JComponent;

import org.cytoscape.view.presentation.customgraphics.CustomGraphicLayer;

/**
 * A factory for making UI panels to configure a specific {@link CyGradient}.
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule presentation-api
 */
public interface CyGradientEditorFactory<T extends CustomGraphicLayer> {

	/**
 	 * Return the {@link CyGradient} class for which this factory creates view editors.
 	 */
	Class<? extends CyGradient<T>> getSupportedClass();
	
	/**
	 * Creates a UI panel that configures the given {@code gradient}.
	 * 
	 * @param gradient the {@link CyGradient} to be configured.
	 * @return a UI panel that configures the given {@code gradient}.
	 */
	JComponent createEditor(CyGradient<T> gradient);
	
}
