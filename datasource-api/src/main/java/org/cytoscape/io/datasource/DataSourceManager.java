package org.cytoscape.io.datasource;

/*
 * #%L
 * Cytoscape Data Source API (datasource-api)
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

import java.util.Collection;
import org.cytoscape.io.DataCategory;

/**
 * Entry point to access all data sources implemented as OSGi services.
 * 
 * Once someone export {@link DataSource} as an OSGi service, this object 
 * automatically add the source and it is accessible by users.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule datasource-api
 */
public interface DataSourceManager {
	
	/**
	 * Returns all data sources under the given category.
	 * 
	 * @param category Category of the data source.  Network, Table, etc.
	 * @return all data sources under the category.
	 */
	Collection<DataSource> getDataSources(final DataCategory category);
	
	
	/**
	 * Returns all data sources from a data provider
	 * 
	 * @param providerName name of the data provider
	 * @return all data source from the given data provider
	 */
	Collection<DataSource> getDataSources(final String providerName);
	
	
	/**
	 * Returns all data sources registered as OSGi services
	 * @return all data sources
	 */
	Collection<DataSource> getAllDataSources();

	/**
	 * Returns all data categories
	 * @return all Data Categories
	 */
	Collection<DataCategory> getAllCategories();

	/**
	 * Remove a DataSource from the DataSourceManager
	 * @return true if the dataSource is removed successfully
	 */	
	boolean deleteDataSource(DataSource pDataSource);

	/**
	 * Save a DataSource to the DataSourceManager
	 * @return void
	 */		
	void saveDataSource(DataSource pDataSource);

	/**
	 * Check if a DataSource already existed in the DataSourceManager
	 * @return true if the dataSource is in the DataSourceManager
	 */		
	boolean containsDataSource(DataSource pDataSource);

}
