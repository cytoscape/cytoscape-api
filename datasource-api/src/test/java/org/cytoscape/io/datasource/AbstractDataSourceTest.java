package org.cytoscape.io.datasource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URL;

import org.cytoscape.io.DataCategory;
import org.cytoscape.io.datasource.DataSource;
import org.junit.Test;

public abstract class AbstractDataSourceTest {

	protected DataSource source;
	protected String provider;
	protected String name;
	protected String description;
	protected DataCategory category;
	protected URL location;

	
	@Test
	public void testDataSource() {
		assertNotNull(source);
	}

	@Test
	public void testGetProvider() {
		final String provider = source.getProvider();
		assertEquals(this.provider, provider);
	}

	@Test
	public void testGetName() {
		final String name = source.getName();
		assertEquals(this.name, name);
	}

	@Test
	public void testGetDescription() {
		assertEquals(this.description, source.getDescription());
	}

	@Test
	public void testGetDataCategory() {
		final DataCategory category = source.getDataCategory();
		assertEquals(this.category, category);
	}

	@Test
	public void testGetLocation() {
		final URL location = source.getLocation();
		assertEquals(this.location, location);
	}
}