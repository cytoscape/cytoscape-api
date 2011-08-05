package org.cytoscape.view.model;

import java.util.HashSet;
import java.util.Set;

import org.cytoscape.model.CyNode;

public class StringVisualProperty extends AbstractVisualProperty<String> {

	
	private static final Range<String> sRange;
	
	static  {
		final Set<String> valSet = new HashSet<String>();
		valSet.add("Foo");
		sRange = new DiscreteRange<String>(String.class, valSet);
	}
	
	public StringVisualProperty() {
		// isolated node. No parent/children.
		super("test", sRange, "string", "String Visual Property", CyNode.class);
	}

	public String parseSerializableString(final String text) {
		return text;
	}

	@Override
	public String toSerializableString(String value) {
		return value;
	}
}