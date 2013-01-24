package org.cytoscape.task.edit;

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
import org.cytoscape.work.TaskIterator;


/**
 * This interface provides a task iterator for editing a network's title.
 * @CyAPI.Api.Interface
 */
public interface EditNetworkTitleTaskFactory extends NetworkTaskFactory{
	
	/**
	 * Creates a task iterator for editing a network's title.
	 * @param network The network to edit the name of.
	 * @param title The new network title.
	 * @return a task ietrator of type {@link TaskIterator}.
	 */
	TaskIterator createTaskIterator (CyNetwork network, String title);
}
