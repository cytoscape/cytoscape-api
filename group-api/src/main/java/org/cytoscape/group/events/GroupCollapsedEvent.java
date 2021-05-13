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


import org.cytoscape.group.CyGroup;

import org.cytoscape.model.CyNetwork;


/**
 * This event signals that a group has either been expanded or collapsed in
 * a particular network.
 *
 * @CyAPI.Final.Class
 * @CyAPI.InModule group-api
 */
public final class GroupCollapsedEvent extends AbstractGroupEvent {
	private boolean collapsed;

	/**
	 * Constructs event.
	 * @param source the {@link CyGroup} that has been changed.
	 * @param network the {@link CyNetwork} the group has been changed in.
	 * @param collapsed true if the group has collapsed
	 */
	public GroupCollapsedEvent(final CyGroup source, final CyNetwork network, boolean collapsed) {
		super(source, GroupCollapsedListener.class, network);
		this.collapsed = collapsed;
	}

	/**
	 * Is the group collapsed or expanded?
	 *
	 * @return true if the group was collapsed
	 */
	public boolean collapsed() {
		return collapsed;
	}
}
