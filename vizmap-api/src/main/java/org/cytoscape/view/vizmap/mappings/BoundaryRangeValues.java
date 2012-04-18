/*
  File: BoundaryRangeValues.java

  Copyright (c) 2006, The Cytoscape Consortium (www.cytoscape.org)

  The Cytoscape Consortium is:
  - Institute for Systems Biology
  - University of California San Diego
  - Memorial Sloan-Kettering Cancer Center
  - Institut Pasteur
  - Agilent Technologies

  This library is free software; you can redistribute it and/or modify it
  under the terms of the GNU Lesser General Public License as published
  by the Free Software Foundation; either version 2.1 of the License, or
  any later version.

  This library is distributed in the hope that it will be useful, but
  WITHOUT ANY WARRANTY, WITHOUT EVEN THE IMPLIED WARRANTY OF
  MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.  The software and
  documentation provided hereunder is on an "as is" basis, and the
  Institute for Systems Biology and the Whitehead Institute
  have no obligations to provide maintenance, support,
  updates, enhancements or modifications.  In no event shall the
  Institute for Systems Biology and the Whitehead Institute
  be liable to any party for direct, indirect, special,
  incidental or consequential damages, including lost profits, arising
  out of the use of this software and its documentation, even if the
  Institute for Systems Biology and the Whitehead Institute
  have been advised of the possibility of such damage.  See
  the GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with this library; if not, write to the Free Software Foundation,
  Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
 */

// BoundaryRangeValues.java
//----------------------------------------------------------------------------
// $Revision: 10059 $
// $Date: 2007-04-20 16:29:49 -0700 (Fri, 20 Apr 2007) $
// $Author: kono $
//----------------------------------------------------------------------------
package org.cytoscape.view.vizmap.mappings;


//----------------------------------------------------------------------------
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
	 * @param lesser
	 *            Object used for values less than this point.
	 * @param equal
	 *            Object used for value equal to this point.
	 * @param greater
	 *            Object used for values greater than this point.
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "{" + lesserValue.toString() + "," + equalValue.toString() + 
				"," + greaterValue.toString() + "}";
	}
}
