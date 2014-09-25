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
 * @problem UVa 10583 Chest of Drawers
 */
public class Main {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		ChestOfDrawers solver = new ChestOfDrawers();
		solver.solve(1, in, out);
		out.close();
	}
}

class ChestOfDrawers {
    int n, s;
    long[][][] dp = new long[66][66][2];

    public long dp(int i, int secure, int locked) {
        if (i == 1) {
            if (secure > i) return 0;

            if (secure == 0 && locked == 0)
                return 1;
            else if (secure == 0 && locked == 1)
                return 0;
            else if (secure == 1 && locked == 0)
                return 0;
            else if (secure == 1 && locked == 1)
                return 1;
        }

        if (dp[i][secure][locked] != -1)
            return dp[i][secure][locked];

        if (secure == 0) {
            if (locked == 1)
                dp[i][secure][locked] = dp(i - 1, secure, 0);
            else
                dp[i][secure][locked] = dp(i - 1, secure, 1) + dp(i - 1, secure, 0);

            return dp[i][secure][locked];
        }

        if (locked == 1)
            dp[i][secure][locked] = dp(i - 1, secure, 0) + dp(i - 1, secure - 1, 1);
        else
            dp[i][secure][locked] = dp(i - 1, secure, 0) + dp(i - 1, secure, 1);

        return dp[i][secure][locked];
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        n = in.nextInt();
        s = in.nextInt();

        long ans;

        for (int i = 0; i <= 65; ++i) {
            for (int j = 0; j <= 65; ++j) {
                for (int k = 0; k < 2; ++k)
                    dp[i][j][k] = -1;
            }
        }

        for (int i = 1; i <= 65; ++i) {
            for (int j = 1; j <= 65; ++j) {
                for (int k = 0; k < 2; ++k)
                   dp(i, j, k);
            }
        }

        while (n > 0 && s >= 0) {
            ans = dp(n, s, 0) + dp(n, s, 1);

            out.println(ans);

            n = in.nextInt();
            s = in.nextInt();
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

