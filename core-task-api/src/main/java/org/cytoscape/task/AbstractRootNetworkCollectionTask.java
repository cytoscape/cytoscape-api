package org.cytoscape.task;

import java.util.Collection;

import org.cytoscape.model.subnetwork.CyRootNetwork;
import org.cytoscape.work.AbstractTask;

/*
 * #%L
 * Cytoscape Core Task API (core-task-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2018 The Cytoscape Consortium
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
 * The base class for all tasks that need to operate on a Collection of {@link CyRootNetwork}s.
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule core-task-api
 */
public abstract class AbstractRootNetworkCollectionTask extends AbstractTask {
	
	/** The collection of root-networks that subclasses will operate on. */
	protected final Collection<CyRootNetwork> networks;

	public AbstractRootNetworkCollectionTask(final Collection<CyRootNetwork> networks) {
		if (networks == null)
			throw new NullPointerException("CyRootNetwork Collection is null");

		this.networks = networks;	
	}
}
