import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


public class FastCollinearPoints {

    private Point[] points;
    private int pnum;
    public FastCollinearPoints(Point[] points)     // finds all line segments containing 4 or more points
    {
        //Corner cases.
        if (points == null)
            throw new IllegalArgumentException("argument to BruteCollinearPoints constructor is null");
        pnum = points.length;
        for(int i=0; i<=pnum-2; i++) {
            if (points[i]==null)
                throw new IllegalArgumentException("some point in the array is null");
            for (int j=i+1; j<=pnum-1;j++) {
                if (points[j]==null)
                    throw new IllegalArgumentException("some point in the array is null");
                if (points[i].equals(points[j])) {
                    throw new IllegalArgumentException("the argument to the constructor contains a repeated point: " + points[i]);
                }
            }
        }
        //Arrays.sort(points);
        this.points = points;
    }
    public int numberOfSegments()        // the number of line segments
    {
        int i;
        for (i=0; i<segments().length; i++)
            if (segments()[i]==null)
                return i;
        return i;

    }

    public LineSegment[] segments()                // the line segments
    {
        //换成arraylist？
        //LineSegment[] lss = new LineSegment[(int) pow(pnum,2)];//new LineSegment[pnum/4+1];
        //int lssnum=0;//lss坐标

        ArrayList<LineSegment> listSegments = new ArrayList<>();


        Point[] ps = new Point[pnum-1];

        for (int i=0; i<pnum; i++)
        {
            int k=0;//ps坐标
            Point originp = points[i];
            for (int j=0; j<pnum; j++) {
                if (j == i)
                    continue;
                ps[k++]=points[j];
            }

            Arrays.sort(ps, originp.slopeOrder());

            for (k=0; k<ps.length-2; k++)
            {
                int t=k+1;
                /*while (t<ps.length && originp.slopeOrder().compare(ps[k], ps[t]) ==0
                        && originp.slopeOrder().compare(ps[k], ps[t+1]) == 0)*/
                //Comparator<Point> comparator = originp.slopeOrder();
                while (t+1<ps.length && originp.slopeTo(ps[k])==originp.slopeTo(ps[t]) &&
                        originp.slopeTo(ps[k])==originp.slopeTo(ps[t+1]))
                {
                    t++;
                }
                if (t-k>=2)
                {
                    Point[] collinearp = new Point[t-k+2];
                    collinearp[0]=originp;
                    for (int cp=1;cp<t-k+2;cp++)
                    {
                        collinearp[cp]=ps[k+cp-1];
                    }
                    Arrays.sort(collinearp);
                    Point minp = collinearp[0];
                    Point maxp = collinearp[t-k+1];//change !!
                    LineSegment ls = new LineSegment(minp, maxp);

                    boolean isNewSegment = true;
                    /*for (int count = 0; count < lssnum; count++) {
                        if (lss[count].toString().equals(ls.toString())) {
                            isNewSegment = false;
                            break;
                        }
                    }*/
                    for (LineSegment existingSegment : listSegments) {
                        if (existingSegment.toString().equals(ls.toString())) {
                            isNewSegment = false;
                            break;
                        }
                    }
                    if (isNewSegment) {
                        //lss[lssnum++] = ls;
                        listSegments.add(ls);
                    }
                    //lss[lssnum++] = ls;
                    k=t;
                }

                //if (originp.slopeTo(ps[k])==originp.slopeTo(ps[k+1]))
            }
        }
        /*LineSegment[] finalSegments = new LineSegment[lssnum];
        for (int i = 0; i < lssnum; i++) {
            finalSegments[i] = lss[i];
        }*/

        LineSegment[] finalSegments = listSegments.toArray(new LineSegment[0]);
        return finalSegments;
    }



/*    public static void main(String[] args) {
        *//* YOUR CODE HERE *//*
        Point p1 = new Point(2, 2);
        Point p2 = new Point(1, 1);
        Point p3 = new Point(3, 3);
        Point p4 = new Point(4, 3);
        Point p5 = new Point(5, 5);
        Point p6 = new Point(0, 1);
        Point p7 = new Point(1, 2);
        Point p8 = new Point(2, 3);
        Point p9 = new Point(3, 4);
        Point p10 = new Point(4, 9);

        Point[] points = new Point[]{p1, p2, p3, p4, p5, p6, p7, p8, p9, p10};
        FastCollinearPoints bcp = new FastCollinearPoints(points);
        Comparator<Point> comparator = p1.slopeOrder();
        int result = comparator.compare(p2, p3);
        System.out.println(result);


        System.out.println(bcp.numberOfSegments());
        for (int i=0; i<bcp.numberOfSegments(); i++)
        {
            System.out.println(bcp.segments()[i]);
        }
        System.out.println(p1);
    }*/


    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
