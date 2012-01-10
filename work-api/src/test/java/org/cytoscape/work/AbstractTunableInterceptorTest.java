/*
 Copyright (c) 2010, 2011, The Cytoscape Consortium (www.cytoscape.org)

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


import java.lang.reflect.Field;

import java.lang.reflect.Method;

import javax.swing.JPanel;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;



public class AbstractTunableInterceptorTest {
	private AbstractTunableInterceptor interceptor;
	private HasAnnotatedField hasAnnotatedField;
	private HasAnnotatedSetterAndGetterMethods hasAnnotatedSetterAndGetterMethods;
	private ProvidesGUIExample providesGUI;
	
	@Before
	public void init() {
		interceptor = new ConcreteTunableInterceptor();
		interceptor.setThrowExceptions(true);
		interceptor.addTunableHandlerFactory( new BasicTunableHandlerFactory(FakeTunableHandler.class,int.class,Integer.class),null);
		hasAnnotatedField = new HasAnnotatedField();
		hasAnnotatedSetterAndGetterMethods = new HasAnnotatedSetterAndGetterMethods();
		providesGUI = new ProvidesGUIExample();
		
	}

	@Test
	public final void testLoadTunables() {
		interceptor.loadTunables(hasAnnotatedField);
		interceptor.loadTunables(hasAnnotatedSetterAndGetterMethods);
		interceptor.loadTunables(providesGUI);
	}

	@Test
	public final void testHasTunables() {
		assertTrue("Should have found tunables!", interceptor.hasTunables(hasAnnotatedField));
		assertTrue("Should have found tunables!", interceptor.hasTunables(hasAnnotatedSetterAndGetterMethods));
		assertTrue("Should have found tunables!", interceptor.hasTunables(providesGUI));
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testInvalidGetter() {
		interceptor.loadTunables(new HasInvalidGetter());
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testInvalidGetterReturnType() {
		interceptor.loadTunables(new HasInvalidGetterReturnType());
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testMisnamedGetter() {
		interceptor.loadTunables(new HasMisnamedGetter());
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testInvalidSetter() {
		interceptor.loadTunables(new HasInvalidSetter());
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testInvalidAnnotatedType() {
		interceptor.loadTunables(new HasInvalidAnotatedType());
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testInvalidProvidesGUIReturnType() {
		interceptor.loadTunables(new HasInvalidProvidesGUIMethod());
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testInvalidProvidesGUIReturnType2() {
		interceptor.loadTunables(new HasInvalidProvidesGUIMethod2());
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testInvalidProvidesGUIMethodWithArg() {
		interceptor.loadTunables(new HasInvalidProvidesGUIMethodWithArg());
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testInvalidMultipleProvidesGUIMethods() {
		interceptor.loadTunables(new Has2ProvidesGUIMethods());
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testSetterAnnotatedInsteadOfGetter() {
		interceptor.loadTunables(new SetterAnnotatedInsteadOfGetter());
	}

	@Test
	public final void testInheritedField() {
		assertTrue( interceptor.hasTunables( new ExtendedFieldClass() ) );
	}

	@Test
	public final void testInheritedMethod() {
		assertTrue( interceptor.hasTunables( new ExtendedMethodClass() ) );
	}
}

class FakeTunableHandler extends AbstractTunableHandler {
	public FakeTunableHandler(Field f, Object o, Tunable t) {
		super(f,o,t);
	}
	public FakeTunableHandler(Method get, Method set, Object o, Tunable t) {
		super(get,set,o,t);
	}
	public void handle() {}
}


//class SimpleHandlerFactory implements TunableHandlerFactory<AbstractTunableHandler> {
//	public AbstractTunableHandler getHandler(final Field field, final Object instance, final Tunable tunable) {
//		return new FakeTunableHandler(field, instance, tunable);
//	}
//
//	public AbstractTunableHandler getHandler(final Method setter, final Method getter, final Object instance, final Tunable tunable) {
//		return new FakeTunableHandler(setter, getter, instance, tunable);
//	}
//}


class ConcreteTunableInterceptor extends AbstractTunableInterceptor {

	public boolean validateAndWriteBackTunables(Object... objs) {
		return true;
	}

	public boolean execUI(Object... obs) {
		return true;
	}
}


class HasInvalidGetter {
	@Tunable
	public int getStuff(int i) { return -1; }
}


class HasMisnamedGetter {
	@Tunable
	public int stuff() { return -1; }
}


class HasInvalidGetterReturnType {
	@Tunable
	public StringBuilder getStuff() { return new StringBuilder(); }
}


class HasInvalidSetter {
	@Tunable
	public int getStuff() { return -1; }

	public void setStuff() { }
}


class HasInvalidAnotatedType {
	@Tunable
	public Exception e;
}


class SetterAnnotatedInsteadOfGetter {
	public int getStuff() { return -1; }

	@Tunable
	public void setStuff(int i) { }
}


class HasInvalidProvidesGUIMethod {
	@ProvidesGUI
	public void badReturnType() { }
}

class HasInvalidProvidesGUIMethod2 {
	@ProvidesGUI
	public Double badReturnType() { return null; }
}


class HasInvalidProvidesGUIMethodWithArg {
	@ProvidesGUI
	public JPanel bad(int i) { return null; }
}


class Has2ProvidesGUIMethods {
	@ProvidesGUI
	public JPanel providesGUI1() { return null; }

	@ProvidesGUI
	public JPanel providesGUI2() { return null; }
}

class BaseFieldClass {
	@Tunable
	public int value;
}

class ExtendedFieldClass extends BaseFieldClass {
}

class BaseMethodClass {
	@Tunable
	public int getValue() { return 0; }

	public void setValue(int a) { };
}

class ExtendedMethodClass extends BaseMethodClass {
}
