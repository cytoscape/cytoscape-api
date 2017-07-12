package org.cytoscape.work;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/*
 * #%L
 * Cytoscape Work API (work-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2017 The Cytoscape Consortium
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

/** 
 * Provides the standard implementation for most of the methods declared by the 
 * TunableHandler interface.
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule work-api
 */
public abstract class AbstractTunableHandler implements TunableHandler {
	
	private enum ParamsParseState {
		KEY_START, LOOKING_FOR_EQUAL_SIGN, VALUE_START, LOOKING_FOR_SEMICOLON;
	}

	private final Field field;
	private final Method getter;
	private final Method setter;
	private final Reference<?> instance;
	private final Tunable tunable;
	private double offset = 0.0; // For @ContainsTunables, maintain the gravity offset
	public static final String TOOLTIP = "tooltip";
	public static final String GRAVITY = "gravity";
	public static final String CONTEXT = "context";
	public static final String FORMAT = "format";

	/** 
	 * Standard base class constructor for <code>TunableHandler</code>s that deal with
	 * <code>Tunable</code>s that annotate a field.
	 *
	 * @param field    An instance of <code>Field</code> that represents a field 
	 * annotated with <code>@Tunable</code>
	 * @param instance An object instance that contains a field corresponding to 
	 * the <i>field</i> parameter
	 * @param tunable  The <code>Tunable</code> that annotates <i>field</i>
	 */
	public AbstractTunableHandler(final Field field, final Object instance, final Tunable tunable) {
		this.field = field;
		this.getter = null;
		this.setter = null;
		this.instance = new WeakReference<Object>(instance);
		this.tunable = tunable;
	}

	/** 
	 * Standard base class constructor for <code>TunableHandler</code>s that deal with
	 * <code>Tunable</code>s that use getter and setter methods.
	 *
	 * @param getter The getter method of the tunable object represented by the 
	 * <i>instance</i> parameter.
	 * @param setter The setter method complimentary to the getter.
	 * @param instance An instance of an object with a getter method that has been 
	 * determined to be annotated with <code>@Tunable</code>.
	 * @param tunable  The <code>Tunable</code> that annotates the <i>getter</i>.
	 */
	public AbstractTunableHandler(final Method getter, final Method setter, final Object instance, final Tunable tunable) {
		this.field = null;
		this.getter = getter;
		this.setter = setter;
		this.instance = new WeakReference<Object>(instance);
		this.tunable = tunable;
	}

	@Override
	public final Class<?> getType() {
		return field != null ? field.getType() : getter.getReturnType();
	}

	@Override
	public final Object getValue() throws IllegalAccessException, InvocationTargetException {
		return field != null ? field.get(instance.get()) : getter.invoke(instance.get());
	}

	@Override
	public void setValue(final Object newValue) throws IllegalAccessException, InvocationTargetException {
		if (field != null)
			field.set(instance.get(), newValue);
		else
			setter.invoke(instance.get(), newValue);
	}

	@Override
	public final String getDescription() {
		return tunable.description();
	}

	@Override
	public final String getLongDescription() {
		return tunable.longDescription();
	}

	public final String getExampleStringValue() {
		return tunable.exampleStringValue();
	}

	@Override
	public final String[] getGroups() {
		return tunable.groups();
	}

	@Override
	public final boolean controlsMutuallyExclusiveNestedChildren() {
		return tunable.xorChildren();
	}

	@Override
	public final String getChildKey() {
		return tunable.xorKey();
	}

	@Override
	public final String dependsOn() {
		return tunable.dependsOn();
	}

	@Override
	public final String[] listenForChange() {
		return tunable.listenForChange();
	}

	@Override
	public final String getName() {
		if (field != null)
			return field.getName();
		else
			return setter.getName().substring(3);
	}

	@Override
	public final String getQualifiedName() {
		final String unqualifiedClassName = field == null ? getter.getDeclaringClass().toString()
				: field.getDeclaringClass().toString();

		return unqualifiedClassName.substring(unqualifiedClassName.lastIndexOf(".") + 1) + "." + getName();
	}

	@Override
	public final Properties getParams() throws IllegalArgumentException {
		final String rawString = tunable.params();
		final Properties keyValuesPairs = new Properties();
		keyValuesPairs.put(GRAVITY, Double.toString(tunable.gravity()+offset));
		keyValuesPairs.put(TOOLTIP, tunable.tooltip());
		keyValuesPairs.put(CONTEXT, tunable.context());
		
		if (tunable.format() != null && tunable.format().length() > 0)
			keyValuesPairs.put(FORMAT, tunable.format());

		StringBuilder key = null;
		StringBuilder value = null;
		ParamsParseState state = ParamsParseState.KEY_START;
		boolean escaped = false;
		
		for (int i = 0; i < rawString.length(); ++i) {
			final char ch = rawString.charAt(i);

			switch (state) {
			case KEY_START:
				key = new StringBuilder();
				if (!Character.isLetter(ch))
					throw new IllegalArgumentException(getName() + "'s getParams() returns an invalid key.");
				key.append(ch);
				state = ParamsParseState.LOOKING_FOR_EQUAL_SIGN;
				break;
			case LOOKING_FOR_EQUAL_SIGN:
				if (ch == '=')
					state = ParamsParseState.VALUE_START;
				else {
					if (!Character.isLetter(ch))
						throw new IllegalArgumentException(getName() + "'s getParams() returns an invalid key.");
					key.append(ch);
				}
				break;
			case VALUE_START:
				value = new StringBuilder();
				if (ch == ';')
					throw new IllegalArgumentException(getName() + "'s getParams() returns an invalid value.");
				if (ch == '\\')
					escaped = true;
				else
					value.append(ch);
				state = ParamsParseState.LOOKING_FOR_SEMICOLON;
				break;
			case LOOKING_FOR_SEMICOLON:
				if (escaped) {
					value.append(ch);
					escaped = false;
				} else if (ch == ';') {
					keyValuesPairs.setProperty(key.toString(), value.toString());
					state = ParamsParseState.KEY_START;
				} else {
					if (ch == '\\')
						escaped = true;
					else
						value.append(ch);
				}
				break;
			}
		}

		if (escaped)
			throw new IllegalArgumentException(getName() + "'s getParams() returns an invalid escaped character.");
		if (state != ParamsParseState.KEY_START && state != ParamsParseState.LOOKING_FOR_SEMICOLON)
			throw new IllegalArgumentException(getName() + "'s getParams() returns an incomplete string: \"" + rawString + "\".");

		if (key != null) {
			if (value == null)
				throw new IllegalArgumentException(getName() + "'s getParams() returns a key without a value.");
			keyValuesPairs.setProperty(key.toString(), value.toString());
		}

		return keyValuesPairs;
	}

	/**
	 * This returns the context of the Tunable.  The context must be one of:
	 * <ul><li><b>gui</b>: used only when a graphical user interface is available</li>
	 * <li><b>nogui</b>: used only when a graphical user interface is not available</li>
	 * <li><b>both</b>: always used</li>
	 * </ul>
	 *
	 * @return the context
	 */
	public final String getContext() {
		return tunable.context();
	}

	/**
	 * Returns the tooltip annotation for this Tunable if there is one.
	 *
	 * @return tooltip string, if any
	 */
	public final String getTooltip() {
		return tunable.tooltip();
	}

	/**
	 * Returns the gravity value for the annotation.
	 *
	 * @return Tunable gravity as a double
	 */
	public final double getGravity() {
		return tunable.gravity() + offset;
	}

	/**
	 * Returns true if this Tunable must be set (i.e. is required), otherwise
	 * returns false.
	 *
	 * @return true if it is required.
	 */
	public final boolean getRequired() {
		return tunable.required();
	}

	/**
	 * Provides a format string suitable for passing to String.format.  This allows default values to be rationally
	 * presented to users.  Note that this is only for presentation purposes and does not effect the underlying
	 * values.  Also, not all Tunables will respect format -- for example formatting boolean, checkboxes, or
	 * List selections don't make sense.
	 *
	 * @return the string to use for the format
	 */
	public final String getFormat() {
		return tunable.format();
	}

	public void setOffset(double offset) {
		this.offset = offset;
	}
}
