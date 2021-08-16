package org.cytoscape.taglets.compatibility;

import java.util.Map;

public class NoReferenceClassTaglet extends AbstractApiTaglet {

	public NoReferenceClassTaglet() {
		super("CyAPI.NoReference.Class",
		      "DO NOT USE",
		      "This class may change or be removed in future minor version upgrades of Cytoscape. " +
		      "It is not safe for Apps to reference this class."
			 );
	}
	
	/**
	 * The method that registers this taglet.
	 * @param tagletMap The map used to which this taglet should be added.
	 */
	@SuppressWarnings("rawtypes")
	public static void register(Map tagletMap) {
		registerTaglet(tagletMap, new NoReferenceClassTaglet());
	}

}
