package org.cytoscape.task;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.cytoscape.model.CyColumn;
import org.cytoscape.work.TaskIterator;
import org.junit.Test;

public class AbstractTableCellTaskFactoryTest {

	private class TableCellTaskFactory extends AbstractTableCellTaskFactory{

		@Override
		public TaskIterator createTaskIterator(CyColumn column,
				Object primaryKeyValue) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	@Test
	public void testIsReady(){
		TableCellTaskFactory tctf = new TableCellTaskFactory();
		assertTrue(tctf.isReady(mock(CyColumn.class), mock(Object.class)));
	}
	
}
