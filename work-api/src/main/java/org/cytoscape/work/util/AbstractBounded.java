/*
 Copyright (c) 2008, 2010, The Cytoscape Consortium (www.cytoscape.org)

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
package org.cytoscape.work.util;


/**
 * A bounded number object whose bounds values cannot be modified
 * @param <N>  Any type of Number
 * @author Pasteur
 */
abstract public class AbstractBounded<N extends Comparable<N>> {
	/**
	 *  Initial value of the Bounded Object that will be modified
	 */
	private N value;

	/**
	 *  Value of the lower bound of the Bounded Object
	 */
	private final N lower;

	/**
	 *  Value of the upper bound of the Bounded Object
	 */
	private final N upper;

	/**
	 *  Whether or not the <code>value</code> could be set to the <code>upper</code> value
	 *
	 *  <p><pre>
	 *    <code> if (upperStrict)</code> , then the <code>value</code> cannot be set to <code>upper</code>
	 *  </pre></p>
	 */
	private final boolean upperStrict;

	/**
	 *  Whether or not the <code>value</code> could be set to the <code>lower</code> value
	 *
	 *  <p><pre>
	 *    <code> if (lowerStrict)</code> , then the <code>value</code> cannot be set to <code>lower</code>
	 *  </pre></p>
	 */
	private final boolean lowerStrict;

	/**
	 *  Creates a new Bounded object.
	 *
	 *  @param lower        The lower bound value
	 *  @param initValue    The initial value
	 *  @param upper        The upper bound value
	 *  @param lowerStrict	True means that the value cannot be equal to the lower bound
	 *  @param upperStrict	True means that the value cannot be equal to the upper bound
	 */
	 public AbstractBounded(final N lower, final N initValue, final N upper, boolean lowerStrict, boolean upperStrict) {
		if (lower == null)
			throw new NullPointerException("lower bound is null!");

		if (upper == null)
			throw new NullPointerException("upper bound is null!");

		if (lower.compareTo(upper) >= 0)
			throw new IllegalArgumentException("lower value is greater than or equal to upper value");

		this.lower = lower;
		this.upper = upper;
		this.lowerStrict = lowerStrict;
		this.upperStrict = upperStrict;
		setValue(initValue);
	}

	/**
	 *  Returns the upper limit of the object
	 *
	 * @return the upper bound
	 */
	public N getUpperBound() {
		return upper;
	}

	/**
	 *  Returns the lower limit of the object
	 *
	 * @return the lower bound
	 */
	public N getLowerBound() {
		return lower;
	}

	/**
	 *  Does the value have to be strictly lower than the upper bound?
	 *
	 *  @return true if the upper bound is strict, otherwise false
	 */
	public boolean isUpperBoundStrict() {
		return upperStrict;
	}

	/**
	 * Does the value have to be strictly greater than the lower bound?
	 *
	 * @return true if the lower bound is strict, otherwise false
	 */
	public boolean isLowerBoundStrict() {
		return lowerStrict;
	}

	/**
	 *  Returns the value
	 *
	 * @return the current value of the <code>Bounded</code> object
	 */
	public synchronized N getValue() {
		return value;
	}

	/**
	 *  Set the value <code>v</code> as the value of the Bounded Object.
	 *
	 *  @param v the value to be set
	 */
	public void setValue(final N v) {
		if (v == null)
			throw new NullPointerException("value is null!");

		synchronized (this) {
			final int up = v.compareTo(upper);

			if (upperStrict) {
				if (up >= 0)
					throw new IllegalArgumentException("value is greater than or equal to upper limit");
			} else {
				if (up > 0)
					throw new IllegalArgumentException("value is greater than upper limit");
			}

			final int low = v.compareTo(lower);

			if (lowerStrict) {
				if (low <= 0)
					throw new IllegalArgumentException("value is less than or equal to lower limit");
			} else {
				if (low < 0)
					throw new IllegalArgumentException("value is less than lower limit");
			}

			value = v;
		}
	}

	/** Sets the value of the <code>Bounded</code> object.
	 *
	 *  @param s  will be converted to the value type of the <code>Bounded</code> object.
	 */
	public abstract void setValue(String s);
}
