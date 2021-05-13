package org.cytoscape.view.vizmap;

/*
 * #%L
 * Cytoscape VizMap API (vizmap-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2021 The Cytoscape Consortium
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


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import org.cytoscape.view.model.CyNetworkView;
import org.junit.Test;

public abstract class AbstractVisualMappingManagerTest {

	protected VisualMappingManager vmm;

	@Test
	public void testVisualMappingManager() {
		assertNotNull(vmm);
	}

	@Test
	public void testGetDefaultVisualStyle() {
		assertNotNull(vmm.getDefaultVisualStyle());
	}
	
	@Test
	public void testGetAndSetVisualStyle() {
		final VisualStyle style1 = mock(VisualStyle.class);
		final VisualStyle style2 = mock(VisualStyle.class);
		final VisualStyle style3 = mock(VisualStyle.class);
		
		final CyNetworkView view1 = mock(CyNetworkView.class);
		final CyNetworkView view2 = mock(CyNetworkView.class);
		final CyNetworkView view3 = mock(CyNetworkView.class);
		
		assertNotNull(vmm.getAllVisualStyles());
		
		//Should contain default style.
		assertEquals(1, vmm.getAllVisualStyles().size());
		assertNotNull(vmm.getAllVisualStyles().iterator().next());
		assertNotNull(vmm.getDefaultVisualStyle());
		
		vmm.setVisualStyle(style1, view1);
		vmm.addVisualStyle(style2);
		vmm.setVisualStyle(style2, view2);
		final VisualStyle targetStyle = vmm.getVisualStyle(view3);
		
		assertEquals(vmm.getDefaultVisualStyle(), targetStyle);
		assertEquals(3, vmm.getAllVisualStyles().size());
		
		vmm.setVisualStyle(style3, view3);
		assertEquals(4, vmm.getAllVisualStyles().size());
		assertEquals(style3, vmm.getVisualStyle(view3));
	}

	@Test
	public void testAddAndRemoveVisualStyle() {
		int originalSize = vmm.getAllVisualStyles().size();
		
		final VisualStyle style1 = mock(VisualStyle.class);
		final VisualStyle style2 = mock(VisualStyle.class);
		
		vmm.addVisualStyle(style1);
		vmm.addVisualStyle(style2);
		assertEquals(originalSize + 2, vmm.getAllVisualStyles().size());
		vmm.removeVisualStyle(style2);
		assertEquals(originalSize + 1, vmm.getAllVisualStyles().size());
	}
	
	@Test
	public void testGetCurrentVisualStyle() {
		assertNotNull(vmm.getCurrentVisualStyle());
	}
	
	@Test
	public void testGetAndSetCurrentVisualStyle() {
		assertNotNull(vmm.getCurrentVisualStyle());
		assertEquals(vmm.getDefaultVisualStyle(), vmm.getCurrentVisualStyle());
		
		VisualStyle style = mock(VisualStyle.class);
		vmm.setCurrentVisualStyle(style);
		assertEquals(style, vmm.getCurrentVisualStyle());
		
		// Setting null current style
		vmm.setCurrentVisualStyle(null);
		assertEquals(vmm.getDefaultVisualStyle(), vmm.getCurrentVisualStyle());
	}
}
