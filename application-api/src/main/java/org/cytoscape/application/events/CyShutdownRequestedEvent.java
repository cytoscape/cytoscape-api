package org.cytoscape.application.events;

/*
 * #%L
 * Cytoscape Application API (application-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2021 The Cytoscape Consortium
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

import org.cytoscape.event.AbstractCyEvent;

/**
 * An event fired immediately before Cytoscape will be shutdown to determine if the shutdown action should be aborted. 
 * If the shutdown request is not aborted then a CyShutdownEvent will be fired immediately after this event.
 *
 * Apps should not listen for {@link CyShutdownRequestedEvent}. It is used internally by Cytoscape to prompt the user
 * if they want to cancel exiting Cytoscape. Apps should only use {@link CyShutdownListener} to be notified
 * of shutdown.
 * 
 * @CyAPI.Final.Class
 * @CyAPI.InModule application-api
 * @CyAPI.NoReference.Class
 */
public final class CyShutdownRequestedEvent extends AbstractCyEvent<Object> {

	private String reason;
	private boolean force;

	/**
	 * Constructor.
	 * @param source The object firing this event.
	 * @param force If true, force the shutdown (no user prompt)
	 */
	public CyShutdownRequestedEvent(final Object source, boolean force) {
		super(source, CyShutdownRequestedListener.class);
		reason = null;
		this.force = force;
	}

	/**
	 * Constructor.
	 * @param source The object firing this event.
	 */
	public CyShutdownRequestedEvent(final Object source) {
		super(source, CyShutdownRequestedListener.class);
		reason = null;
		this.force = false;
	}

	/**
	 * A callback to the firing class that allows a listener to
	 * abort the shutdown.  This can cause conflicts if abused.
	 * @param why A user comprehensible message describing why the shutdown
	 * was aborted.
	 */
	public void abortShutdown(final String why) {
		if ( why == null || why.equals("") )
			return;

		reason = why;
	}

	/**
	 * Returns the reason that the application should not be shut down.
	 * @return The reason that the application should not be shut down.
	 */
	public String abortShutdownReason() {
		return reason;
	}

	/**
	 * Returns true if no reason is provided to abort the shutdown and false
	 * if anyone processing this event wants to prevent shutdown.
	 * @return true if no reason is provided to abort the shutdown and false
	 * if anyone processing this event wants to prevent shutdown.
	 */
	public boolean actuallyShutdown() {
		return (reason == null || reason.length() <= 0);
	}

	/**
	 * Returns true if we want to force the shutdown without any user prompt
	 */
	public boolean forceShutdown() {
		return force;
	}
}
