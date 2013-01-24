package org.cytoscape.session;

/*
 * #%L
 * Cytoscape Session API (session-api)
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
