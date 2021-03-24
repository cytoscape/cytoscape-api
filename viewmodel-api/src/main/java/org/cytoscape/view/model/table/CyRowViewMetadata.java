package org.cytoscape.view.model.table;

import java.util.Map;

public class CyRowViewMetadata {

	private final Object key;
	private final Map<String,String> bypassValues;
	

	public CyRowViewMetadata(Object key, Map<String,String> bypassValues) {
		this.key = key;
		this.bypassValues = bypassValues;
	}


	public Object getKeyValue() {
		return key;
	}

	public Map<String,String> getBypassValues() {
		return bypassValues;
	}
	
}
