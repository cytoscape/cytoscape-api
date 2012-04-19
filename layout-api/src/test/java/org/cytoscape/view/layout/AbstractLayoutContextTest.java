package org.cytoscape.view.layout;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.cytoscape.work.util.ListSingleSelection;
import org.junit.Test;

public abstract class AbstractLayoutContextTest {

	protected AbstractLayoutContext context;

	@Test
	public void testGetSubmenuOptions() {
		assertEquals(2, context.getSubmenuOptions().getPossibleValues().size());
	}

	@Test
	public void testSetSubmenuOptions() {
		final ListSingleSelection<String> options = new ListSingleSelection<String>("All Nodes", "Selected Nodes");
		options.setSelectedValue("Selected Nodes");
		context.setSubmenuOptions(options);
		assertTrue(context.useOnlySelectedNodes());
	}

	@Test
	public void testUseOnlySelectedNodes() {
		assertFalse(context.useOnlySelectedNodes());
	}
}