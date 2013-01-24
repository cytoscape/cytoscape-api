package org.cytoscape.view.model;

/*
 * #%L
 * Cytoscape View Model API (viewmodel-api)
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

import org.cytoscape.model.CyIdentifiable;


/**
 * An abstract implementation of VisualProperty that omits the methods dealing
 * with serializing data.
 * @param <T> the generic type for this AbstractVisualProperty.
 * @CyAPI.Abstract.Class
 */
public abstract class AbstractVisualProperty<T> implements VisualProperty<T> {

	// Default value for this VP.
	private final T defaultValue;
	
	private final Range<T> range;

	// Identifier.
	private final String id;

	// Human-readable name of VP.
	private final String name;

	/** If this is true, default value will be ignored by VizMapper. */
	protected boolean shouldIgnoreDefault;
	
	private final Class<? extends CyIdentifiable> targetObjectDataType;

	
	/**
	 * Constructor to set all immutable fields.
	 * 
	 * @param defaultValue default value for this visual property.
	 * @param range the {@link Range} for the visual property.
	 * @param id unique string for serialization.
	 * @param displayName human-readable name for this visual property.
	 * @param targetObjectDataType the type of the target object for this visual property. 
	 * 
	 */
	public AbstractVisualProperty(final T defaultValue, final Range<T> range, final String id,
			final String displayName, final Class<? extends CyIdentifiable> targetObjectDataType) {
		if (defaultValue == null)
			throw new NullPointerException("defaultValue should not be null.");

		if (id == null)
			throw new NullPointerException("id should not be null.");

		if (displayName == null)
			throw new NullPointerException("displayName should not be null.");

		this.range = range;

		this.defaultValue = defaultValue;
		this.id = id;
		this.name = displayName;
		this.shouldIgnoreDefault = false;
		this.targetObjectDataType = targetObjectDataType;
	}

	@Override
	public Range<T> getRange() {
		return range;
	}
	
	@Override public T getDefault() {
		return defaultValue;
	}
	
	@Override
	public String getIdString() {
		return id;
	}

	@Override
	public String getDisplayName() {
		return name;
	}

	@Override
	public boolean shouldIgnoreDefault() {
		return this.shouldIgnoreDefault;
	}
	
	@Override public Class<? extends CyIdentifiable> getTargetDataType() {
		return this.targetObjectDataType;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [id=" + id + ", defaultValue=" + defaultValue + "]";
	}
}
