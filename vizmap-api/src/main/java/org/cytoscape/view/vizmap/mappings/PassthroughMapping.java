package org.cytoscape.view.vizmap.mappings;

/*
 * #%L
 * Cytoscape VizMap API (vizmap-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2021 The Cytoscape Consortium
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

import org.cytoscape.view.vizmap.VisualMappingFunction;

/**
 * An interface describing a passthrough or identity mapping from attribute value
 * to visual property.
 * 
 *  <p>Fully Supported Node Visual Properties
 *   <ul>
 *     <li>{@link org.cytoscape.view.presentation.property.BasicVisualLexicon#NODE_FILL_COLOR}</li>
 *     <li>{@link org.cytoscape.view.presentation.property.BasicVisualLexicon#NODE_BORDER_LINE_TYPE}</li>
 *     <li>{@link org.cytoscape.view.presentation.property.BasicVisualLexicon#NODE_BORDER_PAINT}</li>
 *     <li>{@link org.cytoscape.view.presentation.property.BasicVisualLexicon#NODE_BORDER_WIDTH}</li>
 *     <li>{@link org.cytoscape.view.presentation.property.BasicVisualLexicon#NODE_BORDER_TRANSPARENCY}</li>
 *     <li>{@link org.cytoscape.view.presentation.property.BasicVisualLexicon#NODE_LABEL}</li>
 *     <li>{@link org.cytoscape.view.presentation.property.BasicVisualLexicon#NODE_LABEL_COLOR}</li>
 *     <li>{@link org.cytoscape.view.presentation.property.BasicVisualLexicon#NODE_LABEL_FONT_FACE}</li>
 *     <li>{@link org.cytoscape.view.presentation.property.BasicVisualLexicon#NODE_LABEL_FONT_SIZE}</li>
 *     <li>{@link org.cytoscape.view.presentation.property.BasicVisualLexicon#NODE_LABEL_WIDTH}</li>
 *     <li>{@link org.cytoscape.view.presentation.property.BasicVisualLexicon#NODE_LABEL_TRANSPARENCY}</li>
 *     <li>{@link org.cytoscape.view.presentation.property.BasicVisualLexicon#NODE_SIZE}</li>
 *     <li>{@link org.cytoscape.view.presentation.property.BasicVisualLexicon#NODE_WIDTH}</li>
 *     <li>{@link org.cytoscape.view.presentation.property.BasicVisualLexicon#NODE_HEIGHT}</li>
 *     <li>{@link org.cytoscape.view.presentation.property.BasicVisualLexicon#NODE_TRANSPARENCY}</li>
 *     <li>{@link org.cytoscape.view.presentation.property.BasicVisualLexicon#NODE_SHAPE}</li>
 *     <li>{@link org.cytoscape.view.presentation.property.BasicVisualLexicon#NODE_TOOLTIP}</li>
 *     <li>{@link org.cytoscape.view.presentation.property.BasicVisualLexicon#NODE_LABEL_FONT_FACE}</li>
 *     <li>{@link org.cytoscape.view.presentation.property.BasicVisualLexicon#NODE_NESTED_NETWORK_IMAGE_VISIBLE}</li>
 *   </ul>
 *   </p>
 *   <p>Fully Supported Edge Visual Properties
 *    <ul>
 *     <li>{@link org.cytoscape.view.presentation.property.BasicVisualLexicon#EDGE_PAINT}</li>
 *     <li>{@link org.cytoscape.view.presentation.property.BasicVisualLexicon#EDGE_TRANSPARENCY}</li>
 *     <li>{@link org.cytoscape.view.presentation.property.BasicVisualLexicon#EDGE_LABEL}</li>
 *     <li>{@link org.cytoscape.view.presentation.property.BasicVisualLexicon#EDGE_LABEL_COLOR}</li>
 *     <li>{@link org.cytoscape.view.presentation.property.BasicVisualLexicon#EDGE_LABEL_TRANSPARENCY}</li>
 *     <li>{@link org.cytoscape.view.presentation.property.BasicVisualLexicon#EDGE_WIDTH}</li>
 *     <li>{@link org.cytoscape.view.presentation.property.BasicVisualLexicon#EDGE_LABEL_FONT_SIZE}</li>
 *     <li>{@link org.cytoscape.view.presentation.property.BasicVisualLexicon#EDGE_LABEL_WIDTH}</li>
 *     <li>{@link org.cytoscape.view.presentation.property.BasicVisualLexicon#EDGE_LINE_TYPE}</li>
 *     <li>{@link org.cytoscape.view.presentation.property.BasicVisualLexicon#EDGE_SOURCE_ARROW_SHAPE}</li>
 *     <li>{@link org.cytoscape.view.presentation.property.BasicVisualLexicon#EDGE_TARGET_ARROW_SHAPE}</li>
 *     <li>{@link org.cytoscape.view.presentation.property.BasicVisualLexicon#EDGE_TOOLTIP}</li>
 *   </ul>
 * </p>
 * <p>Partially Supported Visual Properties
 * 	<ul>
 * 	
 *  </ul>
 * </p>
 * @param <K> Generic type of the attribute mapped.
 * @param <V> Generic type of the VisualProperty used in this mapping.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule vizmap-api
 */
public interface PassthroughMapping<K, V> extends VisualMappingFunction<K,V> {

	
	
	/** A label describing the mapping. */
	public static final String PASSTHROUGH = "Passthrough Mapping";

}
