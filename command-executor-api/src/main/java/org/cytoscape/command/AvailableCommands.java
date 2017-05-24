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

import org.cytoscape.work.ResultDescriptor;

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
	 * Returns the description of the specified command in the specified namespace.
	 * @param namespace The namespace for the command whose description we want to know about.
	 * @param command The command within the specified namespace whose description we want to know about.
	 * @return The description of the specified namespace and command.
	 */
	String getDescription(String namespace, String command);

	/**
	 * Returns the longDescription of the specified command in the specified namespace.
	 * @param namespace The namespace for the command whose longDescription we want to know about.
	 * @param command The command within the specified namespace whose longDescription we want to know about.
	 * @return The longDescription of the specified namespace and command.
	 */
	String getLongDescription(String namespace, String command);

	
	/**
	 * Returns a list of arguments for the specified namespace and command in alphabetical order.
	 * @param namespace The namespace for the command whose arguments we want to know about.
	 * @param command The command within the specified namespace whose arguments we want to know about.
	 * @return a list of arguments for the specified namespace and command in alphabetical order.
	 */
	List<String> getArguments(String namespace, String command);

	/**
	 * Returns the value of the <b>required</b> field for this argument. 
	 * @param namespace The namespace for the command whose argument we want to know about.
	 * @param command The command within the specified namespace whose argument we want to know about.
	 * @param argument The argument we want to know about
	 * @return true if this argument is required, false otherwise
	 */
	boolean getArgRequired(String namespace, String command, String argument);

	/**
	 * Returns the value of the <b>tooltip</b> field for this argument. 
	 * @param namespace The namespace for the command whose argument we want to know about.
	 * @param command The command within the specified namespace whose argument we want to know about.
	 * @param argument The argument we want to know about
	 * @return the tooltip if one is provided, null otherwise
	 */
	String getArgTooltip(String namespace, String command, String argument);

	/**
	 * Returns the value of the <b>description</b> field for this argument. 
	 * @param namespace The namespace for the command whose argument we want to know about.
	 * @param command The command within the specified namespace whose argument we want to know about.
	 * @param argument The argument we want to know about
	 * @return the description if one is provided, null otherwise
	 */
	String getArgDescription(String namespace, String command, String argument);
	
	/**
	 * Returns the value of the <b>longDescription</b> field for this argument. 
	 * @param namespace The namespace for the command whose argument we want to know about.
	 * @param command The command within the specified namespace whose argument we want to know about.
	 * @param argument The argument we want to know about
	 * @return the longDescription if one is provided, null otherwise
	 */
	String getArgLongDescription(String namespace, String command, String argument);

	/**
	 * Returns the value of the <b>defaultStringValue</b> field for this argument. 
	 * @param namespace The namespace for the command whose argument we want to know about.
	 * @param command The command within the specified namespace whose argument we want to know about.
	 * @param argument The argument we want to know about
	 * @return the defaultStringValue if one is provided, null otherwise
	 */
	String getArgDefaultStringValue(String namespace, String command, String argument);
	
	/**
	 * Returns the type of the argument. 
	 * @param namespace The namespace for the command whose argument we want to know about.
	 * @param command The command within the specified namespace whose argument we want to know about.
	 * @param argument The argument we want to know about
	 * @return the type of the argument or null if the argument doesn't exist
	 */
	Class<?> getArgType(String namespace, String command, String argument);

	/**
	 * Returns the value of the argument. 
	 * @param namespace The namespace for the command whose argument we want to know about.
	 * @param command The command within the specified namespace whose argument we want to know about.
	 * @param argument The argument we want to know about
	 * @return the current value of the argument or null if the argument doesn't exist
	 */
	Object getArgValue(String namespace, String command, String argument);

	/**
	 * Returns a human-readable string for this argument of the form:
	 *    argument=&lt;<i>type</i>&gt;
	 * @param namespace The namespace for the command whose argument we want to know about.
	 * @param command The command within the specified namespace whose argument we want to know about.
	 * @param argument The argument we want to know about
	 * @return the formatted string
	 */
	String getArgTypeString(String namespace, String command, String argument);
	
	List<ResultDescriptor> getResultDescriptors(String namespace, String command);
	
}
