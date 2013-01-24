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

import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.work.AbstractTask;

import java.util.Collection;

/** 
 * The base class for all tasks that need to operate on a Collection of {@link CyNetworkView}s.
 * @CyAPI.Abstract.Class
 */
public abstract class AbstractNetworkViewCollectionTask extends AbstractTask {
	/** A collection of network views to operate on for any descendants of this class. */
	protected final Collection<CyNetworkView> networkViews;

	/** Base class for any tasks that need to operate on a collection of network views.
	 *  @param networkViews  must be a non-null collection of network views
	 */
	public AbstractNetworkViewCollectionTask(final Collection<CyNetworkView> networkViews) {
		if (networkViews == null)
			throw new NullPointerException("CyNetworkView Collection is null");

		this.networkViews = networkViews;	
	}
}
