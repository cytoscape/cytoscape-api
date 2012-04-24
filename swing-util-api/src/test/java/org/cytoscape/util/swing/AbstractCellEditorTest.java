package org.cytoscape.util.swing;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class AbstractCellEditorTest {

	AbstractCellEditor editor;

	@Before
	public void setUp() throws Exception {
		editor = new DummyCellEditor();
	}

	@Test
	public void testGetCellEditorValue() {
		assertNull(editor.getCellEditorValue());
	}

	@Test
	public void testIsCellEditable() {
		assertTrue(editor.isCellEditable(null));
	}

	@Test
	public void testShouldSelectCell() {
		assertFalse(editor.shouldSelectCell(null));
	}

	@Test
	public void testStopCellEditing() {
		assertTrue(editor.stopCellEditing());
	}

	@Test
	public void testAddCellEditorListener() {
		CellEditorListener l = mock(CellEditorListener.class);
		editor.addCellEditorListener(l);
		assertEquals(1, editor.listenerList.getListenerCount());

		editor.removeCellEditorListener(l);
		assertEquals(0, editor.listenerList.getListenerCount());
	}

	@Test
	public void testFireEditingStopped() {
		CellEditorListener l = mock(CellEditorListener.class);
		editor.addCellEditorListener(l);
		editor.fireEditingStopped();
		verify(l, times(1)).editingStopped((ChangeEvent) any());
	}

	@Test
	public void testFireEditingCanceled() {
		CellEditorListener l = mock(CellEditorListener.class);
		editor.addCellEditorListener(l);
		editor.fireEditingCanceled();
		verify(l, times(1)).editingCanceled((ChangeEvent) any());
	}

	private static final class DummyCellEditor extends AbstractCellEditor {

		@Override
		public void cancelCellEditing() {}
	}

}
