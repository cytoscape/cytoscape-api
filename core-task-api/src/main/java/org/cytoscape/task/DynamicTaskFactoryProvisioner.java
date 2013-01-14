package org.cytoscape.task;

import org.cytoscape.work.TaskFactory;

/**
 * TODO: Missing documentation
 * @CyAPI.InModule core-task-api
 */
public interface DynamicTaskFactoryProvisioner {
	public  TaskFactory createFor(final NetworkTaskFactory factory);
	public  TaskFactory createFor(final NetworkViewTaskFactory factory);
	public  TaskFactory createFor(final NetworkCollectionTaskFactory factory);
	public  TaskFactory createFor(final NetworkViewCollectionTaskFactory factory);
	public  TaskFactory createFor(final TableTaskFactory factory);
}
