package org.cytoscape.view.presentation.property.values;

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

import static org.cytoscape.view.presentation.property.values.Justification.JUSTIFY_CENTER;
import static org.cytoscape.view.presentation.property.values.Position.CENTER;


import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.cytoscape.view.presentation.property.values.Justification;
import org.cytoscape.view.presentation.property.values.Position;

/**
 * ObjectPosition is the value that is used by Position VisualProperties.
 * It includes information about the anchor point of the object itself, the
 * anchor point of the target, the justification, and an x,y offset.
 *
 * @CyAPI.Final.Class
 * @CyAPI.InModule presentation-api
 */
public final class ObjectPosition {
	
	private static final Pattern p = Pattern
			.compile("^([NSEWC]{1,2}+),([NSEWC]{1,2}+),([clr]{1}+),(-?\\d+(.\\d+)?),(-?\\d+(.\\d+)?)$");
	
	public static ObjectPosition DEFAULT_POSITION = new ObjectPosition();
	
	private Position objectAnchor;
	private Position targetAnchor;
	private Justification justify;

	private double xOffset;
	private double yOffset;

	/**
	 * Creates a new ObjectPosition object.
	 */
	public ObjectPosition() {
		this(CENTER, CENTER, JUSTIFY_CENTER, 0.0, 0.0);
	}

	/**
	 * Copy constructor
	 * 
	 * @param p original position.
	 */
	public ObjectPosition(final ObjectPosition p) {
		targetAnchor = p.getTargetAnchor();
		objectAnchor = p.getAnchor();
		xOffset = p.getOffsetX();
		yOffset = p.getOffsetY();
		justify = p.getJustify();
	}

	/**
	 * Creates a new ObjectPosition object.
	 * 
	 * @param targ the anchor Position of the target (e.g. Node)
	 * @param lab the anchor Position of the label
	 * @param just the Justification of the text
	 * @param x an x offset
	 * @param y a y offset
	 */
	public ObjectPosition(final Position targ, final Position lab,
			final Justification just, final double x, final double y) {
		targetAnchor = targ;
		objectAnchor = lab;
		justify = just;
		xOffset = x;
		yOffset = y;
	}

	/**
	 * Return the anchor of the label (or other object).
	 *
	 * @return object anchor
	 */
	public Position getAnchor() {
		return objectAnchor;
	}

	/**
	 * Return the anchor of the target (e.g. Node)
	 *
	 * @return target anchor
	 */
	public Position getTargetAnchor() {
		return targetAnchor;
	}

	/**
	 * Return the justification of the label (or other object).
	 *
	 * @return text justification
	 */
	public Justification getJustify() {
		return justify;
	}

	/**
	 * Return the X offset of the object
	 *
	 * @return x offset
	 */
	public double getOffsetX() {
		return xOffset;
	}

	/**
	 * Return the Y offset of the object
	 *
	 * @return Y offset
	 */
	public double getOffsetY() {
		return yOffset;
	}

	/**
	 * Set the anchor of the label (or other object).
	 *
	 * @param p anchor of the object
	 */
	public void setAnchor(Position p) {
		objectAnchor = p;
	}

	/**
	 * Set the anchor of the target object (e.g. Node)
	 *
	 * @set p anchor of the target
	 */
	public void setTargetAnchor(Position p) {
		targetAnchor = p;
	}

	/**
	 * Set the justification of the label (or other object).
	 *
	 * @param j text justification
	 */
	public void setJustify(Justification j) {
		justify = j;
	}

	/**
	 * Set the X offset of the object
	 *
	 * @param d x offset
	 */
	public void setOffsetX(double d) {
		xOffset = d;
	}

	/**
	 * Set the Y offset of the object
	 *
	 * @param d y offset
	 */
	public void setOffsetY(double d) {
		yOffset = d;
	}

	/**
	 * Compare two ObjectPositions
	 *
	 * @return true if these are the same
	 */
	public boolean equals(Object lp) {
		// Accepts non-null ObjectPosition only.
		if (lp == null || lp instanceof ObjectPosition == false)
			return false;

		final ObjectPosition p = (ObjectPosition) lp;

		if (Math.abs(p.getOffsetX() - xOffset) > 0.0000001)
			return false;
	
		if (Math.abs(p.getOffsetY() - yOffset) > 0.0000001)
			return false;
		
		if (p.getAnchor() != objectAnchor)
			return false;

		if (p.getTargetAnchor() != targetAnchor)
			return false;
		
		if (p.getJustify() != justify)
			return false;

		return true;
	}

	/**
	 * Return a string representation of this ObjectPosition
	 *
	 * @return a string representation of this ObjectPosition
	 */
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("target: ").append(targetAnchor.getName());
		sb.append("  object: ").append(objectAnchor.getName());
		sb.append("  justify: ").append(justify.getName());
		sb.append("  X offset: ").append(Double.toString(xOffset));
		sb.append("  Y offset: ").append(Double.toString(yOffset));

		return sb.toString();
	}

	
	private String shortString() {
		// force the locale to US so that we consistently serialize
		final DecimalFormat df = new DecimalFormat("#0.00;-#0.00", new DecimalFormatSymbols(Locale.US));

		final StringBuilder sb = new StringBuilder();
		sb.append(targetAnchor.getShortName());
		sb.append(",");
		sb.append(objectAnchor.getShortName());
		sb.append(",");
		sb.append(justify.getShortName());
		sb.append(",");
		sb.append(df.format(xOffset));
		sb.append(",");
		sb.append(df.format(yOffset));

		return sb.toString();
	}
	
	/**
	 * Return a String that represents this ObjectPosition, suitable
	 * for recreating the ObjectPosition with {@link parse}
	 *
	 * @return serialized string
	 */
	public String toSerializableString() {
		return shortString();
	}
	
	
	/**
	 * Parse a String that has been written by {@link toSerializableString} and
	 * create a new ObjectPosition that matches the text description
	 *
	 * @param serializableString
	 * @return Never returns null.  If invalid, simply returns default.
	 */
	public static ObjectPosition parse(final String serializableString) {
		
		final Matcher m = p.matcher(serializableString);
		if (m.matches()) {
			final ObjectPosition lp = new ObjectPosition();
			lp.setTargetAnchor(Position.parse(m.group(1)));
			lp.setAnchor(Position.parse(m.group(2)));
			lp.setJustify(Justification.parse(m.group(3)));
			lp.setOffsetX(Double.valueOf(m.group(4)));
			lp.setOffsetY(Double.valueOf(m.group(6)));
			return lp;
		}

		return DEFAULT_POSITION;
	}
}
