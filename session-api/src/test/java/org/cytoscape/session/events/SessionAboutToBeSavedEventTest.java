package org.cytoscape.session.events;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.cytoscape.property.session.Cytopanel;
import org.cytoscape.property.session.Desktop;
import org.cytoscape.session.CySessionManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SessionAboutToBeSavedEventTest {

	@Mock
	private CySessionManager sessionManager;

	@Mock
	private Desktop desktop;

	@Mock
	private Cytopanel cytopanel;

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
	
	
	@Test
	public void testGetDesktop() throws Exception {
		assertNull(e.getDesktop());
		e.setDesktop(desktop);
		
		assertEquals(desktop, e.getDesktop());
	}
	
	
	@Test(expected=NullPointerException.class)
	public void testAddCytopanel1() throws Exception {
		e.addCytopanel(null);
	}
	
	@Test
	public void testAddCytopanel() throws Exception {
		e.addCytopanel(cytopanel);
		assertNotNull(e.getCytopanels());
		assertEquals(1, e.getCytopanels().size());
		assertEquals(cytopanel, e.getCytopanels().get(0));
	}
}
