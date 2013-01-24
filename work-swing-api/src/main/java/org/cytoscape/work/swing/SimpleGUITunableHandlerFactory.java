package org.cytoscape.work.swing;

/*
 * #%L
 * Cytoscape Work Swing API (work-swing-api)
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

import org.cytoscape.work.BasicTunableHandlerFactory;


/**
 * A specialization for GUITunableHandlers.
 * @param <T> 
 * @CyAPI.Final.Class
 */
public final class SimpleGUITunableHandlerFactory<T extends GUITunableHandler> extends BasicTunableHandlerFactory<T>
		implements GUITunableHandlerFactory<T> {

	/**
	 * Constructs this BasicGUITunableHandlerFactory.
	 * @param specificHandlerType The class of the specific handler to be constructed
	 * to handle the matching classes. For instance FloatHandler.class might be specified
	 * to handle values with a Float type.
	 * @param classesToMatch One or more class types that will be handled by this handler.
	 * For example the FloatHandler might handle both Float.class and float.class.
	 */
	public SimpleGUITunableHandlerFactory(Class<T> specificHandlerType, Class<?>... classesToMatch ) {
		super(specificHandlerType, classesToMatch);
	}

}
