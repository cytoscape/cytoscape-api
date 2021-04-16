package org.cytoscape.util.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.JButton;

/*
 * #%L
 * Cytoscape Swing Utility API (swing-util-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2018 The Cytoscape Consortium
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

import org.cytoscape.service.util.CyServiceRegistrar;
import org.cytoscape.util.color.Palette;
import org.cytoscape.util.color.PaletteType;

/**
 * JButton that opens a Color Chooser when clicked and shows the previously set color as an icon.
 * You can use the <code>addPropertyChangeListener</code> method to listen for a {@link java.beans.PropertyChangeEvent}
 * (for the property "color") whenever a new color is selected by the user.
 *
 * @CyAPI.Final.Class 
 * @CyAPI.InModule swing-util-api
 */
public final class ColorButton extends JButton {

	private static final long serialVersionUID = -580523607515901450L;

	private Color color;
	private Color borderColor;
  private Palette palette;
  private CyColorPaletteChooserFactory chooserFactory;

  /**
   * The basic constructor for a color button without palettes.  The <b>color</b> argument
   * is the initial color.
   *
   * @param color
   */
	public ColorButton(final Color color) {
		super(" ");
    init(color);
		addActionListener(evt -> {
			// Open color chooser
			final Color c = CyColorChooser.showDialog(ColorButton.this, "Colors", this.color);
			ColorButton.this.setColor(c);
		});
  }

  /**
   * The basic constructor for a color button with palettes.  The <b>color</b> argument
   * is the initial color, and the <b>palette</b> argument is the initial palette.  Because
   * palettes are dynamic, the caller must provide the CyServiceRegistrar service
   * and pass that to this constructor.
   *
   * @param serviceRegistrar the CyServiceRegistrar -- used to get all of the currently available palettes
   * @param palette the initial palette
   * @param paletteType the types of palettes to use
   * @param color the initial color
   * @param paletteOnly if true, only allow palette selection, not color selection
   */
	public ColorButton(final CyServiceRegistrar serviceRegistrar, final Palette palette, PaletteType type, final Color color, 
                     boolean paletteOnly) {
    super(" ");
    init(color);
    setPalette(palette);

		addActionListener(evt -> {
			// Open color chooser
      int size = 8;
      if (palette != null)
        size = palette.size();

      CyColorPaletteChooserFactory chooserFactory = serviceRegistrar.getService(CyColorPaletteChooserFactory.class);
      CyColorPaletteChooser chooser = chooserFactory.getColorPaletteChooser(type, paletteOnly);
      String title = "Colors";
      if (paletteOnly)
        title = "Palettes";
      final Color c = chooser.showDialog(ColorButton.this, title, palette, this.color, size);
      ColorButton.this.setColor(c);
		});
  }

		
  private void init(final Color color) {
		if (LookAndFeelUtil.isAquaLAF())
			putClientProperty("JButton.buttonType", "gradient");
		
		setHorizontalTextPosition(JButton.CENTER);
		setVerticalTextPosition(JButton.CENTER);
		borderColor = getContrastingColor(getBackground());
		setIcon(new ColorIcon());
		setColor(color);
	}

	/**
	 * Sets a new color and fires a {@link java.beans.PropertyChangeEvent} for the property "color".
	 * @param color
	 */
	public void setColor(final Color color) {
		final Color oldColor = this.color;
		this.color = color;
		repaint();
		firePropertyChange("color", oldColor, color);
	}

	/**
	 * Sets a new color Palette and fires a {@link java.beans.PropertyChangeEvent} for the property "palette".
	 * @param palette
	 */
	public void setPalette(final Palette palette) {
		final Palette oldPalette = this.palette;
		this.palette = palette;
		repaint();
		firePropertyChange("palette", oldPalette, palette);
	}
	
	/**
	 * @return The currently selected color.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @return The currently selected palette (if any).
	 */
	public Palette getPalette() {
		return palette;
	}
	
	private static Color getContrastingColor(final Color color) {
		int d = 0;
		// Counting the perceptive luminance - human eye favors green color...
		final double a = 1 - (0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue()) / 255;

		if (a < 0.5)
			d = 0; // bright colors - black font
		else
			d = 255; // dark colors - white font

		return new Color(d, d, d);
	}
	
	private class ColorIcon implements Icon {

		@Override
		public int getIconHeight() {
			return 16;
		}

		@Override
		public int getIconWidth() {
			return 44;
		}

		@Override
		public void paintIcon(Component c, Graphics g, int x, int y) {
			int w = getIconWidth();
			int h = getIconHeight();
			
			g.setColor(color);
			g.fillRect(x, y, w, h);
			g.setColor(borderColor);
			g.drawRect(x, y, w, h);
		}
	}
}
