package org.cytoscape.work.swing;

import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Field;

import org.cytoscape.work.Tunable;
import org.junit.Before;
import org.junit.Test;

public class SimpleGUITunableHandlerFactoryTest {
	
	private SimpleGUITunableHandlerFactory<?> factory;

	@Before
	public void setUp() throws Exception {
		factory = new SimpleGUITunableHandlerFactory<DummyHandler>(DummyHandler.class, Object.class);
	}


	@Test
	public void testSimpleGUITunableHandlerFactory() {
		assertNotNull(factory);
	}
	
	private static final class DummyHandler extends AbstractGUITunableHandler {

		protected DummyHandler(Field field, Object instance, Tunable tunable) {
			super(field, instance, tunable);
		}

		@Override
		public void handle() {
		}
		
	}

}
