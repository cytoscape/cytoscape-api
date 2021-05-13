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
import org.cytoscape.model.CyNode;
import java.util.Collections;
import java.util.List;


/**
 * This event signals that edges have been removed from the network.
 *
 * @CyAPI.Final.Class
 * @CyAPI.InModule group-api
 */
public final class GroupNodesRemovedEvent extends AbstractNodesEvent {

	/**
	 * Constructs event.
	 * @param source the {@link CyGroup} that is changing. 
	 * @param node A single node removed. 
	 */
	public GroupNodesRemovedEvent(final CyGroup source, CyNode node) {
		this(source, Collections.singletonList(node)); 
	}

	/**
	 * Constructs event.
	 * @param source the {@link CyGroup} that is changing. 
	 * @param nodes A list of nodes removed. 
	 */
	public GroupNodesRemovedEvent(final CyGroup source, List<CyNode> nodes) {
		super(source, nodes, GroupNodesRemovedListener.class);
	}
}
