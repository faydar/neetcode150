package problems.gas_station;

import java.util.Arrays;

public class Solution {

    // greedy
    // if costs sum are lower than gas sum, there is a solution
    // it is enough if we find where it starts before end of the array
    public int canCompleteCircuitGreedy1(int[] gas, int[] cost) {
        int cs = 0, res = 0;
        for (int i = 0; i < gas.length; i++) {
            cs += gas[i] - cost[i];

            if (cs < 0) {
                res = i + 1;
                cs = 0;
            }
        }

        return res;
    }

    // greedy, actually same with above, for some reason this one runs faster
    public int canCompleteCircuitGreedy2(int[] gas, int[] cost) {
        int cs = 0, res = 0;

        for (int i = 0; i < gas.length * 2; i++) {

            if (cs < 0) {
                if (i >= gas.length) {
                    return -1;
                }
                res = i;
                cs = 0;
            }

            if (i - res == gas.length) {
                return res;
            }

            cs += gas[i % gas.length];
            cs -= cost[i % gas.length];
        }

        return -1;
    }

    public static void main(String[] args) {
        var s = new Solution();
        System.out.println(s.canCompleteCircuitGreedy1(new int[] { 1, 2, 3, 4 }, new int[] { 2, 2, 4, 1 }));
        System.out.println(s.canCompleteCircuitGreedy1(new int[] { 1, 2, 3, 4, 5 }, new int[] { 3, 4, 5, 1, 2 }));
        System.out.println(s.canCompleteCircuitGreedy1(new int[] { 2, 3, 4 }, new int[] { 3, 4, 3 }));
        System.out.println(s.canCompleteCircuitGreedy1(new int[] { 1, 2, 3 }, new int[] { 2, 3, 2 }));
        System.out.println(s.canCompleteCircuitGreedy1(new int[] { 3, 1, 1 }, new int[] { 1, 2, 2 }));

        return;
    }
}
