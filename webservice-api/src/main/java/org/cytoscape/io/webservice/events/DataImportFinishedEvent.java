package org.cytoscape.io.webservice.events;

import org.cytoscape.event.AbstractCyEvent;

public class DataImportFinishedEvent<T> extends AbstractCyEvent<Object> {

	private final T imported;
	
	public DataImportFinishedEvent(final Object source, final T imported) {
		super(source, DataImportFinishedListener.class);
		
		this.imported = imported;
	}

	public T getImportedObject() {
		return this.imported;
	}

}