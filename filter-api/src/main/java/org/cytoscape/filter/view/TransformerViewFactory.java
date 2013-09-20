package org.cytoscape.filter.view;

import java.awt.Component;

import org.cytoscape.filter.model.Transformer;

public interface TransformerViewFactory {
	String getId();
	Component createView(Transformer<?, ?> transformer);
}
