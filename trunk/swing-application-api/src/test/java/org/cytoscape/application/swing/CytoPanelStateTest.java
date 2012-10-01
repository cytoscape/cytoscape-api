package org.cytoscape.application.swing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CytoPanelStateTest {

	@Test
	public void test() {
		assertEquals(3, CytoPanelState.values().length);

		assertEquals(CytoPanelState.valueOf("DOCK"), CytoPanelState.DOCK);
		assertEquals(CytoPanelState.valueOf("FLOAT"), CytoPanelState.FLOAT);
		assertEquals(CytoPanelState.valueOf("HIDE"), CytoPanelState.HIDE);
	}
}
