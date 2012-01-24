/*
 File: CyGroupManager.java

 Copyright (c) 2006, 2010, The Cytoscape Consortium (www.cytoscape.org)

 This library is free software; you can redistribute it and/or modify it
 under the terms of the GNU Lesser General Public License as published
 by the Free Software Foundation; either version 2.1 of the License, or
 any later version.

 This library is distributed in the hope that it will be useful, but
 WITHOUT ANY WARRANTY, WITHOUT EVEN THE IMPLIED WARRANTY OF
 MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.  The software and
 documentation provided hereunder is on an "as is" basis, and the
 Institute for Systems Biology and the Whitehead Institute
 have no obligations to provide maintenance, support,
 updates, enhancements or modifications.  In no event shall the
 Institute for Systems Biology and the Whitehead Institute
 be liable to any party for direct, indirect, special,
 incidental or consequential damages, including lost profits, arising
 out of the use of this software and its documentation, even if the
 Institute for Systems Biology and the Whitehead Institute
 have been advised of the possibility of such damage.  See
 the GNU Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation,
 Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
 */
package org.cytoscape.group;


import java.util.List;
import java.util.Set;

import org.cytoscape.group.CyGroup;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;


/**
 * Basic access to groups in an instance of Cytoscape.
 * @CyAPI.Api.Interface
 */
public interface CyGroupManager {
	/**
	 * Provides the set of all the groups known to the groups manager in a particular network.
	 * 
	 * @param network the network to get the groups from.  If the network is null, then
	 * return only those groups that have been designated as "global" (no referenced network)
	 * for this {@link CyRootNetwork}
	 * @return the complete set of all the currently known groups in a Cytoscape network
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
	 * Test to see if this node represents a {@link CyGroup} in
	 * a particular {@link CyNetwork}.
	 *
	 * @param node the {@link CyNode} to test
	 * @param network the {@link CyNetwork} to test
	 * @return true if this node represents a group
	 */
	public boolean isGroup(CyNode node, CyNetwork network);

	/**
	 * Destroy a group.
	 *
	 * @param group the {@link CyGroup} to remove.
	 **/
	public void destroyGroup(CyGroup group);

	/**
	 * Registers a group with the group manager.  Does nothing if the group is already
	 * known to the group manager.
	 * 
	 * @param group  a non-null {@link CyGroup}
	 */
	public void addGroup(final CyGroup group);

	/** Releases all currently held references and resources. */
	public void reset();
}
