package org.cytoscape.application.swing;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.swing.KeyStroke;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.cytoscape.work.TaskFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

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