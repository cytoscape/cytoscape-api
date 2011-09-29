
/*
 Copyright (c) 2008, The Cytoscape Consortium (www.cytoscape.org)

 The Cytoscape Consortium is:
 - Institute for Systems Biology
 - University of California San Diego
 - Memorial Sloan-Kettering Cancer Center
 - Institut Pasteur
 - Agilent Technologies

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

package org.cytoscape.model.events;

import java.util.Collection;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;


/**
 * Fired before a node is actually removed so that listeners
 * have a chance to clean up before the node object disappears.
 * Note that this event also <i>implies</i> an AboutToRemoveEdgesEvent
 * for every edge adjacent to the node in question (because all
 * adjacent edges are removed as a consequence of removing
 * a node), even though the
 * AboutToRemoveEdgesEvent is not actually fired.  If you only care
 * about removing edges, be sure to listen for this event as well!
 */
public final class AboutToRemoveNodesEvent extends AbstractCyEvent<CyNetwork> {
	
	private final Collection<CyNode> nodes;
	
	/**
	 * Constructs event.
	 * @param source the network firing this event.
	 * @param nodes the collection of nodes about to be removed. 
	 */
	public AboutToRemoveNodesEvent(final CyNetwork source, final Collection<CyNode> nodes) {
		super( source, AboutToRemoveNodesListener.class);
		if ( nodes == null )
			throw new NullPointerException("node collection may not be null");
		this.nodes = nodes;
	}
	
	/**
	 * Returns the collection of nodes about to be removed.
	 * @return the collection of nodes about to be removed.
	 */
	public Collection<CyNode> getNodes() {
			return nodes;
	}
}
