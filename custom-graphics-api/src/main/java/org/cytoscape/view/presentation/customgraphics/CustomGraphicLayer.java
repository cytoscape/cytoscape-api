package org.cytoscape.view.presentation.customgraphics;

import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

/**
 * This interface defines a the mimimum interface to
 * add a custom graphics to a {@link org.cytoscape.model.CyNode}.
 */
public interface CustomGraphicLayer {
	/**
 	 * Return the bounds of the area covered by this
 	 * CustomGraphicLayer as a {@link java.awt.Rectangle}
 	 *
 	 * @return the {@link java.awt.Rectangle}
 	 */
	public Rectangle getBounds();

	/**
 	 * Return the bounds of the area covered by this
 	 * CustomGraphicLayer as a {@link java.awt.Rectangle2D}
 	 *
 	 * @return the {@link java.awt.Rectangle2D}
 	 */
	public Rectangle2D getBounds2D();

	/**
 	 * Return the {@link java.awt.Paint} to be used to
 	 * fill the {@link java.awt.Shape}.
 	 *
 	 * @param bounds the bounding box of the {@link org.cytoscape.model.CyNode}.
 	 * @return the fill {@link java.awt.Paint}
 	 */
	public Paint getPaint(Rectangle2D bounds);

	/**
 	 * Return a new CustomGraphicLayer that has been transformed by the
 	 * provided {@link java.awt.geom.AffineTransform}.
 	 *
 	 * @param xform transform to perform on the shape
 	 * @return the transformed CustomGraphicLayer
 	 */
  public CustomGraphicLayer transform(AffineTransform xform);
}
