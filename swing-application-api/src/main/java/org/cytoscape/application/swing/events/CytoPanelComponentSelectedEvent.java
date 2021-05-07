package org.cytoscape.application.swing.events;

/*
 * #%L
 * Cytoscape Swing Application API (swing-application-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2021 The Cytoscape Consortium
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

import org.cytoscape.application.swing.CytoPanel;
import org.cytoscape.event.AbstractCyEvent;

/**
 * The event fired to indicate that a {@link org.cytoscape.application.swing.CytoPanelComponent} has been selected.
 * @CyAPI.Final.Class
 * @CyAPI.InModule swing-application-api
 */
public final class CytoPanelComponentSelectedEvent extends AbstractCyEvent<Object> {

	private final CytoPanel cp;
	private final int index;

	/**
	 * Constructor.
	 * @param source The object firing the event.
	 * @param cp The CytoPanel on which a component was selected.
	 * @param index The index of the CytoPanelComponent selected.
	 */
	public CytoPanelComponentSelectedEvent(final Object source, final CytoPanel cp, int index) {
		super(source, CytoPanelComponentSelectedListener.class);
		this.cp = cp;
		this.index = index;
	}

	/**
	 * Returns the CytoPanel on which a component was selected.
	 * @return the CytoPanel on which a component was selected.
	 */
	public CytoPanel getCytoPanel() {
		return cp;
	}

	/**
	 * Returns the index of the CytoPanelComponent selected.
	 * @return the index of the CytoPanelComponent selected.
	 */
	public int getSelectedIndex() {
		return index;
	}
}
