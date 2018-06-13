package org.cytoscape.util.swing;

import static javax.swing.GroupLayout.DEFAULT_SIZE;
import static javax.swing.GroupLayout.PREFERRED_SIZE;
import static javax.swing.GroupLayout.Alignment.CENTER;
import static javax.swing.GroupLayout.Alignment.LEADING;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolTip;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.plaf.ToolTipUI;

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

@SuppressWarnings("serial")
/**
 * Cytoscape's custom tool tip, used mostly by tool bar buttons, but of course it can be used by
 * any other UI component.
 */
public final class CyToolTip extends JToolTip {
	
	private final JComponent comp;
	private final String desc;
	private final String longDesc;
	private final Icon icon;

	/**
	 * @param comp the <code>JComponent</code> being described
	 * @param desc a short description
	 * @param longDesc an optional long description
	 * @param icon an optional icon (e.g. an animated GIF that illustrates the component's action)
	 */
	public CyToolTip(JComponent comp, String desc, String longDesc, Icon icon) {
		this.comp = comp;
		this.desc = desc;
		this.longDesc = longDesc;
		this.icon = icon;
		
		setComponent(comp);
		
		setUI(new ToolTipUI() {
		    @Override
		    public Dimension getMinimumSize(JComponent c) {
		        return c.getLayout().minimumLayoutSize(c);
		    }
		    @Override
		    public Dimension getPreferredSize(JComponent c) {
		        return c.getLayout().preferredLayoutSize(c);
		    }
		    @Override
		    public Dimension getMaximumSize(JComponent c) {
		        return getPreferredSize(c);
		    }
		});
		
		if (desc != null || longDesc != null || icon != null)
			init();
		
		if (longDesc != null || icon != null)
			increaseDismissDelay();
	}

	private void init() {
		final Color bg = UIManager.getColor("Table.foreground");
		final Color fg = UIManager.getColor("Table.background");
		
		final JPanel content = new JPanel();
		content.setBackground(bg);
		
		int w = icon != null ? icon.getIconWidth() : 0;
		
		final GroupLayout layout = new GroupLayout(content);
		content.setLayout(layout);
		layout.setAutoCreateContainerGaps(false);
		layout.setAutoCreateGaps(true);
		
		final ParallelGroup hGroup = layout.createParallelGroup(icon != null ? CENTER : LEADING, true);
		final SequentialGroup vGroup = layout.createSequentialGroup();
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGap(2)
				.addGroup(hGroup)
				.addGap(2)
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGap(icon != null ? 2 : 4)
				.addGroup(vGroup)
				.addGap(4)
		);
	
		if (icon != null) {
			if (icon.getIconWidth() > 20)
				w = icon.getIconWidth();
			
			JLabel lbl = new JLabel(icon);
			
			if (icon instanceof ImageIcon) // In case the image is animated
				((ImageIcon) icon).setImageObserver(lbl);
			
			addComponent(lbl, hGroup, vGroup, w);
		}
		
		if (desc != null) {
			JLabel lbl = new JLabel(desc);
			lbl.setFont(lbl.getFont().deriveFont(Font.BOLD));
			lbl.setForeground(fg);
			
			addComponent(lbl, hGroup, vGroup, w);
		}
		
		if (longDesc != null && !longDesc.equalsIgnoreCase(desc)) { // Don't show the same text twice!
			JTextArea ta = new JTextArea(longDesc);
			ta.setLineWrap(true);
			ta.setWrapStyleWord(true);
			ta.setEditable(false);
			ta.setForeground(fg);
			ta.setBackground(bg);
			
			JScrollPane sp = new JScrollPane(ta);
			// We cannot have scroll bars here, because it's a tool tip,
			// so users would not be able to interact with the bars.
			sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
			sp.setBackground(bg);
			sp.getViewport().setBackground(bg);
			sp.setBorder(BorderFactory.createEmptyBorder());
			
			addComponent(sp, hGroup, vGroup, w);
		}
		
		adjustSize(content);
		
		setLayout(new BorderLayout());
		add(content, BorderLayout.CENTER);
	}

	private void addComponent(JComponent c, ParallelGroup hGroup, SequentialGroup vGroup, int w) {
		if (w <= 0)
			hGroup.addComponent(c, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE);
		else
			hGroup.addComponent(c, w, w, w);
		
		vGroup.addComponent(c, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE);
	}
	
	private void increaseDismissDelay() {
		// Hack to prolong a tooltip’s visible delay
		// Thanks to: http://tech.chitgoks.com/2010/05/31/disable-tooltip-delay-in-java-swing/
		comp.addMouseListener(new MouseAdapter() {
		    final int defaultDismissTimeout = ToolTipManager.sharedInstance().getDismissDelay();
		    final int dismissDelayMinutes = (int) TimeUnit.MINUTES.toMillis(1); // 1 minute
		    
		    @Override
		    public void mouseEntered(final MouseEvent e) {
		        ToolTipManager.sharedInstance().setDismissDelay(dismissDelayMinutes);
		    }
		    @Override
		    public void mouseExited(final MouseEvent e) {
		        ToolTipManager.sharedInstance().setDismissDelay(defaultDismissTimeout);
		    }
		});
	}
	
	private void adjustSize(JPanel content) {
		// Here we use a window to pack the tooltip's content panel, so we can get the preferred size
		// of the text area used to display the long description, which can span multiple lines.
		Window window = new Window(null);
		window.add(content);
		window.pack();
		
		for (Component c : content.getComponents()) {
			if (c instanceof JScrollPane) {
				// Found the text area, need the viewport...
				JViewport viewport = ((JScrollPane) c).getViewport();
				// Adjust the size of the scroll pane with the viewport's size,
				// so the pane not hide the text -- Remember: NO scroll bars, must show everything!
				c.setMinimumSize(viewport.getPreferredSize());
				c.setPreferredSize(viewport.getPreferredSize());
			}
		}
		
		window.removeAll();
		window.dispose();
	}
}
