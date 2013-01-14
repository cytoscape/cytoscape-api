/*
 Copyright (c) 2008, The Cytoscape Consortium (www.cytoscape.org)

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
package org.cytoscape.view.model;

import org.cytoscape.model.CyIdentifiable;


/**
 * An abstract implementation of VisualProperty that omits the methods dealing
 * with serializing data.
 * @param <T> the generic type for this AbstractVisualProperty.
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule viewmodel-api
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
