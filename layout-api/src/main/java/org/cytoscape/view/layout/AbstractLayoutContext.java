package org.cytoscape.view.layout;

import org.cytoscape.work.Tunable;
import org.cytoscape.work.util.ListSingleSelection;

public class AbstractLayoutContext implements CyLayoutContext {
	static final String ALL_NODES = "All Nodes";
	static final String SELECTED_NODES = "Selected Nodes";
	
	private boolean useOnlySelectedNodes;
	
	@Tunable(description = "Apply to")
	public final ListSingleSelection<String> getSubmenuOptions() {
		ListSingleSelection<String> options = new ListSingleSelection<String>(ALL_NODES, SELECTED_NODES);
		options.setSelectedValue(useOnlySelectedNodes ? SELECTED_NODES : ALL_NODES);
		return options;
	}
	
	public final void setSubmenuOptions(ListSingleSelection<String> options) {
		useOnlySelectedNodes = SELECTED_NODES.equals(options.getSelectedValue());
	}
	
	public boolean useOnlySelectedNodes() {
		return useOnlySelectedNodes;
	}
}
