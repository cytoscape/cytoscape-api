package org.cytoscape.util.swing;

/*
 * #%L
 * Cytoscape Swing Utility API (swing-util-api)
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


/**
 * A utility provided as an OSGi service for opening
 * a web browser. The Cytoscape property "defaultWebBrowser"
 * may be set with an alternative command for opening
 * a different web browser than default.
 * @CyAPI.Api.Interface 
 */
public interface OpenBrowser {

	/**
	 * The name of a property for a Properties object used to define an alternative
	 * web browser command.
	 */
	String DEF_WEB_BROWSER_PROP_NAME = "defaultWebBrowser";

	/**
	 * Opens a web browser pointing to the specified URL.  
	 * @param url A string URL that should be opened in the web browser. 
	 * @return Whether the browser opened successfully or not.
	 */
	boolean openURL(String url);
}
