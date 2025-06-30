package utils;

import java.util.Comparator;
import java.util.Optional;

// todo: improve, especially the size management
public class IntHeap {

    private static final int INITIAL_HEAP_SIZE = 4;

    int[] arr;
    int size;
    int cursor;
    Comparator<Integer> comparator;
    boolean autoExpand;

    protected int[] getArr() {
        return arr;
    }

    protected int getSize() {
        return size;
    }

    public int getCurrentSize() {
        return cursor;
    }

    public void setAutoExpand(boolean autoExpand) {
        this.autoExpand = autoExpand;
    }

    public IntHeap(Comparator<Integer> comparator) {
        this.arr = new int[INITIAL_HEAP_SIZE];
        this.size = INITIAL_HEAP_SIZE;
        this.cursor = 0;
        this.comparator = comparator;
        this.autoExpand = true;
    }

    public IntHeap(Comparator<Integer> comparator, int fixedSize) {
        this.arr = new int[fixedSize];
        this.size = fixedSize;
        this.cursor = 0;
        this.comparator = comparator;
        this.autoExpand = false;
    }

    static IntHeap newMinHeap(int size) {
        return new IntHeap(Comparator.naturalOrder());
    }

    static IntHeap newMaxHeap(int size) {
        return new IntHeap(Comparator.reverseOrder());
    }

    private int parentIndex(int index) {
        return (index - 1) / 2;
    }

    private int leftChildIndex(int index) {
        return 2 * index + 1;
    }

    private int rightChildIndex(int index) {
        return 2 * index + 2;
    }

    private void expandSize() {
        var newArr = new int[this.size * 2];
        System.arraycopy(this.arr, 0, newArr, 0, this.size);

        this.size = this.size * 2;
        this.arr = newArr;
    }

    public void insert(int elem) {
        if (autoExpand && cursor + 1 == size) {
            expandSize();
        }

        arr[cursor] = elem;
        bubbleUp(cursor);

        cursor++;
    }

    private void swap(int i1, int i2) {
        if (i1 >= arr.length || i2 >= arr.length) {
            throw new IllegalArgumentException();
        }

        var temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }

    private void bubbleUp(int index) {
        if (index <= 0 || index > cursor) {
            return;
        }

        var parent = parentIndex(index);
        var comparison = comparator.compare(arr[index], arr[parent]);

        if (comparison < 0) {
            swap(index, parent);
            bubbleUp(parent);
        }
    }

    public int remove() {
        if (cursor == 0) {
            throw new RuntimeException("Heap is empty");
        }

        var removed = arr[0];
        swap(0, --cursor);
        arr[cursor] = 0;

        bubbleDown(0);
        return removed;
    }

    public int peek() {
        return arr[0];
    }

    private void bubbleDown(int index) {
        if (index > cursor) {
            return;
        }

        var leftChildIndex = leftChildIndex(index);
        var rightChildIndex = rightChildIndex(index);

        Optional<Integer> leftChild = leftChildIndex < cursor ? Optional.of(arr[leftChildIndex]) : Optional.empty();
        Optional<Integer> rightChild = rightChildIndex < cursor ? Optional.of(arr[rightChildIndex]) : Optional.empty();

        int swapIndex = -1;

        if (leftChild.isPresent()) {
            if (rightChild.isPresent()) {
                var swapIndexTmp = comparator.compare(leftChild.get(), rightChild.get()) < 0 ? leftChildIndex
                        : rightChildIndex;

                if (comparator.compare(arr[index], arr[swapIndexTmp]) > 0) {
                    swapIndex = swapIndexTmp;
                }
            } else {
                if (comparator.compare(arr[index], arr[leftChildIndex]) > 0) {
                    swapIndex = leftChildIndex;
                }
            }
        }

        if (swapIndex != -1) {
            swap(index, swapIndex);
            bubbleDown(swapIndex);
        }
    }

    public void printHeap() {
        printHeapRecursive(0, 0);
        System.out.println();
    }

    private void printHeapRecursive(int index, int level) {
        if (index >= arr.length || arr[index] == 0)
            return; // Skip empty indices

        // Right child first (so tree prints top-down, left-to-right)
        int right = 2 * index + 2;
        printHeapRecursive(right, level + 1);

        // Print current node with indentation
        for (int i = 0; i < level; i++)
            System.out.print("    ");
        System.out.println(arr[index]);

        // Left child
        int left = 2 * index + 1;
        printHeapRecursive(left, level + 1);
    }
}
