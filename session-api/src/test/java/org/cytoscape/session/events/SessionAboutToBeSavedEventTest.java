package org.cytoscape.session.events;

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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.cytoscape.session.CySessionManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SessionAboutToBeSavedEventTest {

	@Mock
	private CySessionManager sessionManager;

	private SessionAboutToBeSavedEvent e;

	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);

		e = new SessionAboutToBeSavedEvent(sessionManager);
	}

	@Test
	public void testAddAppFiles1() throws Exception {
		
		final String appName = "sample app";
		final List<File> files = new ArrayList<File>();
		e.addAppFiles(appName, files);
		
		assertNotNull(e.getAppFileListMap());
		assertEquals(files, e.getAppFileListMap().get(appName));		
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddAppFiles2() throws Exception {
		final String appName = "sample app2";
		e.addAppFiles(appName, null);
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddAppFiles3() throws Exception {
		final List<File> files = new ArrayList<File>();
		e.addAppFiles(null, files);
	}
	

	@Test(expected=IllegalArgumentException.class)
	public void testAddAppFiles4() throws Exception {
		final String appName = "sample app4";
		final List<File> files1 = new ArrayList<File>();
		final List<File> files2 = new ArrayList<File>();
		e.addAppFiles(appName, files1);
		e.addAppFiles(appName, files2);
	}
}
