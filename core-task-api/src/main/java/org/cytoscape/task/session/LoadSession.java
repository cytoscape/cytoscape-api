package org.cytoscape.task.session;

import java.io.File;

import org.cytoscape.session.CySession;
import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;

public interface LoadSession extends TaskFactory {
	
	TaskIterator createTaskIterator(final File file);

}
