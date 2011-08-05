package org.cytoscape.view.presentation;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.print.Printable;
import java.util.Properties;

import javax.swing.Icon;

import org.cytoscape.view.model.View;
import org.cytoscape.view.model.VisualLexicon;
import org.cytoscape.view.model.VisualProperty;

/**
 * RenderingEngine is an interface for all visualizers which renders. For a given view-model
 * it renders actual view on display, documents, etc.
 * 
 * @param <T>
 *            source data object to be visualized. For now we have only one
 *            implementation for CyNetwork, but it can be anything, including
 *            CyTable.
 */
public interface RenderingEngine<T> {

	/**
	 * Returns {@linkplain View} being rendered.
	 * 
	 * @return view model.  This is an immutable object.
	 */
	public View<T> getViewModel();

	
	/**
	 * Provide all compatible Visual Properties as a {@linkplain VisualLexicon}.
	 * 
	 * @return Visual Lexicon of this rendering engine.
	 */
	public VisualLexicon getVisualLexicon();
	

	/**
	 * Get property values for the rendering engine, like LOD.
	 * Users can set each property value by getting this {@linkplain Properties} object.
	 * <p>
	 * {@linkplain Properties} object itself is immutable.
	 * 
	 * @return property values.
	 */
	public Properties getProperties();
	

	/**
	 * For export image function.
	 * 
	 * @return A Printable object suitable for submission to a printer.
	 */
	public Printable createPrintable();
	

	/**
	 * Render an {@linkplain Image} object from current visualization.
	 * 
	 * @param width width of the image
	 * @param height height of the image
	 * 
	 * @return Image object created from current window.
	 */
	public Image createImage(final int width, final int height);
	

	/**
	 * Create {@link Icon} object for the given VisualProperty value.
	 * 
	 * @param <V> Data type, such as Color, String, Double, etc.
	 * 
	 * @param vp VisualProperty to be rendered as Icon.
	 * @param value Value for the Icon.  For example, if V is shape, this can be redtangle, triangle, and so on.
	 * @param width width of the icon
	 * @param height height of the icon
	 * 
	 * @return Icon rendered by this engine.
	 */
	public <V> Icon createIcon(final VisualProperty<V> vp, final V value, final int width, final int height);

	/**
	 * Render presentation on the given Java 2D Canvas.
	 * 
	 * @param printCanvas Graphics object provided by vector
	 */
	void printCanvas(final Graphics printCanvas);
	
}
