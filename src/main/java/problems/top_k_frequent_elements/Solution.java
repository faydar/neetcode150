package problems.top_k_frequent_elements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.function.IntFunction;

// can be solved with bucket sort if max frequency is low
public class Solution {

    // WITH HEAP
    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int[] topKFrequentNlogN(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();

        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        // order by frequency
        PriorityQueue<Pair> pq = new PriorityQueue<>((p1, p2) -> p1.x - p2.x);

        for (Map.Entry<Integer, Integer> e : counts.entrySet()) {
            pq.add(new Pair(e.getValue(), e.getKey()));

            if (pq.size() > k) {
                pq.poll();
            }
        }

        return pq.stream().map(e -> e.y).mapToInt(Integer::intValue).toArray();
    }

    // WITH QUICKSELECT
    // IDEA: quick select kth most popular element's number
    // idea is to only find Kth most frequency without having to sort all frequences
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();

        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        int kthBiggestFreq = kthBiggestFrequence(toArr(new ArrayList<>(counts.values())), k);
        List<Integer> result = new ArrayList<>();

        for (int in : counts.keySet()) {
            if (counts.get(in) >= kthBiggestFreq) {
                result.add(in);
            }
        }
        return toArr(result);
    }

    private int[] toArr(List<Integer> l) {
        return l.stream().mapToInt(Integer::intValue).toArray();
    }

    private int kthBiggestFrequence(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, k);
    }

    private int quickSelect(int[] nums, int l, int r, int k) {
        if (l == r) {
            return nums[l];
        }

        int pivotIndex = partition(nums, l, r);

        if (pivotIndex == k - 1) {
            return nums[pivotIndex];
        }

        if (pivotIndex > k - 1) {
            return quickSelect(nums, l, pivotIndex - 1, k);
        }

        return quickSelect(nums, pivotIndex + 1, r, k);
    }

    private int partition(int[] nums, int l, int r) {
        int pivot = nums[r];
        int curPivot = l;

        for (int j = l; j < r; j++) {
            if (nums[j] >= pivot) {
                swap(nums, curPivot++, j);
            }
        }

        swap(nums, curPivot, r);
        return curPivot;
    }

    private void swap(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().topKFrequent(new int[] { 5, 2, 5, 3, 5, 3, 1, 1, 3 }, 2));
    }
}
