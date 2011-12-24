package org.cytoscape.property;


import static org.junit.Assert.*;
import java.util.Properties;
import org.junit.Test;


public class AbstractConfigDirPropsReaderTest {

	private class BasicConfigDirPropsReader extends AbstractConfigDirPropsReader {
		BasicConfigDirPropsReader(String s, SavePolicy sp) {
			super(s,sp);
		}
	}

	@Test(expected=IllegalArgumentException.class)
	public void testBadSavePolicy() throws Exception {
		BasicConfigDirPropsReader p = new BasicConfigDirPropsReader("homer",CyProperty.SavePolicy.DO_NOT_SAVE); 
	}

	@Test(expected=IllegalArgumentException.class)
	public void testBadSavePolicy2() throws Exception {
		BasicConfigDirPropsReader p = new BasicConfigDirPropsReader("homer",CyProperty.SavePolicy.SESSION_FILE); 
	}

	@Test(expected=NullPointerException.class)
	public void testNullSavePol() throws Exception {
		BasicConfigDirPropsReader p = new BasicConfigDirPropsReader("homer",null); 
	}

	@Test(expected=NullPointerException.class)
	public void testNullPropName() throws Exception {
		BasicConfigDirPropsReader p = new BasicConfigDirPropsReader(null,CyProperty.SavePolicy.CONFIG_DIR); 
	}

	@Test
	public void testGetDefaultProp(){
		BasicConfigDirPropsReader p = new BasicConfigDirPropsReader("test.props",CyProperty.SavePolicy.CONFIG_DIR); 
		assertEquals("marge", p.getProperties().getProperty("homer").toString() );
	}

	@Test
	public void testGetSavePolicy(){
		BasicConfigDirPropsReader p = new BasicConfigDirPropsReader("test.props",CyProperty.SavePolicy.CONFIG_DIR); 
		assertEquals(CyProperty.SavePolicy.CONFIG_DIR, p.getSavePolicy());
	}

	// no tests for reading config dir since we don't want to pollute the actual config dir with anything
}
