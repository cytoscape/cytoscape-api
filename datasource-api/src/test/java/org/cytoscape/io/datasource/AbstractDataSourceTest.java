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