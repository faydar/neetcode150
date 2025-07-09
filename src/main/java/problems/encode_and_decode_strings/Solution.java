package problems.encode_and_decode_strings;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public String encode(List<String> strs) {
        StringBuilder encoded = new StringBuilder();

        for (String s : strs) {
            int len = s.length();
            encoded.append(len).append(':').append(s);
        }

        return encoded.toString();
    }

    public List<String> decode(String str) {
        int it = 0;
        List<String> res = new ArrayList<>();

        while (it < str.length()) {
            boolean start = true;
            StringBuilder lengthStr = new StringBuilder();

            while (str.charAt(it) != ':') {
                if (!Character.isDigit(str.charAt(it))) {
                    throw new IllegalArgumentException();
                }

                lengthStr.append(str.charAt(it++));
            }

            it++;
            int length = Integer.parseInt(lengthStr.toString());

            StringBuilder out = new StringBuilder();

            int i;
            for (i = it; i < it + length; i++) {
                out.append(str.charAt(i));
            }

            res.add(out.toString());
            it = i;
        }

        return res;
    }

    public static void main(String[] args) {
        var in = List.of("neet", "code", "love", "you");
        var encoded = new Solution().encode(in);
        var decoded = new Solution().decode(encoded);
        return;
    }
}
