package org.cytoscape.view.presentation.property.table;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.view.model.AbstractVisualProperty;
import org.cytoscape.view.model.DiscreteRange;
import org.cytoscape.view.presentation.property.values.AbstractVisualPropertyValue;

public class TableModeVisualProperty extends AbstractVisualProperty<TableMode> {
	
	public static final TableMode AUTO = new TableModeImpl("Auto", "AUTO");
	public static final TableMode ALL = new TableModeImpl("All", "ALL");
	public static final TableMode SELECTED = new TableModeImpl("Selected", "SELECTED");
	
	private static final Map<String,TableMode> MODES = new HashMap<>();
	private static final DiscreteRange<TableMode> TABLE_MODE_RANGE;
	
	static {
		MODES.put(AUTO.getSerializableString(), AUTO);
		MODES.put(ALL.getSerializableString(), ALL);
		MODES.put(SELECTED.getSerializableString(), SELECTED);
		TABLE_MODE_RANGE = new DiscreteRange<>(TableMode.class, new HashSet<>(MODES.values()));
	}
	

	public TableModeVisualProperty(TableMode defaultValue, String id, String displayName, Class<? extends CyIdentifiable> modelDataType) {
		super(defaultValue, TABLE_MODE_RANGE, id, displayName, modelDataType);
	}

	@Override
	public String toSerializableString(TableMode value) {
		return value.getSerializableString();
	}

	@Override
	public TableMode parseSerializableString(String value) {
		return MODES.get(value);
	}
			
	
	private static class TableModeImpl extends AbstractVisualPropertyValue implements TableMode {
		public TableModeImpl(String displayName, String serializableString) {
			super(displayName, serializableString);
		}
	}
			
}
