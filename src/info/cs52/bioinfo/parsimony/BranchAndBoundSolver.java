package info.cs52.bioinfo.parsimony;

import java.util.ArrayList;
import java.util.List;

public class BranchAndBoundSolver {

	private List<UndirectedTreeEdge> edges;
	private List<Integer> config;
	private List<Integer> bestConfig;
	private int bestValue;
	private UnrootedTree tree;
	private List<String> sequences;
	private FitchSolver fitch = new FitchSolver();
	
	public UnrootedTree solve(List<String> sequences) {
		this.sequences = sequences;
		edges = new ArrayList<UndirectedTreeEdge>();
		tree = new UnrootedTree();
		backtrack(3);
		return getBestTree();
	}

	private UnrootedTree getBestTree() {
		UnrootedTree bestTree = new UnrootedTree();
		edges = new ArrayList<UndirectedTreeEdge>();
		addFirstThreeNode(bestTree);
		for (int l = 0; l < bestConfig.size(); l++) {
			int i = config.get(l);
			UnrootedTreeNode a = edges.get(i).getNode1();
			UnrootedTreeNode b = edges.get(i).getNode2();
			UnrootedTreeNode c = new UnrootedTreeNode(null);
			UnrootedTreeNode d = new UnrootedTreeNode(sequences.get(l+3));
			bestTree.removeEdge(a, b);
			edges.add(bestTree.addEdge(a, c));
			edges.add(bestTree.addEdge(b, c));
			edges.set(i, bestTree.addEdge(c, d));
		}
		return bestTree;
	}

	private void addFirstThreeNode(UnrootedTree bestTree) {
		UnrootedTreeNode node0 = new UnrootedTreeNode(sequences.get(0));
		UnrootedTreeNode node1 = new UnrootedTreeNode(sequences.get(1));
		UnrootedTreeNode node2 = new UnrootedTreeNode(sequences.get(2));
		UnrootedTreeNode node3 = new UnrootedTreeNode(null);
		bestTree.addNode(node0);
		bestTree.addNode(node1);
		bestTree.addNode(node2);
		bestTree.addNode(node3);
		edges.add(bestTree.addEdge(node0, node3));
		edges.add(bestTree.addEdge(node1, node3));
		edges.add(bestTree.addEdge(node2, node3));
	}
	
	public int getBestValue() {
		return bestValue;
	}

	private void backtrack(int l) {
		if (l >= sequences.size()-3) {
			int value = fitch.solve(tree);
			if (value < bestValue) {
				bestConfig = new ArrayList<Integer>(config); 
				bestValue = value;
			}
			return;
		}
		int k = l * 2 + 3;
		
		
	}
	
}
