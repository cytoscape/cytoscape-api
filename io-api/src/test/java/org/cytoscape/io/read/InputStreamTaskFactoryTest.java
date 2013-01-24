package org.cytoscape.io.read;

/*
 * #%L
 * Cytoscape IO API (io-api)
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

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import org.cytoscape.io.BasicCyFileFilter;
import org.cytoscape.io.CyFileFilter;
import org.cytoscape.io.DataCategory;
import org.cytoscape.io.util.StreamUtil;
import org.cytoscape.work.Task;
import org.cytoscape.work.TaskIterator;
import org.junit.Before;

public class InputStreamTaskFactoryTest extends AbstractInputStreamTaskFactoryTest {

	private StreamUtil streamUtil = mock(StreamUtil.class);

	@Before
	public void setUp() throws Exception {
		Set<String> extensions = new HashSet<String>();
		extensions.add("xml");
		Set<String> contentTypes = new HashSet<String>();
		String description = "test filter";
		DataCategory category = DataCategory.NETWORK;
		this.fileFilter = new BasicCyFileFilter(extensions, contentTypes, description, category, streamUtil);
		this.taskFactory = new DummyInputStreamTaskFactory(fileFilter);
	}


	@Override
	public void testIsReady() {
		assertTrue(taskFactory.isReady(null, null));
	}
	
	private static final class DummyInputStreamTaskFactory extends AbstractInputStreamTaskFactory {

		public DummyInputStreamTaskFactory(final CyFileFilter fileFilter) {
			super(fileFilter);
		}

		private Task initialTasks = mock(Task.class);

		@Override
		public TaskIterator createTaskIterator(InputStream is, String inputName) {
			return new TaskIterator(initialTasks );
		}
		
	}
}
