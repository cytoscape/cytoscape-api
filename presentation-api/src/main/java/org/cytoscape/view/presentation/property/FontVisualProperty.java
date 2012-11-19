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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.view.model.AbstractVisualProperty;
import org.cytoscape.view.model.DiscreteRange;
import org.cytoscape.view.model.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Visual Property for {@link Font} values.
 * 
 * @CyAPI.Final.Class
 */
public final class FontVisualProperty extends AbstractVisualProperty<Font> {
	
	private static final Logger logger = LoggerFactory.getLogger(FontVisualProperty.class);

	private static final Range<Font> FONT_RANGE;
	private static final int DEF_FONT_SIZE = 12;
	
	private static final Font DEFAULT_FONT = new Font("SansSerif", Font.PLAIN, DEF_FONT_SIZE);

	private static final Pattern CY2_FONT_PATTERN = Pattern.compile("(.+)-(\\d+)-(\\d+)");

	private static final Pattern FONT_PATTERN = Pattern.compile("(\\.[bB]old)?,[a-zA-Z]+,\\d+(\\.\\d+)?");
	private static final Pattern FONT_BOLD_PATTERN = Pattern.compile("(?i).*\\.bold,[a-zA-Z]+,.*");
	private static final Pattern FONT_SIZE_PATTERN = Pattern.compile(".+,[^,]+,");
	private static final String EMPTY_STRING = "";

	static {
		final Set<Font> fontSet = new HashSet<Font>();
		final Font[] allFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();

		for (Font f : allFonts)
			fontSet.add(f.deriveFont(DEF_FONT_SIZE));

		FONT_RANGE = new DiscreteRange<Font>(Font.class, fontSet) {
			// Takes any String as valid value.
			@Override
			public boolean inRange(Font value) {
				return true;
			}
		};
	}

	/**
	 * Constructor.
	 * @param def The default font value. 
	 * @param id A machine readable string identifying this visual property used for XML serialization. 
	 * @param displayName A human readable string used for displays and user interfaces. 
	 * @param modelDataType The model data type associated with this visual property, e.g. CyNode, CyEdge, or CyNetwork. 
	 */
	public FontVisualProperty(final Font def, final String id, final String displayName, final Class<? extends CyIdentifiable> modelDataType) {
		super(def, FONT_RANGE, id, displayName, modelDataType);
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
		if (text != null && text.trim().length() != 0) {
			final Matcher matcher = CY2_FONT_PATTERN.matcher(text);
			if (matcher.matches())
				return parseCy2Font(matcher.group(1), matcher.group(2), matcher.group(3));
			else
				return parseFont(text);
		} else
			return DEFAULT_FONT;
	}
	
	
	private final Font parseFont(final String text) {
		// e.g. "Monospaced,plain,12"
		final Matcher m1 = FONT_PATTERN.matcher(text);
		final String name = m1.replaceAll(EMPTY_STRING);

		final Matcher boldMatcher = FONT_BOLD_PATTERN.matcher(text);
		final boolean bold = boldMatcher.matches();
		final int style = bold ? Font.BOLD : Font.PLAIN;
		int size = DEF_FONT_SIZE;

		final Matcher sizeMatcher = FONT_SIZE_PATTERN.matcher(text);
		final String sSize = sizeMatcher.replaceAll(EMPTY_STRING);

		try {
			size = Integer.valueOf(sSize);
		} catch (NumberFormatException nfe) {
			logger.warn("Cannot parse font size in '" + text + "'", nfe);
		}

		return new Font(name, style, size);
	}
	
	private Font parseCy2Font(String name, String style, String size) {
		return new Font(name, Integer.parseInt(style), Integer.parseInt(size));
	}
}
