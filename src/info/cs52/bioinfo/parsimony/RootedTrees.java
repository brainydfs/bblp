package info.cs52.bioinfo.parsimony;

public final class RootedTrees {

	RootedTrees() {
	}
	
	public static int getParsimonyScore(RootedTreeNode root) {
		ParsimonyScorer scorer = new ParsimonyScorer();
		root.accept(scorer);
		return scorer.getScore();
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

	private static class ParsimonyScorer implements Visitor {

		private int score = 0;
		
		@Override
		public void visit(RootedTreeNode node) {
			if (node.getParent() != null) {
				score += getHammingDistance(
						node.getParent().getSequence(),
						node.getSequence());
			}
		}
		
		public int getScore() {
			return score;
		}
		
	}
	
}
