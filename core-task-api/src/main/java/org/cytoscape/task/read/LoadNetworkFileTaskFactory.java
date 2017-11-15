package org.cytoscape.task.read;

import java.io.File;

import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.TaskObserver;

/*
 * #%L
 * Cytoscape Core Task API (core-task-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2017 The Cytoscape Consortium
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
 * This interface provides a task iterator for loading networks from a file.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule core-task-api
 */
public interface LoadNetworkFileTaskFactory extends TaskFactory{
	
	/**
	 * Creates a task iterator for loading a network from a file.
	 * The created task runs synchronously in the current thread and does not
	 * create a task monitor.
	 * @param file The file for loading into a network
	 * @return a task iterator of type {@link TaskIterator}
	 */
	TaskIterator createTaskIterator(final File file);
	
	/**
	 * Creates a task iterator for loading a network from a file.
	 * The created task runs synchronously in the current thread and does not
	 * create a task monitor.
	 * @param file The file for loading into a network
	 * @param observer A TaskObserver to notify when we're complete
	 * @return a task iterator of type {@link TaskIterator}
	 */
	TaskIterator createTaskIterator(final File file, TaskObserver observer);
}
