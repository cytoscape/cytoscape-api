package org.cytoscape.io.read;

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
