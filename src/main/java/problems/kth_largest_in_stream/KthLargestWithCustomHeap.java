package problems.kth_largest_in_stream;

import java.util.Comparator;
import java.util.List;

import utils.IntHeap;

class KthLargestWithCustomHeap {

    private IntHeap h;
    private int k;

    public KthLargestWithCustomHeap(int k, int[] nums) {
        this.k = k;
        this.h = new IntHeap(Comparator.naturalOrder(), k + 1);
        for (int n : nums) {
            add(n);
        }
    }

    public int add(int val) {
        h.insert(val);

        if (h.getCurrentSize() == k + 1) {
            h.remove();
        }

        return h.peek();
    }

    public static void main(String[] args) {
        var p = new KthLargestWithCustomHeap(3, new int[] { 4, 5, 8, 2 });

        List.of(3, 5, 10, 9, 4)
                .stream()
                .forEach(e -> {
                    System.out.println(p.add(e));
                });
    }
}