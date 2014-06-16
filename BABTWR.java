/**
 * Created on 6/15/14
 *
 * @author: Anirudh Rayabharam <anirudh.rayabharam@gmail.com>
 * @handle: code_overlord
 */
import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

public class BABTWR {

    static class Box {
        public int[] dim = new int[3];
        public Box(int[] d) {
            for (int i = 0; i < 3; i++) {
                dim[i] = d[i];
            }
        }
    }

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintStream out = System.out;

        int n, aux, max;
        Vector<Box> boxes = new Vector<Box>();
        int[] dp;
        int[] tdim = new int[3];

        while(true) {
            n = in.nextInt();
            if (n == 0) break;

            boxes.clear();
            for (int i = 0; i < n; i++) {

                for (int j = 0; j < 3; j++) {
                    tdim[j] = in.nextInt();
                }
                Arrays.sort(tdim);
                boxes.add(new Box(tdim));

                // add other orientations of this box

                aux = tdim[1];
                tdim[1] = tdim[0];
                tdim[0] = aux;
                boxes.add(new Box(tdim));

                aux = tdim[2];
                tdim[2] = tdim[0];
                tdim[0] = aux;
                boxes.add(new Box(tdim));
            }

            // sort the boxes by base area in decreasing order
            Collections.sort(boxes, new Comparator<Box>() {
                @Override
                public int compare(Box o1, Box o2) {
                    return (o2.dim[1] * o2.dim[2]) - (o1.dim[1] * o1.dim[2]);
                }
            });

            dp = new int[boxes.size()];
            dp[0] = boxes.get(0).dim[0];
            for (int i = 1; i < boxes.size(); i++) {
                max = 0;
                for (int j = 0; j < i; j++) {
                    if (boxes.get(j).dim[1] > boxes.get(i).dim[1] && boxes.get(j).dim[2] > boxes.get(i).dim[2]) {
                        max = Math.max(max, dp[j]);
                    }
                }

                dp[i] = max + boxes.get(i).dim[0];
            }

            int ans = -1;
            for (int i = 0; i < boxes.size(); i++) {
                if (dp[i] > ans) ans = dp[i];
            }

            out.println(ans);
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
