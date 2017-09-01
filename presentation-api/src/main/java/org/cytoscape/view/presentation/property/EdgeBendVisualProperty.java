package org.cytoscape.view.presentation.property;

/*
 * #%L
 * Cytoscape Presentation API (presentation-api)
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

import java.util.Collections;
import java.util.List;

import org.cytoscape.model.CyEdge;
import org.cytoscape.view.model.AbstractVisualProperty;
import org.cytoscape.view.model.ContinuousRange;
import org.cytoscape.view.model.Range;
import org.cytoscape.view.presentation.property.values.Bend;
import org.cytoscape.view.presentation.property.values.BendFactory;
import org.cytoscape.view.presentation.property.values.Handle;

/**
 * Visual Property for Edge {@link Bend} values.
 * 
 * @CyAPI.Final.Class
 * @CyAPI.InModule presentation-api
 */
public class EdgeBendVisualProperty extends AbstractVisualProperty<Bend> {

	/**
	 * The default edge bend.  This "bend" is empty and contains no actual bend.
	 */
	public static final Bend DEFAULT_EDGE_BEND = new EmptyBendImpl();

	private static final Range<Bend> EDGE_BEND_RANGE = new ContinuousRange<Bend>(Bend.class, DEFAULT_EDGE_BEND, DEFAULT_EDGE_BEND, true, true);

	private BendFactory bendFactory;

	/**
	 * Constructor.
	 * @param defaultValue The default bend to use.
	 * @param id A machine readable string identifying this visual property used for XML serialization. 
	 * @param displayName A human readable string used for displays and user interfaces. 
	 */
	public EdgeBendVisualProperty(Bend defaultValue, String id, String displayName) {
		super(defaultValue, EDGE_BEND_RANGE, id, displayName, CyEdge.class);
	}

	/**
	 * Sets the bend factory that is used behind the scenes to create bend objects.
	 * @param bendFactory The bend factory to use for this instance.
	 */
	public void setBendFactory(final BendFactory bendFactory) {
		this.bendFactory = bendFactory;
	}

	@Override
	public String toSerializableString(final Bend value) {
	try
	{
		return value.getSerializableString();
	}
	catch(ClassCastException ex)
	{
		System.out.println("Bend: " + getIdString() + ", " + getDisplayName() + " - - " + value);
		return null;
	}
	}

	@Override
	public Bend parseSerializableString(String value) {
		if(value == null)
			return DEFAULT_EDGE_BEND;
		else
			return bendFactory.parseSerializableString(value);
	}
	
	
	/**
	 * Immutable default Bend object w/no handle.
	 */
	private static final class EmptyBendImpl implements Bend {

		@Override
		public List<Handle> getAllHandles() {
			return Collections.emptyList();
		}

		@Override
		public void insertHandleAt(int index, Handle handle) {
			throw new UnsupportedOperationException("This is a default immutable Bend object.");
		}

		@Override
		public void removeHandleAt(int handleIndex) {
			throw new UnsupportedOperationException("This is a default immutable Bend object.");
		}

		@Override
		public void removeAllHandles() {
			throw new UnsupportedOperationException("This is a default immutable Bend object.");
		}

		@Override
		public int getIndex(Handle handle) {
			return -1;
		}

		@Override
		public String getSerializableString() {
			return null;
		}
	}
}
