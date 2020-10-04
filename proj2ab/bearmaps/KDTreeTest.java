package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import java.security.cert.PolicyNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    private static Random R = new Random();
    public static List<Point> pointList(){
        Point p1 = new Point(2, 3); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(4, 2);
        Point p22 = new Point(4, 2);
        Point p3 = new Point(4, 5);
        Point p4 = new Point(3, 3);
        Point p5 = new Point(1, 5);
        Point p6 = new Point(4, 4);
        return List.of(p1, p2, p22,p3, p4, p5,p6);
    }

    public static KDTree buildLectureTree(){
        KDTree kdTree = new KDTree(pointList());
        return kdTree;
    }

    public static NaivePointSet naivePointSetTest(){
        NaivePointSet naivePointSet = new NaivePointSet(pointList());
        return naivePointSet;
    }

    @Test

    public void testNearestDemoSlides(){
        KDTree kd = buildLectureTree();
        NaivePointSet naivePointSet = naivePointSetTest();

        Point actual = kd.nearest(0,7);
        Point expect = naivePointSet.nearest(0,7);
        assertEquals(expect, actual);
    }

    private Point randPoint(){
        double x = R.nextDouble();
        double y = R.nextDouble();
        return new Point(x, y);
    }

    private List<Point> randomPoints(int N){
        List<Point> points = new ArrayList<>();
        for(int i = 0; i < N; i++){
            points.add(randPoint());
        }
        return points;
    }

    private void testWithNPointsAndMQueries(int N, int M){
        int PointCount = N;
        int QueryCount = M;

        List<Point> points = randomPoints(N);
        KDTree kd = new KDTree(points);
        NaivePointSet nps = new NaivePointSet(points);
        List<Point> queries = randomPoints(M);
        for(Point p : queries){
            Point expected = nps.nearest(p.getX(),p.getY());
            Point actual = kd.nearest(p.getX(), p.getY());
            assertEquals(actual, expected);
        }
    }

    @Test
    public void testWith10000PointsAnd2000Queries(){
        int PointCount = 10000;
        int QueryCount = 2000;
        testWithNPointsAndMQueries(PointCount, QueryCount);
    }
    private void testTimingNPointAndMQueries(int N, int M){
        List<Point> points = randomPoints(N);
        List<Point> queries = randomPoints(M);
        KDTree kd = new KDTree(points);
        NaivePointSet nps = new NaivePointSet(points);



        Stopwatch sw = new Stopwatch();
        for(Point p : queries){
            nps.nearest(p.getX(), p.getY());
        }
        double time = sw.elapsedTime();
        System.out.println("npsTime consume: "+ time +".");

        for(Point p : queries){
            kd.nearest(p.getX(), p.getY());
        }
        time = sw.elapsedTime();
        System.out.println("kdTime consume: "+ time +".");



    }
    @Test
    public void testTime(){
        int PointCount = 1000000;
        int QueryCount = 20000;
        testTimingNPointAndMQueries(PointCount, QueryCount);
    }


}
