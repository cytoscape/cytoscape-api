package org.cytoscape.view.vizmap.gui.editor;



import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.beans.PropertyEditor;
import java.util.Set;

import javax.swing.table.TableCellRenderer;

import org.cytoscape.view.model.VisualProperty;
import org.junit.Test;
import org.junit.Before;


public class AbstractVisualPropertyEditorTest {

	private class DummyEditor extends AbstractVisualPropertyEditor<Object>{

		public DummyEditor(Class<Object> type, PropertyEditor propertyEditor,
				ContinuousEditorType continuousEditorType) {
			super(type, propertyEditor, continuousEditorType);
		}
		
	}
	
	DummyEditor editor;
	PropertyEditor propertyEditor;
	ContinuousEditorType continuousEditorType;
	@Before
	public void testAbstractVisualPropertyEditor(){

		propertyEditor = mock(PropertyEditor.class);
		continuousEditorType = ContinuousEditorType.COLOR;
		editor = new DummyEditor(Object.class, propertyEditor, continuousEditorType);
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
