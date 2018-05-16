package org.cytoscape.task;

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
 * Base class for all tasks that need to operate on a {@link CyRootNetwork}.
 * 
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule core-task-api
 */
public abstract class AbstractRootNetworkTask extends AbstractTask {

	/**
	 * The root-network that descendants of this class will operate on.
	 */
	protected final CyRootNetwork network;

	public AbstractRootNetworkTask(CyRootNetwork network) {
		if (network == null)
			throw new NullPointerException("CyRootNetwork is null");

		this.network = network;
	}
}
