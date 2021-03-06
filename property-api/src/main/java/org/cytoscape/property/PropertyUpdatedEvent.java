package org.cytoscape.property;

/*
 * #%L
 * Cytoscape Property API (property-api)
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

/**
 * This event signals that a CyProperty is updated.
 * @CyAPI.InModule property-api
 */
// TODO: This really should be a final class but changing it now would break API
public class PropertyUpdatedEvent extends AbstractCyEvent<CyProperty<?>> {

	/**
	 * Constructs the event.
	 * @param source Updated CyProperty object. 
	 */
	public PropertyUpdatedEvent(CyProperty<?> source) {
		super(source, PropertyUpdatedListener.class);
	}
}
