package org.cytoscape.work;

/*
 * #%L
 * Cytoscape Work API (work-api)
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

