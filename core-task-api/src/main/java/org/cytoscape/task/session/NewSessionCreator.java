package org.cytoscape.task.session;


import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;

public interface NewSessionCreator extends TaskFactory {

	TaskIterator createTaskIterator(final boolean destroyCurrentSession);

}
