package org.cytoscape.work;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

public class BasicTunableHandlerFactoryTest {
	
	private final Source source = new Source(5);

	private Field getField() {
		try {
			return source.getClass().getField("value");
		} catch (Exception e) {
			return null;
		}
	}
	
	private Tunable getFieldTunable() {
		try {
			return getField().getAnnotation(Tunable.class);
		} catch (Exception e) {
			return null;
		}
	}
	
	private Method getGetter() {
		try {
			return source.getClass().getMethod("getXValue");
		} catch (Exception e) {
			return null;
		}
	}
	
	private Method getSetter() {
		try {
			return source.getClass().getMethod("setXValue", int.class);
		} catch (Exception e) {
			return null;
		}
	}
	
	private Tunable getMethodTunable() {
		try {
			return getGetter().getAnnotation(Tunable.class);
		} catch (Exception e) {
			return null;
		}
	}


	@Test
	public void testGetHandlerField() {
		BasicTunableHandlerFactory<TunableHandler> thf = new BasicTunableHandlerFactory(GoodTunableHandler.class, int.class);
		TunableHandler th = thf.createTunableHandler(getField(), source, getFieldTunable() );
		assertNotNull(th);
	}
	
	@Test
	public void testGetHandlerMethod() {
		BasicTunableHandlerFactory<TunableHandler> thf = new BasicTunableHandlerFactory(GoodTunableHandler.class, int.class);
		TunableHandler th = thf.createTunableHandler(getGetter(), getSetter(), source, getMethodTunable() );
		assertNotNull(th);
	}
	
	@Test
	public void testInvalidTypesField() {
		BasicTunableHandlerFactory<TunableHandler> thf = new BasicTunableHandlerFactory(GoodTunableHandler.class, String.class);
		TunableHandler th = thf.createTunableHandler(getField(), source, getFieldTunable() );
		assertNull(th);
	}
	
	@Test
	public void testInvalidTypesMethod() {
		BasicTunableHandlerFactory<TunableHandler> thf = new BasicTunableHandlerFactory(GoodTunableHandler.class, String.class);
		TunableHandler th = thf.createTunableHandler(getGetter(), getSetter(), source, getMethodTunable() );
		assertNull(th);
	}
	
	@Test
	public void testNoFieldConstructor() {
		BasicTunableHandlerFactory<TunableHandler> thf = new BasicTunableHandlerFactory(NoFieldTunableHandler.class, int.class);
		TunableHandler th = thf.createTunableHandler(getField(), source, getFieldTunable() );
		assertNull(th);
	}
	
	@Test
	public void testNoMethodConstructor() {
		BasicTunableHandlerFactory<TunableHandler> thf = new BasicTunableHandlerFactory(NoMethodTunableHandler.class, int.class);
		TunableHandler th = thf.createTunableHandler(getGetter(), getSetter(), source, getMethodTunable() );
		assertNull(th);
	}
}

class GoodTunableHandler extends AbstractTunableHandler {
	public GoodTunableHandler(Field f, Object o, Tunable t) {
		super(f,o,t);
	}
	public GoodTunableHandler(Method get, Method set, Object o, Tunable t) {
		super(get,set,o,t);
	}
	public void handle() {}
}

class NoFieldTunableHandler extends AbstractTunableHandler {

	public NoFieldTunableHandler(Method get, Method set, Object o, Tunable t) {
		super(get,set,o,t);
	}
	public void handle() {}
}

class NoMethodTunableHandler extends AbstractTunableHandler {

	public NoMethodTunableHandler(Field f, Object o, Tunable t) {
		super(f,o,t);
	}
	public void handle() {}
}

class Source {
	Source(int v) {
		value = v;
	}
	@Tunable
	public int value;
	@Tunable
	public int getXValue() {
		return value;
	}
	public void setXValue(int v) {
		value = v;
	}
}

