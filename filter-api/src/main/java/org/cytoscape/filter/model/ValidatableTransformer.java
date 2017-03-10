package org.cytoscape.filter.model;

import java.util.List;


/**
 * A marker interface that can be added to any {@link Transformer} to indicate
 * that the transformer may be invalid in certain contexts.
 * 
 * For example a ColumnFilter is invalid when the current network
 * does not contain the column referenced by the filter.
 * 
 * Validation runs whenever the context changes (for example when the current network changes)
 * or when the {@link TransformerListener}s for this transformer are notified.
 * 
 * @see Transformer#addListener(TransformerListener)
 */
public interface ValidatableTransformer<C, E> extends Transformer<C, E> {
	
	/**
	 * Called whenever the context changes (for example when the current network changes)
	 * or when the {@link TransformerListener}s for this transformer are notified.
	 * 
	 * @returns If the Transformer is invalid then return a non-empty list of warning messages. 
	 * If the Transformer is valid then return an empty list.
	 */
	List<ValidationWarning> validate(C context);
	
}
