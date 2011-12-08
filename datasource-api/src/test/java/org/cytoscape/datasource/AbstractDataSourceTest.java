package org.cytoscape.datasource;

import org.junit.Test;

public abstract class AbstractDataSourceTest {

	private final DataSource source;
	
	AbstractDataSourceTest(final DataSource source) {
		this.source = source;
	}
	
	@Test
	public void testDataSource() {
		source.getDataCategory();
	}
}
