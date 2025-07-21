package problems.find_median_from_data_stream;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianFinder {

    PriorityQueue<Integer> mins; // minimum n/2 elements, largest at top
    PriorityQueue<Integer> maxes; // maximum of n/2 elements, smallest at top

    public MedianFinder() {
        this.mins = new PriorityQueue<>(Comparator.reverseOrder());
        this.maxes = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if (maxes.isEmpty() || num > maxes.peek()) {
            maxes.add(num);
        } else {
            mins.add(num);
        }

        if (mins.size() > maxes.size()) {
            maxes.add(mins.poll());
        } else if (maxes.size() - mins.size() > 1) {
            // maxes have the extra elems
            mins.add(maxes.poll());
        }

        return;
    }

    public double findMedian() {
        if ((mins.size() + maxes.size()) % 2 == 1) {
            return maxes.peek() * 1.0;
        }

        return (mins.peek() * 1.0 + maxes.peek()) / 2;
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(-1);
        medianFinder.findMedian();
        medianFinder.addNum(-2);
        medianFinder.findMedian();
        medianFinder.addNum(-3);
        medianFinder.findMedian();
        medianFinder.addNum(-4);
        medianFinder.findMedian();
        medianFinder.addNum(-5);
        medianFinder.findMedian();
    }
}
