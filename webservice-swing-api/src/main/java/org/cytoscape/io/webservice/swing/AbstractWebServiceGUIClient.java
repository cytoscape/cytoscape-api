package org.cytoscape.io.webservice.swing;

import java.awt.Container;

import org.cytoscape.io.webservice.client.AbstractWebServiceClient;

/**
 * Abstract base class for web service clients with Swing GUI.
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