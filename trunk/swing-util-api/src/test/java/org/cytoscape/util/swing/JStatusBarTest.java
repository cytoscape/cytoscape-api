package org.cytoscape.util.swing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JStatusBarTest {
	
	private JStatusBar statusBar;

	@Before
	public void setUp() throws Exception {
		statusBar = new JStatusBar();
	}


	@Test
	public void testJStatusBar() {
		assertNotNull(statusBar);
	}

	@Test
	public void testSetLeftLabel() {
		String text = "left label";
		statusBar.setLeftLabel(text);
	}

	@Test
	public void testSetCenterLabel() {
		String text = "center label";
		statusBar.setLeftLabel(text);
	}

	@Test
	public void testSetRightLabel() {
		String text = "right label";
		statusBar.setLeftLabel(text);
	}

}
