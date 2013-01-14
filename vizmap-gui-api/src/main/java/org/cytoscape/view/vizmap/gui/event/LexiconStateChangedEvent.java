package org.cytoscape.view.vizmap.gui.event;

import java.util.Set;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.view.model.VisualProperty;

/**
 * Tell listeners a enabled/disabled visual properties.
 * @CyAPI.Final.Class
 * @CyAPI.InModule vizmap-gui-api
 */
public final class LexiconStateChangedEvent extends AbstractCyEvent<Object> {

	private final Set<VisualProperty<?>> enabled;
	private final Set<VisualProperty<?>> disabled;

	/**
	 * Constructor of the event.
	 * 
	 * @param source Source of this event.
	 * @param enabled set of Visual Properties to be enabled in the lexicon.
	 * @param disabled set of Visual Properties to be disabled in the lexicon.
	 */
	public LexiconStateChangedEvent(final Object source,
			final Set<VisualProperty<?>> enabled,
			final Set<VisualProperty<?>> disabled) {
		super(source, LexiconStateChangedListener.class);
		this.enabled = enabled;
		this.disabled = disabled;
	}

	/**
	 * Get set of Visual Properties to be disabled in the lexicon.
	 * 
	 * @return set of lexicon to be disabled
	 */
	public Set<VisualProperty<?>> getDisabled() {
		return this.disabled;
	}

	/**
	 * Get set of Visual Properties to be enabled in the lexicon.
	 * 
	 * @return set of VP to be enabled.
	 */
	public Set<VisualProperty<?>> getEnabled() {
		return this.enabled;
	}

}
