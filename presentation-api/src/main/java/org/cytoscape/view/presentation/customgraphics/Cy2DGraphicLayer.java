package org.cytoscape.view.presentation.customgraphics;

import java.awt.Graphics2D;
import java.awt.Shape;

import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;

/**
 * Custom graphic layer interface that allows a custom graphics implementation to draw directly onto the
 * {@link CyNetworkView}'s {@link Graphics2D} object.
 * @see CyCustomGraphics CyCustomGraphics2
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule presentation-api
 */
public interface Cy2DGraphicLayer extends CustomGraphicLayer {

	/**
	 * This method is called by Cytoscape when a {@link CyNetworkView} is being updated in order to let the 
	 * custom graphics draw onto its {@link Graphics2D} object.
	 * @param g The graphics object to draw onto
	 * @param shape The node view's shape or the edge or network view's bounding box
	 * @param networkView The network view being updated
	 * @param view The view object that has this layer's {@link CyCustomGraphics} as a visual property value
	 *        (so far, only CyNode views are supported by Cytoscape's default 
	 *        {@link org.cytoscape.view.presentation.RenderingEngine})
	 */
	void draw(Graphics2D g, Shape shape, CyNetworkView networkView, View<? extends CyIdentifiable> view);
	
}
