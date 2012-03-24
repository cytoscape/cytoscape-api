package org.cytoscape.task.table;

import org.cytoscape.model.CyColumn;
import org.cytoscape.task.TableColumnTaskFactory;
import org.cytoscape.work.TaskIterator;

public interface ColumnNameEditor extends TableColumnTaskFactory {

	TaskIterator createTaskIterator(CyColumn column, String newColumnName);
}
