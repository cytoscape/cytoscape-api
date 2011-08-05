package org.cytoscape.model.events;

import org.cytoscape.model.CyRow;

public final class RowSetRecord {
	private final CyRow row;
	private final String column;
	private final Object value;
	private final Object rawValue;

	public RowSetRecord(final CyRow row, final String column,
			final Object value, final Object rawValue) {
		this.row = row;
		this.column = column;
		this.value = value;
		this.rawValue = rawValue;
	}

	public CyRow getRow() {
		return row;
	}

	public String getColumn() {
		return column;
	}

	public Object getValue() {
		return value;
	}

	public Object getRawValue() {
		return rawValue;
	}
}
