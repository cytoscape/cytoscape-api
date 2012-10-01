package org.cytoscape.webservice.swing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import java.awt.Container;

import javax.swing.JPanel;

import org.cytoscape.io.webservice.swing.AbstractWebServiceGUIClient;
import org.cytoscape.io.webservice.swing.WebServiceGUIClient;
import org.cytoscape.work.Task;
import org.cytoscape.work.TaskIterator;
import org.junit.After;
import org.junit.Before;

public class WebServiceGUIClientTest extends AbstractWebServiceGUIClientTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Override
	public void testGetQueryBuilderGUI() {
		WebServiceGUIClient client = new DummyGUIClient("test.xml", "dummy", "dummyClinet");
		final Container clientGUI = client.getQueryBuilderGUI();
		assertNotNull(clientGUI);
		assertEquals(JPanel.class, clientGUI.getClass());
	}
	
	private static final class DummyGUIClient extends AbstractWebServiceGUIClient {

		public DummyGUIClient(String uri, String displayName, String description) {
			super(uri, displayName, description);
			this.gui = new JPanel();
		}

		private Task initialTask = mock(Task.class);

		@Override
		public TaskIterator createTaskIterator(Object query) {
			return new TaskIterator(initialTask );
		}
		
	}
}
