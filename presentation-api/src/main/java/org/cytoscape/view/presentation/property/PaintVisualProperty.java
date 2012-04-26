
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
import java.util.StringTokenizer;

import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.view.model.AbstractVisualProperty;
import org.cytoscape.view.model.Range;

/**
 *  Visual Property for {@link Paint} values.
 *  Usually, this will be used for {@link Color}.
 *  
 * @CyAPI.Final.Class
 */
public final class PaintVisualProperty extends AbstractVisualProperty<Paint> { 

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
		if (paint instanceof Color == false)
			throw new UnsupportedOperationException("Currently, this implementation supports only Color object.");
		
		final Color color = (Color) paint;
		
		String hex = Integer.toHexString(color.getRGB());
		hex = hex.substring(2, hex.length()); // remove alpha bits

		return "#" + hex;
	}

	
	@Override 
	public Paint parseSerializableString(final String text) {
		if (text == null) 
			throw new IllegalArgumentException("invalid color format: null");
		
		// Start by seeing if this is a hex representation
		if (text.startsWith("#")) {
			try {
				return Color.decode(text);
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("invalid hex RGB format");	
			}
		}

		// could be an RGB color, such as "rgb(255,0,255)"
		String s = text.replaceAll("(?i)rgb *\\(", "").replaceAll("\\)", "");
		
		// ok, this must be 3 comma separated integers now
		StringTokenizer strtok = new StringTokenizer(s, ",");

		if (strtok.countTokens() != 3) 
			throw new IllegalArgumentException("not all RGB integers specified");	

		String red = strtok.nextToken().trim();
		String green = strtok.nextToken().trim();
		String blue = strtok.nextToken().trim();

		try {
			int r = Integer.parseInt(red);
			int g = Integer.parseInt(green);
			int b = Integer.parseInt(blue);

			return new Color(r, g, b);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("invalid RGB format");	
		}
	}
}
