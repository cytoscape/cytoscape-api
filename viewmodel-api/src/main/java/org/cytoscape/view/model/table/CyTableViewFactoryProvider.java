package org.cytoscape.view.model.table;

import org.cytoscape.view.model.VisualLexicon;

public interface CyTableViewFactoryProvider {

	CyTableViewFactory createTableViewFactory(VisualLexicon lexicon, String rendererID);
	
}
