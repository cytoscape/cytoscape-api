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

import static org.junit.Assert.*;

import java.net.URL;

import org.cytoscape.io.DataCategory;
import org.cytoscape.io.datasource.DefaultDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DefaultDataSourceTest extends AbstractDataSourceTest {

	@Before
	public void setUp() throws Exception {
		this.name = "test source";
		this.provider = "dummy provider";
		this.description = "dummy data source";
		this.category = DataCategory.NETWORK;
		this.location = new URL("http://chianti.ucsd.edu/test.sif");
		this.source = new DefaultDataSource(name, provider, description, category, location);
	}
}
