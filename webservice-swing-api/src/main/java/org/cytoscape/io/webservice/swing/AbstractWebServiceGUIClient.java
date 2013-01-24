package org.cytoscape.io.webservice.swing;

/*
 * #%L
 * Cytoscape Webservice Swing API (webservice-swing-api)
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

import java.awt.Container;

import org.cytoscape.io.webservice.client.AbstractWebServiceClient;

/**
 * Abstract base class for web service clients with Swing GUI.
 * 
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule webservice-swing-api
 */
public abstract class AbstractWebServiceGUIClient extends AbstractWebServiceClient implements WebServiceGUIClient {

	// Client GUI.  If this is null, default GUI will be used. 
	protected Container gui;

	/**
	 * Constructor.
	 * @param uri A String representation of the URI.
	 * @param displayName The display name to be used for this client.
	 * @param description The description to be used for this client.
	 */
	public AbstractWebServiceGUIClient(final String uri, final String displayName, final String description) {
		super(uri, displayName, description);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Container getQueryBuilderGUI() {
		return gui;
	}
}