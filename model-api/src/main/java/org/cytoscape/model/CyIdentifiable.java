package org.cytoscape.model;

/*
 * #%L
 * Cytoscape Model API (model-api)
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


/**
 * CyIdentifiable is an interface that indicates that the implementing
 * object can be considered a key into a table. In general, table
 * entries will be things like nodes, edges, and networks..
 * @CyAPI.Api.Interface
 * @CyAPI.InModule model-api
 */
public interface CyIdentifiable {

	/**
	 * This will be used as column name of SUID.
	 */
	String SUID = "SUID";
	
	/**
	 * Returns the SUID of the implementing object.
	 * @return the SUID of the implementing object.
	 */
	Long getSUID();
}
