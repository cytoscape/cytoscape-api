package org.cytoscape.view.model;

import java.util.Collections;
import java.util.Set;

/**
 * Defines a discrete range of values for {@link VisualProperty}s.
 *
 * @param <T> The generic type of this DiscreteRange.
 */
public class DiscreteRange<T> implements Range<T> {

	private final Class<T> type;
	private final Set<T> values;
	
	/**
	 * Constructor for this DiscreteRange.
	 * @param type the type of this DiscreteRange.
	 * @param values the initial values of this DiscreteRange.
	 */
	public DiscreteRange(final Class<T> type, final Set<T> values) {
		this.type = type;
		this.values = values;
	}
	
	@Override
	public Class<T> getType() {
		return type;
	}

	@Override
	public boolean isDiscrete() {
		return true;
	}

	/**
	 * Returns a Set of all the values for this DiscreteRange.
	 * @return a Set of all the values for this DiscreteRange.
	 */
	public Set<T> values() {
		// This is immutable to prevent add/remove operation by 3rd party developers.
		return Collections.unmodifiableSet(values);
	}

	/**
	 * Add a value to this DiscreteRange.
	 * @param newValue the value to add to the range.
	 */
	public void addRangeValue(final T newValue) {
		values.add(newValue);
	}

	@Override
	public boolean inRange(T value) {
		if(values.contains(value))
			return true;
		else
			return false;
	}

}
