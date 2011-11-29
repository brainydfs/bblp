package info.cs52.bioinfo.parsimony;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InputReader {

	public String[] read(String path) throws IOException {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(path));
			int count = Integer.parseInt(reader.readLine());
			String[] sequences = new String[count];
			for (int i = 0; i < count; i++) {
				sequences[i] = reader.readLine().trim();
			}
			return sequences;
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}
	
}
