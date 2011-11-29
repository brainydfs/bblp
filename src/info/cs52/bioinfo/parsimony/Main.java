package info.cs52.bioinfo.parsimony;

import java.io.FileWriter;
import java.io.IOException;

public class Main {

	private static final String FILE_NAME = "07.txt";

	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();
		String[] sequences = new InputReader().read("input/" + FILE_NAME);
		BranchAndBoundSolver solver = new BranchAndBoundSolver();
		UnrootedTree tree = solver.solve(sequences);
		long stop = System.currentTimeMillis();
		FileWriter out = new FileWriter("output/" + FILE_NAME);
		new TreeWriter().write(tree, out);
		out.write((stop-start)/1000.0 + "s");
		out.close();
	}
	
}
