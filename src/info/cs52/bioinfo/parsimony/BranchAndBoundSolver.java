package info.cs52.bioinfo.parsimony;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BranchAndBoundSolver {

	private List<UndirectedTreeEdge> edges;
	private int[] config;
	private int[] bestConfig;
	private int bestValue;
	private UnrootedTree tree;
	private String[] sequences;
	private FitchSolver fitch = new FitchSolver();
	
	public UnrootedTree solve(String[] sequences) {
		this.sequences = sequences;
		bestValue = Integer.MAX_VALUE;

		// init
		initMilometer();
		do {
			int value = fitch.solve(tree, sequences[0].length(), false);
//			System.out.println(Arrays.toString(
//					Arrays.copyOfRange(config, 1, config.length)) 
//					+ " : " + value);
//			new TreeWriter().write(tree, System.out);
			
			if (value > bestValue && config[config.length-1] == 0) {
				jumpMilometer();
			} else if (value < bestValue && config[config.length-1] > 0) {
				bestConfig = Arrays.copyOf(config, config.length);
				bestValue = value;
			}
		} while (increaseMilometer());
		
		rebuildBestTree();
		return tree;
	}

	private void initMilometer() {
		config = new int[sequences.length - 2];
		tree = new UnrootedTree();
		edges = new ArrayList<UndirectedTreeEdge>();

		addFirst3Nodes();
		Arrays.fill(config, 0);
		config[0] = config[1] = 1;
		insertNode(sequences[3], 0);
	}

	private boolean increaseMilometer() {
		return increaseMilometerFrom(config.length - 1);
	}

	private void jumpMilometer() {
		int l = config.length - 1;
		while (l > 0 && config[l] == 0) {
			l--;
		}
		increaseMilometerFrom(l);
	}

	private boolean increaseMilometerFrom(int l) {
		while (l > 0) {
			int k = l*2 + 1;
			if (config[l-1] > 0 && config[l] < k) {
				if (config[l] > 0) {
					removeNode(config[l]-1);
				}
				config[l]++;
				insertNode(sequences[l+2], config[l]-1);
				return true;
			}
			if (config[l] > 0) {
				removeNode(config[l]-1);
			}
			config[l] = 0;
			l--;
		}
		return false;
	}

	private void rebuildBestTree() {
		tree = new UnrootedTree();
		edges.clear();
		addFirst3Nodes();
		for (int l = 1; l < bestConfig.length; l++) {
			insertNode(sequences[l+2], bestConfig[l]-1);
		}
		fitch.solve(tree, sequences[0].length(), true);
	}

	private void insertNode(String sequence, int edgeIndex) {
		UnrootedTreeNode a = edges.get(edgeIndex).getNode1();
		UnrootedTreeNode b = edges.get(edgeIndex).getNode2();
		UnrootedTreeNode c = new UnrootedTreeNode(null);
		UnrootedTreeNode d = new UnrootedTreeNode(sequence);
		tree.addNode(c);
		tree.addNode(d);
		tree.removeEdge(a, b);
		edges.add(tree.addEdge(a, c));
		edges.add(tree.addEdge(b, c));
		edges.set(edgeIndex, tree.addEdge(c, d));
	}
	
	/**
	 * Remove the leaf node hanging on edge <code>edgeIndex</code>
	 * and the internal node adjacent to it.
	 */
	private void removeNode(int edgeIndex) {
		UndirectedTreeEdge edge = edges.get(edgeIndex);
		UnrootedTreeNode c = edge.getNode1();
		UnrootedTreeNode d = edge.getNode2();
		if (c.isLeaf()) { // swap
			UnrootedTreeNode t = c; c = d; d = t; 
		}
		UnrootedTreeNode[] ab = new UnrootedTreeNode[2];
		int abIndex = 0;
		for (UndirectedTreeEdge cEdge : c.getEdges()) {
			UnrootedTreeNode node = Utils.getOtherEnd(c, cEdge);
			if (node == d) {
				continue;
			}
			ab[abIndex++] = node;
		}
		tree.removeNode(c);
		tree.removeNode(d);
		edges.remove(edges.size()-1); // remove a-c
		edges.remove(edges.size()-1); // remove b-c
		edges.set(edgeIndex, tree.addEdge(ab[0], ab[1])); // replace c-d by a-b
	}

	private void addFirst3Nodes() {
		UnrootedTreeNode node0 = new UnrootedTreeNode(sequences[0]);
		UnrootedTreeNode node1 = new UnrootedTreeNode(sequences[1]);
		UnrootedTreeNode node2 = new UnrootedTreeNode(sequences[2]);
		UnrootedTreeNode node3 = new UnrootedTreeNode(null);
		tree.addNode(node0);
		tree.addNode(node1);
		tree.addNode(node2);
		tree.addNode(node3);
		edges.add(tree.addEdge(node0, node3));
		edges.add(tree.addEdge(node1, node3));
		edges.add(tree.addEdge(node2, node3));
	}
	
	public int getBestValue() {
		return bestValue;
	}

}
