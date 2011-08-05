package org.cytoscape.view.presentation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.cytoscape.view.model.NullDataType;
import org.cytoscape.view.model.VisualLexicon;
import org.cytoscape.view.model.VisualLexiconNode;
import org.cytoscape.view.model.VisualProperty;
import org.cytoscape.view.presentation.property.AbstractVisualLexiconTest;
import org.cytoscape.view.presentation.property.MinimalVisualLexicon;
import org.cytoscape.view.presentation.property.NullVisualProperty;
import org.cytoscape.view.presentation.property.RichVisualLexicon;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MinimalVisualLexiconTest extends AbstractVisualLexiconTest {

	private VisualLexicon minimalLex;
	private VisualProperty<NullDataType> minimalRoot;
	
	// Fake VPs
	@Mock private VisualProperty<Double> numberVP;
	@Mock private VisualProperty<String> textVP;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		// Create root node.
		minimalRoot = new NullVisualProperty("MINIMAL_ROOT", "Minimal Root Visual Property");
		minimalLex = new MinimalVisualLexicon(minimalRoot);
	}

	@Test
	public void testLexiconInstances() {
		assertNotNull(minimalLex);
	}

	@Test
	public void test2DLexicon() throws Exception {
		assertEquals(29, minimalLex.getAllVisualProperties().size());
	}

	@Test
	public void testTree() throws Exception {
		testTree(minimalLex);
	}

	@Test
	public void testGetAllDecendents() throws Exception {
		final VisualProperty<NullDataType> root = minimalLex.getRootVisualProperty();
		Collection<VisualProperty<?>> allChildren = minimalLex.getAllDescendants(root);

		assertEquals(minimalLex.getAllVisualProperties().size() - 1, allChildren.size());

		Collection<VisualLexiconNode> nodeTextChild = minimalLex.getVisualLexiconNode(MinimalVisualLexicon.NODE_LABEL)
				.getChildren();
		assertEquals(0, nodeTextChild.size());

		Collection<VisualLexiconNode> nodePaintChild = minimalLex.getVisualLexiconNode(MinimalVisualLexicon.NODE_PAINT)
				.getChildren();
		assertEquals(2, nodePaintChild.size());
		assertEquals(minimalLex.getAllDescendants(MinimalVisualLexicon.NODE_PAINT).size(), nodePaintChild.size());

		Collection<VisualProperty<?>> nodeChildren = minimalLex.getAllDescendants(MinimalVisualLexicon.NODE);
		assertEquals(11, nodeChildren.size());

		Collection<VisualProperty<?>> edgeChildren = minimalLex.getAllDescendants(MinimalVisualLexicon.EDGE);
		assertEquals(6, edgeChildren.size());

		Collection<VisualProperty<?>> leaf = minimalLex.getAllDescendants(MinimalVisualLexicon.EDGE_LABEL_COLOR);
		assertEquals(0, leaf.size());

	}

	@Test
	public void testIsSupported() throws Exception {
		assertFalse(minimalLex.isSupported(this.numberVP));
		assertTrue(minimalLex.isSupported(MinimalVisualLexicon.EDGE_PAINT));
		assertTrue(minimalLex.isSupported(MinimalVisualLexicon.NODE_SIZE));
		assertTrue(minimalLex.isSupported(MinimalVisualLexicon.NETWORK_CENTER_Y_LOCATION));
		assertFalse(minimalLex.isSupported(RichVisualLexicon.EDGE_LINE_TYPE));
		assertFalse(minimalLex.isSupported(this.textVP));
	}
}