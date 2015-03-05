package org.cytoscape.util.swing;


import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public final class LookAndFeelUtil {

	public static boolean isAquaLAF() {
		return UIManager.getLookAndFeel() != null && "Mac OS X".equals(UIManager.getLookAndFeel().getName());
	}
	
	public static boolean isNimbusLAF() {
		return UIManager.getLookAndFeel() != null && "Nimbus".equals(UIManager.getLookAndFeel().getName());
	}
	
	public static boolean isWinLAF() {
		return UIManager.getLookAndFeel() != null && "Windows".equals(UIManager.getLookAndFeel().getName());
	}
	
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
	
	public static Border createTitledBorder(final String title) {
		final Border border;
		
		if (title == null || title.trim().isEmpty()) {
			final Border aquaBorder = isAquaLAF() ? UIManager.getBorder("InsetBorder.aquaVariant") : null;
			border = aquaBorder != null ? aquaBorder : BorderFactory.createTitledBorder("SAMPLE").getBorder();
		} else {
			final Border aquaBorder = isAquaLAF() ? UIManager.getBorder("TitledBorder.aquaVariant") : null;
			final TitledBorder tb = aquaBorder != null ?
					BorderFactory.createTitledBorder(aquaBorder, title) : BorderFactory.createTitledBorder(title);
			border = tb;
		}
		
		return border;
	}
	
	public static JPanel createOkCancelPanel(final JButton okBtn, final JButton cancelBtn) {
		final JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(6, 0, 6, 0));
		
		addOkCancelPanel(panel, okBtn, cancelBtn);
		
		return panel;
	}
	
	public static JPanel createOkCancelPanel(final JButton okBtn, final JButton cancelBtn, JButton... otherBtns) {
		final JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(6, 0, 6, 0));
		
		if (otherBtns != null) {
			for (int i = 0; i < otherBtns.length; i++) {
				final JButton btn = otherBtns[i];
				panel.add(btn);
				
				if (i < otherBtns.length-1 && LookAndFeelUtil.isWinLAF())
					panel.add(Box.createHorizontalStrut(5));
			}
		}
		
		addOkCancelPanel(panel, okBtn, cancelBtn);
		
		return panel;
	}
	
	public static void setDefaultOkCancelKeyStrokes(final JRootPane rootPane, final Action okAction,
			final Action cancelAction) {
		final String CANCEL_ACTION_KEY = "CANCEL_ACTION_KEY";
		final String OK_ACTION_KEY = "OK_ACTION_KEY";
		
		final KeyStroke escapeKey = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
		final KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false);
		
		final InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		inputMap.put(escapeKey, CANCEL_ACTION_KEY);
		inputMap.put(enterKey, OK_ACTION_KEY);
		
		rootPane.getActionMap().put(CANCEL_ACTION_KEY, cancelAction);
		rootPane.getActionMap().put(OK_ACTION_KEY, okAction);
	}
	
	private static void addOkCancelPanel(final JPanel panel, final JButton okBtn, final JButton cancelBtn) {
		panel.add(Box.createHorizontalGlue());
		
		if (isMac()) {
			panel.add(cancelBtn);
			panel.add(okBtn);
		} else {
			panel.add(okBtn);
			if (LookAndFeelUtil.isWinLAF()) panel.add(Box.createHorizontalStrut(5));
			panel.add(cancelBtn);
		}
	}
	
	public static boolean isMac() {
		return System.getProperty("os.name").startsWith("Mac OS X");
	}
	
	private LookAndFeelUtil() {
	}
}
