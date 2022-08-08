package org.cytoscape.view.vizmap.events.table;

import org.cytoscape.event.AbstractCyEvent;
import org.cytoscape.view.vizmap.StyleAssociation;
import org.cytoscape.view.vizmap.TableVisualMappingManager;

public class ColumnAssociatedVisualStyleSetEvent extends AbstractCyEvent<TableVisualMappingManager> {

	private final StyleAssociation association; // The column style field may be null if the visual style was removed.
	
	
	public ColumnAssociatedVisualStyleSetEvent(TableVisualMappingManager source, StyleAssociation association) {
		super(source, ColumnAssociatedVisualStyleSetListener.class);
		this.association = association;
	}
	
	public StyleAssociation getAssociation() {
		return association;
	}
	
}
