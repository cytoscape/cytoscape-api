package org.cytoscape.task.export.table;

import org.cytoscape.model.CyTable;
import org.cytoscape.task.TableTaskFactory;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.util.ListSingleSelection;

public interface CurrentTableExporter extends TableTaskFactory {
	TaskIterator createTaskIterator(CyTable table, ListSingleSelection<String> selectTable);

}
