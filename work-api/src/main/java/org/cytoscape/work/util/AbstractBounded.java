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

import java.util.ArrayList;
import java.util.List;

/**
 * A bounded number object whose bounds values cannot be modified
 * @param <N>  Any type of Number.
 * @author Pasteur
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule work-api
 */
abstract public class AbstractBounded<N extends Comparable<N>> {
	/**
	 *  Initial value of the Bounded Object that will be modified.
	 */
	private N value;

	/**
	 *  Value of the lower bound of the Bounded Object.
	 */
	private N lower;

	/**
	 *  Value of the upper bound of the Bounded Object.
	 */
	private N upper;

	/**
	 *  Whether or not the <code>value</code> could be set to the <code>upper</code> value.
	 *
	 *  <p><pre>
	 *    <code> if (upperStrict)</code> , then the <code>value</code> cannot be set to <code>upper</code>
	 *  </pre></p>
	 */
	private boolean upperStrict;

	/**
	 *  Whether or not the <code>value</code> could be set to the <code>lower</code> value.
	 *
	 *  <p><pre>
	 *    <code> if (lowerStrict)</code> , then the <code>value</code> cannot be set to <code>lower</code>
	 *  </pre></p>
	 */
	private boolean lowerStrict;

	/**
 	 * The list of listeners to inform if something changes
 	 */
	private List<BoundedChangeListener<N>> listeners = null;

	/**
	 *  Creates a new Bounded object.
	 *
	 *  @param lower        The lower bound value.
	 *  @param initValue    The initial value.
	 *  @param upper        The upper bound value.
	 *  @param lowerStrict	True means that the value cannot be equal to the lower bound.
	 *  @param upperStrict	True means that the value cannot be equal to the upper bound.
	 */
	 public AbstractBounded(final N lower, final N initValue, final N upper, boolean lowerStrict, boolean upperStrict) {
		if (lower == null)
			throw new NullPointerException("lower bound is null.");

		if (upper == null)
			throw new NullPointerException("upper bound is null.");

		if (lower.compareTo(upper) >= 0)
			throw new IllegalArgumentException("lower value is greater than or equal to upper value");

		this.lower = lower;
		this.upper = upper;
		this.lowerStrict = lowerStrict;
		this.upperStrict = upperStrict;
		setValue(initValue);
	}

	/**
	 *  Returns the upper limit of the object.
	 *
	 * @return the upper bound.
	 */
	public N getUpperBound() {
		return upper;
	}

	/**
	 *  Returns the lower limit of the object.
	 *
	 * @return the lower bound.
	 */
	public N getLowerBound() {
		return lower;
	}

	/**
	 *  Does the value have to be strictly lower than the upper bound?
	 *
	 *  @return true if the upper bound is strict, otherwise false.
	 */
	public boolean isUpperBoundStrict() {
		return upperStrict;
	}

	/**
	 * Does the value have to be strictly greater than the lower bound?
	 *
	 * @return true if the lower bound is strict, otherwise false.
	 */
	public boolean isLowerBoundStrict() {
		return lowerStrict;
	}

	/**
	 *  Returns the value.
	 *
	 * @return the current value of the <code>Bounded</code> object.
	 */
	public synchronized N getValue() {
		return value;
	}

	/**
	 *  Set the upper bound strict policy to <code>upperStrict</code>.
	 *
	 *  @param upperStrict the upper bound strict policy to be set
	 */
	public void setUpperBoundStrict(final boolean upperStrict) {
		this.upperStrict = upperStrict;
		value = clamp(value);
		boundsChanged();
	}

	/**
	 *  Set the lower bound strict policy to <code>lowerStrict</code>.
	 *
	 *  @param lowerStrict the lower bound strict policy to be set
	 */
	public void setLowerBoundStrict(final boolean lowerStrict) {
		this.lowerStrict = lowerStrict;
		value = clamp(value);
		boundsChanged();
	}

	/**
	 *  Set the upper bound (<code>upper</code>) and 
	 *  lower bound (<code>lower</code>) of the Bounded Object.
	 *
	 *  @param lower the lower bound to be set.
	 *  @param upper the upper bound to be set.
	 */
	public void setBounds(final N lower, final N upper) {
		if (upper == null)
			throw new NullPointerException("Upper bound is null.");
		if (upper.compareTo(lower) <= 0)
			throw new IllegalArgumentException("Upper value is less than or equal to lower value");
		if (lower.compareTo(upper) >= 0)
			throw new IllegalArgumentException("Lower value is greater than or equal to upper value");
		this.upper = upper;
		this.lower = lower;
		value = clamp(value);
		boundsChanged();
	}

	/**
	 *  Set the value <code>v</code> as the value of the Bounded Object.
	 *
	 *  @param v the value to be set.
	 */
	public void setValue(final N v) {
		if (v == null)
			throw new NullPointerException("Value is null.");

		synchronized (this) {
			final int up = v.compareTo(upper);

			if (upperStrict) {
				if (up >= 0)
					throw new IllegalArgumentException("Value is greater than or equal to upper limit");
			} else {
				if (up > 0)
					throw new IllegalArgumentException("Value is greater than upper limit");
			}

			final int low = v.compareTo(lower);

			if (lowerStrict) {
				if (low <= 0)
					throw new IllegalArgumentException("Value is less than or equal to lower limit");
			} else {
				if (low < 0)
					throw new IllegalArgumentException("Value is less than lower limit");
			}

			value = v;
		}
		valueChanged();
	}

	/** Sets the value of the <code>Bounded</code> object.
	 *
	 *  @param s  will be converted to the value type of the <code>Bounded</code> object.
	 */
	public abstract void setValue(String s);

	/** 
 	 * Clamps the value of the <code>Bounded</code> object.
	 */
	public abstract N clamp(N value);


	/**
 	 * Adds a listener that will listen for changes to this object
 	 *
 	 *  @param changeListener listener object
 	 */
	public void addListener(BoundedChangeListener<N> changeListener) {
		if (listeners == null)
			listeners = new ArrayList<BoundedChangeListener<N>>();

		synchronized (listeners) {
			listeners.add(changeListener);
		}
	}

	/**
 	 * Removes a listener from the list that will listen for changes to this object
 	 *
 	 *  @param changeListener listener object
 	 */
	public void removeListener(BoundedChangeListener<N> changeListener) {
		if (listeners != null && listeners.contains(changeListener))
			synchronized (listeners) {
				listeners.remove(changeListener);
			}
	}

	/**
 	 * Alert all listeners that the bounds have changed
 	 */
	private void boundsChanged() {
		if (listeners == null) return;
		synchronized (listeners) {
			for (BoundedChangeListener<N> listener: listeners) {
				listener.boundsChanged(this);
			}
		}
	}

	/**
 	 * Alert all listeners that the value has changed
 	 */
	private void valueChanged() {
		if (listeners == null) return;
		synchronized (listeners) {
			for (BoundedChangeListener<N> listener: listeners) {
				listener.valueChanged(this);
			}
		}
	}
}
