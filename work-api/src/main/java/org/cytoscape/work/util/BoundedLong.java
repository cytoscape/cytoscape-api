package org.cytoscape.work.util;

/*
 * #%L
 * Cytoscape Work API (work-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2021 The Cytoscape Consortium
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
 * A Long object which has <i>low</i> and <i>up</i> bounds.
 * @CyAPI.Final.Class 
 * @CyAPI.InModule work-api
 */
public final class BoundedLong extends AbstractBounded<Long> {

	/**
	 * Creates a new Bounded Long object.
	 *
	 * @param lower  The lower bound value.
	 * @param initValue  Initial of default value for the Long.
	 * @param upper  The upper bound value.
	 * @param lowerStrict	True means that the value cannot be equal to the lower bound.
	 * @param upperStrict	True means that the value cannot be equal to the upper bound.
	 */
	public BoundedLong(final Long lower, final Long initValue, final Long upper, boolean lowerStrict, boolean upperStrict) {
		super(lower,initValue,upper,lowerStrict,upperStrict);
	}
	
	/**
	 * Set a new value to the BoundedLong object.
	 * @param s String converted into Long.
	 */
	public void setValue(String s) {
		setValue( Long.valueOf(s) );
	}

	/**
	 * Clamp the value to be within the range.
	 *
	 */
	public Long clamp(Long value) {
		if (value <= getLowerBound()) {
			if (!isLowerBoundStrict()) 
				return getLowerBound();
			else
				return value+1;
		}
		if (value >= getUpperBound()) {
			if (!isUpperBoundStrict()) 
				return getUpperBound();
			else
				return value-1;
		}
		return value;
	}
			
}
