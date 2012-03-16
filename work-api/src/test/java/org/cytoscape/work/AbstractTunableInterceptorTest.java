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
	
	@Before
	public void init() {
		interceptor = new ConcreteTunableInterceptor();
		interceptor.setThrowExceptions(true);
		interceptor.addTunableHandlerFactory( new BasicTunableHandlerFactory(FakeTunableHandler.class,int.class,Integer.class),null);
		hasAnnotatedField = new HasAnnotatedField();
		hasAnnotatedSetterAndGetterMethods = new HasAnnotatedSetterAndGetterMethods();
		
	}

	@Test
	public final void testLoadTunables() {
		interceptor.getHandlers(hasAnnotatedField);
		interceptor.getHandlers(hasAnnotatedSetterAndGetterMethods);
	}

	@Test
	public final void testHasTunables() {
		assertTrue("Should have found tunables!", interceptor.hasTunables(hasAnnotatedField));
		assertTrue("Should have found tunables!", interceptor.hasTunables(hasAnnotatedSetterAndGetterMethods));
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testInvalidGetter() {
		interceptor.getHandlers(new HasInvalidGetter());
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testInvalidGetterReturnType() {
		interceptor.getHandlers(new HasInvalidGetterReturnType());
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testMisnamedGetter() {
		interceptor.getHandlers(new HasMisnamedGetter());
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testInvalidSetter() {
		interceptor.getHandlers(new HasInvalidSetter());
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testInvalidAnnotatedType() {
		interceptor.getHandlers(new HasInvalidAnotatedType());
	}


	@Test(expected=IllegalArgumentException.class)
	public final void testSetterAnnotatedInsteadOfGetter() {
		interceptor.getHandlers(new SetterAnnotatedInsteadOfGetter());
	}

	@Test
	public final void testInheritedField() {
		assertTrue( interceptor.hasTunables( new ExtendedFieldClass() ) );
		assertTrue( interceptor.getHandlers( new ExtendedFieldClass() ).size() > 0 );
	}

	@Test
	public final void testInheritedMethod() {
		assertTrue( interceptor.hasTunables( new ExtendedMethodClass() ) );
		assertTrue( interceptor.getHandlers( new ExtendedMethodClass() ).size() > 0 );
	}

	@Test
	public final void testJustContainsTunables() {
		Object o = new ContainsTunablesClass();
		assertTrue( interceptor.hasTunables( o ) );
		assertTrue( interceptor.getHandlers(o).size() > 0 );
	}

	@Test
	public final void testContainsTunablesAndTunables() {
		Object o = new ContainsTunablesAndTunablesClass();
		assertTrue( interceptor.hasTunables( o ) );
		assertTrue( interceptor.getHandlers(o).size() > 1 );
	}

	@Test
	public final void testContainsTunablesRecusion() {
		Object o = new ContainsTunablesClass2();
		assertTrue( interceptor.hasTunables( o ) );
		assertTrue( interceptor.getHandlers(o).size() > 0 );
	}

	@Test(expected=IllegalArgumentException.class)
	public final void testContainsTunablesUninitialized() {
		Object o = new UninitializedContainsTunablesClass();
		interceptor.getHandlers(o);
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

class ConcreteTunableInterceptor extends AbstractTunableInterceptor {

	public boolean validateAndWriteBackTunables(Object... objs) {
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

class ContainsTunablesClass {
	@ContainsTunables
	public BaseMethodClass bmc = new BaseMethodClass();
}


class ContainsTunablesClass2 {
	@ContainsTunables
	public ContainsTunablesClass ctc = new ContainsTunablesClass();
}

class ContainsTunablesAndTunablesClass {

	@ContainsTunables
	public BaseMethodClass bmc = new BaseMethodClass();

	@Tunable
	public int getXValue() { return 0; }

	public void setXValue(int a) { };

	@Tunable
	public int yValue;
}

class UninitializedContainsTunablesClass {
	@ContainsTunables
	public BaseMethodClass bmc; 
}
