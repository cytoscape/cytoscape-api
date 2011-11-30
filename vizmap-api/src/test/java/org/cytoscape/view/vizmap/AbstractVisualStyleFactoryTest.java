package org.cytoscape.view.vizmap;


import static org.junit.Assert.*;

import org.cytoscape.view.model.VisualLexicon;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractVisualStyleFactoryTest {
	
	protected VisualStyleFactory factory;
		

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testVisualStyleFactory() {
		assertNotNull(factory);
		
		final String title = "original style";
		final VisualStyle style = factory.createVisualStyle(title);
		
		assertNotNull(style);
		
		final VisualStyle copyStyle = factory.createVisualStyle(style);
		assertNotNull(copyStyle);

		assertEquals(title, copyStyle.getTitle());
	}

}
