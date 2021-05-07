package org.cytoscape.view.model;

/*
 * #%L
 * Cytoscape View Model API (viewmodel-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2021 The Cytoscape Consortium
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

import java.util.Collections;
import java.util.Set;

/**
 * Defines a discrete range of values for {@link VisualProperty}s.
 *
 * @param <T> The generic type of this DiscreteRange.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule viewmodel-api
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
