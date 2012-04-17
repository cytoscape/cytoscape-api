package org.cytoscape.task;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import org.cytoscape.model.CyColumn;
import org.cytoscape.work.TaskMonitor;
import org.junit.Test;

public class AbstractTableCellTaskTest {

	private class TableCellTask extends AbstractTableCellTask {

		public TableCellTask(CyColumn column, Object primaryKeyValue) {
			super(column, primaryKeyValue);
		}
		public void run(TaskMonitor tm) { 
			assertNotNull(column);
			assertNotNull(primaryKeyValue);
		}

		@Override
		public void cancel() {
		}
	}
	

	@Test(expected=NullPointerException.class)
	public void testNullColumn() throws Exception {
		new TableCellTask(null,mock(Object.class));
	}

	@Test(expected=NullPointerException.class)
	public void testNullPrimaryKeyValue() throws Exception {
		new TableCellTask(mock(CyColumn.class),null);
	}

	@Test
	public void testGoodTableCellTask() throws Exception {
		TableCellTask tc = new TableCellTask(mock(CyColumn.class), mock(Object.class) );
		tc.run(mock(TaskMonitor.class));
	}
	
}
