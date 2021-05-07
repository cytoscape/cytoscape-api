package org.cytoscape.equations;

/*
 * #%L
 * Cytoscape Equations API (equations-api)
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2010 - 2021 The Cytoscape Consortium
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

import java.util.Stack;


/**
 * A node in the parse tree.
 * @CyAPI.Abstract.Class
 * @CyAPI.InModule equations-api
 */
public abstract class AbstractNode implements TreeNode {
	private final int sourceLocation; // What location the "source code" is this associated with.

	/** Base class constructor for any <code>Node</code> type.
	 *  @param sourceLocation  start of the location in the equation where the code was found
	 *                         that was turned into a node in the parse tree
	 */
	public AbstractNode(final int sourceLocation) {
		this.sourceLocation = sourceLocation;
	}

	/* (non-Javadoc)
	 * @see org.cytoscape.equations.TreeNode#getSourceLocation()
	 */
	@Override
	public final int getSourceLocation() {
		return sourceLocation;
	}

	/** Returns s <code>String</code> representation of this node.
	 *  @return a textual representation of this node
	 *  Note: This is typically only used for debugging.
	 */
	public abstract String toString();

	/* (non-Javadoc)
	 * @see org.cytoscape.equations.TreeNode#getType()
	 */
	@Override
	public abstract Class getType(); // The type of this node.

	/* (non-Javadoc)
	 * @see org.cytoscape.equations.TreeNode#getLeftChild()
	 */
	@Override
	public abstract TreeNode getLeftChild();

	/* (non-Javadoc)
	 * @see org.cytoscape.equations.TreeNode#getRightChild()
	 */
	@Override
	public abstract TreeNode getRightChild();

	/* (non-Javadoc)
	 * @see org.cytoscape.equations.TreeNode#genCode(java.util.Stack)
	 */
	@Override
	public abstract void genCode(final Stack<CodeAndSourceLocation> codeStack);
}
