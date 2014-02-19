package org.cytoscape.util.swing;

/*
 * #%L
 * Cytoscape Swing Utility API (swing-util-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * This is an annoying re-implementation of JColorChooser.showDialog() that remembers
 * recently used colors between invocations of the chooser dialog.
 *
 * @CyAPI.Static.Class 
 * @CyAPI.InModule swing-util-api
 */
public final class CyColorChooser {
	
	private static JColorChooser chooser = new JColorChooser();
	private static ColorListener listener = new ColorListener();
	
	private static Color color;

	private CyColorChooser() {}

	/**
	 * Display custom color chooser dialog.
	 * 
	 * @param parent parent component of this dialog
	 * @param title Title of this dialog
	 * @param initialColor Initially selected color.
	 * 
	 * @return New Color or null if canceled.
	 */
	public static Color showDialog(final Component parent, final String title, final Color initialColor) {
		if (initialColor != null)
			chooser.setColor(initialColor);

		final JDialog dialog = JColorChooser.createDialog(parent, title, true, chooser, listener, null);
		dialog.setVisible(true);

		return color;
	}

	private static final class ColorListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			color = chooser.getColor();
		}
	}
}
