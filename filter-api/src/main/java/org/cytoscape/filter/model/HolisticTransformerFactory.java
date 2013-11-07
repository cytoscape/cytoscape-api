package org.cytoscape.filter.model;


public interface HolisticTransformerFactory<C, E> extends TransformerFactory<C, E> {
	HolisticTransformer<C, E> createHolisticTransformer();
}
