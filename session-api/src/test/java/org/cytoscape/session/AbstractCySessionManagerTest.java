package org.cytoscape.session;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

import org.junit.Test;

public abstract class AbstractCySessionManagerTest {


	protected CySessionManager mgr;

	
	@Test
	public void testGetCurrentSession() {
		assertNotNull(mgr);
		assertNotNull(mgr.getCurrentSession());
	}
	
	@Test
	public void testSetCurrentSession() {
		assertNotNull(mgr);
		CySession session = mock(CySession.class);
		mgr.setCurrentSession(session,"someFile");
		assertNotNull(mgr.getCurrentSession());
		assertEquals(session,mgr.getCurrentSession());
	}

	@Test
	public void testSetCurrentSessionFileName() {
		assertNotNull(mgr);
		CySession session = mock(CySession.class);
		mgr.setCurrentSession(session,"someFile");
		assertEquals("someFile",mgr.getCurrentSessionFileName());
	}

	// TODO should we allow this?  For new sessions?
	@Test
	public void testSetNullCurrentSessionFileName() {
		assertNotNull(mgr);
		CySession session = mock(CySession.class);
		mgr.setCurrentSession(session,null);
		assertNull(mgr.getCurrentSessionFileName());
	}
}
