package org.cytoscape.view.vizmap;

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
 * 
 */
public final class VisualPropertyDependency<T> {
	
	private final String displayName;
	private final Set<VisualProperty<T>> vpSet;
	
	private final VisualProperty<T> parentVisualProperty;
	
	private boolean enabled;
	
	public VisualPropertyDependency(final String displayName, final Set<VisualProperty<T>> vpSet, final VisualLexicon lexicon) {
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
	 * A set of Visual Properties to be set by the parent if locked.
	 * 
	 * @return set of visual properties to be set by parent value.
	 */
	public Set<VisualProperty<?>> getVisualProperties() {
		return Collections.<VisualProperty<?>>unmodifiableSet(vpSet);
	}
	
	public void setDependency(boolean enable) {
		this.enabled = enable;
	}
	
	public boolean isDependencyEnabled() {
		return enabled;
	}
	
	public VisualProperty<T> getParentVisualProperty() {
		return parentVisualProperty;
	}
	
	@Override
	public String toString() {
		return displayName;
	}
}
