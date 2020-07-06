package org.cytoscape.application.events;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.model.CyTable;

public class SetCurrentTableEvent extends AbstractCyEvent<CyApplicationManager> {

	private final CyTable table;
	
	public SetCurrentTableEvent(CyApplicationManager source, CyTable table) {
		super(source, SetCurrentTableListener.class);
		this.table = table;
	}
	
	
	public CyTable getTable() {
		return table;
	}

}
