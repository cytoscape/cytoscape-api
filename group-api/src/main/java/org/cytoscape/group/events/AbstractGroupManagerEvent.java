package org.cytoscape.group.events;

/*
 * #%L
 * Cytoscape Groups API (group-api)
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
import org.cytoscape.group.CyGroup;
import org.cytoscape.group.CyGroupManager;


/**
 *  Base class for all derived concrete event classes classes in this package that require a CyNetwork.
 */
class AbstractGroupManagerEvent extends AbstractCyEvent<CyGroupManager> {
	private final CyGroup group;

	AbstractGroupManagerEvent(final CyGroupManager source, final Class<?> listenerClass, final CyGroup group) {
		super(source, listenerClass);

		if (group == null)
			throw new NullPointerException("the \"group\" parameter must never be null.");
		this.group = group;
	}

	public final CyGroup getGroup() {
		return group;
	}
}
