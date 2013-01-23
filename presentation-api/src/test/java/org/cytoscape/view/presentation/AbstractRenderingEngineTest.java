package org.cytoscape.view.presentation;

/*
 * #%L
 * Cytoscape Presentation API (presentation-api)
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.Image;
import java.awt.print.Printable;

import javax.swing.JPanel;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.view.model.View;
import org.cytoscape.view.model.VisualLexicon;
import org.junit.Before;
import org.junit.Test;

/**
 * Basic test case for rendering engine and its factory.
 *
 */
public abstract class AbstractRenderingEngineTest {

	protected RenderingEngineFactory<CyNetwork> factory;
	protected View<CyNetwork> networkView;
	
	protected int numberOfVP;
	
	protected RenderingEngine<CyNetwork> engine ;

	@Before
	public void setup(){
		final JPanel panel = new JPanel();
		engine = factory.createRenderingEngine(panel, networkView);
	}
	
	@Test
	public void testCreateImage() {
		assertNotNull(engine);
		
		Image img = engine.createImage(400, 400);
		assertNotNull(img);
		assertEquals(400, img.getHeight(null));
		assertEquals(400, img.getWidth(null));
	}
	
	@Test
	public void testCreatePribtable() {
		assertNotNull(engine);
		final Printable printable = engine.createPrintable();
		assertNotNull(printable);
	}
	
	@Test
	public void testModel() {
		View<CyNetwork> viewModel = engine.getViewModel();
		assertEquals(viewModel, networkView);
	}
	
	@Test
	public void testLexicon() throws Exception {
		final VisualLexicon lexicon = engine.getVisualLexicon();
		assertNotNull(lexicon);
		assertEquals(numberOfVP, lexicon.getAllVisualProperties().size());
	}
	
	@Test
	public void testSetPropertiesTrue() {
		engine.getProperties().setProperty("exportTextAsShape", "true");
		assertEquals(true, Boolean.parseBoolean(engine.getProperties().get("exportTextAsShape").toString()));
	}
	
	@Test
	public void testSetPropertiesFalse() {
		engine.getProperties().setProperty("exportTextAsShape", "false");
		assertEquals(false, Boolean.parseBoolean(engine.getProperties().get("exportTextAsShape").toString()));
		
	}
	
	@Test
	public void testSetPropertiesWrongInput() {
		engine.getProperties().setProperty("exportTextAsShape", "wrongInput");
		assertEquals(false, Boolean.parseBoolean(engine.getProperties().get("exportTextAsShape").toString())); 
	}
	
	@Test(expected=NullPointerException.class)
	public void testSetPropertiesNullException() {
		engine.getProperties().setProperty("exportTextAsShape", null);
	}
}
