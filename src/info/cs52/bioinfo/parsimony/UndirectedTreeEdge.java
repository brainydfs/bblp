package info.cs52.bioinfo.parsimony;

public class UndirectedTreeEdge {

	private UnrootedTreeNode node1;
	private UnrootedTreeNode node2;

	public UndirectedTreeEdge(UnrootedTreeNode node1, UnrootedTreeNode node2) {
		super();
		this.node1 = node1;
		this.node2 = node2;
	}

	public UnrootedTreeNode getNode1() {
		return node1;
	}

	public UnrootedTreeNode getNode2() {
		return node2;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof UndirectedTreeEdge) {
			UndirectedTreeEdge edge = (UndirectedTreeEdge) obj;
			return (node1.equals(edge.node1) && node2.equals(edge.node2)) ||
					(node2.equals(edge.node1) && node1.equals(edge.node2));
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return node1.hashCode() + node2.hashCode();
	}
	
}
