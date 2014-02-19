package org.cytoscape.application.events;

/*
 * #%L
 * Cytoscape Application API (application-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2013 The Cytoscape Consortium
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
 * An event fired after Cytoscape startup mostly complete (but not necessarily 100 percent). 
 * This event is fired in the app-impl bundle, which depends on many Cytoscape bundles.
 * Warning: There is no guarantee that this event is fired after all bundles are start-up.  
 * @CyAPI.Final.Class
 * @CyAPI.InModule application-api
 */
public final class CyStartEvent extends AbstractCyEvent<Object> {

	/**
	 * Constructor.
	 * @param source The object firing this event.
	 */
	public CyStartEvent(final Object source) {
		super(source, CyStartListener.class);
	}
}
