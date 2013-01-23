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

import java.util.List;

/**
 * TODO: Missing documentation
 * @CyAPI.Api.Interface
 * @CyAPI.InModule command-executor-api
 */
public interface AvailableCommands {

	/**
	 * Returns a list of available command namespaces in alphabetical order.
	 * @return A list of available command namespaces in alphabetical order.
	 */
	List<String> getNamespaces();

	/**
	 * Returns a list of available commands in alphabetical order for the specified namespace.
	 * @param namespace The namespace whose available commands we want to know about.
	 * @return a list of available commands in alphabetical order for the specified namespace.
	 */
	List<String> getCommands(String namespace);

	/**
	 * Returns a list of arguments for the specified namespace and command in alphabetical order.
	 * @param namespace The namespace for the command whose arguments we want to know about.
	 * @param command The command within the specified namespace whose arguments we want to know about.
	 * @return a list of arguments for the specified namespace and command in alphabetical order.
	 */
	List<String> getArguments(String namespace, String command);
}
