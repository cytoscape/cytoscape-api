package org.cytoscape.view.presentation.annotations;

/*
 * #%L
 * Cytoscape Ding View/Presentation Impl (ding-presentation-impl)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

import java.awt.Graphics;
import java.awt.Paint;
import java.awt.geom.Point2D;
import java.util.List;

import org.cytoscape.model.CyNode;

/**
 * This is the interface for an arrow annotation.  In general, an arrow connects
 * to a source (an existing annotation) and is drawn to a target, which is either
 * another annotation, a {@link CyNode} or a point on the canvas.  The end of the
 * arrow will move with its source annotation or its target (unless it's target is a
 * point).
 *
 * @CyAPI.Api.Interface
 * @CyAPI.InModule presentation-api
 */
public interface ArrowAnnotation extends Annotation {
	/**
 	 * This enum is used to indicate which end of the arrow the
 	 * operation is referring to.
 	 */
	public enum ArrowEnd { SOURCE, TARGET; }

	/**
 	 * This enum is used to indicate how the arrow is anchored to the
 	 * source or target.  {@link AnchorType#CENTER} anchors draw all the way to the center
 	 * of the object, and {@link AnchorType#ANCHOR} anchors will anchor to the closest corner
 	 * or midpoint on the bounding box of the object.
 	 */
	public enum AnchorType { CENTER, ANCHOR; }

	/**
 	 * Return the source {@link Annotation} for this arrow.
 	 *
 	 * @return the source {@link Annotation}
 	 */
	public Annotation getSource();

	/**
 	 * Set the source {@link Annotation} for this arrow.
 	 *
 	 * @param source the {@link Annotation} for the arrow source
 	 */
	public void setSource(Annotation source);

	/**
 	 * Return the target object for this arrow.  Currently supported
 	 * targets include {@link Annotation}s, {@link CyNode}s, and arbitrary
 	 * points on the canvas.
 	 *
 	 * @return the the target.  Callers should test to determine the appropriate type
 	 */
	public Object getTarget();

	/**
 	 * Set the target for this arrow assuming the target is an {@link Annotation}
 	 *
 	 * @param target the target for this arrow
 	 */
	public void setTarget(Annotation target);

	/**
 	 * Set the target for this arrow assuming the target is a {@link CyNode}
 	 *
 	 * @param target the target for this arrow
 	 */
	public void setTarget(CyNode target);

	/**
 	 * Set the target for this arrow assuming the target is a point on the canvas
 	 *
 	 * @param target the target for this arrow
 	 */
	public void setTarget(Point2D target);

	/**
 	 * Get the line width for this arrow
 	 *
 	 * @return line width as a double
 	 */
	public double getLineWidth();

	/**
 	 * Set the line width for this arrow
 	 *
 	 * @param width of the line as a double
 	 */
	public void setLineWidth(double width);


	/**
 	 * Set the line color for this arrow
 	 *
 	 * @return color of the line
 	 */
	public Paint getLineColor();

	/**
 	 * Set the line color for this arrow
 	 *
 	 * @param color of the line
 	 */
	public void setLineColor(Paint color);

	/**
 	 * Get the size of one end of the arrow
 	 *
 	 * @param end the end of the arrow we're getting the size for
 	 * @return the arrow size
 	 */
	public double getArrowSize(ArrowEnd end);

	/**
 	 * Set the size of one end of the arrow
 	 *
 	 * @param end the end of the arrow we're setting the size for
 	 * @param width the arrow size
 	 */
	public void setArrowSize(ArrowEnd end, double width);

	/**
 	 * Get the color of one end of the arrow
 	 *
 	 * @param end the end of the arrow we're getting the color for
 	 * @return the arrow color
 	 */

	public Paint getArrowColor(ArrowEnd end);

	/**
 	 * Set the color of one end of the arrow
 	 *
 	 * @param end the end of the arrow we're setting the color for
 	 * @param color the arrow color
 	 */
	public void setArrowColor(ArrowEnd end, Paint color);

	/**
 	 * Get the list of supported arrow shapes 
 	 *
 	 * @return the list of supported arrow types
 	 */
	public List<String> getSupportedArrows();

	/**
 	 * Get the type of one end of the arrow
 	 *
 	 * @param end the end of the arrow we're getting the size for
 	 * @return the arrow type as a string.
 	 */
	public String getArrowType(ArrowEnd end);

	/**
 	 * Set the type of one end of the arrow
 	 *
 	 * @param end the end of the arrow we're setting the type for
 	 * @param width the arrow size
 	 */
	public void setArrowType(ArrowEnd end, String type);

	/**
 	 * Get the anchor type for one end of the arrow
 	 *
 	 * @param end the end of the arrow we're getting the anchor type for
 	 * @return the anchor type
 	 */
	public AnchorType getAnchorType(ArrowEnd end);

	/**
 	 * Set the anchor type for one end of the arrow
 	 *
 	 * @param end the end of the arrow we're setting the anchor type for
 	 * @param type the anchor type for this end of the arrow
 	 */
	public void setAnchorType(ArrowEnd end, AnchorType type);
}
