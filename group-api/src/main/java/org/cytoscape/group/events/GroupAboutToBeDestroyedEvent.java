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


import org.cytoscape.group.CyGroupManager;
import org.cytoscape.group.CyGroup;


/**
 * This event signals that a group is about to be destroyed -- i.e. completely removed
 * from all networks.  The group's subnetwork and group node will also be destroyed.
 *
 * @CyAPI.Final.Class
 * @CyAPI.InModule group-api
 */
public final class  GroupAboutToBeDestroyedEvent extends AbstractGroupManagerEvent {
	/**
	 * Constructs event.
	 * @param source the {@link CyGroupManager} of the group about to be destroyed.
	 * @param group the {@link CyGroup} about to be destroyed.
	 */
	public GroupAboutToBeDestroyedEvent(final CyGroupManager source, final CyGroup group) {
		super(source, GroupAboutToBeDestroyedListener.class, group);
	}
}
