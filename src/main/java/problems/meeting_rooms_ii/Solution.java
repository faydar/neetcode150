package problems.meeting_rooms_ii;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Solution {

    static record Interval(int start, int end) {

    }

    // intuition: find max simultaneous meeting at any time
    // sweep line algorithm
    // map every timestamp for start and end, basically event points
    // starts add +1, ends add -1
    // we can find max simultaneous meeting count by this
    public int minMeetingRoomsSweepLine(List<Interval> intervals) {
        Map<Integer, Integer> diffs = new TreeMap<>(); // so the keys are sorted
        for (Interval interval : intervals) {
            diffs.put(interval.start, diffs.getOrDefault(interval.start, 0) + 1);
            diffs.put(interval.end, diffs.getOrDefault(interval.end, 0) - 1);
        }

        int res = 0;
        int meetingCount = 0;
        for (Integer key : diffs.keySet()) {
            meetingCount += diffs.get(key);
            res = Math.max(res, meetingCount);
        }

        return res;
    }

    // intuition: find max simultaneous meeting at any time
    // two pointers approach
    // count how many start_time's are there, before the current end_time
    // when start_time passes current end_time, pick next end_time
    public int minMeetingRooms(List<Interval> intervals) {
        int[] starts = intervals.stream().map(interval -> interval.start).mapToInt(s -> s).toArray();
        int[] ends = intervals.stream().map(interval -> interval.end).mapToInt(s -> s).toArray();

        Arrays.sort(starts);
        Arrays.sort(ends);
        int i = 0, j = 0;
        int res = 0;

        while (i < starts.length) {
            if (starts[i] < ends[j]) {
                i++;
            } else {
                j++;
            }

            res = Math.max(res, i - j);
        }

        return res;
    }

    public static void main(String[] args) {
        var s = new Solution();
        var in = List.of(
                new Interval(0, 40),
                new Interval(5, 10),
                new Interval(15, 20));
        var in2 = List.of(
                new Interval(1, 5),
                new Interval(5, 10),
                new Interval(10, 15),
                new Interval(15, 20));

        var r = s.minMeetingRoomsSweepLine(in);
        return;
    }
}
