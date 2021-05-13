package org.cytoscape.util.color;

/*
 * #%L
 * Cytoscape Color Utils (utils-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2008 - 2021 The Cytoscape Consortium
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

import java.awt.Color;

/*
 * This utility class provides acess to a number of color conversion tools.
 */
public class ColorUtils {


	/**
	 * Return an array of colors of the requested length that is created by
	 * interpolating the colors provided in the input array.  Note that this
	 * routine assumes that the input array is smaller than the requested
	 * output.
	 * 
	 * @param input the input array of colors
	 * @param colorCount the size of the output array
	 * @return an array of colors of size colorCount
	 **/
	public Color[] interpolateColors(Color[] input, int colorCount) {
		Color[] colors = new Color[colorCount];
		int maxIndex = input.length-1;
		float scale = maxIndex/(float)(colorCount-1);

		if (colorCount <= input.length) {
			return input;
		}

		for (int i = 0; i < colorCount; i++) {
			float value = scale * i;
			int index = (int)Math.floor(value);

			Color c1 = input[index];
			float remainder = 0.0f;
			Color c2 = null;
			if (index+1 < input.length) {
				c2 = input[index+1];
				remainder = value - index;
			} else {
				c2 = input[index];
			}

			int red   = Math.round((1 - remainder) * c1.getRed()    + (remainder) * c2.getRed());
			int green = Math.round((1 - remainder) * c1.getGreen()  + (remainder) * c2.getGreen());
			int blue  = Math.round((1 - remainder) * c1.getBlue()   + (remainder) * c2.getBlue());

			colors[i] = new Color(red, green, blue);
		}
		return colors;
	}

}
