package org.cytoscape.group.events;

/*
 * #%L
 * Cytoscape Groups API (group-api)
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


import org.cytoscape.group.CyGroupManager;
import org.cytoscape.group.CyGroup;


/**
 * This event signals that a new group has been added.
 * @CyAPI.Final.Class
 */
public final class GroupAddedEvent extends AbstractGroupManagerEvent {
	/**
	 * Constructs event.
	 * @param source the {@link CyGroupManager} the group has been added to.
	 * @param group the {@link CyGroup} that has been added.
	 */
	public GroupAddedEvent(final CyGroupManager source, final CyGroup group) {
		super(source, GroupAddedListener.class, group);
	}
}
