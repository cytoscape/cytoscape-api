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
		return getCyTable().equals(((CyTableMetadata) obj).getCyTable());
	}
	
	@Override
	public int hashCode() {
		return getCyTable().hashCode();
	}

	@Override
	public Class<?> getType() {
		return null;
	}

	@Override
	public CyTable getCyTable() {
		return table;
	}

	@Override
	public CyNetwork getCyNetwork() {
		return null;
	}

	@Override
	public String getNamespace() {
		return null;
	}
}
