package org.cytoscape.app.swing;

/*
 * #%L
 * Cytoscape Swing App API (swing-app-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */



import org.junit.Test;
import static org.junit.Assert.*;


public abstract class CySwingAppAdapterTest {
	protected CySwingAppAdapter adapter;

	@Test
	public void testGetCySwingApplication() { 
		assertNotNull("CySwingApplication exists", adapter.getCySwingApplication());
	} 

	@Test
	public void testDialogTaskManager() { 
		assertNotNull("DialogTaskManager exists", adapter.getDialogTaskManager());
	} 

	@Test
	public void testPanelTaskManager() { 
		assertNotNull("PanelTaskManager exists", adapter.getPanelTaskManager());
	} 

}
