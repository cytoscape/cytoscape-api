package org.cytoscape.view.vizmap.gui;

import java.util.HashSet;
import java.util.Set;

import org.cytoscape.view.model.VisualProperty;

/**
 * All VP dependency should extends this.
 * 
 * TODO: refactor dependnecy.
 * 
 * @CyAPI.Abstract.Class
 */
public abstract class AbstractVisualPropertyDependency implements VisualPropertyDependency {

	private final String displayName;
	
	protected final Set<VisualProperty<?>> group;

	public AbstractVisualPropertyDependency(final String displayName) {
		this.displayName = displayName;
		group = new HashSet<VisualProperty<?>>();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<VisualProperty<?>> getVisualProperties() {
		return group;
	}

}
