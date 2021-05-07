package org.cytoscape.session;

/*
 * #%L
 * Cytoscape Session API (session-api)
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

import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyTable;
import org.cytoscape.model.CyTableMetadata;

public class CyTableMetadataImpl implements CyTableMetadata {

	private CyTable table;

	public CyTableMetadataImpl(CyTable table) {
		this.table = table;
	}

	@Override
	public boolean equals(Object obj) {
		return getTable().equals(((CyTableMetadata) obj).getTable());
	}
	
	@Override
	public int hashCode() {
		return getTable().hashCode();
	}

	@Override
	public Class<?> getType() {
		return null;
	}

	@Override
	public CyTable getTable() {
		return table;
	}

	@Override
	public CyNetwork getNetwork() {
		return null;
	}

	@Override
	public String getNamespace() {
		return null;
	}
}
