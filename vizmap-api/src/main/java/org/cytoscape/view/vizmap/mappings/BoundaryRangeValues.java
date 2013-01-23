package org.cytoscape.view.vizmap.mappings;

/*
 * #%L
 * Cytoscape VizMap API (vizmap-api)
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


/**
 * This class defines a data object representing the range values associated
 * with a particular domain value, called a boundary value. The domain value is
 * not stored here, since objects of this class are intended to be used as the
 * values in a map where the domain value is the key.
 *
 * Three values must be specified for each boundary value. The lesserValue field
 * is used for interpolation upon smaller domain values; the greaterValue field
 * is used for interpolation upon larger domain values; and the equalValue field
 * is used when the domain value is exactly equal to the associated boundary
 * domain value. This distinction is needed to support different ranges of
 * interpolation above and below the same domain value, plus allow a distinctly
 * different value for exact matches.
 *
 * @param <T> the generic type for this BoundaryRangeValues.
 * @CyAPI.Final.Class
 * @CyAPI.InModule vizmap-api
 */
public final class BoundaryRangeValues<T> {
	/**
	 * Will be used for interpolation upon smaller domain values
	 */
	public final T lesserValue;

	/**
	 * Will be used when the domain value is exactly equal to the associated boundary domain value
	 */
	public final T equalValue;

	/**
	 * Will be used for interpolation upon larger domain values
	 */
	public final T greaterValue;

	/**
	 * Creates a new BoundaryRangeValues object.
	 *
	 * @param lesser Object used for values less than this point.
	 * @param equal Object used for value equal to this point.
	 * @param greater Object used for values greater than this point.
	 */
	public BoundaryRangeValues(T lesser, T equal, T greater) {
		lesserValue = lesser;
		equalValue = equal;
		greaterValue = greater;
	}

	/**
	 * Creates a new BoundaryRangeValues object. This is a copy constructor.
	 *
	 * @param original original value.
	 */
	public BoundaryRangeValues(final BoundaryRangeValues<T> original) {
		this.equalValue = original.equalValue;
		this.lesserValue = original.lesserValue;
		this.greaterValue = original.greaterValue;
	}

	@Override
	public String toString() {
		return "{" + lesserValue.toString() + "," + equalValue.toString() + 
				"," + greaterValue.toString() + "}";
	}
}
