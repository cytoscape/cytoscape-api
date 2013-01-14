
package org.cytoscape.command;

import org.cytoscape.work.TaskIterator;
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
	 * @return A task iterator that will execute the specified commands.
	 */
	TaskIterator createTaskIterator(File commandFile);	

	/**
	 * Creates a task iterator that will execute the specified
	 * commands.
	 * @param commands One or more strings where each string represents
	 * one command to execute.
	 * @return A task iterator that will execute the specified commands.
	 */
	TaskIterator createTaskIterator(String ... commands);	

	/**
	 * Creates a task iterator that will execute the specified
	 * commands.
	 * @param commands One or more strings where each string represents
	 * one command to execute.
	 * @return A task iterator that will execute the specified commands.
	 */
	TaskIterator createTaskIterator(List<String> commands);	
}
