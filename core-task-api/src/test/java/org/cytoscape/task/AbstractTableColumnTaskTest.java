package org.cytoscape.task;

/*
 * #%L
 * Cytoscape Core Task API (core-task-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
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
