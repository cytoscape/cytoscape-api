package org.cytoscape.task.read;

import java.io.File;
import java.util.List;

import org.cytoscape.model.subnetwork.CyRootNetwork;
import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.TaskObserver;

/**
 * This interface provides a task iterator for loading networks from multiple files at once.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule core-task-api
 */
public interface LoadMultipleNetworkFilesTaskFactory extends TaskFactory {

	/**
	 * Creates a task iterator for loading one or more networks from supported files (e.g. xgmml, nnf, sif).
	 * @param filesOrDirectories List of network files or directories containing network files.
	 * @param rootNetwork Optional Network Collection into which the new networks must be loaded.
	 *                    If null, Cytoscape creates a new root-network for the loaded networks.
	 * @return a task iterator of type {@link TaskIterator}
	 */
	TaskIterator createTaskIterator(List<File> filesOrDirectories, CyRootNetwork rootNetwork);
	
	/**
	 * Creates a task iterator for loading one or more networks from supported files (e.g. xgmml, nnf, sif).
	 * @param filesOrDirectories List of network files or directories containing network files.
	 * @param rootNetwork Optional Network Collection into which the new networks must be loaded.
	 *                    If null, Cytoscape creates a new root-network for the loaded networks.
	 * @param observer A TaskObserver to notify when we're complete
	 * @return a task iterator of type {@link TaskIterator}
	 */
	TaskIterator createTaskIterator(List<File> filesOrDirectories, CyRootNetwork rootNetwork, TaskObserver observer);
}
