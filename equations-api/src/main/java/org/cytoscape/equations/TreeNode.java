package org.cytoscape.equations;

/*
 * #%L
 * Cytoscape Equations API (equations-api)
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

import java.util.Stack;

/**
 * The interface for a node within the Equation parse tree.
 * @CyAPI.Api.Interface
 * @CyAPI.InModule equations-api
 */
public interface TreeNode {

	/** Returns the start of the location in the equation where the code was found that was
	 *  turned into a node in the parse tree.
	 *  @return the location in the source code that resulted in this node
	 */
	int getSourceLocation();

	/** Returns the type of this node.
	 *  @return the type of the result of the code generated from this node will result in 
	 */
	Class getType(); // The type of this node.

	/** Returns the left child if it exists or null if it doesn't.
	 *  @return the left child, if any, of this node in the parse tree
	 */
	TreeNode getLeftChild();

	/** Returns the right child if it exists or null if it doesn't.
	 *  @return the right child, if any, of this node in the parse tree
	 */
	TreeNode getRightChild();

	/** Generated code for this node and pushes it onto the execution stack. 
	 * @param codeStack the execution stack to push the generated code for this node.
	 */
	void genCode(final Stack<CodeAndSourceLocation> codeStack);

}
