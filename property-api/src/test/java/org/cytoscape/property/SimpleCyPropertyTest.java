package org.cytoscape.property;


import static org.junit.Assert.assertTrue;
import java.util.Properties;
import org.junit.Test;


public class SimpleCyPropertyTest {

	@Test(expected=NullPointerException.class)
	public void testNullName() throws Exception {
		new SimpleCyProperty<Properties>(null, new Properties(), Properties.class, CyProperty.SavePolicy.DO_NOT_SAVE);
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullProp() throws Exception {
		new SimpleCyProperty<Properties>("test", null, Properties.class, CyProperty.SavePolicy.DO_NOT_SAVE);
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullType() throws Exception {
		new SimpleCyProperty<Properties>("test", new Properties(), null, CyProperty.SavePolicy.DO_NOT_SAVE);
	}

	@Test(expected=NullPointerException.class)
	public void testNullSavePol() throws Exception {
		new SimpleCyProperty<Properties>("test", new Properties(), Properties.class, null);
	}
	
	@Test
	public void testGetProp(){
		SimpleCyProperty<Properties> p = new SimpleCyProperty<Properties>("test", new Properties(), Properties.class, CyProperty.SavePolicy.DO_NOT_SAVE);
		assertTrue(p.getProperties() != null);
	}

	@Test
	public void testGetSavePol(){
		SimpleCyProperty<Properties> p = new SimpleCyProperty<Properties>("test", new Properties(), Properties.class, CyProperty.SavePolicy.DO_NOT_SAVE);
		assertTrue(p.getSavePolicy() == CyProperty.SavePolicy.DO_NOT_SAVE);
	}
	
	@Test
	public void testGetPropertyType(){
		SimpleCyProperty<Properties> p = new SimpleCyProperty<Properties>("test", new Properties(), Properties.class, CyProperty.SavePolicy.DO_NOT_SAVE);
		assertTrue(Properties.class.isAssignableFrom(p.getPropertyType()));
	}
}
