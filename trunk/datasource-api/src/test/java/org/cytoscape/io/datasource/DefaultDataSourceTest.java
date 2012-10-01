package org.cytoscape.io.datasource;

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
