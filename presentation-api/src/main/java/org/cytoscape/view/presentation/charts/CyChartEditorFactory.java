package org.cytoscape.view.presentation.charts;

import javax.swing.JComponent;

import org.cytoscape.view.presentation.customgraphics.CustomGraphicLayer;

/**
 * A factory for making UI panels to configure a specific {@link CyChart}.
 * 
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule presentation-api
 */
public interface CyChartEditorFactory<T extends CustomGraphicLayer> {

	/**
 	 * Return the {@link CyChart} class for which this factory creates view editors.
 	 */
	Class<? extends CyChart<T>> getSupportedClass();
	
	/**
	 * Creates a UI panel that configures the given {@code chart}.
	 * 
	 * @param chart the {@link CyChart} to be configured.
	 * @return a UI panel that configures the given {@code chart}.
	 */
	JComponent createEditor(CyChart<T> chart);
	
}
