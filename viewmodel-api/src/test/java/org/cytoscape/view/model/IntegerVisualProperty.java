package org.cytoscape.view.model;

/*
 * #%L
 * Cytoscape View Model API (viewmodel-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2021 The Cytoscape Consortium
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

import org.cytoscape.model.CyNode;


class IntegerVisualProperty extends AbstractVisualProperty<Integer> {
	
	private static final ContinuousRange<Integer> cRange = new ContinuousRange<Integer>(Integer.class, -10, 10, true, true);
	

	public IntegerVisualProperty() {
		// isolated node.  No parent/children.
		super(10, cRange, "integer", "Integer Visual Property", CyNode.class);
	}


	public Integer parseSerializableString(final String text) {
		return Integer.valueOf(text);
	}

	
	@Override
	public String toSerializableString(Integer value) {
		return value.toString();
	}
}
