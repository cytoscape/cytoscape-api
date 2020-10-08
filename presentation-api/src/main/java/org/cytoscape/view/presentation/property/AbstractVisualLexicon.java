package org.cytoscape.view.presentation.property;

import java.awt.Color;
import java.awt.Paint;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.cytoscape.view.model.ContinuousRange;
import org.cytoscape.view.model.DiscreteRange;
import org.cytoscape.view.model.NullDataType;
import org.cytoscape.view.model.Range;
import org.cytoscape.view.model.VisualLexicon;
import org.cytoscape.view.model.VisualLexiconNode;
import org.cytoscape.view.model.VisualProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Reusable template implementation of VisualLexicon.
 * 
 * @CyAPI.InModule presentation-api
 */
public abstract class AbstractVisualLexicon implements VisualLexicon {

	private static final Logger logger = LoggerFactory.getLogger("org.cytoscape.application.userlog");

	
	private final Map<Class<?>, Map<String, VisualProperty<?>>> identifierLookup;
	private final Map<VisualProperty<?>, VisualLexiconNode> visualPropertyMap;
	
	/**
	 * The Root of this tree.
	 */
	protected final VisualProperty<NullDataType> rootVisualProperty;
	
	// TODO move these!
	protected static final Color MIN_COLOR = new Color(0, 0, 0);
	protected static final Color MAX_COLOR = new Color(0xFF, 0xFF, 0xFF);
	protected static final Range<Paint> PAINT_RANGE = new ContinuousRange<>(Paint.class, MIN_COLOR, MAX_COLOR,
			true, true);

	protected static final Set<String> STRING_SET = new HashSet<>();
	
	// This will be used to for String VP which accepts any string values.
	protected static final Range<String> ARBITRARY_STRING_RANGE = new DiscreteRange<String>(String.class, STRING_SET) {
		// Takes any String as valid value.
		@Override
		public boolean inRange(String value) {
			return true;
		}
	};

	protected static final Range<Double> ARBITRARY_DOUBLE_RANGE = new ContinuousRange<>(Double.class,
			Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, true, true);

	protected static final Range<Double> NONE_ZERO_POSITIVE_DOUBLE_RANGE = new ContinuousRange<>(Double.class,
			0d, Double.POSITIVE_INFINITY, false, true);

	protected static final Range<Double> ANGLE_DOUBLE_RANGE = new ContinuousRange<>(Double.class,
			-360d, 360d, false, true);
	
	
	public AbstractVisualLexicon(final VisualProperty<NullDataType> rootVisualProperty) {
		this.visualPropertyMap = new HashMap<>();
		this.rootVisualProperty = rootVisualProperty;
		final VisualLexiconNode rootNode = new VisualLexiconNode(rootVisualProperty, null);

		visualPropertyMap.put(rootVisualProperty, rootNode);

		this.identifierLookup = new HashMap<>();
		for(Class<?> cls : getTypes()) {
			this.identifierLookup.put(cls, new HashMap<>());
		}

		addVisualProperties(rootVisualProperty);
	}

	protected abstract Class<?>[] getTypes();
	protected abstract void addVisualProperties(VisualProperty<NullDataType> root);
	
	@Override
	public final Set<VisualProperty<?>> getAllVisualProperties() {
		return new HashSet<>(visualPropertyMap.keySet());
	}

	@Override
	public final Collection<VisualProperty<?>> getAllDescendants(final VisualProperty<?> prop) {
		if (prop == null)
			throw new NullPointerException("Target visual property cannot be null.");

		if (!this.visualPropertyMap.containsKey(prop)) {
			throw new IllegalArgumentException("No such Visual Property in the Lexicon: "+prop.getDisplayName());
		}

		return getChildNodes(prop);
	}

	@Override
	public final VisualProperty<NullDataType> getRootVisualProperty() {
		return this.rootVisualProperty;
	}

	private Set<VisualProperty<?>> getChildNodes(VisualProperty<?> prop) {
		final VisualLexiconNode node = visualPropertyMap.get(prop);
		final Set<VisualProperty<?>> children = new HashSet<>();

		// if this is a leaf node, return empty set
		if (node.getChildren().size() == 0)
			return children;

		Collection<VisualLexiconNode> currentChildren = node.getChildren();
		for (VisualLexiconNode nd : currentChildren)
			children.add(nd.getVisualProperty());

		for (VisualLexiconNode nd : currentChildren)
			children.addAll(getChildNodes(nd.getVisualProperty()));

		return children;
	}

	/**
	 * Insert a {@link VisualProperty} to the tree.
	 * 
	 * @param vp
	 *            the VisualProperty to insert in the tree.
	 * @param parent
	 *            the parent of the VisualProperty to insert.
	 */
	protected final void addVisualProperty(final VisualProperty<?> vp, final VisualProperty<?> parent) {
		if (this.visualPropertyMap.containsKey(vp))
			throw new IllegalStateException("The key " + vp.getIdString() + " already exists in the lexicon.");

		if (parent == null)
			throw new NullPointerException("Parent cannot be null.");

		final VisualLexiconNode parentNode = this.visualPropertyMap.get(parent);

		if (parentNode == null)
			throw new IllegalArgumentException("Parent does not exist in the lexicon: " + parent.getDisplayName());

		final VisualLexiconNode newNode = new VisualLexiconNode(vp, parentNode);
		this.visualPropertyMap.put(vp, newNode);

		addIdentifierMapping(vp.getTargetDataType(), vp.getIdString(), vp);
	}

	@Override
	public final VisualLexiconNode getVisualLexiconNode(final VisualProperty<?> vp) {
		return this.visualPropertyMap.get(vp);
	}

	@Override
	public final VisualProperty<?> lookup(final Class<?> type, final String id) {
		if (id == null || type == null)
			return null;

		Map<String, VisualProperty<?>> map = identifierLookup.get(type);
		if (map == null)
			return null;

		return map.get(id.toLowerCase());
	}

	@Override
	public boolean isSupported(VisualProperty<?> vp) {
		return visualPropertyMap.containsKey(vp);
	}

	@Override
	public <T> Set<T> getSupportedValueRange(VisualProperty<T> vp) {
		final Range<T> range = vp.getRange();

		if (range.isDiscrete())
			return ((DiscreteRange<T>)range).values();

		return Collections.emptySet();
	}

	/**
	 * @param type
	 * @param id
	 * @param vp
	 */
	protected final void addIdentifierMapping(final Class<?> type, final String id, final VisualProperty<?> vp) {
		if (type == null) {
			logger.warn("attempting to add VisualLexicon identifier lookup mapping with null type");
			return;
		}
		if (id == null) {
			logger.warn("attempting to add VisualLexicon identifier lookup mapping with null id");
			return;
		}
		if (vp == null) {
			logger.warn("attempting to add VisualLexicon identifier lookup mapping with null visual property");
			return;
		}
		Map<String, VisualProperty<?>> map = identifierLookup.get(type);
		if (map == null) {
			logger.warn("attempting to add VisualLexicon identifier lookup mapping with unrecognized type: "
					+ type.getClass().getName() + "(expect: " + identifierLookup.keySet().toString() + ")");
			return;
		}
		map.put(id.toLowerCase(), vp);
	}
}
