package org.cytoscape.io;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DataCategoryTest {

	@Test
	public void testDataCategory() {
		assertEquals(8, DataCategory.values().length);
		assertEquals(DataCategory.IMAGE, DataCategory.valueOf("IMAGE"));
		assertEquals(DataCategory.NETWORK, DataCategory.valueOf("NETWORK"));
		assertEquals(DataCategory.PROPERTIES, DataCategory.valueOf("PROPERTIES"));
		assertEquals(DataCategory.SCRIPT, DataCategory.valueOf("SCRIPT"));
		assertEquals(DataCategory.SESSION, DataCategory.valueOf("SESSION"));
		assertEquals(DataCategory.TABLE, DataCategory.valueOf("TABLE"));
		assertEquals(DataCategory.UNSPECIFIED, DataCategory.valueOf("UNSPECIFIED"));
		assertEquals(DataCategory.VIZMAP, DataCategory.valueOf("VIZMAP"));
	}

}
