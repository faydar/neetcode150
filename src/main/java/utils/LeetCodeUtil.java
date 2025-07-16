package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LeetCodeUtil {
    /**
     * Reads a file that contains a list of integer pairs in the form
     * [[85,163],[37,148], ...]
     * and converts it to an int[][2].
     *
     * @param filePath path to the .txt file
     * @return 2‑D array of size n x 2
     * @throws IOException              if the file cannot be read
     * @throws IllegalArgumentException if the input is malformed
     */
    public static int[][] parsePairs(Path filePath) {
        // Read entire file as one string and strip whitespace
        String raw;

        try {
            raw = Files.readString(filePath)
                    .replaceAll("\\s+", "");
        } catch (IOException e) {
            return new int[0][0];
        }

        // Quick sanity check
        if (!raw.startsWith("[[") || !raw.endsWith("]]")) {
            throw new IllegalArgumentException("Input must start with [[ and end with ]]");
        }

        // Remove the outer square brackets: [[ ... ]] → ...
        String inner = raw.substring(2, raw.length() - 2);

        // Split on "],[" to isolate each pair
        String[] pairTokens = inner.split("],\\[");

        List<int[]> pairs = new ArrayList<>(pairTokens.length);

        for (String token : pairTokens) {
            // Each token is like "85,163"
            String[] nums = token.split(",");
            if (nums.length != 2) {
                throw new IllegalArgumentException("Invalid pair: " + token);
            }
            int first = Integer.parseInt(nums[0]);
            int second = Integer.parseInt(nums[1]);
            pairs.add(new int[] { first, second });
        }

        // Convert List<int[]> → int[][2]
        return pairs.toArray(new int[0][2]);
    }
}
