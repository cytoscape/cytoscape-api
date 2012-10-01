package org.cytoscape.view.vizmap.gui.editor;



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
