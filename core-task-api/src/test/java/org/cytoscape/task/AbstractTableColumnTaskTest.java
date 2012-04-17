package org.cytoscape.task;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import java.util.Collection;

import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.task.AbstractNetworkCollectionTask;
import org.cytoscape.work.TaskMonitor;
import org.junit.Test;


public class AbstractTableColumnTaskTest {
	private class TableColumnTask extends AbstractTableColumnTask {
		public TableColumnTask(CyColumn column) {
			super(column);
		}

		public void run(TaskMonitor tm) { 
			assertNotNull(column);
		}

		@Override
		public void cancel() {
		}
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullColumn() throws Exception {
		new TableColumnTask(null);
	}

	@Test
	public void testGoodTableColumn() throws Exception {
		TableColumnTask ct = new TableColumnTask(mock(CyColumn.class) );
		ct.run(mock(TaskMonitor.class));
	}
}
