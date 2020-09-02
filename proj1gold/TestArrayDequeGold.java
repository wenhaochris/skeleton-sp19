import static org.junit.Assert.*;
import org.junit.Test;

import javax.print.attribute.IntegerSyntax;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestArrayDequeGold {
    @Test
    public void testStuentArrayDeque(){

//        addLast
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer>  expect = new ArrayDequeSolution<>();
        for(int i = 0; i < 10 ; i++){
            int random = StdRandom.uniform(100);
            sad1.addLast(random);
            expect.addLast(random);
        }

        for(int i = 0; i < 10; i++){
            int actual = sad1.get(i);
            int exp = expect.get(i);
            assertEquals("Oh noooo!\nThis is bad:\n   Random number " + actual
                    + " not equal to " + exp + "!", exp, actual);
        }

//      addFirst
        for(int i = 0; i < 10; i++){
            int random = StdRandom.uniform(100);
            sad1.addFirst(random);
            expect.addFirst(random);
        }

        for(int i = 0; i < 10; i++){
            int actual = sad1.get(i);
            int expected = expect.get(i);
            assertEquals("Oh noooo!\nThis is bad:\n   Random number " + actual
                    + " not equal to " + expected + "!", expected, actual);
        }

//        removeFirst
        List<Integer> actualist = new ArrayList<Integer>();
        List<Integer> expectedlist = new ArrayList<Integer>();
        for(int i = 0; i < 10 ;i ++){
            actualist.add(sad1.removeFirst());
            expectedlist.add(expect.removeFirst());
        }

        for(int i = 0; i < 10; i++){
            int actual = sad1.get(i);
            int expected = expect.get(i);
            assertEquals("Oh noooo!\nThis is bad:\n   Random number " + actual
                    + " not equal to " + expected + "!", expected, actual);
        }

        for(int i = 0; i < 10; i++){
            int actuallist = actualist.get(i);
            int expectlist = expectedlist.get(i);
            assertEquals("Oh noooo!\nThis is bad:\n   Random number " + actuallist
                    + " not equal to " + expectlist + "!", expectlist,actuallist);
        }

//        removeLast
//        actualist.clear();
//        expectedlist.clear();
//        for(int i = 0; i < 10; i++){
//            actualist.add(expect.removeLast());
//            expectedlist.add(sad1.removeLast());
//        }
//        for(int i = 0; i < 10; i++){
//            int actual = actualist.get(i);
//            int expected = expectedlist.get(i);
//            assertEquals("Oh noooo!\nThis is bad:\n   Random number " + actual
//                    + " not equal to " + expected + "!",expected, actual);
//        }
    }





}
