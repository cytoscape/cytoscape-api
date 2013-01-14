package org.cytoscape.util.swing;

import javax.swing.*;
import java.awt.*;

/**
 * Button with drop down menu.
 * 
 * @CyAPI.Final.Class
 * @CyAPI.InModule swing-util-api
 */
public final class DropDownMenuButton extends JButton {

	private final static long serialVersionUID = 1202339868695691L;

	private final Icon buttonIcon = new MenuArrowIcon();

	/**
	 * Creates a new DropDownMenuButton object.
	 * 
	 * @param action
	 *            The action that this button represents.
	 */
	public DropDownMenuButton(final AbstractAction action) {
		super(action);
		
		this.setFocusPainted(false);
		this.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4 + buttonIcon.getIconWidth()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		final Dimension dim = getSize();
		final Insets ins = getInsets();
		final int x = dim.width - ins.right;
		final int y = ins.top + ((dim.height - ins.top - ins.bottom - buttonIcon.getIconHeight()) / 2);
		buttonIcon.paintIcon(this, g, x, y);
	}

	private static final class MenuArrowIcon implements Icon {
		
		@Override
		public void paintIcon(Component c, Graphics g, int x, int y) {
			Graphics2D g2 = (Graphics2D) g;
			// Turn AA on
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			g2.setColor(Color.black);
			g2.translate(x, y);
			g2.drawLine(2, 3, 6, 3);
			g2.drawLine(3, 4, 5, 4);
			g2.drawLine(4, 5, 4, 5);
			g2.translate(-x, -y);
		}

		@Override
		public int getIconWidth() {
			return 9;
		}

		@Override
		public int getIconHeight() {
			return 9;
		}
	}
}
