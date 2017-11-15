package org.cytoscape.command;

/*
 * #%L
 * Cytoscape Command Executor Impl (command-executor-impl)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
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

import org.cytoscape.work.TunableHandler;
import org.cytoscape.work.Tunable;
import org.cytoscape.work.AbstractTunableHandler;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractStringTunableHandler extends AbstractTunableHandler implements StringTunableHandler {

	private static final Logger logger = LoggerFactory.getLogger("org.cytoscape.application.userlog");
	private static final String SPACE = " ";
	private static final String EQUALS = "=";
	private static final String QUOTE = "\"";

	public AbstractStringTunableHandler(Field f, Object o, Tunable t) {
		super(f,o,t);
	}

	public AbstractStringTunableHandler(Method get, Method set, Object o, Tunable t) {
		super(get,set,o,t);
	}

	@Override
	public void processArgString(String s) {
		try {
			if ( s == null )
				return;

			String[] args = s.split(SPACE);

			for ( int i = 0; i < args.length; i++ ) {
				String arg = args[i];

				if ( arg.isEmpty() )
					continue;

				String[] keyValue = arg.split(EQUALS);

				if ( keyValue.length != 2 ) {
					logger.warn("couldn't parse 'key=value' string from arg: '" + arg +"'");
					continue;
				}

				String key = keyValue[0];
				String value = keyValue[1];

				// process any quote marks
				if ( value.startsWith(QUOTE) ) {

					// get additional strings from args that are part of this value
					while ( value.startsWith(QUOTE) && !value.endsWith(QUOTE))  {
						value = value + SPACE + args[++i]; 
					}

					// strip off quote marks
					value = value.substring(1,value.length()-1);
				}

				if ( key.equals(getName()) ) {
					Object result;
					try {
						// Now process the value, i.e. set the tunable field/method
						// with a value based on this string.
						result = processArg(value); 
					} catch (Exception e) {
						logger.warn("Couldn't parse value from: " + value, e);
						return;
					}

					// This actually sets the value for the tunable field/method.
					setValue(result);
					return;
				}
			}
			logger.debug("found no match for tunable: " + getQualifiedName());
		} catch ( Exception e) {
			logger.warn("tunable handler exception: " + getQualifiedName(), e);
		}
	}

	/**
	 * Each specific handler really only needs to implement this method and all it
	 * does is convert the String input into a value of the appropriate type.
	 *
	 * @param arg A String representing a value that will be parsed into an object
	 * of a specific type.
	 * @return An object of a particular type based on the input string.
	 * @throws Exception If there is any problem converting the string into an object.
	 */
	public abstract Object processArg(String arg) throws Exception;

	public final void handle() {}
}
