/*
 Copyright (c) 2006, 2007, The Cytoscape Consortium (www.cytoscape.org)

 The Cytoscape Consortium is:
 - Institute for Systems Biology
 - University of California San Diego
 - Memorial Sloan-Kettering Cancer Center
 - Institut Pasteur
 - Agilent Technologies

 This library is free software; you can redistribute it and/or modify it
 under the terms of the GNU Lesser General Public License as published
 by the Free Software Foundation; either version 2.1 of the License, or
 any later version.

 This library is distributed in the hope that it will be useful, but
 WITHOUT ANY WARRANTY, WITHOUT EVEN THE IMPLIED WARRANTY OF
 MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.  The software and
 documentation provided hereunder is on an "as is" basis, and the
 Institute for Systems Biology and the Whitehead Institute
 have no obligations to provide maintenance, support,
 updates, enhancements or modifications.  In no event shall the
 Institute for Systems Biology and the Whitehead Institute
 be liable to any party for direct, indirect, special,
 incidental or consequential damages, including lost profits, arising
 out of the use of this software and its documentation, even if the
 Institute for Systems Biology and the Whitehead Institute
 have been advised of the possibility of such damage.  See
 the GNU Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation,
 Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
*/
package org.cytoscape.io.webservice.client;

import java.awt.Container;
import java.net.URI;
import java.net.URISyntaxException;

import org.cytoscape.io.webservice.WebServiceClient;
import org.cytoscape.work.ProvidesGUI;



/**
 * Abstract class for all web service clients.
 * All clients MUST extend this class.
 */
public abstract class AbstractWebServiceClient implements WebServiceClient {

	/** Service location */
	protected final URI serviceURI;
	
	// Display Name for this client.
	private final String displayName;
	private final String description;
	
	// By default, this is null.
	protected final Container panel = null;
	
	protected Object currentQuery = null;


	public AbstractWebServiceClient(final String uri, final String displayName, final String description) {
		
		// Create URI
		try {
			this.serviceURI = new URI(uri);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("URI string is invalid.");
		}
		
		this.displayName = displayName;
		this.description = description;
	}


	@Override public String getDisplayName() {
		return displayName;
	}


	@Override public String getDescription() {
		return description;
	}
	
	@Override public String toString() {
		return this.displayName;
	}


	@Override
	public URI getServiceLocation() {
		return this.serviceURI;
	}


	@Override @ProvidesGUI
	public Container getQueryBuilderGUI() {
		return panel;
	}
	
	
	@Override public void setQuery(Object query) {
		this.currentQuery = query;
	}
}
