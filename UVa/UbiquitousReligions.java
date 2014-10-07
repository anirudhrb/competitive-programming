import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * @author Anirudh Rayabharam
 * Solution to UVa 10583 Ubiquitous Religions
 */
public class Main {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		UbiquitousReligions solver = new UbiquitousReligions();
		solver.solve(1, in, out);
		out.close();
	}
}

class UbiquitousReligions {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n, m;
        int a, b;

        while (true) {
            n = in.nextInt();
            m = in.nextInt();

            if (n == 0 && m == 0) break;

            DisjointSetForest forest = new DisjointSetForest(n);
            for (int i = 1; i <= n; ++i)
                forest.addSet(i);

            for (int i = 0; i < m; ++i) {
                a = in.nextInt();
                b = in.nextInt();
                forest.union(a, b);
            }

            out.println("Case " + testNumber + ": " + forest.getSetCount());
            ++testNumber;
        }
    }
}

class InputReader {
    public BufferedReader reader;
    private int tokenCount, nextTokenIndex;
    private String[] tokens;

    public InputReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream));
        tokenCount = nextTokenIndex = 0;
    }

    public String next() {
        String nextLine;
        if (nextTokenIndex == tokenCount) {
            try {
                nextLine = reader.readLine();
                nextTokenIndex = 0;
                tokens = nextLine.split("\\s");
                tokenCount = tokens.length;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return tokens[nextTokenIndex++];
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

}

class DisjointSetForest {
    int[] parent, rank;
    int setCount;

    public DisjointSetForest(int size) {
        parent = new int[size + 1];
        rank = new int[size + 1];
        setCount = 0;
    }

    /**
     * Creates a new set with only element as 'element' and adds it to the forest.
     * @param element the element the new should contain.
     */
    public void addSet(int element) {
        ++setCount;
        parent[element] = element;
    }

    public int getSetCount() {
        return setCount;
    }

    /**
     * Merges the sets containing firstElement and secondElement by first finding
     * the representatives of the sets and uses the "union by ranks" heuristic to merge.
     * If both of them are contained in the same set nothing is done.
     *
     * @param firstElement one of the elements of the first set
     * @param secondElement one of the elements of the second set
     */
    public void union(int firstElement, int secondElement) {
        int repX = findSet(firstElement);
        int repY = findSet(secondElement);

        if (repX == repY) return;

        --setCount;
        if (rank[repX] < rank[repY])
            parent[repX] = repY;
        else
            parent[repY] = repX;

        if (rank[repX] == rank[repY]) ++rank[repX];
    }

    /**
     * Finds the representative element of the set containing the element "element".
     * Uses the path-compression heuristic to optimize the operation.
     *
     * @param element the element that the return set should contain
     * @return the set number of the set containing the element "element".
     */
    public int findSet(int element) {
        if (element != parent[element])
            parent[element] = findSet(parent[element]);
        return parent[element];
    }
}

