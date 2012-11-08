package org.cytoscape.view.layout;

import static org.cytoscape.view.presentation.property.BasicVisualLexicon.NODE_X_LOCATION;
import static org.cytoscape.view.presentation.property.BasicVisualLexicon.NODE_Y_LOCATION;
import static org.cytoscape.view.presentation.property.BasicVisualLexicon.NODE_Z_LOCATION;
import static org.cytoscape.view.presentation.property.BasicVisualLexicon.NETWORK_CENTER_X_LOCATION;
import static org.cytoscape.view.presentation.property.BasicVisualLexicon.NETWORK_CENTER_Y_LOCATION;
import static org.cytoscape.view.presentation.property.BasicVisualLexicon.NETWORK_CENTER_Z_LOCATION;
import static org.cytoscape.view.presentation.property.BasicVisualLexicon.NETWORK_SCALE_FACTOR;
import static org.cytoscape.view.presentation.property.BasicVisualLexicon.EDGE_BEND;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNode;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.cytoscape.view.presentation.property.values.Bend;
import org.cytoscape.work.undo.AbstractCyEdit;

/**
 * An undoable edit that will undo and redo of a layout algorithm applied to a
 * network view.
 */
public final class LayoutEdit extends AbstractCyEdit {
	private final CyNetworkView view;
	private List<NodeViewAndLocations> nodeViewsAndLocations;
	private Map<View<CyEdge>, Bend> bendMap;

	private double networkScale;
	private double networkCenterX;
	private double networkCenterY;
	private double networkCenterZ;

	/**
	 * Constructor.
	 * 
	 * @param name
	 *            The name that will appear in the undo menu.
	 * @param view
	 *            The view whose current position will be tracked.
	 */
	public LayoutEdit(String name, final CyNetworkView view) {
		super(name);
		this.view = view;
		saveNodeViewsAndLocations();
		saveEdgeViews();
	}

	@Override
	public void redo() {
		saveAndRestore();
	}

	@Override
	public void undo() {
		saveAndRestore();
	}

	private void saveAndRestore() {
		final List<NodeViewAndLocations> oldNodeViewsAndLocations = nodeViewsAndLocations;
		final double oldNetworkScale = networkScale;
		final double oldNetworkCenterX = networkCenterX;
		final double oldNetworkCenterY = networkCenterY;
		final double oldNetworkCenterZ = networkCenterZ;
		final Map<View<CyEdge>, Bend> oldEdgeBends = bendMap;

		saveNodeViewsAndLocations();
		saveEdgeViews();

		for (final NodeViewAndLocations nodeViewAndLocation : oldNodeViewsAndLocations)
			nodeViewAndLocation.restoreLocations();
		for (View<CyEdge> edgeView : oldEdgeBends.keySet())
			edgeView.setVisualProperty(EDGE_BEND, oldEdgeBends.get(edgeView));

		view.setVisualProperty(NETWORK_SCALE_FACTOR, oldNetworkScale);
		view.setVisualProperty(NETWORK_CENTER_X_LOCATION, oldNetworkCenterX);
		view.setVisualProperty(NETWORK_CENTER_Y_LOCATION, oldNetworkCenterY);
		view.setVisualProperty(NETWORK_CENTER_Z_LOCATION, oldNetworkCenterZ);

		view.updateView();
	}

	private void saveNodeViewsAndLocations() {
		networkScale = view.getVisualProperty(NETWORK_SCALE_FACTOR);
		networkCenterX = view.getVisualProperty(NETWORK_CENTER_X_LOCATION);
		networkCenterY = view.getVisualProperty(NETWORK_CENTER_Y_LOCATION);
		networkCenterZ = view.getVisualProperty(NETWORK_CENTER_Z_LOCATION);

		final Collection<View<CyNode>> nodeViews = view.getNodeViews();
		nodeViewsAndLocations = new ArrayList<NodeViewAndLocations>(nodeViews.size());
		for (final View<CyNode> nodeView : nodeViews)
			nodeViewsAndLocations.add(new NodeViewAndLocations(nodeView));
	}

	private void saveEdgeViews() {
		this.bendMap = new WeakHashMap<View<CyEdge>, Bend>();
		final Collection<View<CyEdge>> edgeViews = view.getEdgeViews();
		for (final View<CyEdge> edgeView : edgeViews) {
			bendMap.put(edgeView, edgeView.getVisualProperty(EDGE_BEND));
		}
	}
}

final class NodeViewAndLocations {
	private final View<CyNode> nodeView;
	private final double xLocation;
	private final double yLocation;
	private final double zLocation;

	NodeViewAndLocations(final View<CyNode> nodeView) {
		this.nodeView = nodeView;
		xLocation = nodeView.getVisualProperty(NODE_X_LOCATION);
		yLocation = nodeView.getVisualProperty(NODE_Y_LOCATION);
		zLocation = nodeView.getVisualProperty(NODE_Z_LOCATION);
	}

	void restoreLocations() {
		nodeView.setVisualProperty(NODE_X_LOCATION, xLocation);
		nodeView.setVisualProperty(NODE_Y_LOCATION, yLocation);
		nodeView.setVisualProperty(NODE_Z_LOCATION, zLocation);
	}
}
