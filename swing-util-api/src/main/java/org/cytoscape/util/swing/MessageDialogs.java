package org.cytoscape.util.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * There have been some issues with JOptionPane not working correctly when triggered from
 * a command or automation script. This utility class provides some static methods for
 * showing simple message and confirmation dialogs.
 */
public class MessageDialogs {
	
	/**
	 * Shows a small modal dialog with a short message and an "Ok" button.
	 */
	public static void showMessageDialog(JFrame parent, String title, String message) {
		showDialog(parent, message, title, "Ok", null);
	}
	
	/**
	 * Shows a small modal dialog with a short message and "Yes" and "No" buttons.
	 * @return true If the "Yes" button was clicked, false otherwise.
	 */
	public static boolean showYesNoDialog(JFrame parent, String title, String message) {
		return showDialog(parent, message, title, "Yes", "No");
	}
	
	/**
	 * Shows a small modal dialog with a short message and "Ok" and "Cancel" buttons.
	 * @return true If the "Ok" button was clicked, false otherwise.
	 */
	public static boolean showOkCancelDialog(JFrame parent, String title, String message) {
		return showDialog(parent, message, title, "Ok", "Cancel");
	}
	
	
	private static boolean showDialog(JFrame parent, String message, String title, String okButtonText, String cancelButtonText) {
		JLabel label = new JLabel(message);
		JButton okButton = okButtonText == null ? null : new JButton(okButtonText);
		JButton cancelButton = cancelButtonText == null ? null : new JButton(cancelButtonText);
		JPanel buttonPanel = LookAndFeelUtil.createOkCancelPanel(okButton, cancelButton); // passing null should be fine
		
		JPanel bodyPanel = new JPanel(new BorderLayout());
		bodyPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		bodyPanel.add(label, BorderLayout.CENTER);
		bodyPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		JDialog dialog = new JDialog(parent);
		dialog.getContentPane().add(bodyPanel);
		
		boolean[] result = { false };
		
		if(okButton != null) {
			okButton.addActionListener(e -> {
				result[0] = true;
				dialog.dispose();
			});
		}
		
		if(cancelButton != null) {
			cancelButton.addActionListener(e -> {
				dialog.dispose();
			});
		}
		
		dialog.setTitle(title);
		dialog.setMinimumSize(new Dimension(200, 100));
		dialog.setLocationRelativeTo(parent);
		dialog.setModal(true);
		dialog.pack();
		dialog.setVisible(true);
		
		return result[0];
	}

}
