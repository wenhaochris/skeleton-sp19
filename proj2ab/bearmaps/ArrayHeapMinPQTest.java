package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArrayHeapMinPQTest {
    @Test
    public void testSmallest(){
        ArrayHeapMinPQ<String> testPQ = new ArrayHeapMinPQ<>();
        testPQ.add("loveyou", 2.5);
        testPQ.add("leileleile", 0.0);
        testPQ.add("usausa", 9.9);
        assertEquals("leileleile", testPQ.getSmallest());
        assertEquals("leileleile", testPQ.removeSmallest());
        assertEquals("loveyou", testPQ.removeSmallest());
        assertEquals("usausa", testPQ.getSmallest());

    }

    @Test
    public  void testChangePriority(){
        ArrayHeapMinPQ<String> testPQ = new ArrayHeapMinPQ<>();
        testPQ.add("fuckyou", 98);
        testPQ.add("loveyou", 78);
        testPQ.add("bibibi", 4.4);
        testPQ.changePriority("loveyou", 0);
        assertEquals("loveyou", testPQ.getSmallest());
    }

    @Test
    public void testSpeed(){
        ArrayHeapMinPQ<Integer> myMQ = new ArrayHeapMinPQ<>();
        Stopwatch w3 = new Stopwatch();
        for(int i = 0; i < 1000000; i++){
            int j = 1000000 - i;
            myMQ.add(j,j);
        }

        System.out.println("myPQ add function consume:" + w3.elapsedTime() + " s");

        NaiveMinPQ<Integer> naiveMinPQ = new NaiveMinPQ<>();
        Stopwatch w4 = new Stopwatch();
        for(int i = 0; i < 1000000; i++){
            int j = 1000000 - i;
            naiveMinPQ.add(j,j);
        }

        System.out.println("naivePQ add function consume:" + w4.elapsedTime() + " s");

        Stopwatch w1 = new Stopwatch();
        for(int i = 0; i < 1000; i++){
            int j = (int) (Math.random() * 1000000);
            naiveMinPQ.contains(j);
        }

        double timeSpend2 = w1.elapsedTime();
        System.out.println("naivePQ contains function consume:" + w1.elapsedTime() + " s");

        Stopwatch w2 = new Stopwatch();
        for(int i = 0; i < 1000; i++){
            int j = (int) (Math.random() * 1000000);
            myMQ.contains(j);
        }
        double timeSpend1 = w2.elapsedTime();
        System.out.println("myPQ contains function consume:" + w2.elapsedTime() + " s");
        assertTrue(timeSpend1 / timeSpend2 < 0.01);
    }

}
