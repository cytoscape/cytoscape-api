package org.cytoscape.view.presentation.annotations;

/*
 * #%L
 * Cytoscape Presentation API (presentation-api)
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

import java.awt.geom.Point2D;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.cytoscape.view.model.CyNetworkView;

/**
 * This is the base interface for all Annotations.  An annotation is a graphical object that
 * is displayed on the network, but is not part of the graph model.  Example annotations are
 * shapes, text, and images that annotate the contents of the network to elucidate aspects of
 * the network for presentation or explanatory purposes.  In general, annotations are drawn
 * either on top of the network ({@link #FOREGROUND}) or behind ({@link #BACKGROUND}) the network
 * canvas.  Arg map initialization parameters (for {@link AnnotationFactory#createAnnotation()})
 * include: {@link #ZOOM}, {@link #CANVAS}, {@link #X}, and {@link #Y}.
 *
 * @CyAPI.Api.Interface
 * @CyAPI.InModule presentation-api
 */

public interface Annotation {
	/**
	 * The background canvas
	 */
	public static final String BACKGROUND =  "background";

	/**
	 * The foreground canvas
	 */
	public static final String FOREGROUND =  "foreground";

	/**
	 * Arg map key to initialize the zoom value for this annotation
	 */
	public static final String ZOOM = "zoom";

	/**
	 * Arg map key to initialize the canvas to draw this annotation on
	 */
	public static final String CANVAS = "canvas";

	/**
	 * Arg map key to initialize the x location for this annotation
	 */
	public static final String X = "x";

	/**
	 * Arg map key to initialize the y location for this annotation
	 */
	public static final String Y = "y";

	/**
	 * Arg map key to initialize the z location for this annotation.
	 * Note that the z-order is just a hint. The AnnotationManager is free to set an annotation's
	 * z-order according to its placement within an AnnotationGroup.
	 */
	public static final String Z = "z";

	/**
	 * Arg map key to initialize the name of this annotation
	 */
	public static final String NAME = "name";

	/**
	 * Return the view that this Annotation is for
	 *
	 * @return the network view this annotation is for
	 */
	public CyNetworkView getNetworkView();

	/**
	 * Return the canvas that this Annotation is on
	 *
	 * @return the canvas this annotation is on
	 */
	public String getCanvasName();

	/**
	 * Set the canvas for this annotation.  This must be one of
	 * BACKGROUND or FOREGROUND.
	 * <br><br>
	 * Note: Changing the canvas of a GroupAnnotation will not automatically set its members
	 * to the same canvas. Each annotation must have its setCanvas() method called individually.
	 * 
	 * @param canvas the canvas to move the annotation to
	 */
	public void setCanvas(String canvas);

	/**
	 * Get the name for this annotation.  This is useful for applications
	 * that want to allow the user to associate a particular annotation
	 * with an aspect of an algorithm.
	 */
	public default String getName() { return null; }

	/**
	 * Set the name for this annotation.  This is useful for applications
	 * that want to allow the user to associate a particular annotation
	 * with an aspect of an algorithm.
	 */
	public default void setName(String name) { }

	/**
	 * Move an annotation to the specified location
	 *
	 * @param location the location to move the annotation to
	 */
	public void moveAnnotation(Point2D location);

	/**
	 * Get the current zoom for this annotation
	 *
	 * @return the current zoom value
	 */
	public double getZoom();

	/**
	 * Set the current zoom for this annotation
	 *
	 * @param zoom the zoom to set
	 */
	public void setZoom(double zoom);


	/**
	 * Get the specific zoom for this annotation
	 *
	 * @return the current specific zoom for this annotation
	 */
	public double getSpecificZoom();

	/**
	 * Set the specific zoom for this annotation
	 *
	 * @param zoom the specific zoom for this annotation
	 */
	public void setSpecificZoom(double zoom);

	/**
	 * Return true if this annotation is currently selected
	 *
	 * @return true if selected
	 */
	public boolean isSelected();

	/**
	 * Set the selected state for this annotation
	 *
	 * @param selected select if true
	 */
	public void setSelected(boolean selected);

	/**
	 * Add an arrow annotation to this annotation.  This will throw an
	 * IllegalArgument exception if this is an arrow annotation.
	 *
	 * @param arrow the arrow annotation to add
	 */
	public void addArrow(ArrowAnnotation arrow);

	/**
	 * Remove an arrow from this annotation.
	 *
	 * @param arrow the arrow annotation to remove
	 */
	public void removeArrow(ArrowAnnotation arrow);

	/**
	 * Get all arrows that are currently linked to this annotation.
	 *
	 * @return the set of arrows linked to this annotation
	 */
	public Set<ArrowAnnotation> getArrows();

	/**
	 * Get the argument map that is used to serialize this annotation.  This
	 * is essentially the inverse of the argMap used in {@link AnnotationFactory#createAnnotation()}.
	 *
	 * @return the argMap
	 */
	public Map<String, String> getArgMap();

	/**
	 * Get the UUID of this annotation
	 *
	 * @return the annotation UUID
	 */
	public UUID getUUID();

	/**
	 * Remove the annotation.  This will remove the annotation from the canvas
	 * and the annotation manager.
	 * @deprecated Use {@link AnnotationManager#removeAnnotation()} instead.
	 */
	@Deprecated
	public void removeAnnotation();

	/**
	 * Update the annotation.  This will cause the annotation to redraw on
	 * the canvas.
	 */
	public void update();
}
