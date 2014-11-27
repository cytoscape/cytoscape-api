package org.cytoscape.util.swing;


import javax.swing.BorderFactory;
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
	
	private LookAndFeelUtil() {
	}
}
