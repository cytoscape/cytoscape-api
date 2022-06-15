package org.cytoscape.io.read;

/*
 * #%L
 * Cytoscape IO API (io-api)
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

import java.util.Set;

import org.cytoscape.view.vizmap.StyleAssociation;
import org.cytoscape.view.vizmap.VisualStyle;
import org.cytoscape.work.Task;

/**
 * An extension of the Task interface that returns a 
 * {@link org.cytoscape.view.vizmap.VisualStyle} object. 
 * Instances of this interface are created by {@link org.cytoscape.io.read.InputStreamTaskFactory}
 * objects registered as OSGi services, which are in turn processed
 * by associated reader manager objects that distinguish 
 * InputStreamTaskFactories based on the {@link org.cytoscape.io.DataCategory} associated with
 * the {@link org.cytoscape.io.CyFileFilter}.
 * @CyAPI.Spi.Interface
 * @CyAPI.InModule io-api
 */
public interface VizmapReader extends Task {

	/**
	 * Returns a list of network {@link org.cytoscape.view.vizmap.VisualStyle} objects.
	 * @return A list of network {@link org.cytoscape.view.vizmap.VisualStyle} objects.
	 */
	Set<VisualStyle> getVisualStyles();
	
	/**
	 * Returns a list of table {@link org.cytoscape.view.vizmap.VisualStyle} objects.
	 * @return A list of table {@link org.cytoscape.view.vizmap.VisualStyle} objects.
	 */
	Set<VisualStyle> getTableVisualStyles();
	
	default Set<StyleAssociation> getColumnStyleAssociations() {
		return null;
	}
}
