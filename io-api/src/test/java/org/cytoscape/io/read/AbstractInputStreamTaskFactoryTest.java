package org.cytoscape.io.read;

import static org.junit.Assert.*;

import org.cytoscape.io.CyFileFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractInputStreamTaskFactoryTest {

	protected InputStreamTaskFactory taskFactory;
	protected CyFileFilter fileFilter;

	@Test
	public void testAbstractInputStreamTaskFactory() {
		assertNotNull(taskFactory);
		assertNotNull(fileFilter);
	}

	@Test
	public abstract void testIsReady();

	@Test
	public void testGetFileFilter() {
		assertEquals(fileFilter, taskFactory.getFileFilter());
	}
}
