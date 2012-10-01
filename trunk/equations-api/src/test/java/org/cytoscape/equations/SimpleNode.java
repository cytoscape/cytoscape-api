package org.cytoscape.equations;


import java.util.Stack;


class SimpleNode extends AbstractNode {
	SimpleNode(final int sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public String toString() { return null; }

	@Override
	public Class getType() { return null; }

	@Override
	public TreeNode getLeftChild() { return null; }

	@Override
	public TreeNode getRightChild() { return null; }

	@Override
	public void genCode(final Stack<CodeAndSourceLocation> codeStack) { }
}