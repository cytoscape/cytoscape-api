package org.cytoscape.command;

/*
 * #%L
 * Cytoscape Command Executor API (command-executor-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2013 The Cytoscape Consortium
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

import java.util.Map;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.TaskObserver;
import java.io.File; 
import java.util.List;

/**
 * TODO: Missing documentation
 * @CyAPI.InModule command-executor-api
 */
public interface CommandExecutorTaskFactory {

	/**
	 * Creates a task iterator that will execute the specified
	 * commands.
	 * @param commandFile A file containing a one command string per line,
	 * blank lines, or comment lines beginning with the '#' character. 
	 * @param observer a possible observer for this series of tasks
	 * @return A task iterator that will execute the specified commands.
	 */
	TaskIterator createTaskIterator(File commandFile, TaskObserver observer);	

	/**
	 * Creates a task iterator that will execute the specified
	 * commands.
	 * @param observer a possible observer for this series of tasks
	 * @param commands One or more strings where each string represents
	 * one command to execute.
	 * @return A task iterator that will execute the specified commands.
	 */
	TaskIterator createTaskIterator(TaskObserver observer, String ... commands);	

	/**
	 * Creates a task iterator that will execute the specified
	 * commands.
	 * @param commands One or more strings where each string represents
	 * one command to execute.
	 * @param observer a possible observer for this series of tasks
	 * @return A task iterator that will execute the specified commands.
	 */
	TaskIterator createTaskIterator(List<String> commands, TaskObserver observer);	
	
	/**
	 * Creates a task iterator that will execute the specified
	 * command.  Note that this differs from the other methods in that
	 * this assumes the command string has already been parsed
	 * @param namespace the command namespace
	 * @param command the command
	 * @param args the map of arguments
	 * @param observer a possible observer for this series of tasks
	 * @return A task iterator that will execute the specified commands.
	 */
	TaskIterator createTaskIterator(String namespace, String command, Map<String,Object> args, TaskObserver observer);
}
