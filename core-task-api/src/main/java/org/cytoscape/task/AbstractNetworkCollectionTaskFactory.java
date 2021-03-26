package org.cytoscape.task;

import java.util.Collection;

import org.cytoscape.model.CyNetwork;

/*
 * #%L
 * Cytoscape Core Task API (core-task-api)
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

/**
 * An NetworkCollectionTaskFactory that is always ready to produce a TaskIterator.
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule core-task-api
 */
public abstract class AbstractNetworkCollectionTaskFactory implements NetworkCollectionTaskFactory {
	
	/**
	 * Returns true if the supplied collection is not null.
	 * @param networks The collection of networks.
	 * @return true if the supplied collection is not null.
	 */
	@Override
	public boolean isReady(Collection<CyNetwork> networks) {
		return networks != null && !networks.isEmpty();
	}
}
