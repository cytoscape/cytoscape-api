package org.cytoscape.application.swing;

import static org.junit.Assert.*;

import javax.swing.KeyStroke;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AcceleratorParserTest {

	@Test
	public void testParse() {
		final KeyStroke shortCut1 = AcceleratorParser.parse("cmd s");
		
		assertNotNull(shortCut1);
		//assertEquals("aaa", shortCut1.toString());
	}

}
