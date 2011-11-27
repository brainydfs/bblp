package info.cs52.bioinfo.parsimony;

public class RootedTreeNode {

	private String sequence;
	private RootedTreeNode left;	
	private RootedTreeNode right;
	private RootedTreeNode parent;

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	

	public RootedTreeNode getLeft() {
		return left;
	}

	public void setLeft(RootedTreeNode left) {
		if (left != null) {
			left.setParent(this);
		}
		this.left = left;
	}

	public RootedTreeNode getRight() {
		return right;
	}

	public void setRight(RootedTreeNode right) {
		if (right != null) {
			right.setParent(this);
		}
		this.right = right;
	}

	public RootedTreeNode getParent() {
		return parent;
	}

	public void setParent(RootedTreeNode parent) {
		this.parent = parent;
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
		if (left != null) {
			left.accept(visitor);
		}
		if (right != null) {
			right.accept(visitor);
		}
	}
	
}
