package org.cytoscape.work.swing;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import javax.swing.JMenuItem;

import org.cytoscape.work.AbstractTunableHandler;
import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.Tunable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SimpleSubmenuTunableHandlerFactoryTest {

	private SimpleSubmenuTunableHandlerFactory<DummySubmenuTunableHandler> factory;

	@Before
	public void setUp() throws Exception {
		factory = new SimpleSubmenuTunableHandlerFactory<DummySubmenuTunableHandler>(DummySubmenuTunableHandler.class, Object.class);
	}

	@Test
	public void testSimpleSubmenuTunableHandlerFactory() {
		assertNotNull(factory);
	}

	private static final class DummySubmenuTunableHandler extends AbstractTunableHandler implements
			SubmenuTunableHandler {

		public DummySubmenuTunableHandler(Field field, Object instance, Tunable tunable) {
			super(field, instance, tunable);
		}

		@Override
		public void handle() {
		}

		@Override
		public JMenuItem getSubmenuItem() {
			return null;
		}

		@Override
		public void chosenMenu(String menuName) {
		}

		@Override
		public void setExecutionParams(DialogTaskManager dtm, TaskFactory tf) {
		}

	}

}
