package org.cytoscape.equations;

import java.util.Stack;

/**
 * The interface for a node within the Equation parse tree.
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
