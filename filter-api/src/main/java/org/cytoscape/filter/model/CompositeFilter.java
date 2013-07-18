package org.cytoscape.filter.model;

public interface CompositeFilter<C, E> extends Filter<C, E> {
	enum Type {
		ANY,
		ALL
	}
	
	void append(Filter<C, E> filter);
	void insert(int index, Filter<C, E> filter);
	Filter<C, E> get(int index);
	Filter<C, E> remove(int index);
	int indexOf(Filter<C, E> filter);
	int getLength();
	Type getType();
	void setType(Type type);
}
