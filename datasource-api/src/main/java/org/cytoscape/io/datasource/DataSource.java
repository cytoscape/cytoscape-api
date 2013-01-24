package org.cytoscape.io.datasource;

/*
 * #%L
 * Cytoscape Data Source API (datasource-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
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

import java.net.URL;

import org.cytoscape.io.DataCategory;

/**
 * Immutable data source object, which represents one data file associated with an URL.
 *
 */
public interface DataSource {
	
	/**
	 * Name of this data provider for this source.
	 * Usually, this is an database name or organization name providing set of data.
	 * 
	 * @return Name of data provider
	 */
	String getProvider();
	
	/**
	 * Name of this data source
	 * 
	 * @return Name of this data source
	 */
	String getName();
	
	/**
	 * Description for this data.
	 * 
	 * @return description
	 */
	String getDescription();
	
	/**
	 * {@link DataCategory} of this data.
	 * 
	 * @return category of this data file.
	 */
	DataCategory getDataCategory();
	
	/**
	 * Location of this data file.
	 * 
	 * @return URL of this data file
	 */
	URL getLocation();

}
