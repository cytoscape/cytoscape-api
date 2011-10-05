package org.cytoscape.view.presentation.property;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.cytoscape.view.model.NullDataType;
import org.cytoscape.view.model.VisualLexicon;
import org.cytoscape.view.model.VisualLexiconNode;
import org.cytoscape.view.model.VisualProperty;

import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyEdge;
import org.cytoscape.model.CyNetwork;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementations for common features for all VisualLexicons.
 *
 */
public abstract class AbstractVisualLexicon implements VisualLexicon {

	private static final Logger logger = LoggerFactory.getLogger(AbstractVisualLexicon.class);

	private final Map<Class<?>,Map<String,VisualProperty<?>>> identifierLookup;
	
	//
	private final Map<VisualProperty<?>, VisualLexiconNode> visualPropertyMap;
	
	/**
	 * The Root of this tree.
	 */
	protected final VisualProperty<NullDataType> rootVisualProperty;
	

	/**
	 * Constructor for VisualLexicon.  The parameters are required for all lexicons.
	 * 
	 * @param rootVisualProperty Root of the visual property tree.
	 * @param nodeFactory factory to create tree nodes for a lexicon.
	 */
	public AbstractVisualLexicon(final VisualProperty<NullDataType> rootVisualProperty) {
		
		this.visualPropertyMap = new HashMap<VisualProperty<?>, VisualLexiconNode>();
		this.rootVisualProperty = rootVisualProperty;
		final VisualLexiconNode rootNode = new VisualLexiconNode(rootVisualProperty, null);
		
		visualPropertyMap.put(rootVisualProperty, rootNode);

		this.identifierLookup = new HashMap<Class<?>,Map<String,VisualProperty<?>>>();
		this.identifierLookup.put(CyNode.class,new HashMap<String,VisualProperty<?>>());
		this.identifierLookup.put(CyEdge.class,new HashMap<String,VisualProperty<?>>());
		this.identifierLookup.put(CyNetwork.class,new HashMap<String,VisualProperty<?>>());
	}

	
	@Override public Set<VisualProperty<?>> getAllVisualProperties() {
		return new HashSet<VisualProperty<?>>(visualPropertyMap.keySet());
	}

	
	@Override public Collection<VisualProperty<?>> getAllDescendants(final VisualProperty<?> prop) {
		if(prop == null)
			throw new NullPointerException("Target visual property cannot be null.");
		
		if(!this.visualPropertyMap.containsKey(prop))
			throw new IllegalArgumentException("No such Visual Property in the Lexicon.");
		
		return getChildNodes(prop);
	}
	

	@Override public VisualProperty<NullDataType> getRootVisualProperty() {
		return this.rootVisualProperty;
	}
	
	
	private Set<VisualProperty<?>> getChildNodes(VisualProperty<?> prop) {
		final VisualLexiconNode node = visualPropertyMap.get(prop);
		final Set<VisualProperty<?>> children = new HashSet<VisualProperty<?>>();
		
		// if this is a leaf node, return empty set
		if(node.getChildren().size() == 0)
			return children;
		
		Collection<VisualLexiconNode> currentChildren = node.getChildren();
		for(VisualLexiconNode nd: currentChildren)
			children.add(nd.getVisualProperty());

		for(VisualLexiconNode nd: currentChildren)
			children.addAll(getChildNodes(nd.getVisualProperty()));
		
		return children;
	}
	
	/**
	 * Insert a Visual Property to the tree.
	 * 
	 * @param vp
	 * @param parent
	 */
	protected void addVisualProperty(final VisualProperty<?> vp, final VisualProperty<?> parent) {
		if(this.visualPropertyMap.containsKey(vp))
			throw new IllegalStateException("The key " + vp.getIdString() + " already exists in the lexicon.");
		
		if(parent == null)
			throw new NullPointerException("Parent cannot be null.");
		
		final VisualLexiconNode parentNode = this.visualPropertyMap.get(parent);
		
		if(parentNode == null)
			throw new IllegalArgumentException("Parent does not exist in the lexicon: " + parent.getDisplayName());
		
		final VisualLexiconNode newNode = new VisualLexiconNode(vp, parentNode);
		this.visualPropertyMap.put(vp, newNode);
		
		addIdentifierMapping(vp.getTargetDataType(), vp.getIdString(), vp);
	}
	
	
	@Override public VisualLexiconNode getVisualLexiconNode(final VisualProperty<?> vp) {
		return this.visualPropertyMap.get(vp);
	}

	@Override public VisualProperty<?> lookup(final Class<?> type, final String id) {
		if ( id == null || type == null )
			return null;

		Map<String,VisualProperty<?>> map = identifierLookup.get(type);
		if ( map == null )
			return null;

		return map.get(id.toLowerCase());
	}
	
	@Override
	public boolean isSupported(VisualProperty<?> vp) {
		return visualPropertyMap.containsKey(vp);
	}

	protected final void addIdentifierMapping(final Class<?> type, final String id, final VisualProperty<?> vp) {
		if ( type == null ) {
			logger.warn("attempting to add VisualLexicon identifier lookup mapping with null type");
			return;
		}
		if ( id == null ) {
			logger.warn("attempting to add VisualLexicon identifier lookup mapping with null id");
			return;
		}
		if ( vp == null ) {
			logger.warn("attempting to add VisualLexicon identifier lookup mapping with null visual property");
			return;
		}
		Map<String,VisualProperty<?>> map = identifierLookup.get(type);
		if ( map == null ) {
			logger.warn("attempting to add VisualLexicon identifier lookup mapping with unrecognized type: " + type.getClass().getName() + "(expect: " + identifierLookup.keySet().toString() + ")" );
			return;
		}
		map.put(id.toLowerCase(), vp);
	}
}
