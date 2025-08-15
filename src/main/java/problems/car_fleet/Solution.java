package problems.car_fleet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Solution {

    static class Fleet {
        int speed;
        int loc;

        public Fleet(int loc, int speed) {
            this.loc = loc;
            this.speed = speed;
        }
    }

    // nlogn, Monotonic Stack
    // sort by decreasing start location
    // if for current car, remaining time is smaller than top of stack, they will
    // merge at some point as fleet
    // (because even though the new car starts from an earlier location, it has less
    // remaining time to target, so it will catch)
    public int carFleet(int target, int[] position, int[] speed) {
        List<Fleet> fleets = new ArrayList<>();
        for (int i = 0; i < position.length; i++) {
            fleets.add(new Fleet(position[i], speed[i]));
        }

        fleets.sort((f1, f2) -> Integer.compare(f2.loc, f1.loc));
        Stack<Double> st = new Stack<>();

        for (Fleet fleet : fleets) {
            double fleetRemainingTime = (target * 1.0 - fleet.loc) / fleet.speed;
            if (st.isEmpty() || fleetRemainingTime > st.peek()) {
                st.add(fleetRemainingTime);
            }
        }
        return st.size();
    }

    public static void main(String[] args) {
        var s = new Solution();
        // s.carFleet(12, new int[] { 10, 8, 0, 5, 3 }, new int[] { 2, 4, 1, 1, 3 });
        s.carFleet(25, new int[] { 5, 10, 15 }, new int[] { 3, 2, 1 });
    }
}
