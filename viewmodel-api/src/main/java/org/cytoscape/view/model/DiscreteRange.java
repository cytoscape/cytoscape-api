package org.cytoscape.view.model;

import java.util.Collections;
import java.util.Set;

public class DiscreteRange<T> implements Range<T> {

	private final Class<T> type;
	private final Set<T> values;
	
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

	public Set<T> values() {
		// This is immutable to prevent add/remove operation by 3rd party developers.
		return Collections.unmodifiableSet(values);
	}

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
