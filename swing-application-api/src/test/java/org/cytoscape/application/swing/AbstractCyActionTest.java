package org.cytoscape.application.swing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import javax.swing.KeyStroke;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.work.TaskFactory;
import org.junit.Test;
import org.mockito.Mock;

/*
 * #%L
 * Cytoscape Swing Application API (swing-application-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2019 The Cytoscape Consortium
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

public abstract class AbstractCyActionTest {
	
	protected AbstractCyAction action;
	
	protected String name = "action name";
	protected Map<String, String> configProps = new HashMap<String, String>();
	@Mock protected CyApplicationManager applicationManager;
	@Mock protected CyNetworkViewManager networkViewManager;
	@Mock protected TaskFactory factory;
	protected String enableFor;

	@Test
	public abstract void testAbstractCyActionConstructor();


	@Test
	public void testSetName() {		
		final String newName ="New name";
		action.setName(newName);
		assertEquals(newName, action.getName());
	}


	@Test
	public void testIsInMenuBar() {
		assertTrue(action.isInMenuBar());
	}

	@Test
	public void testIsInToolBar() {
		assertFalse(action.isInToolBar());
	}

	@Test
	public void testSetMenuGravity() {
		action.setMenuGravity(10.2f);
		assertTrue(action.getMenuGravity() == 10.2f);
	}

	@Test
	public void testSetToolbarGravity() {
		action.setToolbarGravity(10.2f);
		assertTrue(action.getToolbarGravity() == 10.2f);
	}

	@Test
	public void testSetAcceleratorKeyStroke() {
		final KeyStroke testStroke = KeyStroke.getKeyStroke("s");
		action.setAcceleratorKeyStroke(testStroke);
		assertEquals(testStroke, action.getAcceleratorKeyStroke());
	}

	@Test
	public void testSetPreferredMenu() {
		action.setPreferredMenu("file");
		assertEquals("file", action.getPreferredMenu());
	}

	@Test
	public void testUseCheckBoxMenuItem() {
		assertFalse(action.useCheckBoxMenuItem());
	}
	
	@Test
	public void testUseToggleButton() {
		assertFalse(action.useToggleButton);
	}

	@Test
	public void testMenuCanceled() {
		action.menuCanceled(null);
	}

	@Test
	public void testMenuDeselected() {
		action.menuDeselected(null);
	}

	@Test
	public void testMenuSelected() {
		action.menuSelected(null);
	}

	@Test
	public void testPopupMenuWillBecomeVisible() {
		action.popupMenuWillBecomeVisible(null);
	}

	@Test
	public void testPopupMenuWillBecomeInvisible() {
		action.popupMenuWillBecomeInvisible(null);
	}

	@Test
	public void testPopupMenuCanceled() {
		action.popupMenuCanceled(null);
	}

	@Test
	public void testUpdateEnableState() {
		action.updateEnableState();
	}
}