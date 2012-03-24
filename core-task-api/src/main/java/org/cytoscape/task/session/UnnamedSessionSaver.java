package org.cytoscape.task.session;

import java.io.File;

import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;

public interface UnnamedSessionSaver extends TaskFactory{
	
	TaskIterator createTaskIterator(final File file);
}
