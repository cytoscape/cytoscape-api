

package org.cytoscape.command;

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
