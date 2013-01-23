package org.cytoscape.work;

/*
 * #%L
 * Cytoscape Work API (work-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2010 - 2013 The Cytoscape Consortium
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


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Properties;

import static org.mockito.Mockito.*;


public class AbstractTaskManagerTest {

	final TunableMutator interceptor = mock(TunableMutator.class);
	SimpleTaskMananger taskManager; 

	@Before 
	public void setup() {
		taskManager = new SimpleTaskMananger(interceptor);
	}

	static private class SimpleTaskMananger<T,C> extends AbstractTaskManager<T,C> {
		SimpleTaskMananger(final TunableMutator tunableMutator) {
			super(tunableMutator);
		}

		TunableMutator getTunableMutator() {
			return this.tunableMutator;
		}

		int getNumTunableRecorders() {
			return tunableRecorders.size();
		}

		@Override
		public void execute(TaskIterator iterator) { }

		@Override
		public T getConfiguration(TaskFactory factory, Object tunableContext) { return null; }
	}

	@Test
	public void testConstructor() {
		assertEquals("The TunableMutator passed into the TaskMananger's constructor is not that same as that of the protected data members!", interceptor, taskManager.getTunableMutator());
	}

	@Test
	public void testAddTunableRecorders() {
		taskManager.addTunableRecorder( mock(TunableRecorder.class), new Properties());
		assertEquals( 1, taskManager.getNumTunableRecorders());
	}

	@Test
	public void testRemoveTunableRecorders() {
		TunableRecorder rec = mock(TunableRecorder.class);
		taskManager.addTunableRecorder( rec, new Properties());
		assertEquals( 1, taskManager.getNumTunableRecorders());
		taskManager.removeTunableRecorder( rec, new Properties());
		assertEquals( 0, taskManager.getNumTunableRecorders());
	}


}
