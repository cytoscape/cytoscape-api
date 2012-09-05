package org.cytoscape.view.presentation.customgraphics;

import java.awt.Paint;
import java.awt.geom.Rectangle2D;

/**
 * This interface defines a custom graphics object that provides
 * only a simple {@link java.awt.Paint} to a
 * {@link org.cytoscape.view.presentation.RenderingEngine} that can
 * be used to fill an arbitrary shape.  This might be used for something
 * like creating a data-driven gradient to use on a {@link org.cytoscape.model.CyNode}
 * visualization.  {@link org.cytoscape.view.presentation.RenderingEngine}s
 * are not required to implement this, so any implementation of this interface
 * must also implement {@link CustomGraphics}.
 */
public interface CustomGraphicsPaint {
	/**
 	 * Return the {@link java.awt.Paint} to use for the
 	 * {@link org.cytoscape.model.CyNode} specified by the
 	 * {@link org.cytoscape.view.presentation.customgraphics.CustomGraphicsFactory}.
 	 *
 	 * @param bounds the bounding box of the {@link org.cytoscape.model.CyNode}.  Not
 	 *               usually required for {@link java.awt.Paint} but provided here for
 	 *               completeness.
 	 * @return the {@link java.awt.Paint} to for this {@link org.cytoscape.model.CyNode}.
 	 */
	public Paint getPaint(Rectangle2D bounds);
}
