package org.cytoscape.task;

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

import org.cytoscape.model.CyEdge;
import org.cytoscape.view.model.View;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.work.AbstractTask;

/** 
 * The base class for all tasks that need to operate on an edge view and possibly its associated network view.
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule core-task-api
 */
public abstract class AbstractEdgeViewTask extends AbstractTask {
	/** The edge view that descendant tasks will operate on. */
	protected final View<CyEdge> edgeView;

	/** The network view associated with {@link #edgeView}. */
	protected final CyNetworkView netView; // TODO: should be renamed to networkView

	/** Base constructor for all tasks that need an edge view to operate on.
	 *  @param edgeView  the edge view to work with for this task
	 *  @param netView   the network view that contains "edgeView"
	 */
	public AbstractEdgeViewTask(final View<CyEdge> edgeView, final CyNetworkView netView) {
		super();
		if (edgeView == null)
			throw new NullPointerException("EdgeView is null");
		if (netView == null)
			throw new NullPointerException("CyNetworkView is null");

		this.edgeView = edgeView;	
		this.netView = netView;	
	}
}
