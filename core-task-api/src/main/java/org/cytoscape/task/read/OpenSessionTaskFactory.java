package org.cytoscape.task.read;

import java.io.File;

import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;

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

/**
 * This interface provides a task iterator for loading a session.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule core-task-api
 */
public interface OpenSessionTaskFactory extends TaskFactory {
	
	/**
	 * Creates a task iterator for loading a session from a file.
	 * The task created here will not ask the user to confirm it, even if there is currently a non-empty session.
	 * This is the same as calling <code>createTaskIterator(file, false)</code>.
	 * @param file The input file for loading the session from.
	 * @return a task iterator of type {@link TaskIterator}.
	 */
	TaskIterator createTaskIterator(final File file);
	
	/**
	 * Creates a task iterator for loading a session from a file, with an option to let the user confirm it
	 * before disposing the current session if it is not empty.
	 * @param file The input file for loading the session from.
	 * @param confirm Whether or not Cytoscape should ask the user to confirm this action.
	 * @return a task iterator of type {@link TaskIterator}. 
	 */
	TaskIterator createTaskIterator(final File file, boolean confirm);

}
