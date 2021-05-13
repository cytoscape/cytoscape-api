package org.cytoscape.view.model;

/*
 * #%L
 * Cytoscape View Model API (viewmodel-api)
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

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.function.Consumer;

/**
 * A node in the visual property tree (lexicon).
 * <p>
 * Wrapping a {@linkplain VisualProperty} and holding parent-child relationships.
 * <p>
 * All data fields are immutable. 
 * 
 * @CyAPI.Final.Class
 * @CyAPI.InModule viewmodel-api
 */
public final class VisualLexiconNode implements Comparable<VisualLexiconNode> {
	
	private final VisualProperty<?> vp;
	
	private final VisualLexiconNode parent;
	private final Collection<VisualLexiconNode> children;
		
	/**
	 * Constructs this VisualLexiconNode.
	 * @param vp the {@link VisualProperty} to wrap.
	 * @param parent this VisualLexiconNodes parent VisualLexiconNode.
	 */
	public VisualLexiconNode(final VisualProperty<?> vp, final VisualLexiconNode parent) {
		if(vp == null)
			throw new NullPointerException("Visual Property cannot be null.");
		
		this.vp = vp;
		this.parent = parent;
		this.children = new LinkedHashSet<>(); // apply VPs in consistent order
		
		if(parent != null)
			parent.getChildren().add(this);
	}

	
	/**
	 * Returns wrapped {@linkplain VisualProperty} object.
	 * 
	 * Since VisualProperty itself does not have any hierarchical structure, 
	 * such relationship is implemented by this wrapper.
	 * 
	 * @return wrapped {@linkplain VisualProperty} object.
	 */
	public VisualProperty<?> getVisualProperty() {
		return vp;
	}

	
	/**
	 * Get the parent of this VP node. The relationship is immutable, i.e.,
	 * cannot change parent/child relationship.
	 * 
	 * @return parent VisualProperty object.
	 */
	public VisualLexiconNode getParent() {
		return parent;
	}

	/**
	 * Returns collection of all children of this node.
	 * 
	 * @return collection of all children of this node.
	 */
	public Collection<VisualLexiconNode> getChildren() {
		return children;
	}


	/**
	 * {@inheritDoc}
	 * Compare by display name of this Visual Property.
	 */
	@Override
	public int compareTo(VisualLexiconNode other) {
		var n1 =  this.getVisualProperty().getDisplayName();
		var n2 = other.getVisualProperty().getDisplayName();
		return n1.compareTo(n2);
	}
	
	
	/**
	 * The given visitor function will be applied to this node and all its children recursively.
	 * @since 3.8
	 */
	public void visitDescendants(Consumer<VisualLexiconNode> visitor) {
		visitor.accept(this);
		for(var child : getChildren()) {
			child.visitDescendants(visitor);
		}
	}

}
