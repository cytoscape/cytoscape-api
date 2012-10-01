package org.cytoscape.task;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.cytoscape.model.CyColumn;
import org.cytoscape.work.TaskIterator;
import org.junit.Test;

public class AbstractTableColumnTaskFactoryTest {

	private class TableColumnTaskFactory extends AbstractTableColumnTaskFactory{

		@Override
		public TaskIterator createTaskIterator(CyColumn column) {
			return null;
		}
		
	}
	
	@Test
	public void testIsReady(){
		TableColumnTaskFactory tctf = new TableColumnTaskFactory();
		assertTrue(tctf.isReady(mock(CyColumn.class)));
	}
}

