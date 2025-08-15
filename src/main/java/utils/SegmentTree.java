package utils;

import java.util.function.BiFunction;

public class SegmentTree {

    int[] tree;
    int n;
    BiFunction<Integer, Integer, Integer> func;
    int defaultValueOutsideRange;

    public static SegmentTree rangeSumSegmentTree(int[] arr) {
        return new SegmentTree(arr, Integer::sum, 0);
    }

    public static SegmentTree rangeMaxSegmentTree(int[] arr) {
        return new SegmentTree(arr, Math::max, Integer.MIN_VALUE);
    }

    public static SegmentTree rangeMinSegmentTree(int[] arr) {
        return new SegmentTree(arr, Math::min, Integer.MAX_VALUE);
    }

    public SegmentTree(int[] arr, BiFunction<Integer, Integer, Integer> func, int defaultValueOutsideRange) {
        this.n = arr.length;
        this.tree = new int[4 * this.n];
        this.func = func;
        this.defaultValueOutsideRange = defaultValueOutsideRange;

        build(0, 0, n - 1, arr);
    }

    private void build(int node, int start, int end, int[] arr) {
        if (start == end) {
            tree[node] = arr[start];
            return;
        }

        int mid = start + (end - start) / 2;
        build(node * 2 + 1, start, mid, arr);
        build(node * 2 + 2, mid + 1, end, arr);
        tree[node] = func.apply(tree[node * 2 + 1], tree[node * 2 + 2]);
    }

    public int query(int l, int r) {
        return query(0, 0, n - 1, l, r);
    }

    private int query(int node, int start, int end, int l, int r) {
        // [l, r] is outside of our current node
        if (r < start || l > end) {
            return defaultValueOutsideRange; // no effect
        }

        // [l, r] fully includes current node range
        if (l <= start && end <= r) {
            return tree[node];
        }

        int mid = start + (end - start) / 2;
        var lr = query(node * 2 + 1, start, mid, l, r);
        var rr = query(node * 2 + 2, mid + 1, end, l, r);
        return func.apply(lr, rr);
    }

    public void update(int index, int val) {
        update(0, 0, n - 1, index, val);
    }

    private void update(int node, int start, int end, int index, int val) {
        if (start == end) {
            tree[node] = val;
            return;
        }

        int mid = start + (end - start) / 2;
        if (index <= mid) {
            update(node * 2 + 1, start, mid, index, val);
        } else {
            update(node * 2 + 2, mid + 1, end, index, val);
        }

        tree[node] = func.apply(tree[node * 2 + 1], tree[node * 2 + 2]);
    }

    public static void main(String[] args) {
        testRangeMin();
        testRangeMax();
        testRangeSum();
    }

    public static void testRangeMin() {
        int[] arr = new int[] { 1, 3, 8, 22, -1, 10, 9, 0, 5 };
        var st = SegmentTree.rangeMinSegmentTree(arr);
        assertEquals(st.query(0, 3), 1);
        assertEquals(st.query(5, 5), 10);
        assertEquals(st.query(1, 8), -1);
        assertEquals(st.query(1, 7), -1);

        st.update(0, 33);
        assertEquals(st.query(0, 3), 3);
    }

    public static void testRangeMax() {
        int[] arr = new int[] { 1, 3, 8, 22, -1, 10, 9, 0, 5 };
        var st = SegmentTree.rangeMaxSegmentTree(arr);
        assertEquals(st.query(0, 3), 22);
        assertEquals(st.query(5, 5), 10);
        assertEquals(st.query(1, 8), 22);
        assertEquals(st.query(5, 7), 10);

        st.update(0, 33);
        assertEquals(st.query(0, 3), 33);
    }

    public static void testRangeSum() {
        int[] arr = new int[] { 1, 3, 8, 22, -1, 10, 9, 0, 5 };
        var st = SegmentTree.rangeSumSegmentTree(arr);
        assertEquals(st.query(0, 3), 34);
        assertEquals(st.query(5, 5), 10);
        assertEquals(st.query(1, 8), 56);
        assertEquals(st.query(5, 7), 19);

        st.update(0, 33);
        assertEquals(st.query(0, 3), 66);
    }

    public static void assertEquals(int x, int y) {
        if (x != y) {
            throw new RuntimeException();
        }
    }
}
