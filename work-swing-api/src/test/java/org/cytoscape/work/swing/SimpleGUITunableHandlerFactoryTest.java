package org.cytoscape.work.swing;

/*
 * #%L
 * Cytoscape Work Swing API (work-swing-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2021 The Cytoscape Consortium
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

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
