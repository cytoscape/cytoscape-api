package org.cytoscape.view.model;

/*
 * #%L
 * Cytoscape View Model API (viewmodel-api)
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
 * Factory for {@link CyNetworkView} objects.
 * Modules which need to create view models should import this as a service.
 * 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule viewmodel-api
 */
public interface CyNetworkViewFactory {
	
	/** 
	 * Create a {@link CyNetworkView} from a {@link CyNetwork} object. 
	 * This method will create a view regardless of the viewThreshold
	 * property.
	 * This method only creates a CyNetworkView instance and does nothing with respect to visual
	 * style, layout, or CyNetworkViewManager. 
	 * @param network Network for which the CyNetworkView is to be created
	 * @return the view model for the network data model
	 */
	CyNetworkView createNetworkView(CyNetwork network);
	
}
