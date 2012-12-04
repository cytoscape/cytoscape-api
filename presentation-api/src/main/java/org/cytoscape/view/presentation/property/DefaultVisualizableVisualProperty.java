package org.cytoscape.view.presentation.property;

import java.util.HashSet;
import java.util.Set;

import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.view.model.AbstractVisualProperty;
import org.cytoscape.view.model.DiscreteRange;
import org.cytoscape.view.model.Range;
import org.cytoscape.view.model.Visualizable;

/**
 * Visual Property to represent abstract concept such as Node or Edge. If
 * rendering engine have this visual property in the lexicon tree and if it's a
 * leaf, it should render it with default settings.
 * 
 * @CyAPI.Final.Class
 */
public final class DefaultVisualizableVisualProperty extends
		AbstractVisualProperty<Visualizable> {

	private static final Visualizable visualizable = new VisualizableImpl();
	private static final Range<Visualizable> VISUALIZABLE_RANGE;

	static {
		final Set<Visualizable> vRange = new HashSet<Visualizable>();
		VISUALIZABLE_RANGE = new DiscreteRange<Visualizable>(
				Visualizable.class, vRange);
	}

	/**
	 * @param id A machine readable string identifying this visual property used for XML serialization. 
	 * @param displayName A human readable string used for displays and user interfaces. 
	 * @param modelDataType The model data type associated with this visual property, e.g. CyNode, CyEdge, or CyNetwork. 
	 */
	public DefaultVisualizableVisualProperty(final String id,
			final String displayName, final Class<? extends CyIdentifiable> modelDataType) {
		super(visualizable, VISUALIZABLE_RANGE, id, displayName, modelDataType);
	}

	@Override
	public String toSerializableString(final Visualizable value) {
		// return value.toString();
		return "DefaultVisualizableVisualProperty(id="+getIdString()+", name="+getDisplayName()+")";
	}

	@Override
	public Visualizable parseSerializableString(final String text) {
		return visualizable;
	}
	
	private static final class VisualizableImpl implements Visualizable {
		// Dummy class.  Currently this does nothing.
	}

}
