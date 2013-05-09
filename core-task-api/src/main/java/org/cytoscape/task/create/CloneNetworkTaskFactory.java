package org.cytoscape.task.create;

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
import org.cytoscape.task.NetworkTaskFactory;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.TaskObserver;
/**
 * This interface provides a task iterator for network cloning.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule core-task-api
 */
public interface CloneNetworkTaskFactory extends NetworkTaskFactory{
	/**
	 * Returns a TaskIterator that clones the given network.  The given
	 * TaskObserver will be notified once the cloning is complete.
	 * 
	 * @param network The network to clone.
	 * @param observer The observer to notify once the clone is complete.
	 * @return a TaskIterator that clones the given network.
	 */
	TaskIterator createTaskIterator(CyNetwork network, TaskObserver<CyNetworkView> observer);
}
