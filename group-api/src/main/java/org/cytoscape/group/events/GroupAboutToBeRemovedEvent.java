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


import org.cytoscape.group.CyGroup;
import org.cytoscape.model.CyNetwork;


/**
 * This event signals that a group is about to be removed from a network, but
 * not completely deleted.  This event is meant to be used primarily by the
 * the view and presentation layers for managing the visualization of the
 * network.
 *
 * @CyAPI.Final.Class
 */
public final class GroupAboutToBeRemovedEvent extends AbstractGroupEvent {
	/**
	 * Constructs event.
	 * @param source the {@link CyGroup} that is about to be removed.
	 * @param network the {@link CyNetwork} the group is about to be removed from
	 */
	public GroupAboutToBeRemovedEvent(final CyGroup source, final CyNetwork network) {
		super(source, GroupAboutToBeRemovedListener.class, network);
	}
}
