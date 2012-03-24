package org.cytoscape.task.loaddatatable;

import java.io.File;

import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;

public interface AttributesFileLoader extends TaskFactory {
	TaskIterator createTaskIterator(final File file);

}
