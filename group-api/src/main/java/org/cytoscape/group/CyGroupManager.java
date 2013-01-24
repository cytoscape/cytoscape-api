package org.cytoscape.group;

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


import java.util.List;
import java.util.Set;

import org.cytoscape.group.CyGroup;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;


/**
 * The CyGroupManager maintains information about all of the groups
 * an instance of Cytoscape.
 * @CyAPI.Api.Interface
 */
public interface CyGroupManager {
	/**
	 * Provides the set of all the groups known to the groups 
	 * manager in a particular network.
	 * 
	 * @param network the network to get the groups from.  If 
	 *                the network is null, then return only those 
	 *                groups that have been designated as 
	 *                "global" (no referenced network)
	 *                for this {@link org.cytoscape.model.subnetwork.CyRootNetwork}
	 * @return the complete set of all the currently known groups 
	 *         in a Cytoscape network
	 */
	public Set<CyGroup> getGroupSet(CyNetwork network);

	/**
	 * Return the list of {@link CyGroup}s this node is in.
	 *
	 * @param node the {@link CyNode} we want get the groups for
	 * @return the list of {@link CyGroup}s the node is in
	 * null if it is not in any groups
	 */
	public List<CyGroup> getGroupsForNode(CyNode node);

	/**
	 * Return the list of {@link CyGroup}s this node is in for
	 * a particular network.
	 *
	 * @param node the {@link CyNode} we want get the groups for
	 * @param network the {@link CyNetwork} we want to restrict our search to
	 * @return the list of {@link CyGroup}s the node is in
	 * null if it is not in any groups
	 */
	public List<CyGroup> getGroupsForNode(CyNode node, CyNetwork network);

	/**
	 * Test to see if this node represents a {@link CyGroup} in
	 * a particular {@link CyNetwork}.
	 *
	 * @param node the {@link CyNode} to test
	 * @param network the {@link CyNetwork} to test
	 * @return true if this node represents a group
	 */
	public boolean isGroup(CyNode node, CyNetwork network);

	/**
	 * Return the group the corresponds to a particular
	 * node in a {@link CyNetwork}.
	 *
	 * @param node the {@link CyNode} to test
	 * @param network the {@link CyNetwork} to test
	 * @return the {@link CyGroup} that corresponds to
	 * the node, or null if this group doesn't exist in
	 * the network
	 */
	public CyGroup getGroup(CyNode node, CyNetwork network);

	/**
	 * Destroy a group.
	 *
	 * @param group the {@link CyGroup} to remove.
	 **/
	public void destroyGroup(CyGroup group);

	/**
	 * Registers a group with the group manager.  Does nothing 
	 * if the group is already known to the group manager.
	 * Fires GroupAddedEvent.
	 * 
	 * @param group  a non-null {@link CyGroup}
	 */
	public void addGroup(final CyGroup group);

	/**
	 * Registers a list of groups with the group manager.  Does 
	 * nothing if the group is already known to the group manager.
	 * 
	 * @param groups  a list of {@link CyGroup}
	 */
	public void addGroups(final List<CyGroup> groups);

	/** Releases all currently held references and resources. */
	public void reset();
}
