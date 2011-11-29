package info.cs52.bioinfo.parsimony;

public final class RootedTrees {

	RootedTrees() {
	}
	
	public static int getParsimonyScore(RootedTreeNode root) {
		ParsimonyScorer scorer = new ParsimonyScorer();
		root.accept(scorer);
		return scorer.getScore();
	}
	
	private static class ParsimonyScorer implements Visitor {

		private int score = 0;
		
		@Override
		public void visit(RootedTreeNode node) {
			if (node.getParent() != null) {
				score += Utils.getHammingDistance(
						node.getParent().getSequence(),
						node.getSequence());
			}
		}
		
		public int getScore() {
			return score;
		}
		
	}
	
}
