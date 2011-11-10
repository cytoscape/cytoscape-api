package org.cytoscape.view.presentation.property;

import java.util.HashSet;
import java.util.Set;

import org.cytoscape.view.model.AbstractVisualProperty;
import org.cytoscape.view.model.DiscreteRange;
import org.cytoscape.view.model.Range;
import org.cytoscape.view.model.Visualizable;

/**
 * Visual Property to represent abstract concept such as Node or Edge. If
 * rendering engine have this visual property in the lexicon tree and if it's a
 * leaf, it should render it with default settings.
 * @CyAPI.Final.Class #ASKMIKE needs final declare
 */
public class DefaultVisualizableVisualProperty extends
		AbstractVisualProperty<Visualizable> {

	private static final Visualizable visualizable = new VisualizableImpl();
	private static final Range<Visualizable> VISUALIZABLE_RANGE;

	static {
		final Set<Visualizable> vRange = new HashSet<Visualizable>();
		VISUALIZABLE_RANGE = new DiscreteRange<Visualizable>(
				Visualizable.class, vRange);
	}

	/**
	 * Constructs this DefaultVisualProperty.
	 * @param id the id for this DefaultVisualProperty.
	 * @param name the name for this DefaultVisualProperty.
	 * @param targetDataType #ASKMIKE
	 */
	public DefaultVisualizableVisualProperty(final String id,
			final String name, final Class<?> targetDataType) {
		super(visualizable, VISUALIZABLE_RANGE, id, name, targetDataType);
	}

	@Override
	public String toSerializableString(final Visualizable value) {
		return value.toString();
	}

	@Override
	public Visualizable parseSerializableString(final String text) {
		return visualizable;
	}
	
	private static final class VisualizableImpl implements Visualizable {
		// Dummy class.  Currently this does nothing.
	}

}