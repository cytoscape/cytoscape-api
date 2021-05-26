package org.cytoscape.view.model.table;

import java.util.Map;

/**
 * A snapshot of information about a CyRow and its associated visual property values. 
 * The interface is used internally to capture
 * table information for serialization and shouldn't be needed for most
 * normal use of tables.
 * 
 * @CyAPI.NoReference.Class
 * @CyAPI.Api.Interface
 * @CyAPI.InModule viewmodel-api
 * @since 3.9
 */
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
