package org.cytoscape.task.table;

import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.util.ListSingleSelection;

public interface GlobalToLocalTableMapper extends TaskFactory{

	TaskIterator createTaskIterator(final ListSingleSelection<String> globalTables, final ListSingleSelection<String> localTables);
}
