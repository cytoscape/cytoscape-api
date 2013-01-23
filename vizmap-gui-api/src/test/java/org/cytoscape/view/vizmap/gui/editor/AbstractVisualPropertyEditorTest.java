package org.cytoscape.view.vizmap.gui.editor;

/*
 * #%L
 * Cytoscape VizMap GUI API (vizmap-gui-api)
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
import static org.mockito.Mockito.*;

import java.beans.PropertyEditor;

import javax.swing.table.TableCellRenderer;

import org.junit.Test;
import org.junit.Before;


public class AbstractVisualPropertyEditorTest {

	private class DummyEditor extends AbstractVisualPropertyEditor<Object>{

		public DummyEditor(Class<Object> type, PropertyEditor propertyEditor,
				ContinuousEditorType continuousEditorType) {
			super(type, propertyEditor, continuousEditorType, new DummyCellRendererFactory());
		}
		
	}
	
	private class DummyCellRendererFactory implements ContinuousMappingCellRendererFactory {
		@Override
		public TableCellRenderer createTableCellRenderer(ContinuousMappingEditor<? extends Number, ?> editor) {
			return renderer;
		}
	}
	
	DummyEditor editor;
	PropertyEditor propertyEditor;
	ContinuousEditorType continuousEditorType;
	TableCellRenderer renderer;
	
	@Before
	public void testAbstractVisualPropertyEditor(){

		propertyEditor = mock(PropertyEditor.class);
		continuousEditorType = ContinuousEditorType.COLOR;
		editor = new DummyEditor(Object.class, propertyEditor, continuousEditorType);
		renderer = mock(TableCellRenderer.class);
	}
	
	@Test
	public void testGetClass(){
		assertEquals(Object.class,  editor.getType());
	}
	
	@Test
	public void testGetPropertyEditor(){
		assertEquals(propertyEditor, editor.getPropertyEditor());
	}
	
	@Test
	public void testGetContinuousTableCellRenderer(){
		ContinuousMappingEditor<?,?> continuousEditor = mock(ContinuousMappingEditor.class);
		assertNotNull(editor.getContinuousTableCellRenderer((ContinuousMappingEditor<? extends Number, Object>) continuousEditor));
	}
	
	@Test
	public void testGetContinuousEditorType(){
		assertEquals(continuousEditorType, editor.getContinuousEditorType());
	}
	
	@Test
	public void testGetDiscreteTableCellRenderer (){
		
		TableCellRenderer discreteTableCellRenderer = mock(TableCellRenderer.class);
		editor.discreteTableCellRenderer = discreteTableCellRenderer;
		
		assertEquals(discreteTableCellRenderer, editor.getDiscreteTableCellRenderer());
	}
	
	@Test
	public void testGetDefaultIcon(){
		assertEquals(null, editor.getDefaultIcon(0, 0));
	}
	
}
