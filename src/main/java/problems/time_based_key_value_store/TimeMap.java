package problems.time_based_key_value_store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.Pair;

class TimeMap {

    private final Map<String, List<Pair<Integer, String>>> map;

    public TimeMap() {
        this.map = new HashMap<>();
    }

    // "All the timestamps timestamp of set are strictly increasing."
    public void set(String key, String value, int timestamp) {
        this.map.computeIfAbsent(key, k -> new ArrayList<>()).add(new Pair<Integer, String>(timestamp, value));
    }

    public String get(String key, int timestamp) {
        List<Pair<Integer, String>> li = map.getOrDefault(key, List.of());

        int l = 0, r = li.size() - 1;
        String result = "";

        while (l <= r) {
            int mid = l + (r - l + 1) / 2;
            var midPair = li.get(mid);

            int ts = midPair.getFirst();

            if (ts > timestamp) {
                r = mid - 1;
            } else {
                result = midPair.getSecond();
                l = mid + 1;
            }
        }

        return result;
    }
}
