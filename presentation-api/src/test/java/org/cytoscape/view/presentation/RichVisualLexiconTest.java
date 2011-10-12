package org.cytoscape.view.presentation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.cytoscape.view.model.NullDataType;
import org.cytoscape.view.model.VisualLexicon;
import org.cytoscape.view.model.VisualProperty;
import org.cytoscape.view.presentation.property.AbstractVisualLexiconTest;
import org.cytoscape.view.presentation.property.NullVisualProperty;
import org.cytoscape.view.presentation.property.RichVisualLexicon;
import org.junit.Before;
import org.junit.Test;

public class RichVisualLexiconTest extends AbstractVisualLexiconTest {

	private VisualLexicon richLex;
	private VisualProperty<NullDataType> richRoot;

	@Before
	public void setUp() throws Exception {
		// Create root node.
		richRoot = new NullVisualProperty("RICH_ROOT", "Rich Root Visual Property");
		richLex = new RichVisualLexicon(richRoot);
	}

	@Test
	public void testLexiconInstances() {
		assertNotNull(richLex);
	}

	@Test
	public void test2DLexicon() throws Exception {
		assertEquals(51, richLex.getAllVisualProperties().size());
	}

	@Test
	public void testTree() throws Exception {
		testTree(richLex);
	}

}
