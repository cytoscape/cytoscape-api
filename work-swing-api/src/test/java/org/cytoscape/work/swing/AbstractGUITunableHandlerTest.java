package org.cytoscape.work.swing;

/*
 * #%L
 * Cytoscape Work Swing API (work-swing-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2010 - 2021 The Cytoscape Consortium
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

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.cytoscape.work.Tunable;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class AbstractGUITunableHandlerTest {

	private static final class SimpleGUITunableHandler extends AbstractGUITunableHandler {
		SimpleGUITunableHandler(final Method getter, final Method setter, final Object instance, final Tunable tunable) {
			super(getter, setter, instance, tunable);
		}

		SimpleGUITunableHandler(final Field field, final Object instance, final Tunable tunable) {
			super(field, instance, tunable);
		}

		public void handle() {
		}

		public void update() {
			testUpdateCalled = true;
		}

		public boolean testUpdateCalled = false;
	}

	private SimpleGUITunableHandler fieldHandler;
	private SimpleGUITunableHandler methodHandler;
	private SimpleGUITunableHandler methodHandlerS;

	@Before
	public void initialise() throws Exception {
		final HasAnnotatedField hasAnnotatedField = new HasAnnotatedField();
		final Field annotatedIntField = hasAnnotatedField.getClass().getField("annotatedInt");
		final Tunable annotatedIntTunable = annotatedIntField.getAnnotation(Tunable.class);
		fieldHandler = new SimpleGUITunableHandler(annotatedIntField, hasAnnotatedField, annotatedIntTunable);

		final HasAnnotatedMethod hasAnnotatedMethod = new HasAnnotatedMethod();
		final Method setter = hasAnnotatedMethod.getClass().getMethod("setAnnotatedInt", int.class);
		final Method getter = hasAnnotatedMethod.getClass().getMethod("getAnnotatedInt");
		final Tunable annotatedMethodTunable = getter.getAnnotation(Tunable.class);
		methodHandler = new SimpleGUITunableHandler(getter, setter, hasAnnotatedMethod, annotatedMethodTunable);

		final Method setterS = hasAnnotatedMethod.getClass().getMethod("setAnnotatedString", String.class);
		final Method getterS = hasAnnotatedMethod.getClass().getMethod("getAnnotatedString");
		final Tunable annotatedMethodTunableS = getterS.getAnnotation(Tunable.class);
		methodHandlerS = new SimpleGUITunableHandler(getterS, setterS, hasAnnotatedMethod, annotatedMethodTunableS);

	}

	@Test
	public void testFieldConstructor() throws Exception {
		assertEquals("Unexpected return value from getState()!", "42", fieldHandler.getState());
	}

	@Test
	public void testFieldGetJPanel() throws Exception {
		assertNotNull("getJPanel() must *not* return null!", fieldHandler.getJPanel());
	}

	@Test
	public void testMethodConstructor() throws Exception {
		assertEquals("Unexpected return value from getState()!", "47", methodHandler.getState());
	}

	@Test
	public void testMethodGetJPanel() throws Exception {
		assertNotNull("getJPanel() must *not* return null!", methodHandler.getJPanel());
	}

	@Test
	public void testNotifyChangeListeners() throws Exception {
		methodHandler.addChangeListener(methodHandlerS);
		methodHandler.notifyChangeListeners();
		assertTrue(methodHandlerS.testUpdateCalled);
	}

	@Test
	public void testGetChangeSources() throws Exception {
		assertEquals("AnnotatedInt", methodHandlerS.getChangeSources()[0]);
	}

	@Test
	public void testEmptyGetChangeSources() throws Exception {
		assertEquals(0, methodHandler.getChangeSources().length);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetValue() throws Exception {
		fieldHandler.setValue(null);
	}


	@Test
	public void testNotifyDependents() {
		GUITunableHandler gh = mock(GUITunableHandler.class);
		fieldHandler.addDependent(gh);
		fieldHandler.notifyDependents();
		verify(gh, times(1)).checkDependency(anyString(), anyString());
	}

	@Test
	public void testAddChangeListener() {
		GUITunableHandler gh = mock(GUITunableHandler.class);
		fieldHandler.addChangeListener(gh);
		fieldHandler.notifyChangeListeners();
		verify(gh, times(1)).changeOccurred(anyString(), anyString());
	}


	@Test
	public void testGetDependency() {
		assertNotNull(fieldHandler.getDependency());
	}

	@Test
	public void testChangeOccurred() {
		String name = "test name";
		String state = "test state";
		fieldHandler.changeOccurred(name, state);
	}

	@Test
	public void testCheckDependency() {
		String depName = "depName";
		String depState = "dep state";
		fieldHandler.checkDependency(depName, depState);
		
		fieldHandler.checkDependency(null, depState);
	}

	@Test
	public void testGetJPanel() {
		assertNotNull(fieldHandler.getJPanel());
	}

	@Test
	public void testHandle() {
		fieldHandler.handle();
	}

	@Test
	public void testUpdate() {
		fieldHandler.update();
	}

	@Test
	public void testGetState() {
		assertEquals("42", fieldHandler.getState());
	}
}
