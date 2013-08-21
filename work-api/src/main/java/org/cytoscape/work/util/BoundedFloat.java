package org.cytoscape.work.util;

/*
 * #%L
 * Cytoscape Work API (work-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2013 The Cytoscape Consortium
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

/**
 * A Float object which has <i>low</i> and <i>up</i> bounds.
 * @CyAPI.Final.Class 
 * @CyAPI.InModule work-api
 */
public final class BoundedFloat extends AbstractBounded<Float> {

	/**
	 * Creates a new Bounded Float object.
	 *
	 * @param lower  The lower bound value.
	 * @param initValue  Initial of default value for the Float.
	 * @param upper  The upper bound value.
	 * @param lowerStrict	True means that the value cannot be equal to the lower bound.
	 * @param upperStrict	True means that the value cannot be equal to the upper bound.
	 */
	public BoundedFloat(final Float lower, final Float initValue, final Float upper, boolean lowerStrict, boolean upperStrict) {
		super(lower,initValue,upper,lowerStrict,upperStrict);
	}

	/**
	 * Set a new value to the BoundedFloat object.
	 * @param s String converted into Float.
	 */
	public void setValue(String s) {
		setValue( Float.valueOf(s) );
	}

	/**
	 * Clamp the value to be within the range.
	 *
	 */
	public Float clamp(Float value) {
		if (value <= getLowerBound()) {
			if (!isLowerBoundStrict()) 
				return getLowerBound();
			else
				return Math.nextAfter(value, Float.NEGATIVE_INFINITY);
		}
		if (value >= getUpperBound()) {
			if (!isUpperBoundStrict()) 
				return getUpperBound();
			else
				return Math.nextAfter(value, Float.POSITIVE_INFINITY);
		}
		return value;
	}
}
