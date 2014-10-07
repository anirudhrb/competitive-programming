import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * @author Anirudh Rayabharam (anirudhrb@yandex.com)
 */
public class Main {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		Task1225 solver = new Task1225();
		solver.solve(1, in, out);
		out.close();
	}
}

class Task1225 {
    final int RED = 0;
    final int WHITE = 1;
    final int BLUE = 2;

    int n;
    long[][] dp = new long[50][3];

    long solveIt(int index, int color) {
        if (index <= 0) return 0;

        if (index == 1) {
            if (color == BLUE) return 0;
            else return 1;
        }

        if (dp[index][color] != -1)
            return dp[index][color];

        if (color == BLUE) {
            dp[index][color] = solveIt(index - 1, RED) + solveIt(index - 1, WHITE);
        } else if (color == RED) {
            dp[index][color] = solveIt(index - 1, WHITE) + solveIt(index - 2, WHITE);
        } else if (color == WHITE) {
            dp[index][color] = solveIt(index - 1, RED) + solveIt(index - 2, RED);
        }

        return dp[index][color];
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n = in.nextInt();
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j] = -1;
            }
        }

        long ans = solveIt(n, RED) + solveIt(n, WHITE);

        out.println(ans);
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
