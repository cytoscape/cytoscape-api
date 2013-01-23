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


import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;

/**
 * This interface provides a task iterator for creating a new session.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule core-task-api
 */
public interface NewSessionTaskFactory extends TaskFactory {
	
	/**
	 * Creates a task iterator for creating a new session and
	 * destroying the current session based on the input.
	 * @param destroyCurrentSession Whether or not to destroy the current session.
	 * @return A TaskIterator object containing one or more {@link org.cytoscape.work.Task} objects to execute. 
	 */
	TaskIterator createTaskIterator(boolean destroyCurrentSession);
}
