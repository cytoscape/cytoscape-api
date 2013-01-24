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

import java.awt.geom.Point2D;

import org.cytoscape.view.model.CyNetworkView;

/**
 * A base class for task factories that need to add a functionality to the network view 
 * considering a given location (for example adding a node where a mouse was clicked).
 */
public abstract class AbstractNetworkViewLocationTaskFactory implements NetworkViewLocationTaskFactory{
	
	/**
	 * Returns true if the supplied view and coordinates are not null.
	 * @param networkView The network view
	 * @param javaPt the point on the network view in java coordinates
	 * @param xformPt the point on the network view transformed into Cytoscape coordinates 
	 * @return true if the supplied view and coordinates are not null.
	 */
	@Override
	public boolean isReady(CyNetworkView networkView, Point2D javaPt, Point2D xformPt) {
		return networkView != null && javaPt != null && xformPt != null;
	}
	
}
