package org.cytoscape.util.swing;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

import java.awt.Graphics2D;

import javax.swing.AbstractAction;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class DropDownMenuButtonTest {
	DropDownMenuButton button;
	@Mock private AbstractAction action;
	@Mock private Graphics2D g;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		button = new DropDownMenuButton(action);
	}


	@Test
	public void testDropDownMenuButton() {
		assertNotNull(action);
	}

	@Test
	public void testPaintComponentGraphics() {
		button.repaint();
	}

}
