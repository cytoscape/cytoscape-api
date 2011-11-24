package org.cytoscape.property;


import static org.junit.Assert.assertTrue;
import java.util.Properties;
import org.junit.Test;


public class SimpleCyPropertyTest {

	@Test(expected=NullPointerException.class)
	public void testNullProp() throws Exception {
		SimpleCyProperty p = new SimpleCyProperty(null, CyProperty.SavePolicy.DO_NOT_SAVE);
	}

	@Test(expected=NullPointerException.class)
	public void testNullSavePol() throws Exception {
		Properties props = new Properties();
		SimpleCyProperty p = new SimpleCyProperty(props, null);
	}

	@Test
	public void testGetProp(){
		Properties props = new Properties();
		SimpleCyProperty p = new SimpleCyProperty(props, CyProperty.SavePolicy.DO_NOT_SAVE);
		assertTrue(p.getProperties() != null);
	}

	@Test
	public void testGetSavePol(){
		Properties props = new Properties();
		SimpleCyProperty p = new SimpleCyProperty(props, CyProperty.SavePolicy.DO_NOT_SAVE);
		assertTrue(p.getSavePolicy() == CyProperty.SavePolicy.DO_NOT_SAVE);
	}
}
