package info.cs52.bioinfo.parsimony;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class TreeWriter {

	public void write(UnrootedTree tree, Writer writer) {
		PrintWriter out = new PrintWriter(writer);
		Map<UnrootedTreeNode,Integer> indexMap = 
				new HashMap<UnrootedTreeNode, Integer>();
		int i = 0;
		for (UnrootedTreeNode node : tree.getNodes()) {
			out.printf("node %d: %s\n", i, node.getSequence());
			indexMap.put(node, i);
			i++;
		}
		boolean[] checked = new boolean[tree.getNodes().size()];
		Deque<UnrootedTreeNode> stack = new LinkedList<UnrootedTreeNode>();
		stack.add(Utils.getFirst(tree.getNodes()));
		while (!stack.isEmpty()) {
			UnrootedTreeNode node = stack.pop();
			Integer nodeIndex = indexMap.get(node);
			for (UndirectedTreeEdge edge : node.getEdges()) {
				UnrootedTreeNode o = Utils.getOtherEnd(node, edge);
				int oIndex = indexMap.get(o);
				if (checked[oIndex]) {
					continue;
				}
				out.printf("edge %d - %d", nodeIndex, oIndex);
				if (node.getSequence() != null && o.getSequence() != null) {
					out.printf(": %d", Utils.getHammingDistance(node.getSequence(), o.getSequence()));
				}
				out.println();
				stack.push(o);
			}
			checked[nodeIndex] = true;
		}
		out.flush();
	}

	public void write(UnrootedTree tree, OutputStream out) {
		write(tree, new OutputStreamWriter(out));
	}
	
}
