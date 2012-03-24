package org.cytoscape.task.loaddatatable;

import java.net.URL;

import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;

public interface AttributesURLLoader extends TaskFactory {
	TaskIterator createTaskIterator(final URL url);


}
