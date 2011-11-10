/*
 Copyright (c) 2008, The Cytoscape Consortium (www.cytoscape.org)

 The Cytoscape Consortium is:
 - Institute for Systems Biology
 - University of California San Diego
 - Memorial Sloan-Kettering Cancer Center
 - Institut Pasteur
 - Agilent Technologies

 This library is free software; you can redistribute it and/or modify it
 under the terms of the GNU Lesser General Public License as published
 by the Free Software Foundation; either version 2.1 of the License, or
 any later version.

 This library is distributed in the hope that it will be useful, but
 WITHOUT ANY WARRANTY, WITHOUT EVEN THE IMPLIED WARRANTY OF
 MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.  The software and
 documentation provided hereunder is on an "as is" basis, and the
 Institute for Systems Biology and the Whitehead Institute
 have no obligations to provide maintenance, support,
 updates, enhancements or modifications.  In no event shall the
 Institute for Systems Biology and the Whitehead Institute
 be liable to any party for direct, indirect, special,
 incidental or consequential damages, including lost profits, arising
 out of the use of this software and its documentation, even if the
 Institute for Systems Biology and the Whitehead Institute
 have been advised of the possibility of such damage.  See
 the GNU Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation,
 Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
 */
package org.cytoscape.view.presentation.property;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.HashSet;
import java.util.Set;

import org.cytoscape.view.model.AbstractVisualProperty;
import org.cytoscape.view.model.DiscreteRange;
import org.cytoscape.view.model.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * #ASKMIKE needs class comment, construct comment
 * @CyAPI.Final.Class
 */
public class FontVisualProperty extends AbstractVisualProperty<Font> {

	private static final Range<Font> FONT_RANGE;
	private static final int DEF_FONT_SIZE = 12;
	
	private static final Logger logger = LoggerFactory.getLogger(FontVisualProperty.class);

	static {
		final Set<Font> fontSet = new HashSet<Font>();
		final Font[] allFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();

		for(Font f: allFonts)
			fontSet.add(f.deriveFont(DEF_FONT_SIZE));
		
		FONT_RANGE = new DiscreteRange<Font>(Font.class, fontSet) {
			// Takes any String as valid value.
			@Override public boolean inRange(Font value) {
				return true;
			}
		};
	}

	public FontVisualProperty(final Font def, final String id,
			final String name, final Class<?> targetDataType) {
		super(def, FONT_RANGE, id, name, targetDataType);
	}

	@Override
	public String toSerializableString(final Font value) {
		// e.g.: "SanSerif,bold,10"
		String name = value.getFontName();
		String weight = value.isBold() ? "bold" : "plain";
		int size = value.getSize();
		
		return name + "," + weight + "," + size;
	}

	@Override
	public Font parseSerializableString(final String text) {
		Font font = null;
		
		if (text != null) {
			// e.g. "Monospaced,plain,12"
            String name = text.replaceAll("(\\.[bB]old)?,[a-zA-Z]+,\\d+(\\.\\d+)?", "");

            boolean bold = text.matches("(?i).*\\.bold,[a-zA-Z]+,.*");
            int style = bold ? Font.BOLD : Font.PLAIN;
            int size = 12;

            String sSize = text.replaceAll(".+,[^,]+,", "");
            
            try {
                size = Integer.parseInt(sSize);
            } catch (NumberFormatException nfe) {
                logger.warn("Cannot parse font size in '" + text +"'", nfe);
            }

            font = new Font(name, style, size);
        }
		
		return font;
	}

	private static Set<Font> getSystemFonts() {
		//TODO: implement this.
		final Set<Font> fontSet = new HashSet<Font>();
		return fontSet;
	}
}
