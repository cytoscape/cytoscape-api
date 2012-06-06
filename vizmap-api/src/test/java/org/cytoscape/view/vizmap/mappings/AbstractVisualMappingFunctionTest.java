package org.cytoscape.view.vizmap.mappings;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.Paint;

import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.model.CyRow;
import org.cytoscape.view.model.View;
import org.cytoscape.view.model.VisualProperty;
import org.cytoscape.view.presentation.property.BasicVisualLexicon;
import org.cytoscape.view.vizmap.VisualMappingFunction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

public class AbstractVisualMappingFunctionTest {
	
	private String attrName;
	private Class<Double> attrType;	
	private VisualProperty<Paint> vp;
	
	private VisualMappingFunction<Double, Paint> function;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		attrName = "test mapping function";
		attrType = Double.class;
		vp = BasicVisualLexicon.NODE_FILL_COLOR;
		
		function = new SimpleMappingFunction<Double, Paint>(attrName, attrType, vp);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAbstractVisualMappingFunction() {
		assertNotNull(function);
	}

	@Test
	public void testGetMappingColumnName() {
		assertEquals(attrName, function.getMappingColumnName());
	}

	@Test
	public void testGetMappingColumnType() {
		assertEquals(attrType, function.getMappingColumnType());
	}

	@Test
	public void testGetVisualProperty() {
		assertEquals(vp, function.getVisualProperty());
	}


	private static final class SimpleMappingFunction<K, V> extends AbstractVisualMappingFunction<K, V> {

		public SimpleMappingFunction(String attrName, Class<K> attrType, VisualProperty<V> vp) {
			super(attrName, attrType, vp);
		}

		@Override
		public void apply(CyRow row, View<? extends CyIdentifiable> view) {}
		
	}
}
