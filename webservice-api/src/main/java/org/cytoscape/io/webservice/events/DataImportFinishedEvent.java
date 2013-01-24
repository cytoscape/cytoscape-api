package org.cytoscape.io.webservice.events;

/*
 * #%L
 * Cytoscape Webservice API (webservice-api)
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

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.io.webservice.WebServiceClient;

/**
 * An event indicating that data has been successfully imported.
 *
 * @param <T>
 *            the generic type of the object associated with this
 *            DataImportFinishedEvent.
 * @CyAPI.Final.Class
 */
public final class DataImportFinishedEvent<T> extends AbstractCyEvent<WebServiceClient> {

	private final T imported;

	/**
	 * Constructs the event.
	 *
	 * @param source
	 *            the source of the object that was imported.
	 * @param imported
	 *            the object that was imported.
	 */
	public DataImportFinishedEvent(final WebServiceClient source, final T imported) {
		super(source, DataImportFinishedListener.class);

		this.imported = imported;
	}

	/**
	 * Returns the object that was imported.
	 *
	 * @return the object that was imported.
	 */
	public T getImportedObject() {
		return this.imported;
	}

}
