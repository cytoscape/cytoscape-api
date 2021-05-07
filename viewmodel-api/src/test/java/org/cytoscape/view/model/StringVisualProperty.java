package org.cytoscape.view.model;

/*
 * #%L
 * Cytoscape View Model API (viewmodel-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2021 The Cytoscape Consortium
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

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