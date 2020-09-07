
import org.junit.Test;

import static org.junit.Assert.*;
public class TestUnionFind {
    @Test
    public void TestConnect(){
        UnionFind test = new UnionFind(8);

        assertFalse(test.connected(3, 4));
        test.union(1, 2);
        assertTrue(test.connected(1, 2));
        test.union(2, 3);
        assertTrue(test.connected(1, 3));
        test.union(4, 5);
        assertTrue(test.connected(4, 5));
        test.union(3, 3);
        assertTrue(test.connected(3, 3));
        try{
            test.union(10, 11);
        }
        catch (IllegalArgumentException e){
            System.out.println("Invalid input");
        }
    }
}
