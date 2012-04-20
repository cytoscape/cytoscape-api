package org.cytoscape.io.datasource;

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
