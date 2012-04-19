package org.cytoscape.view.layout;

import org.junit.Before;

public class LayoutContextTest extends AbstractLayoutContextTest {

	@Before
	public void setUp() throws Exception {
		context = new DummyContext();
	}

	private static final class DummyContext extends AbstractLayoutContext {}
}
