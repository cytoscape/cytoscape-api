package org.cytoscape.view.vizmap.gui.event;

import java.util.Set;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.view.model.VisualProperty;

/**
 * #ASKMIKE constructor/method comments
 * @CyAPI.Final.Class
 */
public final class LexiconStateChangedEvent extends AbstractCyEvent<Object> {

	private final Set<VisualProperty<?>> enabled;
	private final Set<VisualProperty<?>> disabled;

	public LexiconStateChangedEvent(final Object source,
			final Set<VisualProperty<?>> enabled,
			final Set<VisualProperty<?>> disabled) {
		super(source, LexiconStateChangedListener.class);
		this.enabled = enabled;
		this.disabled = disabled;
	}

	public Set<VisualProperty<?>> getDisabled() {
		return this.disabled;
	}

	public Set<VisualProperty<?>> getEnabled() {
		return this.enabled;
	}

}
