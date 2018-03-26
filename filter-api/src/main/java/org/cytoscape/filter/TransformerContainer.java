package org.cytoscape.filter;

import org.cytoscape.filter.model.NamedTransformer;

/**
 * A class that contains and manages a list of Transformers.
 * 
 * Typically these transformers will show up in the Select tab of the Cytoscape UI.
 * 
 * How to acquire this service in your CyActivator:
 * <pre>
 * TransformerContainer&lt;CyNetwork,CyIdentifiable&gt; filterContainer = getService(TransformerContainer.class, "(container.type=filter)");
 * TransformerContainer&lt;CyNetwork,CyIdentifiable&gt; chainTransformerContainer = getService(TransformerContainer.class, "(container.type=chain)");
 * </pre>
 * 
 * @param <C> The context type for transformers this TransformerContainer contains.
 * @param <E> The element type for transformers this TransformerContainer contains.
 */
public interface TransformerContainer<C,E> {
	
	/**
	 * Add a NamedTransformer to this container.
	 * 
	 * A NamedTransformer can be created using {@link TransformerManager#createNamedTransformer(String, java.util.List)}
	 * or loaded from a JSON file using CyTransformerReader.
	 * 
	 * @see TransformerManager#createNamedTransformer(String, java.util.List)
	 * @see CyTransformerReader
	 */
	void addNamedTransformer(NamedTransformer<C, E> transformer);
	
	/**
	 * Remove a transformer from this container.
	 * 
	 * @return true if the transformer with the given name exists and was removed, false otherwise.
	 */
	boolean removeNamedTransformer(String name);
	
	/**
	 * Returns the transformer with the given name, or null if a transformer with the given name
	 * does not exist in this container.
	 */
	NamedTransformer<C, E> getNamedTransformer(String name);

}
