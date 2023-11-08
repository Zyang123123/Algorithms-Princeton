import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private int gridwidth;
    private int T;
    private int count=0;
    private double threshold;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials)
    {
        Percolation per = new Percolation(n);
        int col, row;
        while(!per.percolates())
        {
            int num = StdRandom.uniformInt(n * n) + 1;
            // Generates a random integer between 0 (inclusive) and n*n (exclusive)
            // Generates a random integer between 1 (inclusive) and n*n+1 (exclusive)

            col = num % n;
            if(col==0) {
                col = n;
                row = num / n;
            }
            else{
                row = num/n +1;
            }
            if (!per.isOpen(row, col)) {
                per.open(row, col);
                count++;
            }
        }
        this.threshold=(double) count/(n*n);
        this.gridwidth=n;
        this.T=trials;

    }
    //all trials are expressed by array
    public double[] trials()
    {
        double[] trial = new double[T];
        for (int i=0; i<T; i++)
        {
            PercolationStats perstat = new PercolationStats(gridwidth, T);
            trial[i] = perstat.threshold;
        }
        return trial;
    }
    // sample mean of percolation threshold
    public double mean()
    {
        double[] trial = this.trials();
        return StdStats.mean(trial);
    }

    // sample standard deviation of percolation threshold
    public double stddev()
    {
        double[] trial = trials();
        return StdStats.stddev(trial);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo()
    {
        return this.mean()-1.96*this.stddev()/Math.sqrt(T);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi()
    {
        return this.mean()+1.96*this.stddev()/Math.sqrt(T);
    }

    // test client (see below)
    public static void main(String[] args)
    {
        PercolationStats perc = new PercolationStats(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
        //PercolationStats perc = new PercolationStats(200,100);
        //PercolationStats perc = new PercolationStats(2,100000);
        StdOut.print("mean\t\t\t=");
        StdOut.println(perc.mean());
        StdOut.print("stddev\t\t\t=");
        StdOut.println(perc.stddev());
        StdOut.print("95% confidence interval\t=[");
        StdOut.print(perc.confidenceLo());
        StdOut.print(", ");
        StdOut.print(perc.confidenceHi());
        StdOut.println("]");
    }
}
