package org.cytoscape.task.export.vizmap;

import java.io.File;

import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;

public interface VizmapExporter extends TaskFactory{

	TaskIterator creatTaskIterator(final File file);
}
