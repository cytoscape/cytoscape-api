package org.cytoscape.view.presentation.customgraphics;

import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;

/**
 * This interface defines a the information required to
 * construct a {@link java.awt.Shape} that has an arbitrary
 * fill {@link java.awt.Paint} and {@link java.awt.Stroke}.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule presentation-api
 */
public interface PaintedShape extends CustomGraphicLayer {
	/**
 	 * Return the {@link java.awt.Shape}
 	 *
 	 * @return the {@link java.awt.Shape}
 	 */
	public Shape getShape();

	/**
 	 * Return the {@link java.awt.Paint} to fill the shape with.  This
 	 * is a convenience method that is essentially the same as calling
 	 * getPaint(getShape().getBounds());
 	 *
 	 * @return the {@link java.awt.Paint}
 	 */
	public Paint getPaint();

	/**
 	 * Return the {@link java.awt.Stroke} to use to outline the
 	 * {@link java.awt.Shape} provided by the {@link #getShape} method above.
 	 *
 	 * @return the {@link java.awt.Stroke} to use.
 	 */
	public Stroke getStroke();

	/**
 	 * Return the {@link java.awt.Paint} to use to color the
 	 * {@link java.awt.Stroke} returned by {@link #getStroke}.
 	 *
 	 * @return the stroke {@link java.awt.Paint}
 	 */
	public Paint getStrokePaint();
}
