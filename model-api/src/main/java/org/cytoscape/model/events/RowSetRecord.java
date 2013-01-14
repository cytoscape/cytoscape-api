package org.cytoscape.model.events;

import org.cytoscape.model.CyRow;

/**
 * Holds a record of a {@link CyRow} that was set and what it was set to.
 * @CyAPI.Final.Class
 * @CyAPI.InModule model-api
 */
public final class RowSetRecord {
	private final CyRow row;
	private final String column;
	private final Object value;
	private final Object rawValue;

	/**
	 * Constructs a RowSetRecord.
	 * @param row The {@link CyRow} of the data that was set.
	 * @param column The name of the column of the data that was set.
	 * @param value The value the data was set as.
	 * @param rawValue The raw value the data was set as.
	 */
	public RowSetRecord(final CyRow row, final String column,
			final Object value, final Object rawValue) {
		this.row = row;
		this.column = column;
		this.value = value;
		this.rawValue = rawValue;
	}

	/**
	 * Returns the {@link CyRow} of the data that was set.
	 * @return the {@link CyRow} of the data that was set.
	 */
	public CyRow getRow() {
		return row;
	}

	/**
	 * Returns the name of the column of the data that was set.
	 * @return the name of the column of the data that was set.
	 */
	public String getColumn() {
		return column;
	}

	/**
	 * Returns the value that the data was set as.
	 * @return the value that the data was set as.
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Returns the raw value that the data was set as.
	 * @return the raw value that the data was set as.
	 */
	public Object getRawValue() {
		return rawValue;
	}
}
