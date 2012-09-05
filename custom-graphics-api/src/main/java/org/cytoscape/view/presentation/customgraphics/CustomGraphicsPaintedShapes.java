package org.cytoscape.view.presentation.customgraphics;

import java.awt.geom.Rectangle2D;
import java.util.List;

/**
 * This interface defines a custom graphics object that provides
 * a series of {@link java.awt.Shape}s with arbitrary fill
 * {@link java.awt.Paint} and {@link java.awt.Stroke} to a
 * {@link org.cytoscape.view.presentation.RenderingEngine}.
 * This might be used for something
 * like creating a data-driven pie chart to use on a {@link org.cytoscape.model.CyNode}
 * visualization.  {@link org.cytoscape.view.presentation.RenderingEngine}s
 * are not required to implement this, so any implementation of this interface
 * must also implement {@link CustomGraphics}.
 */
public interface CustomGraphicsPaintedShapes {
	/**
 	 * Return a list of {@link PaintedShape}s to the
 	 * {@link org.cytoscape.view.presentation.RenderingEngine} for
 	 * painting onto the {@link org.cytoscape.model.CyNode} visualization.
 	 *
 	 * @param bounds the bounding box of the 
 	 *               {@link org.cytoscape.model.CyNode} visualization.
 	 * @return a {@link java.util.List} of {@link PaintedShape}s that will 
 	 *         be drawn in list order.  This means that the first 
 	 *         {@link PaintedShape}s may be under subsequent {@link PaintedShape}s.
 	 */
	public List<PaintedShape> getPaintedShapes(Rectangle2D bounds);
}
