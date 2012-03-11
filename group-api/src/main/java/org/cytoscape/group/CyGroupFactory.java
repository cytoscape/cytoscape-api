/*
 Copyright (c) 2008, 2010, The Cytoscape Consortium (www.cytoscape.org)

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
	 * @param edges the {@link CyEdge}s that are part of this group.  If this is null, the edges
	 * @param register if true, register the {@link CyGroup}
	 * are determined based on the node connectivity.
	 * @return A new {@link CyGroup} as part of the designated network.
	 */
	public CyGroup createGroup(CyNetwork network, List<CyNode> nodes, List<CyEdge> edges, boolean register);

	/**
	 * Creates a CyGroup object in the referenced network from an existing {@link CyNode}.
	 *
	 * @param network the {@link CyNetwork} this group is part of
	 * @param node the {@link CyNode} to convert into a group
	 * @param nodes the {@link CyNode}s that are part of this group.
	 * @param edges the {@link CyEdge}s that are part of this group.  If this is null, the edges
	 * @param register if true, register the {@link CyGroup}
	 * are determined based on the node connectivity.
	 * @return A new {@link CyGroup} as part of the designated network.
	 */
	public CyGroup createGroup(CyNetwork network, CyNode node, List<CyNode>nodes, List<CyEdge> edges, boolean register);
}
