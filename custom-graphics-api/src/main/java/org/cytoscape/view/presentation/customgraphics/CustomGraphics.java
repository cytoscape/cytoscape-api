package org.cytoscape.view.presentation.customgraphics;

import java.awt.Image;
import java.awt.geom.Rectangle2D;

/**
 * This interface defines a custom graphics object that
 * provides a simple {@link java.awt.Image} to be painted
 * on a {@link org.cytoscape.model.CyNode} and is the only mandatory interface that
 * must be implemented by all
 * {@link org.cytoscape.view.presentation.RenderingEngine}s.
 * It is assumed that
 * any {@link org.cytoscape.view.presentation.RenderingEngine} will
 * be able to display an {@link java.awt.Image}, and that all more complicated
 * CustomGraphics implementations will be able to generate an image from
 * their graphical data.
 */
public interface CustomGraphics {
	/**
 	 * This key is the threshold the provider wants to set for re-rendering.  If the
 	 * zoom changes less than this value, none of the CustomGraphics methods will be
 	 * called and the existing graphics will be reused.  If it exceeds this threshold
 	 * the appropriate methods will be called to generate new custom graphics data.
 	 */
	public static String ZOOM_THRESHOLD = "zoomThreshold";

	/**
 	 * This method is called by the 
 	 * {@link org.cytoscape.view.presentation.RenderingEngine} to get an image
 	 * to paint onto a {@link org.cytoscape.model.CyNode}.  The size (in display
 	 * coordinates) are given by the bounding rectangle.
 	 *
 	 * @param bounds the bounding rectangle of the displayed {@link org.cytoscape.model.CyNode}.
 	 * @return an {@link java.awt.Image} that should be painted on the appropriate 
 	 * {@link org.cytoscape.model.CyNode}.
 	 */
	public Image getRenderedImage(Rectangle2D bounds);
}
