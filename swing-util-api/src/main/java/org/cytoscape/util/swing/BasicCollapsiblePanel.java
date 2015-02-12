package org.cytoscape.util.swing;

/*
 * #%L
 * Cytoscape Swing Utility API (swing-util-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2004 - 2013
 *   Memorial Sloan-Kettering Cancer Center
 *   The Cytoscape Consortium
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


import static org.cytoscape.util.swing.LookAndFeelUtil.createPanelBorder;
import static org.cytoscape.util.swing.LookAndFeelUtil.isAquaLAF;
import static org.cytoscape.util.swing.LookAndFeelUtil.isNimbusLAF;
import static org.cytoscape.util.swing.LookAndFeelUtil.isWinLAF;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;
import java.util.EventListener;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.border.Border;

/*
 * User: Vuk Pavlovic
 * Date: Nov 29, 2006
 * Time: 5:34:46 PM
 * Description: The user-triggered collapsible panel containing the component (trigger) in the titled border
 * 
 * Updated by: Gregory Hannum
 * June 7, 2010
 * Added override for setToolTipText(String text), applying the tip to the titleComponent so that the tool-tip is visible on the entire component.
 */

/**
 * A user-triggered collapsible panel containing the component (trigger) in the
 * titled border
 * @CyAPI.InModule swing-util-api
 */
public class BasicCollapsiblePanel extends JPanel {
	
	private static final long serialVersionUID = 2010434345567315524L;

	public interface CollapseListener extends EventListener {
		
		public void collapsed();
		public void expanded();
	}

	private final Vector<CollapseListener> collapseListeners = new Vector<CollapseListener>();
	
	private Border border; 

	// Title displayed in the titled border
	// protected scope for unit testing
	AbstractButton titleComponent; 

	// Expand/Collapse button
	private final static int COLLAPSED = 0, EXPANDED = 1; // image States
	private ImageIcon[] iconArrow = createExpandAndCollapseIcon();
	private JButton arrowBtn;

	// Content Pane
	private JPanel contentPane;

	// Container State
	private boolean collapsed; // stores curent state of the collapsable panel

	/**
	 * Constructor for an option button controlled collapsible panel. This is
	 * useful when a group of options each have unique sub contents. The radio
	 * buttons should be created, grouped, and then used to construct their own
	 * collapsible panels. This way choosing a different option in the same
	 * option group will collapse all unselected options. Expanded panels draw a
	 * border around the contents and through the radio button in the fashion of
	 * a titled border.
	 * 
	 * @param component
	 *            Radio button that expands and collapses the panel based on if
	 *            it is selected or not
	 */
	public BasicCollapsiblePanel(JRadioButton component) {
		this(component, !component.isSelected());
		component.addItemListener(new ExpandAndCollapseAction());
		
		setCollapsed(collapsed);
	}

	/**
	 * Constructor for a label/button controlled collapsible panel. Displays a
	 * clickable title that resembles a native titled border except for an arrow
	 * on the right side indicating an expandable panel. The actual border only
	 * appears when the panel is expanded.
	 * 
	 * @param text
	 *            Title of the collapsible panel in string format, used to
	 *            create a button with text and an arrow icon
	 */
	public BasicCollapsiblePanel(String text) {
		this(null, true);
		getArrowBtn().setText(text);
		
		setCollapsed(collapsed);
	}

	/**
	 * Sets layout, creates the content panel and adds it and the title
	 * component to the container, all constructors have this procedure in
	 * common.
	 */
	private BasicCollapsiblePanel(final AbstractButton titleComponent, final boolean collapsed) {
		border = createPanelBorder();
		iconArrow = createExpandAndCollapseIcon();
		this.titleComponent = titleComponent != null ? titleComponent : getArrowBtn();
		this.collapsed = collapsed;

		setLayout(new BorderLayout());

		super.add(this.titleComponent, BorderLayout.NORTH);
		super.add(getContentPane(), BorderLayout.CENTER);
	}

	/**
	 * Sets the title of of the border title component.
	 * 
	 * @param text
	 *            The string title.
	 */
	public void setTitleComponentText(String text) {
		if (titleComponent instanceof JButton) {
			titleComponent.setText(text);
		}
	}

	/**
	 * This class requires that all content be placed within a designated panel,
	 * this method returns that panel.
	 * 
	 * @return panel The content panel.
	 */
	public JPanel getContentPane() {
		if (contentPane == null) {
			contentPane = new JPanel();
			contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
			contentPane.setBorder(border);
			
			if (isAquaLAF())
				contentPane.setOpaque(false);
		}
		
		return contentPane;
	}

	/**
	 * Overridden to add any new components to the content panel, as might be expected.
	 * @param comp The component to add.
	 */
	@Override
	public Component add(Component comp) {
		adjust(comp);
		return contentPane.add(comp);
	}

	/**
	 * Overridden to add any new components to the content panel, as might be expected.
	 * @param comp The component to add.
	 * @param index The index at which to add the component. 
	 */
	@Override
	public Component add(Component comp, int index) {
		adjust(comp);
		return contentPane.add(comp,index);
	}

	/**
	 * Overridden to add any new components to the content panel, as might be expected.
	 * @param comp The component to add.
	 * @param constraints The constraints to adding. 
	 */
	@Override
	public void add(Component comp, Object constraints) {
		adjust(comp);
		contentPane.add(comp,constraints);
	}

	/**
	 * Overridden to add any new components to the content panel, as might be expected.
	 * @param comp The component to add.
	 * @param constraints The constraints to adding. 
	 * @param index The index at which to add the component. 
	 */
	@Override
	public void add(Component comp, Object constraints, int index) {
		adjust(comp);
		contentPane.add(comp,constraints,index);
	}

	/**
	 * Overridden to add any new components to the content panel, as might be expected.
	 * @param name The name of the component to add. 
	 * @param comp The component to add.
	 */
	@Override
	public Component add(String name, Component comp) {
		adjust(comp);
		return contentPane.add(name,comp);
	}

	/**
	 * Collapses or expands the panel. This is done by adding or removing the
	 * content pane, alternating between a frame and empty border, and changing
	 * the title arrow. Also, the current state is stored in the collapsed
	 * boolean.
	 * 
	 * @param collapse
	 *            When set to true, the panel is collapsed, else it is expanded
	 */
	public void setCollapsed(boolean collapse) {
		if (collapse) {
			// Hide content
			getContentPane().setVisible(false);
			getArrowBtn().setIcon(iconArrow[COLLAPSED]);
		} else {
			// Show content
			getContentPane().setVisible(true);
			getArrowBtn().setIcon(iconArrow[EXPANDED]);
		}
		
		collapsed = collapse;
		updateUI();
		
		if (collapseListeners != null) {
	        for (CollapseListener listener : collapseListeners) {
	        	if (collapse)
	        		listener.collapsed();
	        	else
	        		listener.expanded();
	        }
    	}
	}

	/**
	 * Returns the current state of the panel, collapsed (true) or expanded
	 * (false).
	 * 
	 * @return collapsed Returns true if the panel is collapsed and false if it
	 *         is expanded
	 */
	public boolean isCollapsed() {
		return collapsed;
	}
	
	/**
	 * Sets the tooltip text of this BasicCollapsiblePanel.
	 * 
	 * @param text The string to set as the tooltip.
	 */
	@Override
	public void setToolTipText(final String text) {
		super.setToolTipText(text);
		titleComponent.setToolTipText(text);
	}
	
	public void addCollapseListener(CollapseListener listener) {
		collapseListeners.add(listener);
	}

	public boolean removeCollapeListener(CollapseListener listener) {
		return collapseListeners.remove(listener);
	}

	/**
	 * Returns an ImageIcon array with arrow images used for the different
	 * states of the panel.
	 * 
	 * @return iconArrow An ImageIcon array holding the collapse and expanded
	 *         versions of the right hand side arrow
	 */
	private ImageIcon[] createExpandAndCollapseIcon() {
		ImageIcon[] iconArrow = new ImageIcon[2];
		URL iconURL;

		iconURL = getClass().getResource("/images/arrow_collapsed.png");

		if (iconURL != null) {
			iconArrow[COLLAPSED] = new ImageIcon(iconURL);
		}
		iconURL = getClass().getResource("/images/arrow_expanded.png");

		if (iconURL != null) {
			iconArrow[EXPANDED] = new ImageIcon(iconURL);
		}
		return iconArrow;
	}

	private JButton getArrowBtn() {
		if (arrowBtn == null) {
			arrowBtn = new JButton("", iconArrow[COLLAPSED]);
			
			if (isWinLAF()) {
				arrowBtn.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			} else {
				arrowBtn.setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 4));
			}
			
			arrowBtn.setMargin(new Insets(0, 0, 3, 0));
			arrowBtn.setContentAreaFilled(false);
			arrowBtn.setFocusable(false);
			arrowBtn.setHorizontalAlignment(JButton.LEFT);
			arrowBtn.setHorizontalTextPosition(JButton.RIGHT);
			arrowBtn.setVerticalAlignment(JButton.CENTER);
			arrowBtn.setVerticalTextPosition(JButton.CENTER);

			// We want to use the same font as those in the titled border font
			Font font = BorderFactory.createTitledBorder(border, "Sample").getTitleFont();
			if (font == null) font = UIManager.getFont("Label.font");
			Color color = BorderFactory.createTitledBorder(border, "Sample").getTitleColor();
			if (color == null) color = UIManager.getColor("Label.foreground");
			
			if (font != null) arrowBtn.setFont(font);
			if (isNimbusLAF()) arrowBtn.setFont(arrowBtn.getFont().deriveFont(Font.BOLD));
			if (color != null) arrowBtn.setForeground(color);

			arrowBtn.addActionListener(new ExpandAndCollapseAction());
		}
		
		return arrowBtn;
	}
	
	private void adjust(final Component c) {
		if (isAquaLAF() && c instanceof JPanel)
			((JPanel) c).setOpaque(false);
	}

	/**
	 * Handles expanding and collapsing of extra content on the user's click of
	 * the titledBorder component.
	 */
	private final class ExpandAndCollapseAction extends AbstractAction implements ActionListener, ItemListener {
		
		private static final long serialVersionUID = 2010434345567315525L;

		@Override
		public void actionPerformed(ActionEvent e) {
			setCollapsed(!isCollapsed());
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			setCollapsed(!isCollapsed());
		}
	}
}
