package org.cytoscape.session;

/*
 * #%L
 * Cytoscape Session API (session-api)
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


import org.cytoscape.model.CyNetwork;


/**
 * A utility that helps with the creation of unique network names.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule session-api
 */
public interface CyNetworkNaming {
	/**
	 *  Generates a unique title for a subnetwork based on a parent network.
	 * @param parentNetwork a non-null reference to a parent network.
	 * @return a unique title somehow based on the title of the parent network.
	 */
	String getSuggestedSubnetworkTitle(CyNetwork parentNetwork);

	/**
	 *  Generates a unique network title based on a provided suggestion.
	 * @param desiredTitle  the "ideal" title that we would like
	 * @return "desiredTitle" if this title is not yet in use, or 
	 * with a title that is unique and uses "desiredTitle" as a prefix.
	 */
	String getSuggestedNetworkTitle(String desiredTitle);
}
