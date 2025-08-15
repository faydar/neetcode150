package problems.sliding_window_maximum;

public class SlidingWindowSegmentTree {

    // todo solve with dynamic programming

    // Segment Tree (range max queries for k ranges) O(NlogN)
    static class SegmentTree {
        int[] tree;
        int originalLength;

        public SegmentTree(int[] arr) {
            this.originalLength = arr.length;
            this.tree = new int[4 * arr.length];
            build(arr, 0, 0, arr.length - 1);
        }

        public void build(int[] arr, int node, int rangeStart, int rangeEnd) {
            if (rangeStart == rangeEnd) {
                tree[node] = arr[rangeStart];
            } else {
                int mid = rangeStart + (rangeEnd - rangeStart) / 2;
                build(arr, node * 2 + 1, rangeStart, mid);
                build(arr, node * 2 + 2, mid + 1, rangeEnd);
                tree[node] = Math.max(tree[node * 2 + 1], tree[node * 2 + 2]);
            }
        }

        public int query(int start, int end) {
            return query(0, 0, originalLength - 1, start, end);
        }

        private int query(int node, int start, int end, int l, int r) {
            if (r < start || l > end) {
                return Integer.MIN_VALUE;
            }

            if (l <= start && end <= r) {
                return tree[node];
            }

            int mid = start + (end - start) / 2;
            var leftMax = query(node * 2 + 1, start, mid, l, r);
            var rightMax = query(node * 2 + 2, mid + 1, end, l, r);

            return Math.max(leftMax, rightMax);
        }

        public void update(int index, int value) {
            update(0, 0, originalLength - 1, index, value);
        }

        private void update(int node, int start, int end, int index, int value) {
            if (start == end) {
                tree[node] = value;
                return;
            }

            int mid = start + (end - start) / 2;

            if (index <= mid) {
                update(node * 2 + 1, start, mid, index, value);
            } else {
                update(node * 2 + 2, mid + 1, end, index, value);
            }

            tree[node] = Math.max(tree[node * 2 + 1], tree[node * 2 + 2]);
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        var st = new SegmentTree(nums);
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length - k + 1; i++) {
            result[i] = st.query(i, i + k - 1);
        }

        return result;
    }

    public static void main(String[] args) {
        var s = new SlidingWindowSegmentTree();
        s.maxSlidingWindow(new int[] { 1, -1 }, 1);
        return;
    }
}
