package org.cytoscape.util.swing;

/*
 * #%L
 * Cytoscape Swing Utility API (swing-util-api)
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
