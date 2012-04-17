package org.cytoscape.webservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.net.URI;

import org.cytoscape.io.webservice.WebServiceClient;
import org.cytoscape.work.TaskIterator;
import org.junit.Test;

public abstract class AbstractWebServiceClientTest {

	protected WebServiceClient client;
	protected URI locationUri;
	protected String displayName;
	protected String description;
	
	protected Object queryObject;
	
	@Test
	public void testGetServiceLocation() {
		final URI serviceLocation = client.getServiceLocation();
		assertNotNull(serviceLocation);
		assertEquals(locationUri, serviceLocation);
	}

	@Test
	public void testGetDisplayName() {
		final String displayName = client.getDisplayName();
		assertEquals(this.displayName, displayName);
	}

	@Test
	public void testGetDescription() {
		final String description = client.getDescription();
		assertEquals(this.description, description);
	}

	@Test
	public void testCreateTaskIterator() {
		final TaskIterator itr = client.createTaskIterator(queryObject);
		assertNotNull(itr);
		assertFalse(itr.getNumTasks() == 0);
	}

}
