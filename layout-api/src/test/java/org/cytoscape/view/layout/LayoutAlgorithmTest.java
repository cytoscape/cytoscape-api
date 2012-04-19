package org.cytoscape.view.layout;

import java.util.Set;

import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.cytoscape.work.TaskIterator;
import org.junit.Before;

public class LayoutAlgorithmTest extends AbstractLayoutAlgorithmTest {

	@Before
	public void setUp() throws Exception {
		computerName = "dummy";
		humanName = "Dummy Layout";
		this.layout = new DummyLayout(computerName, humanName);
	}

	private static final class DummyLayout extends AbstractLayoutAlgorithm<CyLayoutContext> {

		public DummyLayout(String computerName, String humanName) {
			super(computerName, humanName);
		}

		@Override
		public TaskIterator createTaskIterator(CyNetworkView networkView, CyLayoutContext layoutContext,
				Set<View<CyNode>> nodesToLayOut) {
			return null;
		}

		@Override
		public CyLayoutContext createLayoutContext() {
			return null;
		}
	}
}
