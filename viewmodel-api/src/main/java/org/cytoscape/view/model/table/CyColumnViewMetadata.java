package org.cytoscape.view.model.table;

import java.util.Map;

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
