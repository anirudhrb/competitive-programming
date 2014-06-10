/**
 * Created on 6/4/14
 *
 * @author: Anirudh Rayabharam <anirudh.rayabharam@gmail.com>
 * @handle: code_overlord
 */
import java.io.*;
import java.util.Vector;

public class DevuArrayPartition {
    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintStream out = System.out;

        int n = in.nextInt();
        int k = in.nextInt();
        int p = in.nextInt();

        Vector<Integer> evens = new Vector<Integer>();
        Vector<Integer> odds = new Vector<Integer>();
        int oddCount = 0, evenCount = 0;
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            if (x % 2 == 0) {
                evenCount++;
                evens.add(x);
            }
            else {
                oddCount++;
                odds.add(x);
            }
        }

        if (oddCount < k-p || (oddCount - k + p) % 2 == 1) {
            out.println("NO");
            return;
        }

        int oddsReqForEven = 0;
        if (evenCount < p) {
            if (oddCount-k+p < 2*(p-evenCount) || (oddCount-k+p-2*(p-evenCount)) % 2 == 1) {
                out.println("NO");
                return;
            }

            oddsReqForEven = oddCount-k+p;
        }

        out.println("YES");
        int el = evens.size();
        int ol = odds.size();
        int ei = 0, oi = 0;
        // print odds
        for (int i = 1; i <= k-p; i++) {
            if (i == k-p && oddsReqForEven == 0) {
                if (p == 0)
                    out.print((ol-oi+el) + " ");
                else
                    out.print((ol-oi) + " ");
                while(oi < ol) {
                    out.print(odds.get(oi++));
                    if (oi < ol) out.print(" ");
                }

                if (p == 0) {
                    out.print(" ");
                    while(ei < el) {
                        out.print(evens.get(ei++));
                        if (ei < el) out.print(" ");
                    }
                }

                out.println();
                break;
            }

            out.println("1 " + odds.get(oi++));
        }

        // print evens
        for (int i = 1; i <= p; i++) {

            if (i == p && ei == el) {
                out.print((ol-oi) + " ");
                while(oi < ol) {
                    out.print(odds.get(oi++) + " ");
                }
                out.println();
                break;
            } else if (i == p) {
                if (k-p == 0)
                    out.print((el-ei+ol) + " ");
                else
                    out.print((el-ei) + " ");

                while(ei < el) {
                    out.print(evens.get(ei++));
                    if (ei < el) out.print(" ");
                }

                if (k-p==0) {
                    out.print(" ");
                    while (oi < ol) {
                        out.print(odds.get(oi++));
                        if (oi < ol) out.print(" ");
                    }
                }

                out.println();
                break;
            }

            if (ei == el) {
                out.print("2 ");
                out.print(odds.get(oi++) + " ");
                out.println(odds.get(oi++));
            } else
                out.println("1 " + evens.get(ei++));
        }
    }

    private static class InputReader {
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
}
