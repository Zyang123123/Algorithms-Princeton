import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class BruteCollinearPoints {
    //Point p, q, r, s;
    private Point[] points;
    private int pnum;
    //LineSegment[] lss = new LineSegment[]{(p, q);
    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points)
    {
    /*    p = points[0];
        q = points[1];
        r = points[2];
        s = points[3];*/
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
        if (pnum==1 && points[0]==null)
            throw new IllegalArgumentException("some point in the array is null");
        this.points = points;
    }
    // the number of line segments
    public int numberOfSegments()
    {
        int i;
        for (i=0; i<segments().length; i++)
            if (segments()[i]==null)
                return i;
        return i;
    }
    // the line segments
    public LineSegment[] segments()
    {
        int p, q, r, s, i=0;
        LineSegment[] lss = new LineSegment[pnum/4+1];
        //point 1
        for (p=0; p<=pnum-4; p++)
        {
            //point 2
            for (q=p+1; q<=pnum-3; q++)
            {
                /*Point minp = points[p];
                Point maxp = points[p];
                Comparator<Point> comparator = points[p].slopeOrder();
                if (minp.compareTo(points[q])>0)
                {
                    minp = points[q];
                }
                if (maxp.compareTo(points[q])<0)
                {
                    maxp = points[q];
                }*/



                //point 3
                for (r=q+1; r<=pnum-2; r++)
                {
                   /* if (minp.compareTo(points[r])>0)
                    {
                        minp = points[r];
                    }
                    if (maxp.compareTo(points[r])<0)
                    {
                        maxp = points[r];
                    }*/


                    //point 4
                    for (s=r+1; s<=pnum-1; s++)
                    {

                       /*Point minp = points[p];
                        Point maxp = points[p];

                        Comparator<Point> comparator = points[p].slopeOrder();
                        if (minp.compareTo(points[s])>0)
                        {
                            minp = points[s];
                        }
                        if (maxp.compareTo(points[s])<0)
                        {
                            maxp = points[s];
                        }*/

                        Comparator<Point> comparator = points[p].slopeOrder();
                        if (comparator.compare(points[q], points[r]) ==0
                                && comparator.compare(points[q], points[s]) == 0)
                        {
                            Point[] p4 = new Point[]{points[p], points[q], points[r], points[s]};
                            Arrays.sort(p4);
                            //在Java中，数组的排序通常通过Arrays.sort()方法完成。
                            // 当你传递一个对象数组给这个方法时，Java会使用对象的compareTo方法来决定排序的顺序。
                            // 这就要求数组中的对象类型必须实现了Comparable接口。
                            Point minp = p4[0];
                            Point maxp = p4[3];
                            LineSegment ls = new LineSegment(minp, maxp);
                            lss[i++] = ls;
                            //break;
                        }

                    }
                    //break;
                }
                //break;
            }
           // break;
        }
        LineSegment[] finalSegments = new LineSegment[i];
        for (int count = 0; count < i; count++) {
            finalSegments[count] = lss[count];
        }

        return finalSegments;

    }


/*    public LineSegment[] segments() {
        int maxSegments = pnum * (pnum - 1) * (pnum - 2) * (pnum - 3) / 24; // 最坏情况的线段数目
        LineSegment[] tempSegments = new LineSegment[maxSegments];
        int segmentCount = 0; // 实际找到的线段数目

        // 对所有点按自然顺序排序
        Point[] sortedPoints = Arrays.copyOf(points, points.length);
        Arrays.sort(sortedPoints);

        for (int p = 0; p < pnum - 3; p++) {
            for (int q = p + 1; q < pnum - 2; q++) {
                for (int r = q + 1; r < pnum - 1; r++) {
                    for (int s = r + 1; s < pnum; s++) {
                        // 使用p点的斜率比较器对q, r, s进行排序
                        Point[] collinearPoints = new Point[]{sortedPoints[p], sortedPoints[q], sortedPoints[r], sortedPoints[s]};
                        Comparator<Point> comparator = sortedPoints[p].slopeOrder();
                        Arrays.sort(collinearPoints, 1, 4, comparator);
                        //总归需要排序，就在最开始进行

                        if (comparator.compare(collinearPoints[1], collinearPoints[2]) == 0 &&
                                comparator.compare(collinearPoints[1], collinearPoints[3]) == 0) {
                            // 检查是否已经包含了这个线段
                            boolean isNewSegment = true;
                            for (int i = 0; i < segmentCount; i++) {
                                if (tempSegments[i].toString().equals(new LineSegment(collinearPoints[0], collinearPoints[3]).toString())) {
                                    isNewSegment = false;
                                    break;
                                }
                            }
                            if (isNewSegment) {
                                tempSegments[segmentCount++] = new LineSegment(collinearPoints[0], collinearPoints[3]);
                            }
                        }
                    }
                }
            }
        }

        // 裁剪数组以移除未使用的部分
        LineSegment[] finalSegments = new LineSegment[segmentCount];
        for (int i = 0; i < segmentCount; i++) {
            finalSegments[i] = tempSegments[i];
        }

        return finalSegments;
    }*/



 /*   public static void main(String[] args) {
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
*//*        p1.slopeTo(p2);
        p1.slopeTo(p3);*//*
        Point[] points = new Point[]{p1, p2, p3, p4, p5, p6, p7, p8, p9, p10};
        BruteCollinearPoints bcp = new BruteCollinearPoints(points);
        Comparator<Point> comparator = p1.slopeOrder();
        int result = comparator.compare(p2, p3);
        System.out.println(result);

        *//*     p1.slopeOrder();*//*
        System.out.println(bcp.numberOfSegments());
        for (int i=0; i<bcp.numberOfSegments(); i++)
        {
            System.out.println(bcp.segments()[i]);
        }
*//*        System.out.println(bcp.segments()[0]);
        System.out.println(bcp.segments()[1]);
        System.out.println(bcp.segments()[2]);*//*

        System.out.println(p1);
    }
*/



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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
