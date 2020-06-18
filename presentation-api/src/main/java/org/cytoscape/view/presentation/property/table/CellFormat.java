package org.cytoscape.view.presentation.property.table;

public class CellFormat {

	private final String format;
	
	public CellFormat(String format) {
		this.format = format;
	}
	
	public String getFormat() {
		return format;
	}
	
	@Override
	public String toString() {
		return format;
	}
}
