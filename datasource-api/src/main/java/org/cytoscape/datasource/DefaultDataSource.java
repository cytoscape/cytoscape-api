package org.cytoscape.datasource;

import java.net.URL;

import org.cytoscape.io.DataCategory;


/**
 * Basic implementation of {@link DataSource}.
 * In general, most of the data sources can be encoded with this default implementation. 
 *
 */
public class DefaultDataSource implements DataSource {
	
	private final String name;
	private final String provider;
	private final String description;
	private final DataCategory category;
	private final URL url;

	/**
	 * Constructor for the default data source.
	 * All of the parameters should be provided as a non-null value.
	 * 
	 * @param name name of source
	 * @param provider provider name of data source
	 * @param description description for this source
	 * @param category category of data
	 * @param url location of data file as URL
	 */
	public DefaultDataSource(final String name, final String provider, final String description,
			final DataCategory category, final URL url) {
		if(name == null)
			throw new NullPointerException("Name is null");
		if(description == null)
			throw new NullPointerException("Description is null");
		if(provider == null)
			throw new NullPointerException("Provider is null");
		if(category == null)
			throw new NullPointerException("DataCategory is null");
		if(url == null)
			throw new NullPointerException("URL is null");
		
		this.name = name;
		this.provider = provider;
		this.description = description;
		this.category = category;
		this.url = url;

	}

	@Override
	public String getProvider() {
		return this.provider;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public DataCategory getDataCategory() {
		return this.category;
	}

	@Override
	public URL getLocation() {
		return this.url;
	}
}
