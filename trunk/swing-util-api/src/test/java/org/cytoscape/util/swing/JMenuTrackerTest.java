package org.cytoscape.util.swing;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.List;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;


public class JMenuTrackerTest {
	JMenuTracker tracker;
	JMenuTracker menuBarTracker;
	JPopupMenu popup;
	JMenuBar menuBar;

	@Before
	public void setUp() {
		popup = new JPopupMenu("test");
		tracker = new JMenuTracker(popup);	

		menuBar = new JMenuBar();
		menuBarTracker = new JMenuTracker(menuBar);
	}

	@Test(expected=NullPointerException.class)
	public void testGetNullString() {
		tracker.getGravityTracker(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testGetZeroLengthString() {
		tracker.getGravityTracker("");
	}

	@Test
	public void testOneMenu() {
		JMenu m = (JMenu)tracker.getGravityTracker("File").getMenu();
		assertNotNull(m);
		assertEquals("title","File",m.getText());
	}

	@Test
	public void testSubMenu() {
		JMenu m = (JMenu)tracker.getGravityTracker("File.Import").getMenu();
		assertNotNull(m);
		assertEquals("title","Import",m.getText());
	}

	@Test
	public void testSpacesInName() {
		JMenu m = (JMenu)tracker.getGravityTracker("File.Network Import").getMenu();
		assertNotNull(m);
		assertEquals("title", "Network Import", m.getText());
	}

	@Test
	public void testBasicMenuOrder() {
		JMenu file = (JMenu)tracker.getGravityTracker("File").getMenu();
		JMenu first = (JMenu)tracker.getGravityTracker("File.First").getMenu();
		JMenu second = (JMenu)tracker.getGravityTracker("File.Second").getMenu();

		assertEquals("num sub menus",2, file.getItemCount());
		assertEquals("first sub menu", first, file.getItem(0));
		assertEquals("second sub menu", second, file.getItem(1));
	}

	@Test
	public void testMenuOrderSpecificationUsingWeights() {
		JMenu file = (JMenu)tracker.getGravityTracker("File").getMenu();
		JMenu first = (JMenu)tracker.getGravityTracker("File.First[10]").getMenu();
		JMenu second = (JMenu)tracker.getGravityTracker("File.Second[5]").getMenu();

		assertEquals("num sub menus", 2, file.getItemCount());
		assertEquals("first sub menu", first, file.getItem(1));
		assertEquals("second sub menu", second, file.getItem(0));
	}

	@Test
	public void testMenuOrderSpecificationUsingAlphabeticalOrder() {
		JMenu file = (JMenu)tracker.getGravityTracker("File").getMenu();
		JMenu second = (JMenu)tracker.getGravityTracker("File.Second").getMenu();
		JMenu first = (JMenu)tracker.getGravityTracker("File.First").getMenu();
		JMenu third = (JMenu)tracker.getGravityTracker("File.Third").getMenu();

		assertEquals("num sub menus", 3, file.getItemCount());
		assertEquals("first sub menu", first, file.getItem(0));
		assertEquals("second sub menu", second, file.getItem(1));
		assertEquals("third sub menu", third, file.getItem(2));
	}

	@Test
	public void testPopupContainsFirst() {
		JMenu file = (JMenu)tracker.getGravityTracker("File").getMenu();
		assertTrue("popup contains menu", popup.getComponentIndex(file) >= 0 );
	}

	@Test
	public void testPopupDoesntContainChildren() {
		JMenu imp = (JMenu)tracker.getGravityTracker("File.Import").getMenu();
		assertTrue("popup contains menu", popup.getComponentIndex(imp) < 0 );
	}

	@Test
	public void testMenuBarContainsFirst() {
		JMenu file = (JMenu)menuBarTracker.getGravityTracker("File").getMenu();
		assertTrue("menubar contains menu", menuBar.getComponentIndex(file) >= 0 );
	}

	@Test
	public void testMenuBarDoesntContainChildren() {
		JMenu imp = (JMenu)menuBarTracker.getGravityTracker("File.Import").getMenu();
		assertTrue("menubar contains menu", menuBar.getComponentIndex(imp) < 0 );
	}

	@Test
	public void testMenuStringParsing() {
		List<JMenuTracker.MenuNameAndGravity> namesAndGravities = JMenuTracker.parseMenuString("AAA.B[10].CC[22]");
		assertEquals(3, namesAndGravities.size());
		assertEquals("AAA", namesAndGravities.get(0).getMenuName());
		assertEquals(GravityTracker.USE_ALPHABETIC_ORDER, namesAndGravities.get(0).getGravity(), 0.0);
		assertEquals("B", namesAndGravities.get(1).getMenuName());
		assertEquals(10.0, namesAndGravities.get(1).getGravity(), 0.0);
		assertEquals("CC", namesAndGravities.get(2).getMenuName());
		assertEquals(22.0, namesAndGravities.get(2).getGravity(), 0.0);
	}
}
