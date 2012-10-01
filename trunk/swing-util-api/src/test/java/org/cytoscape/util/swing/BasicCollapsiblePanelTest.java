package org.cytoscape.util.swing;

import static org.junit.Assert.*;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BasicCollapsiblePanelTest {
	
	private BasicCollapsiblePanel panel;

	@Before
	public void setUp() throws Exception {
		panel = new BasicCollapsiblePanel(new JRadioButton());
	}


	@Test
	public void testBasicCollapsiblePanelConstructor() {
		panel = new BasicCollapsiblePanel(new JRadioButton());
		assertNotNull(panel);
		
		panel = new BasicCollapsiblePanel("Test");
		assertNotNull(panel);
	}

	@Test
	public void testSetTitleComponentText() {
		final String title ="Title text";
		
		final String original = "original title";
		panel = new BasicCollapsiblePanel(original);
		assertEquals(original, panel.titleComponent.getText());
		panel.setTitleComponentText(title);
		assertEquals(title, panel.titleComponent.getText());
		
	}

	@Test
	public void testGetContentPane() {
		assertNotNull(panel.getContentPane());
	}

	@Test
	public void testAddComponent() {
		JPanel testPanel = new JPanel();
		assertNotNull(panel.add(testPanel));
	}

	@Test
	public void testSetCollapsed() {
		panel.setCollapsed(true);
		assertTrue(panel.isCollapsed());
		
		panel.setCollapsed(false);
		assertFalse(panel.isCollapsed());
	}

	@Test
	public void testSetToolTipTextString() {
		String text = "tooltip";
		panel.setToolTipText(text);
		assertEquals(text, panel.getToolTipText());
	}
}
