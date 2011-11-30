package org.cytoscape.view.presentation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.Image;

import javax.swing.JPanel;

import org.cytoscape.model.CyNetwork;
import org.cytoscape.view.model.View;
import org.cytoscape.view.model.VisualLexicon;
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

	@Test
	public void testFactory() {

		final JPanel panel = new JPanel();
		RenderingEngine<CyNetwork> engine = factory.createRenderingEngine(panel, networkView);

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
}
