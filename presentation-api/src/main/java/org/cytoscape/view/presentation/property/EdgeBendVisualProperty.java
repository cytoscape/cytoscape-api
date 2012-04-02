package org.cytoscape.view.presentation.property;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.cytoscape.model.CyEdge;
import org.cytoscape.view.model.AbstractVisualProperty;
import org.cytoscape.view.model.ContinuousRange;
import org.cytoscape.view.model.Range;
import org.cytoscape.view.presentation.property.values.Bend;
import org.cytoscape.view.presentation.property.values.BendFactory;
import org.cytoscape.view.presentation.property.values.Handle;

public class EdgeBendVisualProperty extends AbstractVisualProperty<Bend> {

	private static final Range<Bend> EDGE_BEND_RANGE;
	public static final Bend DEFAULT_EDGE_BEND = new EmptyBendImpl();

	static {
		EDGE_BEND_RANGE = new ContinuousRange<Bend>(Bend.class, DEFAULT_EDGE_BEND, DEFAULT_EDGE_BEND, true, true);
	}
	
	private BendFactory bendFactory;

	public EdgeBendVisualProperty(Bend defaultValue, String id, String displayName) {
		super(defaultValue, EDGE_BEND_RANGE, id, displayName, CyEdge.class);
	}
	
	public void setBendFactory(final BendFactory bendFactory) {
		this.bendFactory = bendFactory;
	}

	@Override
	public String toSerializableString(final Bend value) {
		return value.getSerializableString();
	}

	@Override
	public Bend parseSerializableString(String value) {
		if(value == null)
			return DEFAULT_EDGE_BEND;
		else
			return bendFactory.parseSerializableString(value);
	}
	
	
	/**
	 * Immutable default Bend object w/no handle.
	 */
	private static final class EmptyBendImpl implements Bend {

		@Override
		public List<Handle> getAllHandles() {
			return Collections.emptyList();
		}

		@Override
		public void insertHandleAt(int index, Handle handle) {
			throw new UnsupportedOperationException("This is a default immutable Bend object.");
		}

		@Override
		public void removeHandleAt(int handleIndex) {
			throw new UnsupportedOperationException("This is a default immutable Bend object.");
		}

		@Override
		public void removeAllHandles() {
			throw new UnsupportedOperationException("This is a default immutable Bend object.");
		}

		@Override
		public int getIndex(Handle handle) {
			throw new UnsupportedOperationException("This is a default immutable Bend object.");
		}

		@Override
		public String getSerializableString() {
			throw new UnsupportedOperationException("This is a default immutable Bend object.");
		}
	}
}
