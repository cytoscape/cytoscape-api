package org.cytoscape.view.vizmap;

import java.util.Collections;
import java.util.Set;

import org.cytoscape.event.CyEventHelper;
import org.cytoscape.view.model.VisualLexicon;
import org.cytoscape.view.model.VisualLexiconNode;
import org.cytoscape.view.model.VisualProperty;
import org.cytoscape.view.vizmap.events.VisualPropertyDependencyChangedEvent;

/*
 * #%L
 * Cytoscape VizMap API (vizmap-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2017 The Cytoscape Consortium
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

/**
 * Represents a set of Visual Properties to be set by their parent value if
 * dependency is enabled.
 *
 * @param <T> type of {@linkplain VisualProperty} value in this group.
 * @CyAPI.Api.Interface 
 * @CyAPI.InModule vizmap-api
 */
public final class VisualPropertyDependency<T> {
	
	private final String id;
	private final String displayName;
	private final Set<VisualProperty<T>> vpSet;
	
	private final VisualProperty<T> parentVisualProperty;
	
	private boolean enabled;
	
	private CyEventHelper eventHelper;

	/**
	 * Constructor.
	 * @param displayName A human readable name for use in user interfaces.
	 * @param vpSet The set of visual properties.
	 * @param lexicon The visual lexicon used by this dependency. The lexicon determines
	 * the parent visual property for this dependency.
	 */
	public VisualPropertyDependency(final String id, final String displayName, final Set<VisualProperty<T>> vpSet, final VisualLexicon lexicon) {
		this.id = id;
		this.displayName = displayName;
		this.vpSet = vpSet;
		this.enabled = false;
		
		this.parentVisualProperty = getParent(lexicon);
	}
	
	@SuppressWarnings("unchecked")
	private VisualProperty<T> getParent(VisualLexicon lexicon) {
		
		// Make sure all VPs are pointing to the same parent
		VisualProperty<?> parent = null;
		for(final VisualProperty<T> vp: vpSet) {
			final VisualLexiconNode node = lexicon.getVisualLexiconNode(vp);
			if(parent == null)
				parent = node.getParent().getVisualProperty();
			else {
				final VisualProperty<?> newParent = node.getParent().getVisualProperty();
				if (newParent != parent)
					throw new IllegalArgumentException("All Visual Properties should points to the same parent: "
							+ newParent.getDisplayName() + ", " + parent.getDisplayName());
			}
		}
		
		return (VisualProperty<T>) parent;
	}

	/**
	 * Provides human-readable name of this dependency. For example,
	 * "Synchronize edge color to arrow head color," or
	 * "Fit Custom Graphics to node"
	 * 
	 * @return name of this dependency.
	 */
	public String getDisplayName() {
		return displayName;
	}
	
	
	/**
	 * Provides serializable string of this dependency.
	 * Will be used for saving and loading this dependency.
	 * 
	 * @return ID of this dependency.
	 */
	public String getIdString() {
		return id;
	}

	/**
	 * A set of Visual Properties to be set by the parent if locked.
	 * 
	 * @return set of visual properties to be set by parent value.
	 */
	public Set<VisualProperty<?>> getVisualProperties() {
		return Collections.<VisualProperty<?>>unmodifiableSet(vpSet);
	}

	/**
	 * Enables or disables the dependency based on the boolean parameter.
	 * @param enable Whether to enable (true) or disable (false) this dependency.
	 */
	public void setDependency(boolean enable) {
		if (enable != this.enabled) {
			this.enabled = enable;
			
			if (eventHelper != null)
				eventHelper.fireEvent(new VisualPropertyDependencyChangedEvent(this));
		}
	}

	/**
	 * Returns whether or not this dependency is enabled.
	 * @return whether or not this dependency is enabled.
	 */
	public boolean isDependencyEnabled() {
		return enabled;
	}
	
	/**
	 * Returns the parent visual property for this dependency. 
	 * @return the parent visual property for this dependency. 
	 */
	public VisualProperty<T> getParentVisualProperty() {
		return parentVisualProperty;
	}
	
	/**
	 * The {@link VisualStyle} implementation should use this method to pass a valid CyEventHelper
	 * after this dependency is added to the visual style object.
	 * @param eventHelper
	 */
	public void setEventHelper(CyEventHelper eventHelper) {
		this.eventHelper = eventHelper;
	}
	
	@Override
	public String toString() {
		return displayName;
	}

	@Override
	public int hashCode() {
		final int prime = 37;
		int result = 23;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof VisualPropertyDependency)) return false;
		VisualPropertyDependency<?> other = (VisualPropertyDependency<?>) obj;
		if (id == null) {
			if (other.id != null) return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
}
