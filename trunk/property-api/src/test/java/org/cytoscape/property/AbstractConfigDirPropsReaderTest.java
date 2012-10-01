package org.cytoscape.property;


import static org.cytoscape.property.CyProperty.SavePolicy.CONFIG_DIR;
import static org.cytoscape.property.CyProperty.SavePolicy.DO_NOT_SAVE;
import static org.cytoscape.property.CyProperty.SavePolicy.SESSION_FILE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Properties;

import org.junit.Test;


public class AbstractConfigDirPropsReaderTest {

	private class BasicConfigDirPropsReader extends AbstractConfigDirPropsReader {
		BasicConfigDirPropsReader(String name, String fileName, SavePolicy sp) {
			super(name, fileName, sp);
		}
	}

	@Test(expected=IllegalArgumentException.class)
	public void testBadSavePolicy() throws Exception {
		BasicConfigDirPropsReader p = new BasicConfigDirPropsReader("Homer","homer.props",DO_NOT_SAVE); 
	}

	@Test(expected=IllegalArgumentException.class)
	public void testBadSavePolicy2() throws Exception {
		BasicConfigDirPropsReader p = new BasicConfigDirPropsReader("Homer","homer.props",SESSION_FILE); 
	}

	@Test(expected=NullPointerException.class)
	public void testNullName() throws Exception {
		BasicConfigDirPropsReader p = new BasicConfigDirPropsReader(null,"homer.props",null); 
	
	}

	@Test(expected=NullPointerException.class)
	public void testNullPropName() throws Exception {
		BasicConfigDirPropsReader p = new BasicConfigDirPropsReader("Test",null,CONFIG_DIR); 
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullSavePol() throws Exception {
		BasicConfigDirPropsReader p = new BasicConfigDirPropsReader("Homer","homer.props",null); 
	}

	@Test
	public void testGetDefaultProp(){
		BasicConfigDirPropsReader p = new BasicConfigDirPropsReader("Test","test.props",CONFIG_DIR); 
		assertEquals("marge", p.getProperties().getProperty("homer").toString() );
	}
	
	@Test
	public void testGetName(){
		BasicConfigDirPropsReader p = new BasicConfigDirPropsReader("Test","test.props",CONFIG_DIR); 
		assertEquals("Test", p.getName());
	}

	@Test
	public void testGetSavePolicy(){
		BasicConfigDirPropsReader p = new BasicConfigDirPropsReader("Test","test.props",CONFIG_DIR); 
		assertEquals(CONFIG_DIR, p.getSavePolicy());
	}
	
	@Test
	public void testGetPropertyType(){
		BasicConfigDirPropsReader p = new BasicConfigDirPropsReader("Test","test.props",CONFIG_DIR);
		assertTrue(Properties.class.isAssignableFrom(p.getPropertyType()));
	}

	// no tests for reading config dir since we don't want to pollute the actual config dir with anything
}
