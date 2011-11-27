package info.cs52.bioinfo.parsimony;

import java.util.HashSet;
import java.util.Set;

public class UnrootedTree {

	private Set<UnrootedTreeNode> nodes = new HashSet<UnrootedTreeNode>();

	public void addNode(UnrootedTreeNode node) {
		nodes.add(node);
	}
	
	public UndirectedTreeEdge addEdge(UnrootedTreeNode a, UnrootedTreeNode b) {
		UndirectedTreeEdge edge = new UndirectedTreeEdge(a, b);
		a.edges.add(edge);
		b.edges.add(edge);
		return edge;
	}

	public void removeEdge(UndirectedTreeEdge edge) {
		edge.getNode1().edges.remove(edge);
		edge.getNode2().edges.remove(edge);
	}
	
	public void removeEdge(UnrootedTreeNode a, UnrootedTreeNode b) {
		UndirectedTreeEdge edge = new UndirectedTreeEdge(a, b);
		a.edges.remove(edge);
		b.edges.remove(edge);
	}
	
}
