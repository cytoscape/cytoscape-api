package org.cytoscape.taglets.compatibility;

import java.util.Map;
import java.util.Set;

public class NoReferenceMethodTaglet extends AbstractApiTaglet {

	public NoReferenceMethodTaglet() {
		super("CyAPI.NoReference.Method",
		      "DO NOT USE",
		      "This method may change or be removed in future minor version upgrades of Cytoscape. " +
		      "It is not safe for Apps to call or override this method."
			 );
	}
	
	@Override
	public Set<Location> getAllowedLocations() {
		return Set.of(Location.METHOD);
	}
	
	/**
	 * The method that registers this taglet.
	 * @param tagletMap The map used to which this taglet should be added.
	 */
	@SuppressWarnings("rawtypes")
	public static void register(Map tagletMap) {
		registerTaglet(tagletMap, new NoReferenceMethodTaglet());
	}

}
