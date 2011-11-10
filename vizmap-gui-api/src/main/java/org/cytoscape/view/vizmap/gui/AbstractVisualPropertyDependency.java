package org.cytoscape.view.vizmap.gui;

import java.util.HashSet;
import java.util.Set;

import org.cytoscape.view.model.VisualProperty;

/**
 * @CyAPI.Abstract.Class
 */
public abstract class AbstractVisualPropertyDependency implements
		VisualPropertyDependency {
	
	private final String displayName;
	protected final Set<VisualProperty<?>> group;
	
	public AbstractVisualPropertyDependency(final String displayName) {
		this.displayName = displayName;
		group = new HashSet<VisualProperty<?>>();
	}

	@Override
	public String getDisplayName() {
		return displayName;
	}

	@Override
	public Set<VisualProperty<?>> getVisualProperties() {
		return group;
	}

}
