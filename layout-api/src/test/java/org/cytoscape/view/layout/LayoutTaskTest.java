package org.cytoscape.view.layout;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Set;

import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.cytoscape.work.TaskMonitor;
import org.junit.After;
import org.junit.Before;
import org.mockito.MockitoAnnotations;

public class LayoutTaskTest extends AbstractLayoutTaskTest {

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		name = "test";
		nodesToLayOut.add(networkView.getNodeView(source));
		nodesToLayOut.add(networkView.getNodeView(target));
		task = new DummyLayoutTask(name, networkView, nodesToLayOut, supportedNodeAttributeTypes,
				supportedEdgeAttributeTypes, initialAttributes);
	}

	@Override
	public void testAbstractLayoutTaskConstructor() {
		name = "test";
		nodesToLayOut.add(networkView.getNodeView(source));
		nodesToLayOut.add(networkView.getNodeView(target));
		task = new DummyLayoutTask(name, networkView, nodesToLayOut, supportedNodeAttributeTypes,
				supportedEdgeAttributeTypes, initialAttributes);
		assertNotNull(task);

		AbstractLayoutTask task2 = new DummyLayoutTask(name, networkView, nodesToLayOut);
		assertNotNull(task2);
	}

	@Override
	public void testDoLayout() {
		// Should be implemented child classes.
	}

	private static final class DummyLayoutTask extends AbstractLayoutTask {

		public DummyLayoutTask(String name, CyNetworkView networkView, Set<View<CyNode>> nodesToLayOut) {
			super(name, networkView, nodesToLayOut);
		}

		public DummyLayoutTask(String name, CyNetworkView networkView, Set<View<CyNode>> nodesToLayOut,
				Set<Class<?>> supportedNodeAttributeTypes, Set<Class<?>> supportedEdgeAttributeTypes,
				List<String> initialAttributes) {
			super(name, networkView, nodesToLayOut, supportedNodeAttributeTypes, supportedEdgeAttributeTypes,
					initialAttributes);

		}

		@Override
		protected void doLayout(TaskMonitor taskMonitor) {
		}

	}
}
