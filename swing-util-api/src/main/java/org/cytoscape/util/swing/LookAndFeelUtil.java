package org.cytoscape.util.swing;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/*
 * #%L
 * Cytoscape Swing Utility API (swing-util-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2016 The Cytoscape Consortium
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
 * <http://www.gnu.org/licensses/lgpl-2.1.html>.
 * #L%
 */

/**
 * Class that provides useful methods to help create standard and consistent UI on the current Look and Feel and OS.
 * 
 * @CyAPI.Final.Class 
 * @CyAPI.InModule swing-util-api
 */
public final class LookAndFeelUtil {

	static final float AQUA_TITLED_BORDER_FONT_SIZE = 11.0f;
	
	private static Font iconFont;

	static {
		try {
			iconFont = Font.createFont(Font.TRUETYPE_FONT,
					LookAndFeelUtil.class.getResourceAsStream("/fonts/fontawesome-webfont.ttf"));
		} catch (FontFormatException e) {
			throw new RuntimeException();
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
	
	/**
	 * @return true if the current Look and Feel is "Aqua", usually available on Mac OS X only.
	 */
	public static boolean isAquaLAF() {
		return UIManager.getLookAndFeel() != null && 
				("Mac".equals(UIManager.getLookAndFeel().getID()) || "Aqua".equals(UIManager.getLookAndFeel().getID()));
	}
	
	/**
	 * @return true if the current Look and Feel is "Nimbus".
	 */
	public static boolean isNimbusLAF() {
		return UIManager.getLookAndFeel() != null && "Nimbus".equals(UIManager.getLookAndFeel().getID());
	}
	
	/**
	 * @return true if the current Look and Feel is the Windows system one.
	 */
	public static boolean isWinLAF() {
		return UIManager.getLookAndFeel() != null && "Windows".equals(UIManager.getLookAndFeel().getID());
	}
	
	/**
	 * @return The color that should be used on information icons or labels, for example.
	 */
	public static Color getInfoColor() {
		return UIManager.getColor("CyColor.complement(+2)");
	}
	
	/**
	 * @return The color that should be used on warning icons or labels, for example.
	 */
	public static Color getWarnColor() {
		return UIManager.getColor("CyColor.primary");
	}
	
	/**
	 * @return The color that should be used on error icons or labels, for example.
	 */
	public static Color getErrorColor() {
		return UIManager.getColor("CyColor.secondary2(-1)");
	}
	
	/**
	 * @return The color that should be used on success related icons or labels.
	 */
	public static Color getSuccessColor() {
		return UIManager.getColor("CyColor.secondary1(-1)");
	}
	
	/**
	 * @return The standard small font size for the current Look and feel.
	 */
	public static float getSmallFontSize() {
		return 11.0f; // TODO Maybe different values for different LAF
	}
	
	/**
	 * Use this method to create a simple panel {@link Border} which looks native on the current Look and Feel.
	 * @return The proper {@link Border} for the current Look and Feel.
	 */
	public static Border createPanelBorder() {
		// Try to create Aqua recessed borders on Mac OS
		Border border = isAquaLAF() ? UIManager.getBorder("InsetBorder.aquaVariant") : null;
		
		if (border == null) {
			if (isWinLAF())
				border = new TitledBorder("");
			else
				border = BorderFactory.createTitledBorder("SAMPLE").getBorder();
		}
			
		if (border == null)
			border = BorderFactory.createLineBorder(UIManager.getColor("Separator.foreground"));
		
		return border;
	}
	
	/**
	 * Use this method to create a Titled Border which looks native on the current Look and Feel.
	 * The major problem is that the default border created by {@link BorderFactory#createTitledBorder(String)}
	 * does not look good on the "Aqua" Look and Feel (Mac OS X).
	 * @return The proper {@link Border} for the current Look and Feel.
	 */
	public static Border createTitledBorder(final String title) {
		final Border border;
		
		if (title == null || title.trim().isEmpty()) {
			final Border aquaBorder = isAquaLAF() ? UIManager.getBorder("InsetBorder.aquaVariant") : null;
			border = aquaBorder != null ? aquaBorder : BorderFactory.createTitledBorder("SAMPLE").getBorder();
		} else {
			final Border aquaBorder = isAquaLAF() ? UIManager.getBorder("TitledBorder.aquaVariant") : null;
			final TitledBorder tb = aquaBorder != null ?
					BorderFactory.createTitledBorder(aquaBorder, title) : BorderFactory.createTitledBorder(title);
			
			if (isAquaLAF())
				tb.setTitleFont(UIManager.getFont("Label.font").deriveFont(AQUA_TITLED_BORDER_FONT_SIZE));
			
			border = tb;
		}
		
		return border;
	}
	
	/**
	 * Use this method to create a standard Cytoscape panel (usually added to the bottom of a dialog)
	 * that contains an "OK" and/or a "Cancel" button.
	 * The buttons will be correctly positioned and aligned according to the current Look and Feel and OS.
	 * @param okBtn The button that executes the main action. It can be null.
	 * @param cancelBtn The button that cancels the action (or simply closes a dialog). It can be null.
	 * @return A {@link JPanel} which contains the passed buttons.
	 */
	public static JPanel createOkCancelPanel(final JButton okBtn, final JButton cancelBtn) {
		return createOkCancelPanel(okBtn, cancelBtn, new JComponent[0]);
	}
	
	/**
	 * Use this method to create OK, Cancel and Help
	 * @param helpStr can be a full url, a lookup, or a help page name
	 */
	public static JPanel createOkCancelPanel(final JButton okBtn, final JButton cancelBtn, String helpStr) {
		return createOkCancelPanel(okBtn, cancelBtn, helpStr, new JComponent[0]);
	}
	
	/**
	 * Use this method to create a standard Cytoscape panel (usually added to the bottom of a dialog)
	 * that contains an "OK" and/or a "Cancel" button, as well as other extra components.
	 * The default OK/Cancel buttons and the extra components will be correctly positioned and aligned according to
	 * the current Look and Feel and OS.
	 * @param okBtn The button that executes the main action. It can be null.
	 * @param cancelBtn The button that cancels the action (or simply closes a dialog). It can be null.
	 * @param otherComponents Any other components to be included in the panel, such as check-boxes or other buttons.
	 * @return A {@link JPanel} which contains the passed components.
	 */
	public static JPanel createOkCancelPanel(final JButton okBtn, final JButton cancelBtn,
			JComponent... otherComponents) {
		return createOkCancelPanel(okBtn, cancelBtn, null, otherComponents);
	}
	
	/**
	 * Use this method to include a help button in the bottom left corner.
	 * @param helpStr can be a full url, a lookup, or a help page name
	 */
	public static JPanel createOkCancelPanel(final JButton okBtn, final JButton cancelBtn, String helpStr,
			JComponent... otherComponents) {
		final JPanel panel = new JPanel();
		
		final GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateContainerGaps(false);
		layout.setAutoCreateGaps(true);
		
		final SequentialGroup hg = layout.createSequentialGroup();
		final ParallelGroup vg = layout.createParallelGroup(Alignment.CENTER, false);
		
		if (helpStr != null && !helpStr.trim().isEmpty()) {
			final JButton helpButton = createHelpButton(helpStr);
			hg.addComponent(helpButton);
			vg.addComponent(helpButton);
		}
		
		if (otherComponents != null) {
			for (int i = 0; i < otherComponents.length; i++) {
				final JComponent c = otherComponents[i];
				hg.addComponent(c);
				vg.addComponent(c);
			}
		}
		
		hg.addGap(0, 0, Short.MAX_VALUE);
		
		final JButton btn1 = isMac() ? cancelBtn : okBtn;
		final JButton btn2 = isMac() ? okBtn : cancelBtn;
		
		if (btn1 != null) {
			hg.addComponent(btn1);
			vg.addComponent(btn1);
		}
		if (btn2 != null) {
			hg.addComponent(btn2);
			vg.addComponent(btn2);
		}
		
		layout.setHorizontalGroup(hg);
		layout.setVerticalGroup(vg);
		
		if (okBtn != null && cancelBtn != null)
			equalizeSize(okBtn, cancelBtn);
        
        return panel; 
    }
	
	public static JButton createHelpButton(final String helpStr) {
		final JButton helpButton = new JButton();
		helpButton.setToolTipText("Help...");

		if (isAquaLAF()) {
			helpButton.putClientProperty("JButton.buttonType", "help");
		} else {
			helpButton.setText(IconManager.ICON_QUESTION_CIRCLE);
			helpButton.setFont(getIconFont(22.0f));
			helpButton.setBorderPainted(false);
			helpButton.setContentAreaFilled(false);
			helpButton.setFocusPainted(false);
			helpButton.setBorder(BorderFactory.createEmptyBorder());
			helpButton.setMinimumSize(new Dimension(22, 22));
		}
		
		helpButton.addActionListener((ActionEvent e) -> {
			launch(helpStr);
		});
		
		return helpButton;
	}
	
	/**
	 * Maps the standard key strokes (usually ENTER and ESCAPE) to the passed OK and/or Cancel actions.
	 * @param rootPane A {@link javax.swing.JDialog}'s root pane, for instance.
	 * @param okAction The main {@link Action} of a {@link javax.swing.JDialog}, for instance. It can be null.
	 * @param cancelAction The {@link Action} that cancels or closes a {@link javax.swing.JDialog}, for instance.
	 *                     It can be null.
	 */
	public static void setDefaultOkCancelKeyStrokes(final JRootPane rootPane, final Action okAction,
			final Action cancelAction) {
		final InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		
		if (okAction != null) {
			final String OK_ACTION_KEY = "OK_ACTION_KEY";
			final KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false);
			inputMap.put(enterKey, OK_ACTION_KEY);
			rootPane.getActionMap().put(OK_ACTION_KEY, okAction);
		}
		
		if (cancelAction != null) {
			final String CANCEL_ACTION_KEY = "CANCEL_ACTION_KEY";
			final KeyStroke escapeKey = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
			inputMap.put(escapeKey, CANCEL_ACTION_KEY);
			rootPane.getActionMap().put(CANCEL_ACTION_KEY, cancelAction);
		}
	}
	
	/**
	 * Resizes the given components making them equal in size.
	 */
	public static void equalizeSize(final JComponent... components) {
		if (components == null || components.length == 0)
			return;
		
		final Dimension prefSize = components[0].getPreferredSize();
		final Dimension maxSize = components[0].getMaximumSize();
		
		for (JComponent c : components) {
			ensureSize(prefSize, c.getPreferredSize());
			ensureSize(maxSize, c.getMaximumSize());
		}
		
		for (JComponent c : components) {
			c.setPreferredSize(prefSize);
			c.setMaximumSize(maxSize);
		}
	}
	
	/**
	 * Returns true if the current operating system is Mac OS X.
	 * @return true if the current OS is Mac OS X and false otherwise.
	 */
	public static boolean isMac() {
		return System.getProperty("os.name").startsWith("Mac OS X");
	}
	
	/**
	 * Returns true if the current operating system is any modern Windows distribution.
	 * @return true if the current OS is Windows and false otherwise.
	 */
	public static boolean isWindows() {
		return System.getProperty("os.name").startsWith("Windows");
	}
	
	/**
	 * Enlarges, if necessary, the given current size to cover the given other size.
	 * <p>
	 * If both the width and height of <code>currentSize</code> are larger than the width and height of
	 * <code>minSize</code>, respectively, calling this method has no effect.
	 * </p>
	 * 
	 * @param currentSize Size to be enlarged if necessary.
	 * @param minSize Minimal required size of <code>currentSize</code>.
	 */
	private static void ensureSize(final Dimension currentSize, final Dimension minSize) {
		if (currentSize.height < minSize.height)
			currentSize.height = minSize.height;
		
		if (currentSize.width < minSize.width)
			currentSize.width = minSize.width;
	}
	
	//FIXME         don't hardcode version tag!
	private static String HELP_PREFIX = "http://manual.cytoscape.org/en/3.4.0/";
	private static String HELP_SUFFIX = ".html";

	private static URI helpStrToURI(String helpStr) {
		try {
			return new URL(helpStrToURL(helpStr)).toURI();
		} catch (Exception e) {
			System.out.println(" helpStrToURI  Exception: " + e.getMessage());
		}

		return null;
	}

	private static String helpStrToURL(String rawString) {
		if (rawString.startsWith("http"))
			return rawString;

		return HELP_PREFIX + rawString + HELP_SUFFIX;
	}

	private static void launch(String helpStr) {
		try {
			URI uri = helpStrToURI(helpStr);
			
			if (uri != null)
				Desktop.getDesktop().browse(uri);
		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getMessage());
		}
	}
	
	private static Font getIconFont(float size) {
		return iconFont.deriveFont(size);
	}
	
	private LookAndFeelUtil() {
	}
}
