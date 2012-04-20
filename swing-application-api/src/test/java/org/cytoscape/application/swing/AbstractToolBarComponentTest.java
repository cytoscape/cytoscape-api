package org.cytoscape.application.swing;

import static org.junit.Assert.*;

import java.awt.Component;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AbstractToolBarComponentTest {
	
	private AbstractToolBarComponent toolBarComponent;

	@Before
	public void setUp() throws Exception {
		toolBarComponent = new DummyToolBarComponent();
	}

	@Test
	public void testSetToolBarGravity() {
		toolBarComponent.setToolBarGravity(12f);
		assertTrue(12f == toolBarComponent.getToolBarGravity());
	}
	
	private static final class DummyToolBarComponent extends AbstractToolBarComponent {

		@Override
		public Component getComponent() {
			return null;
		}
		
	}

}
