package org.cytoscape.view.vizmap;

/*
 * #%L
 * Cytoscape VizMap API (vizmap-api)
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
import java.util.Set;

import org.cytoscape.view.model.VisualLexicon;
import org.cytoscape.view.model.VisualLexiconNode;
import org.cytoscape.view.model.VisualProperty;

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
		this.enabled = enable;
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
	
	@Override
	public String toString() {
		return displayName;
	}
}
