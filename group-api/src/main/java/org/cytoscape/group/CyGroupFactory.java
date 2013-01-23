package org.cytoscape.group;

/*
 * #%L
 * Cytoscape Groups API (group-api)
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

import org.cytoscape.group.CyGroup;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

import java.util.List;


/**
 * An interface describing a factory used for creating 
 * {@link CyGroup} objects.  This factory will be
 * provided as a service through Spring/OSGi.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule group-api
 */
public interface CyGroupFactory {
	/**
	 * Creates a CyGroup object in the referenced network.
	 *
	 * @param network the {@link CyNetwork} this group is part of
	 * @param register if true, register the {@link CyGroup}
	 * @return A new {@link CyGroup} as part of the designated network.
	 */
	public CyGroup createGroup(CyNetwork network, boolean register);

	/**
	 * Creates a CyGroup object in the referenced network initially populated with
	 * the supplied nodes.
	 *
	 * @param network the {@link CyNetwork} this group is part of
	 * @param nodes the {@link CyNode}s that are part of this group.
	 * @param edges the {@link CyEdge}s that are part of this group.  
	 *              If this is null, the edges are determined based
	 *              on the node connectivity
	 * @param register if true, register the {@link CyGroup}
	 * @return A new {@link CyGroup} as part of the designated network.
	 */
	public CyGroup createGroup(CyNetwork network, 
	                           List<CyNode> nodes, List<CyEdge> edges, 
	                           boolean register);

	/**
	 * Creates a CyGroup object in the referenced network from 
	 * an existing {@link CyNode}.
	 *
	 * @param network the {@link CyNetwork} this group is part of
	 * @param node the {@link CyNode} to convert into a group
	 * @param nodes the {@link CyNode}s that are part of this group.
	 * @param edges the {@link CyEdge}s that are part of this group.  
	 *              If this is null, the edges are determined based
	 *              on the node connectivity
	 * @param register if true, register the {@link CyGroup}
	 * @return A new {@link CyGroup} as part of the designated network.
	 */
	public CyGroup createGroup(CyNetwork network, 
	                           CyNode node, List<CyNode>nodes, 
	                           List<CyEdge> edges, boolean register);

	/**
	 * Creates a CyGroup object in the referenced network from 
	 * an existing {@link CyNode}. If the {@link CyNode} has a
	 * network pointer in the same root network as the referenced
	 * network, it is used as the group network.
	 *
	 * @param network the {@link CyNetwork} this group is part of
	 * @param node the {@link CyNode} to convert into a group
	 * @param register if true, register the {@link CyGroup}
	 * @return A new {@link CyGroup} as part of the designated network.
	 */
	public CyGroup createGroup(CyNetwork network, CyNode node, boolean register);
}
