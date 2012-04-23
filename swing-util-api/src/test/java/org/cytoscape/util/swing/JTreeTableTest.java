package org.cytoscape.util.swing;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.awt.Point;
import java.awt.event.MouseEvent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class JTreeTableTest {
	
	JTreeTable table;
	
	@Mock TreeTableModel model;
	@Mock MouseEvent mouseEvent;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		table = new JTreeTable(model);
	}


	@Test
	public void testUpdateUI() {
		table.updateUI();
	}

	@Test
	public void testSetRowHeightInt() {
		table.setRowHeight(100);
		assertEquals(100, table.getRowHeight());
	}

	@Test
	public void testGetEditingRow() {
		assertEquals(-1,table.getEditingRow());
	}

	@Test
	public void testJTreeTable() {
		assertNotNull(table);
		assertTrue(table.getModel() instanceof TreeTableModelAdapter);
	}

	@Test
	public void testGetToolTipTextMouseEvent() {
		when(mouseEvent.getPoint()).thenReturn(new Point(10, 20));
		
		table.setToolTipText("test");
		assertEquals("test", table.getToolTipText(mouseEvent));
	}

	@Test
	public void testGetTree() {
		assertNotNull(table.getTree());
	}
}
