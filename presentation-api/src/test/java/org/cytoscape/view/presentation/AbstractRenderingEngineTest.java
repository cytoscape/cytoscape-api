package org.cytoscape.view.presentation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


import java.awt.Image;

import javax.swing.JPanel;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.view.model.View;
import org.cytoscape.view.model.VisualLexicon;
import org.junit.Before;
import org.junit.Test;

/**
 * Basic test case for rendering engine and its factory.
 * 
 * @author kono
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
	public void testFactory() {
		
		assertNotNull(engine);

		View<CyNetwork> viewModel = engine.getViewModel();
		assertEquals(viewModel, networkView);
		
		final VisualLexicon lexicon = engine.getVisualLexicon();
		assertNotNull(lexicon);
		assertEquals(numberOfVP, lexicon.getAllVisualProperties().size());
		
		Image img = engine.createImage(400, 400);
		assertNotNull(img);
		assertEquals(400, img.getHeight(null));
		assertEquals(400, img.getWidth(null));
	}
	
	@Test
	public void testSetPropertiesTrue() {
		
		engine.setProperties("exportTextAsShape", "true");
		assertEquals(true, Boolean.parseBoolean(engine.getProperties().get("exportTextAsShape").toString()));
		
	}
	
	@Test
	public void testSetPropertiesFalse() {
		
		engine.setProperties("exportTextAsShape", "false");
		assertEquals(false, Boolean.parseBoolean(engine.getProperties().get("exportTextAsShape").toString()));
		
	}
	
	@Test
	public void testSetPropertiesWrongInput() {
		
		engine.setProperties("exportTextAsShape", "wrongInput");
		assertEquals(false, Boolean.parseBoolean(engine.getProperties().get("exportTextAsShape").toString())); 
	
	}
	
	@Test(expected=NullPointerException.class)
	public void testSetPropertiesNullException() {
		
		engine.setProperties("exportTextAsShape", null);
	}
	
	
}
