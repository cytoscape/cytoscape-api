/*
 Copyright (c) 2006, 2007, The Cytoscape Consortium (www.cytoscape.org)

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
package org.cytoscape.util.swing;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

/**
 * Simple status bar with 3 fields.
 * 
 * @CyAPI.Final.Class 
 */
public final class JStatusBar extends JPanel {
	
	private final static long serialVersionUID = 1202339875133611L;

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
		leftPanel = new javax.swing.JPanel();
		leftLabel = new javax.swing.JLabel();
		centerPanel = new javax.swing.JPanel();
		centerLabel = new javax.swing.JLabel();
		rightPanel = new javax.swing.JPanel();
		rightLabel = new javax.swing.JLabel();

		leftPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		leftPanel.setPreferredSize(new Dimension(40, 40));
		leftLabel.setFont(new Font("Sans-Serif", Font.PLAIN, 10));
		leftLabel.setPreferredSize(new Dimension(20, 20));

		GroupLayout jPanel1Layout = new GroupLayout(leftPanel);
		leftPanel.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				GroupLayout.Alignment.TRAILING,
				jPanel1Layout.createSequentialGroup().addContainerGap()
						.addComponent(leftLabel, GroupLayout.DEFAULT_SIZE, 300,
								Short.MAX_VALUE).addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout.createSequentialGroup().addComponent(leftLabel)
						.addContainerGap(2, Short.MAX_VALUE)));

		centerPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		centerLabel.setText("jLabel2");

		GroupLayout jPanel2Layout = new GroupLayout(centerPanel);
		centerPanel.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				jPanel2Layout.createSequentialGroup().addContainerGap()
						.addComponent(centerLabel, GroupLayout.DEFAULT_SIZE,
								100, Short.MAX_VALUE).addContainerGap()));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(centerLabel,
				GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE));

		rightPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		rightLabel.setText("jLabel3");

		GroupLayout jPanel3Layout = new GroupLayout(rightPanel);
		rightPanel.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				jPanel3Layout.createSequentialGroup().addContainerGap()
						.addComponent(rightLabel, GroupLayout.DEFAULT_SIZE,
								130, Short.MAX_VALUE).addContainerGap()));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				jPanel3Layout.createSequentialGroup().addComponent(rightLabel)
						.addContainerGap(2, Short.MAX_VALUE)));

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup().addComponent(leftPanel,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(centerPanel,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE).addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(rightPanel,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(leftPanel, 0, 21,
				Short.MAX_VALUE).addComponent(centerPanel,
				GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
				Short.MAX_VALUE).addComponent(rightPanel, 0, 21,
				Short.MAX_VALUE));
	} // </editor-fold>

	// Variables declaration - do not modify
	private javax.swing.JLabel leftLabel;
	private javax.swing.JLabel centerLabel;
	private javax.swing.JLabel rightLabel;
	private javax.swing.JPanel leftPanel;
	private javax.swing.JPanel centerPanel;
	private javax.swing.JPanel rightPanel;

	// End of variables declaration
}
