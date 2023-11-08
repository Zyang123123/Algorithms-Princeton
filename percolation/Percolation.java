import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // creates n-by-n grid, with all sites initially blocked
    private boolean[][] ifopen;
    private WeightedQuickUnionUF uf;
    private int OpenSites;
    private int gridwidth;
    public Percolation(int n)
    {
        if (n<=0)
        {
            throw new IllegalArgumentException("n-by-n grid's n must be positive");
        }
        gridwidth = n;
        ifopen = new boolean[n][n];
        for (int i=0;i<n;i++)
        {
            for (int j=0;j<n;j++)
            ifopen[i][j]=false;
        }
        OpenSites=0;
        uf = new WeightedQuickUnionUF(n*n+2);
    }

    // check whether it need to throw a warning.
    public boolean ifthrow(int row, int col)
    {
        if (row<1 || col<1 || row>gridwidth || col>gridwidth) {
            //throw new IllegalArgumentException("the argument is outside its prescribed range");
            return true;
        }
        return false;
    }

    //
    public int IndexOfsite(int row, int col)
    {
        return (row-1)*gridwidth+col;
    }

    //connect a site with its neighbours
    public void connectneighbor(int row, int col)
    {
        if(!ifthrow(row-1,col) && isOpen(row-1, col))
        {
            uf.union(IndexOfsite(row-1,col),IndexOfsite(row, col));
        }
        if(!ifthrow(row,col-1) && isOpen(row, col-1))
        {
            uf.union(IndexOfsite(row,col-1),IndexOfsite(row, col));
        }
        if(!ifthrow(row+1,col) && isOpen(row+1, col))
        {
            uf.union(IndexOfsite(row+1,col),IndexOfsite(row, col));
        }
        if(!ifthrow(row,col+1) && isOpen(row, col+1))
        {
            uf.union(IndexOfsite(row,col+1),IndexOfsite(row, col));
        }
    }


    // opens the site (row, col) if it is not open already
    public void open(int row, int col)
    {
        if(ifthrow(row, col))
            throw new IllegalArgumentException("the argument is outside its prescribed range");
        if (!isOpen(row,col))
        {
            ifopen[row - 1][col - 1] = true;
            OpenSites++;
            connectneighbor(row, col);
            if(row==1)
                uf.union(0,IndexOfsite(row,col));
            if(row==gridwidth)
                uf.union(gridwidth*gridwidth+1, IndexOfsite(row,col));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col)
    {
        if(ifthrow(row, col))
            throw new IllegalArgumentException("the argument is outside its prescribed range");
        return ifopen[row-1][col-1];
    }


    // is the site (row, col) full?
    public boolean isFull(int row, int col)
    {
        if(ifthrow(row, col))
            throw new IllegalArgumentException("the argument is outside its prescribed range");
                return (uf.find(0)==uf.find(IndexOfsite(row,col)));
    }

    // returns the number of open sites
    public int numberOfOpenSites()
    {
        return OpenSites;
    }

    // does the system percolate?
    public boolean percolates()
    {
        return (uf.find(0)==uf.find(gridwidth*gridwidth+1));
    }

    // test client (optional)
    public static void main(String[] args)
    {
        Percolation perc = new Percolation(3);
        perc.open(1, 3);
        perc.open(2, 3);
        perc.open(2, 2);
        perc.open(3, 1);
        perc.open(2, 1);
        StdOut.println(perc.percolates());
        StdOut.println(perc.isFull(2,2));
        StdOut.println(perc.isFull(3,1));
        StdOut.println(perc.isOpen(3,1));
        StdOut.println(perc.isOpen(3,2));
    }
}
