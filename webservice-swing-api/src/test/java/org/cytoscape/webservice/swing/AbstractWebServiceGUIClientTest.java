package org.cytoscape.webservice.swing;

import java.awt.Container;

import org.cytoscape.io.webservice.swing.WebServiceGUIClient;
import org.cytoscape.webservice.AbstractWebServiceClientTest;
import org.junit.Test;


public abstract class AbstractWebServiceGUIClientTest extends AbstractWebServiceClientTest {

	@Test
	public void testGetQueryBuilderGUI() {
		WebServiceGUIClient guiClient = (WebServiceGUIClient) client;
		final Container builder = guiClient.getQueryBuilderGUI();
	}

}
