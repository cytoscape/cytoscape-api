package org.cytoscape.task.read;

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

import java.io.File;
import java.util.Collection;

import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.TaskObserver;


/**
 * This interface provides a task iterator for loading networks from files.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule core-task-api
 */
public interface LoadNetworkFileTaskFactory extends TaskFactory{
	
	/**
	 * Create a task iterator for loading a network from a file.
	 * The created task runs synchronously in the current thread and does not
	 * create a task monitor.
	 * @param file The file for loading into a network
	 * @return a task iterator of type {@link TaskIterator}
	 */
	TaskIterator createTaskIterator(final File file);

	/**
	 * Returns a TaskIterator that loads networks from a file.  The
	 * given observer will be notified when the networks are finished loading.
	 * If the network's total nodes and edges don't exceed the system's
	 * limits, a CyNetworkView will be automatically be created for each view.
	 * Networks that exceed those limits will be wrapped within a
	 * NullCyNetworkView.
	 * 
	 * @param file the file containing the networks.
	 * @param observer The observer to notify once loading is complete.
	 * @return a TaskIterator that loads networks from a file.
	 */
	TaskIterator createTaskIterator(File file, TaskObserver<Collection<CyNetworkView>> observer);
}
