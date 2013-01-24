package org.cytoscape.view.presentation.property;

/*
 * #%L
 * Cytoscape Presentation API (presentation-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2013 The Cytoscape Consortium
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

import org.cytoscape.view.model.VisualLexicon;
import org.cytoscape.view.model.VisualLexiconNode;
import org.cytoscape.view.model.VisualProperty;

/**
 * Utility functions for traversing visual lexicon tree.
 * 
 * @CyAPI.Static.Class
 * @CyAPI.InModule presentation-api
 */
public final class VisualPropertyUtil {
	
	/**
	 * So no one else can contruct this!
	 */
	private VisualPropertyUtil() {}

	/**
	 * Check whether the given {@linkplain VisualProperty} is a child of parent vp or not.
	 * 
	 * @param parent parent visual property
	 * @param vp visual property to be tested
	 * @param lexicon lexicon tree
	 * 
	 * @return true if it's a child of the parent or parent itself.  Otherwise, return false.
	 *  
	 */
	public static boolean isChildOf(final VisualProperty<?> parent, final VisualProperty<?> vp,
			final VisualLexicon lexicon) {
		
		if(vp == null)
			throw new NullPointerException("Visual Property is null.");
		
		if(lexicon == null)
			throw new NullPointerException("Lexicon is null.");
		
		if(!lexicon.getAllVisualProperties().contains(vp))
			throw new IllegalArgumentException("No such Visual Porperty in the lexicon: " + vp.getDisplayName());
		
		
		VisualLexiconNode node = lexicon.getVisualLexiconNode(vp);
		
		// This is a root
		if(node.getParent() == null)
			return false;

		if (vp.equals(parent) || node.getParent().getVisualProperty().equals(parent))
			return true;

		
		while (node.getParent() != null) {
			node = node.getParent();
			if (node.getVisualProperty() == parent)
				return true;
		}

		return false;
	}
}
