package org.cytoscape.webservice.swing;

/*
 * #%L
 * Cytoscape Webservice Swing API (webservice-swing-api)
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
