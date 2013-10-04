package org.cytoscape.filter.view;

import javax.swing.JComponent;

import org.cytoscape.filter.model.Transformer;

public interface TransformerViewFactory {
	String getId();
	JComponent createView(Transformer<?, ?> transformer);
}
