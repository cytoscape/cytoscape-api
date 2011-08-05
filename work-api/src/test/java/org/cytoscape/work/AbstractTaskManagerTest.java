/*
 Copyright (c) 2010, The Cytoscape Consortium (www.cytoscape.org)

 This library is free software; you can redistribute it and/or modify it
 under the terms of the GNU Lesser General Public License as published
 by the Free Software Foundation; either version 2.1 of the License, or
 any later version.

 This library is distributed in the hope that it will be useful, but
 WITHOUT ANY WARRANTY, WITHOUT EVEN THE IMPLIED WARRANTY OF
 MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.  The software and
 documentation provided hereunder is on an "as is" basis, and the
 Institute for Systems Biology and the Whitehead Institute
 have no obligations to provide maintenance, support,
 updates, enhancements or modifications.  In no event shall the
 Institute for Systems Biology and the Whitehead Institute
 be liable to any party for direct, indirect, special,
 incidental or consequential damages, including lost profits, arising
 out of the use of this software and its documentation, even if the
 Institute for Systems Biology and the Whitehead Institute
 have been advised of the possibility of such damage.  See
 the GNU Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation,
 Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
*/
package org.cytoscape.work;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class AbstractTaskManagerTest {
	static private class SimpleTaskMananger extends AbstractTaskManager {
		SimpleTaskMananger(final TunableInterceptor tunableInterceptor) {
			super(tunableInterceptor);
		}

		TunableInterceptor getTunableInterceptor() {
			return this.tunableInterceptor;
		}

		public void execute(TaskFactory factory) {
		}
	}

	static private class SimpleTunableInterceptor<TH extends TunableHandler> extends AbstractTunableInterceptor {
		SimpleTunableInterceptor() {
			super();
		}

		public boolean execUI(Object... obs) {
			return false;
		}

		public boolean validateAndWriteBackTunables(Object... objs) {
			return false;
		}
	}

	@Test
	public void testConstructor() {
		final TunableInterceptor interceptor = mock(TunableInterceptor.class);
		final SimpleTaskMananger taskManager = new SimpleTaskMananger(interceptor);
		assertEquals("The TunableInterceptor passed into the TaskMananger's constructor is not that same as that of the protected data members!",
			     interceptor, taskManager.getTunableInterceptor());
	}

	@Test
	public void testHasTunables() {
		final TunableHandlerFactory<SimpleTunableHandler> handlerFactory= mock(TunableHandlerFactory.class);
		final SimpleTunableInterceptor interceptor = new SimpleTunableInterceptor<SimpleTunableHandler>();
		interceptor.addTunableHandlerFactory(handlerFactory, null);
		final SimpleTaskMananger taskManager = new SimpleTaskMananger(interceptor);
		assertFalse("This object has *no* Tunable annotation!", taskManager.hasTunables(new Object()));
		assertTrue("This object has an annotated field!",
			   taskManager.hasTunables(new HasAnnotatedField()));
		assertTrue("This object has annotated getter/setter methods!",
			   taskManager.hasTunables(new HasAnnotatedSetterAndGetterMethods()));
	}
}
