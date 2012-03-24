package org.cytoscape.task.loadnetwork;

import java.io.File;

import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;

public interface NetworkFileLoader extends TaskFactory{
	TaskIterator creatTaskIterator(final File file);

}
