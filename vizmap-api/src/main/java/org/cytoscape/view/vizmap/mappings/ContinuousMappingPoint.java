/*
  File: ContinuousMappingPoint.java

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

//----------------------------------------------------------------------------
// $Revision: 10980 $
// $Date: 2007-07-17 10:16:16 -0700 (Tue, 17 Jul 2007) $
// $Author: kono $
//----------------------------------------------------------------------------
package org.cytoscape.view.vizmap.mappings;

import org.cytoscape.view.vizmap.mappings.BoundaryRangeValues;


/**
 * Encapsulates a ContinuousMapping Point with a single point value
 * and associated BoundaryRangeValues.
 *
 */
public final class ContinuousMappingPoint<K, V> {
	private K value;
	private BoundaryRangeValues<V> range;

	/**
	 * Constructor.
	 * @param value double.
	 * @param range BoundaryRangeValues object.
	 */
	public ContinuousMappingPoint(K value, BoundaryRangeValues<V> range) {
		if( value instanceof Number == false)
			throw new IllegalArgumentException("Value must be a number.");
		
		this.value = value;
		this.range = range;
	}

	/**
	 * Gets Point Value.
	 * @return double value.
	 */
	public K getValue() {
		return value;
	}

	/**
	 * Sets Point Value.
	 * @param value double value.
	 */
	public void setValue(K value) {
		this.value = value;
	}

	/**
	 * Gets BoundaryRangeValues.
	 * @return BoundaryRangeValues Object.
	 */
	public BoundaryRangeValues<V> getRange() {
		return range;
	}

	/**
	 * Sets BoundaryRangeValues.
	 * @param range BoundaryRangeValues Object.
	 */
	public void setRange(BoundaryRangeValues<V> range) {
		this.range = range;
	}

}
