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
package org.cytoscape.view.model;

import org.cytoscape.model.CyNetwork;


/**
 * Factory for {@linkplain CyNetworkView} objects.
 * Modules which need to create view models should import this as a service.
 *
 */
public interface CyNetworkViewFactory {
	
	/** 
	 * Create a {@linkplain CyNetworkView} from a {@linkplain org.cytoscape.model.CyNetwork} object. 
	 * 
	 * This method always checks viewThreshold property value and returns empty view if number of 
	 * graph objects (nodes and edges) is above the threshold.
	 * 
	 * @param network for which the CyNetworkView is to be created
	 * @return the view model for the network data model
	 */
	public CyNetworkView getNetworkView(final CyNetwork network);
	
	/**
	 * Creates view with or without using viewThreshold property.
	 * 
	 * @param network network data model for this view. 
	 * @param useThreshold if false, this factory creates actual view model by ignoring threshold.
	 * 
	 * @return View model for the given data model.
	 */
	public CyNetworkView getNetworkView(final CyNetwork network, final Boolean useThreshold);
}
