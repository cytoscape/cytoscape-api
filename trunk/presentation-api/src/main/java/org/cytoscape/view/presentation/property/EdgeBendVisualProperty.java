package org.cytoscape.view.presentation.property;

import java.util.Collections;
import java.util.List;

import org.cytoscape.model.CyEdge;
import org.cytoscape.view.model.AbstractVisualProperty;
import org.cytoscape.view.model.ContinuousRange;
import org.cytoscape.view.model.Range;
import org.cytoscape.view.presentation.property.values.Bend;
import org.cytoscape.view.presentation.property.values.BendFactory;
import org.cytoscape.view.presentation.property.values.Handle;

/**
 * Visual Property for Edge {@link Bend} values.
 * 
 * @CyAPI.Final.Class
 */
public class EdgeBendVisualProperty extends AbstractVisualProperty<Bend> {

	/**
	 * The default edge bend.  This "bend" is empty and contains no actual bend.
	 */
	public static final Bend DEFAULT_EDGE_BEND = new EmptyBendImpl();

	private static final Range<Bend> EDGE_BEND_RANGE = new ContinuousRange<Bend>(Bend.class, DEFAULT_EDGE_BEND, DEFAULT_EDGE_BEND, true, true);

	private BendFactory bendFactory;

	/**
	 * Constructor.
	 * @param defaultValue The default bend to use.
	 * @param id A machine readable string identifying this visual property used for XML serialization. 
	 * @param displayName A human readable string used for displays and user interfaces. 
	 */
	public EdgeBendVisualProperty(Bend defaultValue, String id, String displayName) {
		super(defaultValue, EDGE_BEND_RANGE, id, displayName, CyEdge.class);
	}

	/**
	 * Sets the bend factory that is used behind the scenes to create bend objects.
	 * @param bendFactory The bend factory to use for this instance.
	 */
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
			return -1;
		}

		@Override
		public String getSerializableString() {
			return null;
		}
	}
}
