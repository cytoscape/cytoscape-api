package org.cytoscape.model.subnetwork;

/*
 * #%L
 * Cytoscape Model API (model-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2021 The Cytoscape Consortium
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
 * A manager that provides access to the CyRootNetwork
 * objects associated with CyNetwork objects.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule model-api
 */
public interface CyRootNetworkManager {

	/**
	 * Converts a CyNetwork to a CyRootNetwork
	 * @param n The CyNetwork for which to return the CyRootNework.
	 * @return The root network associated with the specified CyNetwork.
	 */
	CyRootNetwork getRootNetwork(CyNetwork n);
}
