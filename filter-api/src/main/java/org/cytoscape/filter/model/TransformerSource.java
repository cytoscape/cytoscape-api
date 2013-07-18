package org.cytoscape.filter.model;

import java.util.List;

public interface TransformerSource<C, E> {
	List<E> getElementList(C context);

	Class<C> getContextType();
	Class<E> getElementType();
	int getElementCount(C context);
}
