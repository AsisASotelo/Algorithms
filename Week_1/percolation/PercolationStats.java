/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;
    private final double[] results;
    private final int numTrials;


    // Perform independent trials on an n by n grid.
    public PercolationStats(int n, int trials) {

        if (trials < 1) {
            throw new IllegalArgumentException("int trials is less than 1.");
        }
        if (n < 1) {
            throw new IllegalArgumentException("int n is less than 1.");
        }
        numTrials = trials;
        results = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                perc.open(StdRandom.uniform(1, n + 1), StdRandom.uniform(1, n + 1));
            }
            double openSites = perc.numberOfOpenSites();
            results[i] = openSites / (double) (n * n);
        }

    }

    // Sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);
    }

    // Sample Standard Deviation of percolation threshold.
    public double stddev() {
        return StdStats.stddev(results);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return StdStats.mean(results) - ((CONFIDENCE_95 * StdStats.stddev(results) / Math
                .sqrt(numTrials)));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return StdStats.mean(results) + ((CONFIDENCE_95 * StdStats.stddev(results) / Math
                .sqrt(numTrials)));
    }


    public static void main(String[] args) {
        int n = 0;
        int t = 0;
        double interval[] = new double[2];

        if (args.length == 2) {
            n = Integer.parseInt(args[0]);
            t = Integer.parseInt(args[1]);
        }


        PercolationStats statObject = new PercolationStats(n, t);
        interval[0] = statObject.confidenceHi();
        interval[1] = statObject.confidenceLo();
        System.out.println("mean                        = " + statObject.mean());
        System.out.println("stddev                      = " + statObject.stddev());
        System.out.println("95% Confidence Interval     = " + "[" + statObject.confidenceLo() + ", "
                                   + statObject.confidenceHi() + "]");
    }
}
