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
import org.cytoscape.view.presentation.property.BasicVisualLexicon;
import org.cytoscape.view.presentation.property.NullVisualProperty;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BasicVisualLexiconTest extends AbstractVisualLexiconTest {

	private VisualLexicon richLex;
	private VisualProperty<NullDataType> richRoot;

	// Fake VPs
	@Mock
	private VisualProperty<Double> numberVP;
	@Mock
	private VisualProperty<String> textVP;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		// Create root node.
		richRoot = new NullVisualProperty("RICH_ROOT", "Rich Root Visual Property");
		richLex = new BasicVisualLexicon(richRoot);
	}

	@Test
	public void testLexiconInstances() {
		assertNotNull(richLex);
	}

	@Test
	public void test2DLexicon() throws Exception {
		assertEquals(56, richLex.getAllVisualProperties().size());
	}

	@Test
	public void testTree() throws Exception {
		testTree(richLex);
	}

	@Test
	public void testGetAllDecendents() throws Exception {
		final VisualProperty<NullDataType> root = richLex.getRootVisualProperty();
		Collection<VisualProperty<?>> allChildren = richLex.getAllDescendants(root);

		assertEquals(richLex.getAllVisualProperties().size() - 1, allChildren.size());

		Collection<VisualLexiconNode> nodeTextChild = richLex.getVisualLexiconNode(BasicVisualLexicon.NODE_LABEL)
				.getChildren();
		assertEquals(0, nodeTextChild.size());

		Collection<VisualLexiconNode> nodePaintChild = richLex.getVisualLexiconNode(BasicVisualLexicon.NODE_PAINT)
				.getChildren();
		assertEquals(4, nodePaintChild.size());
		assertEquals(richLex.getAllDescendants(BasicVisualLexicon.NODE_PAINT).size(), nodePaintChild.size());

		Collection<VisualProperty<?>> nodeChildren = richLex.getAllDescendants(BasicVisualLexicon.NODE);
		assertEquals(24, nodeChildren.size());

		Collection<VisualProperty<?>> edgeChildren = richLex.getAllDescendants(BasicVisualLexicon.EDGE);
		assertEquals(18, edgeChildren.size());

		Collection<VisualProperty<?>> leaf = richLex.getAllDescendants(BasicVisualLexicon.EDGE_LABEL_COLOR);
		assertEquals(0, leaf.size());
	}

	@Test
	public void testIsSupported() throws Exception {
		assertFalse(richLex.isSupported(this.numberVP));
		assertTrue(richLex.isSupported(BasicVisualLexicon.EDGE_PAINT));
		assertTrue(richLex.isSupported(BasicVisualLexicon.NODE_SIZE));
		assertTrue(richLex.isSupported(BasicVisualLexicon.NETWORK_CENTER_Y_LOCATION));
		assertTrue(richLex.isSupported(BasicVisualLexicon.EDGE_LINE_TYPE));
		assertFalse(richLex.isSupported(this.textVP));
	}
}
