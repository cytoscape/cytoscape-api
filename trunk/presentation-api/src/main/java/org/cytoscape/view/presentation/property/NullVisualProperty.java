package org.cytoscape.view.presentation.property;

import java.util.HashSet;
import java.util.Set;

import org.cytoscape.model.CyIdentifiable;
import org.cytoscape.view.model.AbstractVisualProperty;
import org.cytoscape.view.model.DiscreteRange;
import org.cytoscape.view.model.NullDataType;
import org.cytoscape.view.model.Range;

/**
 * Visual Property for root. This will not be used in actual visualization. Just
 * a marker node in the tree.
 *
 * @CyAPI.Final.Class
 */
public final class NullVisualProperty extends AbstractVisualProperty<NullDataType> {

	private static final NullDataType dummyObject = new NullDataTypeImpl();
	private static final Range<NullDataType> NULL_RANGE;

	static {
		final Set<NullDataType> nRange = new HashSet<NullDataType>();
		NULL_RANGE = new DiscreteRange<NullDataType>(
				NullDataType.class, nRange);
	}

	/**
	 * Constructor.
	 * @param id A machine readable string identifying this visual property used for XML serialization. 
	 * @param displayName A human readable string used for displays and user interfaces. 
	 */
	public NullVisualProperty(final String id, final String displayName) {
		super(dummyObject, NULL_RANGE, id, displayName, CyIdentifiable.class);
	}

	
	@Override
	public String toSerializableString(final NullDataType value) {
		return value.toString();
	}

	
	@Override
	public NullDataType parseSerializableString(final String text) {
		return dummyObject;
	}
	
	private final static class NullDataTypeImpl implements NullDataType {
		// Dummy class.  Currently this does nothing.
	}
}
