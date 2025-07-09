package problems.meeting_rooms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public boolean canAttendMeetings(List<Interval> intervals) {
        intervals.sort((e1, e2) -> {
            if (e1.start == e2.start) {
                return e1.end - e2.end;
            }

            return e1.start - e2.start;
        });

        for (int i = 0; i < intervals.size() - 1; i++) {
            if (intervals.get(i).end > intervals.get(i + 1).start) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        var in = List.of(
                new Interval(5, 8),
                new Interval(9, 15));

        System.out.println(new Solution().canAttendMeetings(new ArrayList<>(in)));
    }

    static class Interval {
        public int start, end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
