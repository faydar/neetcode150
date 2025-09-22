package problems.task_scheduler;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {

    // My initial solution, O(N) - or O(26N) but I don't like its style
    // intuition is same with other solutions: Sort by frequencies, use the most
    // available tasks that is not used recently (last_used<t-n)

    // intuition: if we don't use most frequent tasks first they pile up towards the
    // end

    // the thing I didn't realize first and went for this solution, is that max heap
    // is not logN in this is case, it is really log26, and overall complexity
    // becomes N*log26 -> O(N), so I could have used heap
    // directly
    public int leastInterval(char[] tasks, int n) {
        int[] counts = new int[26];
        for (Character t : tasks) {
            counts[t - 'A']++;
        }

        int interval = 0;
        int[] availableAt = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            int maxCount = 0;
            int selectedNow = -1;

            int minAvailableAt = Integer.MAX_VALUE;
            int selectedAvailableAt = -1;
            for (int j = 0; j < 26; j++) {
                if (counts[j] > maxCount && availableAt[j] <= interval) {
                    maxCount = counts[j];
                    selectedNow = j;
                }

                if (counts[j] > maxCount && availableAt[j] < minAvailableAt) {
                    minAvailableAt = availableAt[j];
                    selectedAvailableAt = j;
                }
            }

            if (selectedNow != -1) {
                availableAt[selectedNow] = interval + n + 1;
                counts[selectedNow]--;
                interval++;
            } else {
                availableAt[selectedAvailableAt] = minAvailableAt + n + 1;
                counts[selectedAvailableAt]--;
                interval = minAvailableAt + 1;
            }
        }

        return interval;
    }

    static record Pair(int x, int y) {

    }

    // interesting greedy solution that:
    // considers idle time is created by the most frequent task
    // creates an upper bound for idle time and then decreases it as much as
    // possible
    public int leastIntervalGreedy(char[] tasks, int n) {
        int[] counts = new int[26];
        for (Character t : tasks) {
            counts[t - 'A']++;
        }

        Arrays.sort(counts);
        int maxFreq = counts[25];
        int maxIdle = (maxFreq - 1) * n; // last appearance of a task does not create idle time

        for (int i = 24; i >= 0; i--) {
            maxIdle -= Math.min(maxFreq - 1, counts[i]); // counts[i] coulds still be bigger than maxFreq-1, and we can
                                                         // fill only a maximum of maxFreq-1 idle spots at once, with
                                                         // one type of task             
        }

        return Math.max(0, maxIdle) + tasks.length;
    }

    public static void main(String[] args) {
        var s = new Solution();
        var r = s.leastInterval(new char[] { 'A', 'A', 'A', 'B', 'B', 'B' }, 3);
        var r2 = s.leastInterval(new char[] { 'A', 'C', 'A', 'B', 'D', 'B' }, 1);
        var r3 = s.leastInterval(new char[] { 'A', 'A', 'A', 'B', 'B', 'B' }, 2);
        var r4 = s.leastInterval(new char[] { 'A', 'A', 'A', 'B', 'C' }, 3);
        var r5 = s.leastInterval(new char[] { 'X', 'X', 'Y', 'Y' }, 2);

        return;
    }
}
