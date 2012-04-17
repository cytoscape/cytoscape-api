package org.cytoscape.task;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.cytoscape.model.CyTable;
import org.cytoscape.work.TaskIterator;
import org.junit.Test;

public class AbstractTableTaskFactoryTest {

	private class TableTaskFactory extends AbstractTableTaskFactory{

		@Override
		public TaskIterator createTaskIterator(CyTable table) {
			return null;
		}
		
	}
	
	@Test
	public void testIsReady(){
		TableTaskFactory ttf = new TableTaskFactory();
		assertTrue(ttf.isReady(mock(CyTable.class)));
	}
	
}
