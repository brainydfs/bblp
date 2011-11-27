package info.cs52.bioinfo.parsimony;

import java.util.HashSet;
import java.util.Set;

public class UnrootedTreeNode {

	private String sequence;
	Set<UndirectedTreeEdge> edges = new HashSet<UndirectedTreeEdge>();
	
	public UnrootedTreeNode(String sequence) {
		super();
		this.sequence = sequence;
	}

	public String getSequence() {
		return sequence;
	}
	
	public Set<UndirectedTreeEdge> getEdges() {
		return edges;
	}
	
}
