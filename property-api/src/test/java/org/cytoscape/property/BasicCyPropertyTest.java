package org.cytoscape.property;


import static org.junit.Assert.assertTrue;
import java.util.Properties;
import org.junit.Test;


public class BasicCyPropertyTest {

	@Test(expected=NullPointerException.class)
	public void testNullProp() throws Exception {
		BasicCyProperty p = new BasicCyProperty(null, CyProperty.SavePolicy.DO_NOT_SAVE);
	}

	@Test(expected=NullPointerException.class)
	public void testNullSavePol() throws Exception {
		Properties props = new Properties();
		BasicCyProperty p = new BasicCyProperty(props, null);
	}

	@Test
	public void testGetProp(){
		Properties props = new Properties();
		BasicCyProperty p = new BasicCyProperty(props, CyProperty.SavePolicy.DO_NOT_SAVE);
		assertTrue(p.getProperties() != null);
	}

	@Test
	public void testGetSavePol(){
		Properties props = new Properties();
		BasicCyProperty p = new BasicCyProperty(props, CyProperty.SavePolicy.DO_NOT_SAVE);
		assertTrue(p.getSavePolicy() == CyProperty.SavePolicy.DO_NOT_SAVE);
	}
}
