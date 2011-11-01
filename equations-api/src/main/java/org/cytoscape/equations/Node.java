/*
  File: Node.java

  Copyright (c) 2010, The Cytoscape Consortium (www.cytoscape.org)

  This library is free software; you can redistribute it and/or modify it
  under the terms of the GNU Lesser General Public License as published
  by the Free Software Foundation; either version 2.1 of the License, or
  any later version.

  This library is distributed in the hope that it will be useful, but
  WITHOUT ANY WARRANTY, WITHOUT EVEN THE IMPLIED WARRANTY OF
  MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.  The software and
  documentation provided hereunder is on an "as is" basis, and the
  Institute for Systems Biology and the Whitehead Institute
  have no obligations to provide maintenance, support,
  updates, enhancements or modifications.  In no event shall the
  Institute for Systems Biology and the Whitehead Institute
  be liable to any party for direct, indirect, special,
  incidental or consequential damages, including lost profits, arising
  out of the use of this software and its documentation, even if the
  Institute for Systems Biology and the Whitehead Institute
  have been advised of the possibility of such damage.  See
  the GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with this library; if not, write to the Free Software Foundation,
  Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
*/
package org.cytoscape.equations;


import java.util.Stack;


/**
 *  A node in the parse tree.
 *  @CyAPI.Abstract.Class
 */
public abstract class Node {
	private final int sourceLocation; // What location the "source code" is this associated with.

	/** Base class constructor for any <code>Node</code> type.
	 *  @param sourceLocation  start of the location in the equation where the code was found
	 *                         that was turned into a node in the parse tree
	 */
	public Node(final int sourceLocation) {
		this.sourceLocation = sourceLocation;
	}

	/** Returns the start of the location in the equation where the code was found that was
	 *  turned into a node in the parse tree.
	 *  @return the location in the source code that resulted in this node
	 */
	public final int getSourceLocation() {
		return sourceLocation;
	}

	/** Returns s <code>String</code> representation of this node.
	 *  @return a textual representation of this node
	 *  Note: This is typically only used for debugging.
	 */
	public abstract String toString();

	/** Returns the type of this node.
	 *  @return the type of the result of the code generated from this node will result in 
	 */
	public abstract Class getType(); // The type of this node.

	/** Returns the left child if it exists or null if it doesn't.
	 *  @return the left child, if any, of this node in the parse tree
	 */
	public abstract Node getLeftChild();

	/** Returns the right child if it exists or null if it doesn't.
	 *  @return the right child, if any, of this node in the parse tree
	 */
	public abstract Node getRightChild();

	/** Generated code for this node and pushes it onto the execution stack. 
	 * @param codeStack the execution stack to push the generated code for this node.
	 */
	public abstract void genCode(final Stack<CodeAndSourceLocation> codeStack);
}
