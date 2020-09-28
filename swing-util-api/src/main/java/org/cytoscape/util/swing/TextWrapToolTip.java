package org.cytoscape.util.swing;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.CellRendererPane;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JToolTip;
import javax.swing.JWindow;
import javax.swing.plaf.basic.BasicToolTipUI;



@SuppressWarnings("serial")
public class TextWrapToolTip extends JToolTip {

	public TextWrapToolTip() {
		updateUI();
	}

	@Override
	public void updateUI() {
		setUI(TextWrapToolTipUI.getInstance());
	}

}


class TextWrapToolTipUI extends BasicToolTipUI {

	private static TextWrapToolTipUI instance;
	
	private CellRendererPane rendererPane;
	private JTextArea textArea;

	
	private TextWrapToolTipUI() {
	}
	
	public static synchronized TextWrapToolTipUI getInstance() {
		if(instance == null)
			instance = new TextWrapToolTipUI();
		return instance;
	}

	@Override
	public void installUI(JComponent c) {
		super.installUI(c);
		rendererPane = new CellRendererPane();
		c.add(rendererPane);
	}

	@Override
	public void uninstallUI(JComponent c) {
		super.uninstallUI(c);

		c.remove(rendererPane);
		rendererPane = null;
	}

	@Override
	public void paint(Graphics g, JComponent c) {
		Dimension size = c.getSize();
		textArea.setBackground(c.getBackground());
		rendererPane.paintComponent(g, textArea, c, 1, 1, size.width - 1, size.height - 1, true);
	}

	@Override
	public Dimension getPreferredSize(JComponent c) {
		String tipText = ((JToolTip) c).getTipText();

		if (tipText == null)
			return new Dimension(0, 0);

		textArea = new JTextArea(tipText);
		textArea.setWrapStyleWord(true);
		
		if (c.getFont() != null)
			textArea.setFont(c.getFont());
		
		textArea.setLineWrap(true);
		
		Dimension pref = super.getPreferredSize(c);
		Dimension max = c.getMaximumSize();
		int w = Math.min(max.width, pref.width);
		int h = Math.min(max.height, pref.height);
		
		JWindow win = new JWindow();
		win.getContentPane().add(textArea);
		win.setPreferredSize(new Dimension(w, h));
		win.setMaximumSize(new Dimension(w, max.height));
		win.pack();
		Dimension size = win.getSize();
		win.getContentPane().removeAll();
		
		textArea.setSize(size);
		
		Dimension dim = textArea.getPreferredSize();
		dim.height += 1;
		dim.width += 1;
		
		rendererPane.removeAll();
		rendererPane.add(textArea);
		
		return dim;
	}

	@Override
	public Dimension getMinimumSize(JComponent c) {
		return textArea == null ? super.getMinimumSize(c) : textArea.getMinimumSize();
	}

	@Override
	public Dimension getMaximumSize(JComponent c) {
		return textArea == null ? super.getMaximumSize(c) : textArea.getMaximumSize();
	}
}
