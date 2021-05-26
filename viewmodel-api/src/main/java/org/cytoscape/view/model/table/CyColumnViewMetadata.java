package org.cytoscape.view.model.table;

import java.util.Map;

/**
 * A snapshot of information about a CyColumn and its associated visual property values. 
 * The interface is used internally to capture
 * table information for serialization and shouldn't be needed for most
 * normal use of tables.
 * 
 * @CyAPI.NoReference.Class
 * @CyAPI.Api.Interface
 * @CyAPI.InModule viewmodel-api
 * @since 3.9
 */
public class CyColumnViewMetadata {
	
	private final String name;
	private final String styleName;
	private final Map<String,String> bypassValues;
	

	public CyColumnViewMetadata(String name, String styleName, Map<String,String> bypassValues) {
		this.name = name;
		this.styleName = styleName;
		this.bypassValues = bypassValues;
	}


	public String getName() {
		return name;
	}

	public Map<String,String> getBypassValues() {
		return bypassValues;
	}
	
	public String getStyleName() {
		return styleName;
	}

}
