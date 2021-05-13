package org.cytoscape.util.swing;

/*
 * #%L
 * Cytoscape Swing Utility API (swing-util-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2021 The Cytoscape Consortium
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
