package org.cytoscape.task;

/*
 * #%L
 * Cytoscape Core Task API (core-task-api)
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

import org.cytoscape.model.CyNetwork;
import org.cytoscape.work.AbstractTask;

/**
 * Base class for all tasks that need to operate on a {@link CyNetwork}.
 * 
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule core-task-api
 */
public abstract class AbstractNetworkTask extends AbstractTask {

	/**
	 * The network that descendants of this class will operate on.
	 */
	protected final CyNetwork network;

	/**
	 * Base class for tasks that need to operate on a {@link CyNetwork}
	 * 
	 * @param network
	 *            the {@link CyNetwork} the tasks need to operate on. Must be a
	 *            non-null CyNetwork.
	 */
	public AbstractNetworkTask(final CyNetwork network) {
		if (network == null)
			throw new NullPointerException("CyNetwork is null");

		this.network = network;
	}
}
