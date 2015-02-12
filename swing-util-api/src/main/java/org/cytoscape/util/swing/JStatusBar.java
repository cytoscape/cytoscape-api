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

import static javax.swing.GroupLayout.DEFAULT_SIZE;
import static javax.swing.GroupLayout.Alignment.LEADING;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Simple status bar with 3 fields.
 * 
 * @CyAPI.Final.Class 
 * @CyAPI.InModule swing-util-api
 */
public final class JStatusBar extends JPanel {
	
	private final static long serialVersionUID = 1202339875133611L;
	
	private static final float FONT_SIZE = 11.0f;

	private JLabel leftLabel;
	private JLabel centerLabel;
	private JLabel rightLabel;
	private JPanel leftPanel;
	private JPanel centerPanel;
	private JPanel rightPanel;
	
	/**
	 * Creates a new JStatusBar object.
	 */
	public JStatusBar() {
		initComponents();
	}

	/**
	 * Set the left label of this JStatusBar.
	 * 
	 * @param text The String to set the left label to.
	 */
	public void setLeftLabel(final String text) {
		leftLabel.setText(text);
	}

	/**
	 * Set the center label of this JStatusBar.
	 * 
	 * @param text The String to set the center label to.
	 */
	public void setCenterLabel(final String text) {
		centerLabel.setText(text);
	}

	/**
	 * Set the right label of this JStatusBar.
	 * 
	 * @param text The String to set the right label to.
	 */
	public void setRightLabel(final String text) {
		rightLabel.setText(text);
	}

	private void initComponents() {
		leftPanel = new JPanel();
		leftLabel = new JLabel();
		centerPanel = new JPanel();
		centerLabel = new JLabel();
		rightPanel = new JPanel();
		rightLabel = new JLabel();

		leftPanel.setBorder(LookAndFeelUtil.createPanelBorder());
		leftLabel.setFont(leftLabel.getFont().deriveFont(FONT_SIZE));
		
		centerPanel.setBorder(LookAndFeelUtil.createPanelBorder());
		centerLabel.setFont(centerLabel.getFont().deriveFont(FONT_SIZE));
		
		rightPanel.setBorder(LookAndFeelUtil.createPanelBorder());
		rightLabel.setFont(rightLabel.getFont().deriveFont(FONT_SIZE));

		final GroupLayout layout1 = new GroupLayout(leftPanel);
		leftPanel.setLayout(layout1);
		
		layout1.setHorizontalGroup(layout1.createSequentialGroup()
				.addComponent(leftLabel, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
		);
		layout1.setVerticalGroup(layout1.createSequentialGroup()
				.addComponent(leftLabel)
		);

		final GroupLayout layout2 = new GroupLayout(centerPanel);
		centerPanel.setLayout(layout2);
		
		layout2.setHorizontalGroup(layout2.createSequentialGroup()
				.addComponent(centerLabel, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
		);
		layout2.setVerticalGroup(layout2.createSequentialGroup()
				.addComponent(centerLabel)
		);

		final GroupLayout layout3 = new GroupLayout(rightPanel);
		rightPanel.setLayout(layout3);
		
		layout3.setHorizontalGroup(layout3.createSequentialGroup()
				.addComponent(rightLabel, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
		);
		layout3.setVerticalGroup(layout3.createSequentialGroup()
				.addComponent(rightLabel)
		);

		final GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addComponent(leftPanel, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(centerPanel, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(rightPanel, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
		);
		layout.setVerticalGroup(layout.createParallelGroup(LEADING)
				.addComponent(leftPanel, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(centerPanel, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(rightPanel, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
		);
	}
}
