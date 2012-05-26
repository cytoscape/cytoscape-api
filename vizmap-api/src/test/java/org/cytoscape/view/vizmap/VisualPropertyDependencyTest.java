package org.cytoscape.view.vizmap;

import static org.junit.Assert.*;

import java.awt.Paint;
import java.util.HashSet;
import java.util.Set;

import org.cytoscape.view.model.NullDataType;
import org.cytoscape.view.model.VisualLexicon;
import org.cytoscape.view.model.VisualProperty;
import org.cytoscape.view.presentation.property.BasicVisualLexicon;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class VisualPropertyDependencyTest {
	
	private VisualPropertyDependency<Paint> dependency;
	
	private String id = "TEST_DEPENDENCY";
	private String displayName = "test dependency";
	private Set<VisualProperty<Paint>> vpSet;

	@Mock private VisualProperty<NullDataType> rootVisualProperty;
	
	private VisualLexicon lexicon;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		lexicon = new BasicVisualLexicon(rootVisualProperty);
		vpSet = new HashSet<VisualProperty<Paint>>();
		vpSet.add(BasicVisualLexicon.NODE_BORDER_PAINT);
		vpSet.add(BasicVisualLexicon.NODE_FILL_COLOR);
		
		dependency = new VisualPropertyDependency<Paint>(id, displayName, vpSet, lexicon);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected=IllegalArgumentException.class)
	public void testVisualPropertyDependency() {
		assertNotNull(dependency);
		
		final Set<VisualProperty<Paint>> badSet = new HashSet<VisualProperty<Paint>>();
		badSet.add(BasicVisualLexicon.EDGE_LABEL_COLOR);
		badSet.add(BasicVisualLexicon.NODE_FILL_COLOR);
		
		// This throws exception
		final VisualPropertyDependency<Paint> badDependency = new VisualPropertyDependency<Paint>(id, displayName, badSet, lexicon);
	}

	@Test
	public void testGetDisplayName() {
		assertEquals(displayName, dependency.getDisplayName());
	}

	@Test
	public void testGetVisualProperties() {
		assertEquals(vpSet, dependency.getVisualProperties());
	}

	@Test
	public void testSetDependency() {
		dependency.setDependency(true);
		assertTrue(dependency.isDependencyEnabled());
	}

	@Test
	public void testIsDependencyEnabled() {
		assertFalse(dependency.isDependencyEnabled());
		dependency.setDependency(true);
		assertTrue(dependency.isDependencyEnabled());
	}

	@Test
	public void testGetParentVisualProperty() {
		VisualProperty<Paint> parent = dependency.getParentVisualProperty();
		assertNotNull(parent);
		assertEquals(BasicVisualLexicon.NODE_PAINT, parent);
	}

	@Test
	public void testToString() {
		assertEquals(displayName, dependency.toString());
	}
}
