package org.cytoscape.session;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyTable;
import org.cytoscape.model.CyTableMetadata;

public class CyTableMetadataImpl implements CyTableMetadata {

	private CyTable table;

	public CyTableMetadataImpl(CyTable table) {
		this.table = table;
	}

	@Override
	public boolean equals(Object obj) {
		return getTable().equals(((CyTableMetadata) obj).getTable());
	}
	
	@Override
	public int hashCode() {
		return getTable().hashCode();
	}

	@Override
	public Class<?> getType() {
		return null;
	}

	@Override
	public CyTable getTable() {
		return table;
	}

	@Override
	public CyNetwork getNetwork() {
		return null;
	}

	@Override
	public String getNamespace() {
		return null;
	}
}
