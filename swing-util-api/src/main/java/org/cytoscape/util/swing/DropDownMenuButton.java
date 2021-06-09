package org.cytoscape.util.swing;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;

/*
 * #%L
 * Cytoscape Swing Utility API (swing-util-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2021 The Cytoscape Consortium
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
 * Button with drop down menu.
 * 
 * @CyAPI.InModule swing-util-api
 */
@SuppressWarnings("serial")
public class DropDownMenuButton extends JButton {

	private final static int TEXT_ARROW_GAP = 6;
	private final Icon buttonIcon = new MenuArrowIcon();
	
	private ActionListener popupMenuActionListener;

	
	/**
	 * Creates a new DropDownMenuButton object.
	 * @param action  The action that this button represents.
	 */
	public DropDownMenuButton(AbstractAction action) {
		this();
		setAction(action);
	}
	
	public DropDownMenuButton(JPopupMenu popupMenu) {
		this();
		setPopupMenu(popupMenu);
	}
	
	public DropDownMenuButton() {
		setFocusPainted(false);
		setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4 + buttonIcon.getIconWidth()));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		var dim = getSize();
		var ins = getInsets();
		int x = dim.width - buttonIcon.getIconWidth() - ins.right;
		int y = ins.top + ((dim.height - ins.top - ins.bottom - buttonIcon.getIconHeight()) / 2);
		buttonIcon.paintIcon(this, g, x, y);
	}

	public void setPopupMenu(JPopupMenu popupMenu) {
		if (popupMenuActionListener != null)
			removeActionListener(popupMenuActionListener);

		if (popupMenu != null) {
			popupMenuActionListener = e -> {
				popupMenu.show(DropDownMenuButton.this, 0, getHeight());
				popupMenu.requestFocusInWindow();
			};
			addActionListener(popupMenuActionListener);
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		var d = super.getPreferredSize();
		d.width += buttonIcon.getIconWidth() + TEXT_ARROW_GAP;
		
		return d;
	}
	
	private static final class MenuArrowIcon implements Icon {
		
		@Override
		public void paintIcon(Component c, Graphics g, int x, int y) {
			var g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(UIManager.getColor(c.isEnabled() ? "Button.foreground" : "Button.disabledForeground"));

			var w = getIconWidth();
			var h = getIconHeight();
			var pad = 2;
			
			g2.translate(x, y);
			g2.fillPolygon(new int[]{ pad, w - pad, w / 2 }, new int[]{ pad + 1, pad + 1, h - pad }, 3); // downward triangle
			
			g2.dispose();
		}

		@Override
		public int getIconWidth() {
			return 10;
		}

		@Override
		public int getIconHeight() {
			return 10;
		}
	}
}
