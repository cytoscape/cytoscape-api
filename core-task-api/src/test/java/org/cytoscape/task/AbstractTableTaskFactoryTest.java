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
