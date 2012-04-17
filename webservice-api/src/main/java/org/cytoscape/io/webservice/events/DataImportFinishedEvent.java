package org.cytoscape.io.webservice.events;

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
