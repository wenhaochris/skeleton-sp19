import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import static org.junit.Assert.*;


public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        Queue<Integer> test = new Queue<>();
        for (int i = 0; i < 100; i++) {
            int temp = StdRandom.uniform(1, 100);
            test.enqueue(temp);
        }
        Stopwatch sw = new Stopwatch();
        QuickSort.quickSort(test);
        System.out.println("Time spent:" + sw.elapsedTime() + "s");
        assertTrue(isSorted(test));
    }

    @Test
    public void testMergeSort() {
        Queue<Integer> test1 = new Queue<>();
        for (int i = 0; i < 100; i++) {
            int temp = StdRandom.uniform(1, 100);
            test1.enqueue(temp);
        }
        Stopwatch sw = new Stopwatch();
        MergeSort.mergeSort(test1);
        System.out.println("Time spent:" + sw.elapsedTime() + "s");
        assertTrue(isSorted(test1));
    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items A Queue of items
     * @return true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
