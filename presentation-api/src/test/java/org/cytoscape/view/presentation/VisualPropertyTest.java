package org.cytoscape.view.presentation;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.awt.Paint;

import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.NullDataType;
import org.cytoscape.view.model.VisualProperty;
import org.cytoscape.view.model.Visualizable;
import org.cytoscape.view.presentation.property.BasicVisualLexicon;
import org.cytoscape.view.presentation.property.NullVisualProperty;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VisualPropertyTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testVisualProperties() {
		final VisualProperty<Paint> colorProp = BasicVisualLexicon.NODE_FILL_COLOR;
		assertEquals(Paint.class, colorProp.getRange().getType());
		
		final VisualProperty<Boolean> booleanProp = BasicVisualLexicon.NODE_VISIBLE;
		assertEquals(Boolean.class, booleanProp.getRange().getType());
		assertEquals("false", booleanProp.toSerializableString(Boolean.FALSE));
		assertEquals(false, booleanProp.parseSerializableString("false"));
		assertEquals(false, booleanProp.parseSerializableString("False"));
		assertEquals(false, booleanProp.parseSerializableString("FALSE"));
		assertEquals(CyNode.class, booleanProp.getTargetDataType());
		
		final VisualProperty<Double> doubleProp = BasicVisualLexicon.NODE_SIZE;
		assertEquals(Double.class, doubleProp.getRange().getType());
		assertEquals("20.0", doubleProp.toSerializableString(Double.valueOf(20)));
		assertEquals(Double.valueOf(100.12), doubleProp.parseSerializableString("100.12"));
		
		final VisualProperty<Paint> paintProp = BasicVisualLexicon.NODE_FILL_COLOR;
		assertEquals(Paint.class, paintProp.getRange().getType());
		assertEquals(CyNode.class, paintProp.getTargetDataType());
		
		final Color testColor = new Color(10, 20, 30); 
		assertEquals("#0A141E", paintProp.toSerializableString(testColor).toUpperCase());
		assertEquals(testColor, paintProp.parseSerializableString("#0A141E"));
		assertEquals(testColor, paintProp.parseSerializableString("#0a141e"));
		assertEquals(testColor, paintProp.parseSerializableString("10,20,30"));
		assertEquals(testColor, paintProp.parseSerializableString("rgb(10,20,30)"));
		assertEquals(testColor, paintProp.parseSerializableString("RGB ( 10 , 20 , 30 )"));
		
		try {
			paintProp.parseSerializableString(null);
		}catch(Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		
		try {
			paintProp.parseSerializableString("#2JK20A141E");
		}catch(Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		
		try {
			paintProp.parseSerializableString("10, 20");
		}catch(Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		
		try {
			paintProp.parseSerializableString("10, 20, IJK");
		}catch(Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		
		final VisualProperty<Visualizable> visualizableProp = BasicVisualLexicon.NODE;
		assertEquals(Visualizable.class, visualizableProp.getRange().getType());
		assertTrue(visualizableProp.parseSerializableString("test string") instanceof Visualizable );
		
		final VisualProperty<String> stringProp = BasicVisualLexicon.NODE_LABEL;
		assertEquals(String.class, stringProp.getRange().getType());
		assertEquals("test string", stringProp.toSerializableString("test string"));
		assertEquals("test string 2", stringProp.parseSerializableString("test string 2"));
		
		final VisualProperty<NullDataType> nullProp = new NullVisualProperty("ROOT", "Root Visual Property");
		assertEquals(NullDataType.class, nullProp.getRange().getType());
		assertTrue(nullProp.parseSerializableString("test string") instanceof NullDataType );		
	}
}
