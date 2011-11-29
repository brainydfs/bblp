package info.cs52.bioinfo.parsimony;

import java.util.HashSet;
import java.util.Set;

public class FitchSolver {

	private int score;

	public int solve(UnrootedTree tree, int sequenceLength, boolean setInternalNodeLabels) {
		UnrootedTreeNode a = Utils.getFirst(tree.getNodes());
		UnrootedTreeNode b = Utils.getOtherEnd(a, Utils.getFirst(a.getEdges()));
		score = 0;
		for (int i = 0; i < sequenceLength; i++) {
			Set<Character> r1 = solveAsRooted(a, b, i);
			Set<Character> r2 = solveAsRooted(b, a, i);
			if (Utils.intersect(r1, r2).isEmpty()) {
				score++;
			} 
		}
		return score;
	}

	@SuppressWarnings("unchecked")
	private Set<Character> solveAsRooted(
			UnrootedTreeNode node, UnrootedTreeNode parent, int i) {
		if (node.isLeaf()) {
			HashSet<Character> r = new HashSet<Character>();
			r.add(node.getSequence().charAt(i));
			return r;
		}
		Set<Character>[] r = new Set[2];
		int rIndex = 0;
		for (UndirectedTreeEdge edge : node.getEdges()) {
			UnrootedTreeNode child = Utils.getOtherEnd(node, edge);
			if (child == parent) {
				continue;
			}
			r[rIndex++] = solveAsRooted(child, node, i);
		}
		HashSet<Character> intersection = Utils.intersect(r[0], r[1]);
		if (intersection.isEmpty()) {
			r[0].addAll(r[1]);
			score++;
			return r[0];
		} 
		return intersection;
	}
	
}
