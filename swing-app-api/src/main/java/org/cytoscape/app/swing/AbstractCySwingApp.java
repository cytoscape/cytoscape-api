package org.cytoscape.app.swing;

/*
 * #%L
 * Cytoscape Swing App API (swing-app-api)
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

import org.cytoscape.app.AbstractCyApp;

/**
 * The primary Swing-based app interface for Cytoscape that all
 * Swing-based apps must extend. This extension to
 * AbstractCyApp simply provides access to CySwingAppAdapter,
 * which provides access to Swing specific services.  All other
 * services from CyAppAdapter will still be available.
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule swing-app-api
 */
public abstract class AbstractCySwingApp extends AbstractCyApp {

	/**
	 * Reference to access Cytoscape functionality -- various managers and 
	 * factories that are normally available as OSGi services.
	 */
	protected final CySwingAppAdapter swingAdapter;

	/**
	 * The constructor that all apps must call using "super(adapter);" where
	 * the "adapter" is a {@link CySwingAppAdapter} reference provided as an
	 * argument to the constructor. Cytoscape's app loader will execute
	 * the constructor and provide the proper CySwingAppAdapter reference.
	 * <br/>
	 * 
	 * <blockquote><pre> 
	 * public class MySwingApp extends AbstractCySwingApp {
	 *    public MySwingApp(CySwingAppAdapter adapter) {
	 *       super(adapter);
	 *       // app code here
	 *    }
	 * }
	 * </pre></blockquote>
	 * @param adapter a {@link CySwingAppAdapter} reference provided as an
	 * argument to the constructor.
	 */
	public AbstractCySwingApp(final CySwingAppAdapter swingAdapter) {
		super(swingAdapter);
		this.swingAdapter = swingAdapter;
	}
}
