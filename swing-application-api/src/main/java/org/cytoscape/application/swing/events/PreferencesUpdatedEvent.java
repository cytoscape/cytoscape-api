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

import org.cytoscape.event.AbstractCyEvent;
import java.util.Properties;

/**
 * An event indicating that system preferences have been updated. 
 * @CyAPI.Final.Class
 * @CyAPI.InModule swing-application-api
 */
public final class PreferencesUpdatedEvent extends AbstractCyEvent<Object> {

	private final Properties oldProps;
	private final Properties newProps;

	/**
	 * Constructor.
	 * @param source The object firing this event.
	 * @param oldProps The old properties. 
	 * @param newProps The new properties. 
	 */
	public PreferencesUpdatedEvent(final Object source, final Properties oldProps, final Properties newProps) {
		super(source, PreferencesUpdatedListener.class);
		this.oldProps = oldProps;
		this.newProps = newProps;
	}

	/**
	 * The old properties before they were modified.
	 * @return the old properties before they were modified.
	 */
	public Properties getOldProperties() {
		return oldProps;
	}

	/**
	 * The new properties after being modified.
	 * @return the new properties after being modified.
	 */
	public Properties getNewProperties() {
		return newProps;
	}
}
