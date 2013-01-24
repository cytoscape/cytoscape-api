
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

import java.awt.Color;
import java.awt.Paint;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.view.model.AbstractVisualProperty;
import org.cytoscape.view.model.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  Visual Property for {@link Paint} values.
 *  Usually, this will be used for {@link Color}.
 *  
 * @CyAPI.Final.Class
 * @CyAPI.InModule presentation-api
 */
public final class PaintVisualProperty extends AbstractVisualProperty<Paint> { 

	private static final Map<String, String> COLOR_MAP = new HashMap<String, String>();
	private static final String COLOR_CODE_RESOURCE = "cross_browser_color_code.txt";
	
	private static final Logger logger = LoggerFactory.getLogger(PaintVisualProperty.class);

	static {
		buildColorCodeTable(PaintVisualProperty.class.getClassLoader().getResource(COLOR_CODE_RESOURCE));
	}

	/**
	 * Constructor.
	 * @param def The default paint value.
	 * @param id A machine readable string identifying this visual property used for XML serialization. 
	 * @param displayName A human readable string used for displays and user interfaces. 
	 * @param modelDataType The model data type associated with this visual property, e.g. CyNode, CyEdge, or CyNetwork. 
	 */
	public PaintVisualProperty(final Paint def, final Range<Paint> range, final String id, final String displayName, final Class<? extends CyIdentifiable> modelDataType) {
		super(def, range, id, displayName, modelDataType);
	}
	
	
	@Override 
	public String toSerializableString(final Paint paint) {
		
		final Color color;
		if (paint instanceof Color == false) {
			logger.warn("None-Color object found.  Currently, this implementation supports only Color object.");
			color = Color.white;
		} else {
			color = (Color) paint;
		}
		
		String hex = Integer.toHexString(color.getRGB());
		hex = hex.substring(2, hex.length()); // remove alpha bits

		return "#" + hex;
	}

	
	@Override 
	public Paint parseSerializableString(String text) {
		if (text == null) 
			return null;
		
		text = text.trim().toUpperCase();
		text = COLOR_MAP.containsKey(text) ? COLOR_MAP.get(text) : text;
		
		// Start by seeing if this is a hex representation
		if (text.startsWith("#")) {
			try {
				return Color.decode(text);
			} catch (NumberFormatException e) {
				logger.error("Invalid hex RGB format: " + text, e);
				return null;
			}
		}

		// could be an RGB color, such as "rgb(255,0,255)"
		final String s = text.replaceAll("(?i)rgb *\\(", "").replaceAll("\\)", "");
		
		// ok, this must be 3 comma separated integers now
		final StringTokenizer strtok = new StringTokenizer(s, ",");

		if (strtok.countTokens() != 3) {
			logger.error("Not all RGB integers specified: " + text);
			return null;
		}

		final String red = strtok.nextToken().trim();
		final String green = strtok.nextToken().trim();
		final String blue = strtok.nextToken().trim();

		try {
			int r = Integer.parseInt(red);
			int g = Integer.parseInt(green);
			int b = Integer.parseInt(blue);

			return new Color(r, g, b);
		} catch (NumberFormatException e) {
			logger.error("Invalid hex RGB format: " + text, e);
			return null;
		}
	}
	
	private static void buildColorCodeTable(final URL resourceURL) {
		BufferedReader bufRd = null;
		String line;

		try {
			bufRd = new BufferedReader(new InputStreamReader(resourceURL.openStream()));
			while ((line = bufRd.readLine()) != null) {
				String[] parts = line.split("\\t");
				COLOR_MAP.put(parts[0].trim().toUpperCase(), parts[1].trim());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufRd != null) {
				try {
					bufRd.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					bufRd = null;
				}
			}
		}
	}
}
