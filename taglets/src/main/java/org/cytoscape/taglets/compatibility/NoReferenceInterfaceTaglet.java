package org.cytoscape.taglets.compatibility;

import java.util.Map;

public class NoReferenceInterfaceTaglet extends AbstractApiTaglet {

	public NoReferenceInterfaceTaglet() {
		super("CyAPI.NoReference.Interface",
		      "DO NOT USE",
		      "This interface may change or be removed in future minor version upgrades of Cytoscape. " +
		      "It is not safe for Apps to reference or implement this interface."
			 );
	}
	
	/**
	 * The method that registers this taglet.
	 * @param tagletMap The map used to which this taglet should be added.
	 */
	@SuppressWarnings("rawtypes")
	public static void register(Map tagletMap) {
		registerTaglet(tagletMap, new NoReferenceInterfaceTaglet());
	}

}
