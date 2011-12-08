package org.cytoscape.datasource;

import java.util.Collection;

import org.cytoscape.io.DataCategory;

/**
 * Entry point to access all data sources implemented as OSGi services.
 * 
 * Once someone export {@link DataSource} as an OSGi service, this object 
 * automatically add the source and it is accessible by users.
 *
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
}
