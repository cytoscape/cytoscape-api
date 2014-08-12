package org.cytoscape.view.presentation.customgraphics;

import javax.swing.JComponent;

/**
 * A factory for making UI panels to configure a specific {@link CyCustomGraphics2}.
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule presentation-api
 */
public interface CyCustomGraphics2EditorFactory<T extends CustomGraphicLayer> {

	/**
 	 * Return the {@link CyCustomGraphics2} class for which this factory creates view editors.
 	 */
	Class<? extends CyCustomGraphics2<T>> getSupportedClass();
	
	/**
	 * Creates a UI panel that configures the given {@code CyCustomGraphics2}.
	 * 
	 * @param customGraphics the {@link CyCustomGraphics2} to be configured.
	 * @return a UI panel that configures the given {@code CyCustomGraphics2}.
	 */
	JComponent createEditor(CyCustomGraphics2<T> customGraphics);
	
}
