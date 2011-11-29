package info.cs52.bioinfo.parsimony;

import java.util.HashSet;
import java.util.Set;

public final class Utils {

	private Utils() {
	}

	public static int getHammingDistance(String a, String b) {
		if (a.length() != b.length()) {
			throw new IllegalArgumentException(
					"Two strings must be of the same length");
		}
		int distance = 0;
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) != b.charAt(i)) {
				distance++;
			}
		}
		return distance;
	}

	public static <T> HashSet<T> intersect(Set<T> r1, Set<T> r2) {
		HashSet<T> intersection = new HashSet<T>(r1);
		intersection.retainAll(r2);
		return intersection;
	}

	public static UnrootedTreeNode getOtherEnd(UnrootedTreeNode a,
			UndirectedTreeEdge edge) {
		return edge.getNode1() == a ? 
				edge.getNode2() : edge.getNode1();
	}

	public static <T> T getFirst(Set<T> set) {
		return set.iterator().next();
	}
	
}
