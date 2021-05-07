package org.cytoscape.task.read;

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

import java.net.URL;
import java.util.Collection;

import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.TaskObserver;

/**
 * This interface provides a task iterator for loading a URL into a network.
 * 
 * @CyAPI.Api.Interface
 * @CyAPI.InModule core-task-api
 */
public interface LoadNetworkURLTaskFactory extends TaskFactory {

	/**
	 * Creates a task iterator for loading a URL into a network. The created
	 * task runs synchronously in the current thread and does not create a task
	 * monitor.
	 * 
	 * @param url
	 *            the URL for loading into the network.
	 * @return a task iterator of type {@link TaskIterator}.
	 */
	TaskIterator loadCyNetworks(final URL url);

	/**
	 * Creates a task iterator for loading a URL into a network. The created
	 * task runs synchronously in the current thread and does not create a task
	 * monitor.
	 * 
	 * @param url
	 *            the URL for loading into the network.
	 * @param observer a TaskObserver that wants to know when we're done
	 * @return a task iterator of type {@link TaskIterator}.
	 */
	TaskIterator createTaskIterator(final URL url, TaskObserver observer);
}
