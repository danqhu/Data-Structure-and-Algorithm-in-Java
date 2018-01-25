package unionFind;

import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	
	private double[] perc;
	
	public PercolationStats(int n, int trials) {
		perc = new double[trials];
		for(int i = 0; i < trials; i++) {
			Percolation pr = new Percolation(n);
			pr.percolates();
			perc[i] = ((double)pr.numberOfOpenSites())/(n*n);
		}
	}
	
	public double mean() {
		return StdStats.mean(perc);
	}
	
	public double stddev() {
		return StdStats.stddev(perc);
	}
	
	public double confidenceLo() {
		return mean()-1.96*stddev()/Math.sqrt(perc.length);
	}
	
	public double confidenceHi() {
		return mean()+1.96*stddev()/Math.sqrt(perc.length);
	}
	
	public static void main(String[] args) {
		PercolationStats ps = new PercolationStats(2, 100000);
		System.out.println("mean   = " + ps.mean());
		System.out.println("stddev = " + ps.stddev());
		System.out.println("95% CI = " + "[" + ps.confidenceLo() + " - " + ps.confidenceHi() + "]");
	}
	
}
