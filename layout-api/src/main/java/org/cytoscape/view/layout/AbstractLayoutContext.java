package org.cytoscape.view.layout;

import org.cytoscape.work.Tunable;
import org.cytoscape.work.util.ListSingleSelection;

public class AbstractLayoutContext implements CyLayoutContext {
	
	private static final String ALL_NODES = "All Nodes";
	private static final String SELECTED_NODES = "Selected Nodes";
	
	private boolean useOnlySelectedNodes = false;
	
	@Tunable(description = "Apply to")
	public final ListSingleSelection<String> getSubmenuOptions() {
		final ListSingleSelection<String> options = new ListSingleSelection<String>(ALL_NODES, SELECTED_NODES);
		options.setSelectedValue(useOnlySelectedNodes ? SELECTED_NODES : ALL_NODES);
		return options;
	}
	
	public final void setSubmenuOptions(ListSingleSelection<String> options) {
		useOnlySelectedNodes = SELECTED_NODES.equals(options.getSelectedValue());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean useOnlySelectedNodes() {
		return useOnlySelectedNodes;
	}
}
