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
import org.cytoscape.model.CyEdge;
import java.util.Collections;
import java.util.List;


/**
 * This event signals that edges have been added to the network.
 *
 * @CyAPI.Final.Class
 * @CyAPI.InModule group-api
 */
public final class GroupEdgesAddedEvent extends AbstractEdgesEvent {

	/**
	 * Constructs event.
	 * @param source the {@link CyGroup} that is changing. 
	 * @param edge A single edge added. 
	 */
	public GroupEdgesAddedEvent(final CyGroup source, CyEdge edge) {
		this(source, Collections.singletonList(edge));
	}

	/**
	 * Constructs event.
	 * @param source the {@link CyGroup} that is changing. 
	 * @param edges A list of edges added. May be null.
	 */
	public GroupEdgesAddedEvent(final CyGroup source, List<CyEdge> edges) {
		super(source, edges, GroupEdgesAddedListener.class);
	}
}
