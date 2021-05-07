package org.cytoscape.application.swing;

/*
 * #%L
 * Cytoscape Swing Application API (swing-application-api)
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


/**
 * Implement this interface rather than {@link CytoPanelComponent}, if you want to allow your component to be retrieved
 * from the {@link CytoPanel} by its identifier. See {@link CytoPanel#indexOfComponent(String)}.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule swing-application-api
 */
public interface CytoPanelComponent2 extends CytoPanelComponent {

	/**
	 * Returns this component's identifier, which should be unique. To minimize the risk of returning an identifier that
	 * is also used by another app, you should add a prefix/namespace.
	 * As a suggestion, the prefix can be your app's name or your organization reverse domain.
	 * For example, return "com.myorganizationname.MyPanel" instead of just "MyPanel".
	 * The prefix "org.cytoscape." is reserved for modules and apps provided by the Cytoscape Consortium only
	 * and must not be used by third-party apps.
	 * @return The identifier of this component.
	 */
	String getIdentifier();
}
