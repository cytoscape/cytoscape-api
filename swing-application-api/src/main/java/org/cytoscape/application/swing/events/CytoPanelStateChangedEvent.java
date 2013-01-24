package org.cytoscape.application.swing.events;

/*
 * #%L
 * Cytoscape Swing Application API (swing-application-api)
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

import org.cytoscape.application.swing.CytoPanel;
import org.cytoscape.application.swing.CytoPanelState;
import org.cytoscape.event.AbstractCyEvent;

/**
 * An event to indicate that a CytoPanel has changed state. 
 * @CyAPI.Final.Class
 * @CyAPI.InModule swing-application-api
 */
public final class CytoPanelStateChangedEvent extends AbstractCyEvent<Object> {

	private final CytoPanel cp;
	private final CytoPanelState newState;

	/**
	 * Constructor.
	 * @param source The object firing the event.
	 * @param cp The CytoPanel whose state has changed. 
	 * @param newState The new state of the CytoPanel. 
	 */
	public CytoPanelStateChangedEvent(final Object source, final CytoPanel cp, final CytoPanelState newState) {
		super(source, CytoPanelStateChangedListener.class);
		this.cp = cp;
		this.newState = newState;
	}

	/**
	 * Returns the CytoPanel whose state has changed. 
	 * @return the CytoPanel whose state has changed. 
	 */
	public CytoPanel getCytoPanel() {
		return cp;
	}

	/**
	 * Returns the new state of the CytoPanel. 
	 * @return The new state of the CytoPanel. 
	 */
	public CytoPanelState getNewState() {
		return newState;
	}
}
