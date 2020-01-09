package org.cytoscape.util.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.UIManager;

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

/**
 * Renders any text/font as an icon.
 */
public class TextIcon implements Icon {
	
	private final Color TRANSPARENT_COLOR = new Color(255, 255, 255, 0);
	
	private final String[] texts;
	private final Font[] fonts;
	private final Color[] colors;
	private final int width;
	private final int height;
	
	private Set<Integer> disabledLayers = new HashSet<>();
	
	public TextIcon(String text, Font font, Color color, int width, int height) {
		this.texts = new String[] { text };
		this.fonts = new Font[] { font };
		this.colors = new Color[] { color };
		this.width = width;
		this.height = height;
	}
	
	/**
	 * The icon color is the target component's foreground.
	 */
	public TextIcon(String text, Font font, int width, int height) {
		this(text, font, null, width, height);
	}
	
	public TextIcon(String[] texts, Font font, Color[] colors, int width, int height) {
		this(texts, new Font[] { font }, colors, width, height);
	}
	
	/**
	 * The icon color is the target component's foreground.
	 */
	public TextIcon(String[] texts, Font font, int width, int height) {
		this(texts, new Font[] { font }, null, width, height);
	}
	
	/**
	 * 
	 * @param texts
	 * @param font
	 * @param colors
	 * @param width
	 * @param height
	 * @param disabledLayers The indexes of the layers that must be transparent when the target component is disabled.
	 */
	public TextIcon(String[] texts, Font font, Color[] colors, int width, int height, Integer... disabledLayers) {
		this(texts, new Font[] { font }, colors, width, height, disabledLayers);
	}
	
	public TextIcon(String[] texts, Font[] fonts, Color[] colors, int width, int height) {
		this(texts, fonts, colors, width, height, (Integer[]) null);
	}
	
	/**
	 * The icon color is the target component's foreground.
	 */
	public TextIcon(String[] texts, Font[] fonts, int width, int height) {
		this(texts, fonts, null, width, height, (Integer[]) null);
	}
	
	/**
	 * @param texts
	 * @param fonts
	 * @param colors
	 * @param width
	 * @param height
	 * @param disabledLayers The indexes of the layers that must be transparent when the target component is disabled.
	 */
	public TextIcon(String[] texts, Font[] fonts, Color[] colors, int width, int height, Integer... disabledLayers) {
		this.texts = texts;
		this.fonts = fonts;
		this.colors = colors;
		this.width = width;
		this.height = height;
		
		if (disabledLayers != null)
			this.disabledLayers.addAll(Arrays.asList(disabledLayers));
	}
	
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		// Draw to an upscaled image first, to prevent the misaligned icon layers that happens
		// when drawing directly on the component's Graphics on Java 11 and low density monitors
        var scale = 2;
        var img = createImage(c, scale);
        
        // Now draw the image to the component (it has to be rescaled back to the original size)
        var g2d = (Graphics2D) g.create();
		g2d.setRenderingHints(
				new RenderingHints(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR));
        
		g2d.scale(1.0 / scale, 1.0 / scale);
        g2d.translate(x, y);
        g2d.drawImage(img, x, y, c);
        
        g2d.dispose();
	}
	
	@Override
	public int getIconWidth() {
		return width;
	}

	@Override
	public int getIconHeight() {
		return height;
	}
	
	private BufferedImage createImage(Component c, int scale) {
		var w = width * scale;
		var h = height * scale;
		var img = new BufferedImage(w, h * scale, BufferedImage.TYPE_INT_ARGB);
		
		var g2d = img.createGraphics();
		g2d.setRenderingHints(
				new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON));
        
        g2d.setPaint(TRANSPARENT_COLOR);
        g2d.fillRect(0, 0, w, h);
        
		if (texts != null && fonts != null) {
			Font f = null;
			Color fg = null;

			for (int i = 0; i < texts.length; i++) {
				if (c != null && !c.isEnabled() && disabledLayers.contains(i))
					continue;
				
				String txt = texts[i];

				if (fonts.length > i)
					f = fonts[i];
				else if (fonts.length > 0)
					f = fonts[0];

				if (txt == null || f == null)
					continue;

				f = f.deriveFont(f.getSize2D() * scale);
				
				if (colors != null && colors.length > i)
					fg = colors[i];

				if (fg == null)
					fg = c != null ? c.getForeground() : UIManager.getColor("Label.foreground");

				if (c instanceof AbstractButton) {
					if (!c.isEnabled())
						fg = UIManager.getColor("Label.disabledForeground");
					else if (((AbstractButton) c).getModel().isPressed())
						fg = fg.darker();
				}

				g2d.setPaint(fg);
				g2d.setFont(f);
				drawText(txt, f, g2d, 0, 0, scale);
			}
        }
        
        return img;
	}
	
	private void drawText(String text, Font font, Graphics g, int x, int y, int scale) {
		// IMPORTANT:
		// For height and ascent, we now use LineMetrics, because the values returned by FontMetrics
		// have changed in Java 11 (maybe since Java 9?), and are just incorrect
		// (e.g. FontMetrics.getHeight() and FontMetrics.getAscent() always return 0 for our custom font icons)
		FontMetrics fm = g.getFontMetrics(font);
		Rectangle2D rect = fm.getStringBounds(text, g);
		LineMetrics lm = fm.getLineMetrics(text, g);

		int textHeight = (int) lm.getHeight();
		int textWidth = (int) rect.getWidth();

		// Center text horizontally and vertically
		var w = width * scale;
		var h = height * scale;
		int xx = x + Math.round((w - textWidth) / 2.0f);
		int yy = y + Math.round(((h - textHeight) / 2.0f) + lm.getAscent());
		
		g.drawString(text, xx, yy);
	}
}
