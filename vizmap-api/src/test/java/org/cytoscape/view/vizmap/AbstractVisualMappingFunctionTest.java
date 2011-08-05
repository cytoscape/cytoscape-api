package org.cytoscape.view.vizmap;


import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.cytoscape.view.model.VisualProperty;
import org.cytoscape.view.vizmap.VisualMappingFunction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractVisualMappingFunctionTest {
	
	protected VisualMappingFunction<?, ?> mapping;
	
	// Aname of controlling attr.
	protected String attrName;
	protected Class<?> attrType;
	protected VisualProperty<?> vp;
		

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	@Test
	public void testMappingSettings() throws Exception {
		
		assertNotNull(mapping);
		
		assertEquals(attrName, mapping.getMappingAttributeName());
		assertEquals(attrType, mapping.getMappingAttributeType());
		assertEquals(vp, mapping.getVisualProperty());
	}

}
