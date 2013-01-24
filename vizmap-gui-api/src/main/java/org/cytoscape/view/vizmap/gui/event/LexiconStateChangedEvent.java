package org.cytoscape.view.vizmap.gui.event;

/*
 * #%L
 * Cytoscape VizMap GUI API (vizmap-gui-api)
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
