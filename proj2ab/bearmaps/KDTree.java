package bearmaps;

import bearmaps.Point;
import bearmaps.PointSet;

import java.util.List;

public class KDTree implements PointSet {
    private static final boolean HORIZONTAL = false;
    private Node root;

    public KDTree(List<Point> points){
        for(Point point : points){
            insert(point);
        }
    }
    private void insert(Point point){
        if(point == null){
            throw new IllegalArgumentException();
        }
        root = insert(point ,root, HORIZONTAL);
    }

    private Node insert(Point p, Node T, boolean orientation){
        if(T == null){
            return new Node(p, orientation);
        }
        if(T.getPoint().equals(p)) return T;

        int cmp = new Node(p, orientation).compareTo(T);

        if(cmp > 0){
            T.right = insert(p, T.right,!orientation );
        }else{
            T.left = insert(p,T.left,!orientation);
        }

        return T;
    }

    @Override
    public Point nearest(double x,double y){

        return  nearestHelp(root, root, new Point(x, y), HORIZONTAL).getPoint();
    }

    private Node nearestHelp(Node T, Node bestNode, Point targetPoint, boolean orientation){
        if(T == null) return bestNode;
        double currDist = Point.distance(T.getPoint(), targetPoint);
        double bestDist = Point.distance(bestNode.getPoint(), targetPoint);
        if(currDist < bestDist) bestNode = T;

        Node goodSide;
        Node badSide;
        if(new Node(targetPoint, orientation).compareTo(T) > 0){
            goodSide = T.right;
            badSide = T.left;
        }else{
            goodSide = T.left;
            badSide = T.right;
        }

        bestNode = nearestHelp(goodSide, bestNode, targetPoint, !orientation);
        if(isWorkLook(T, targetPoint, bestDist)){
            bestNode = nearestHelp(badSide, bestNode, targetPoint, !orientation);
        }
        return bestNode;
    }


    private boolean isWorkLook(Node currNode, Point targetPoint, double currLength){
        if(currNode.getOrientation()){
            return Math.pow(currNode.getPoint().getY() - targetPoint.getY(), 2) < currLength;
        }else{
            return Math.pow(currNode.getPoint().getX() - targetPoint.getX(), 2) < currLength;
        }
    }






    private class Node implements Comparable{
        private Node left;
        private Node right;
        private Point p;
        private boolean orientation;

        public Node(Point p, boolean orientation){
            this.p = p;
            this.orientation = orientation;
        }

        public Point getPoint(){
            return p;
        }

        private boolean getOrientation(){
            return orientation;
        }

        @Override
        public int compareTo(Object o){
            if(getOrientation()){
                return Double.compare(p.getY(), ((Node)o).getPoint().getY());
            }else{
                return Double.compare(p.getX(), ((Node)o).getPoint().getX());
            }
        }

    }
}
