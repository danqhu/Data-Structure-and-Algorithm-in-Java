package unionFind;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	private int[][] grid;
	private WeightedQuickUnionUF uf;
	private int numberOfOpenSites = 0;
	private List<Integer> indexes;
	
	
	
	public Percolation(int n) {
		grid = new int[n][n];
		//the first one for virtual top site and the last one for virtual bottom one
		uf = new WeightedQuickUnionUF(n*n + 2);
		indexes = new ArrayList<Integer>();
		for(int i = 1; i < n * n + 1; i++) {
			indexes.add(i);
		}
	}
	
	public void open(int row, int col) {
		grid[row-1][col-1] = 1;
		numberOfOpenSites++;
	}
	
	public boolean isOpen(int row, int col) {
		if(grid[row-1][col-1] == 1)
			return true;
		else
			return false;
	}
	
	public boolean isFull(int row, int col) {
		return uf.connected((row - 1) * grid.length + col, 0);
	}
	
	
	public int numberOfOpenSites() {
		return numberOfOpenSites;
	}
	
	public boolean percolates() {
		boolean isPercolating = false;
		while(!isPercolating) {
			//找到一个未open的site
			
			int pos = StdRandom.uniform(0, indexes.size());
			int site = indexes.get(pos);
			indexes.remove(pos);
			int row = site % grid.length != 0 ? site / grid.length + 1 : site / grid.length;
			int col = site % grid.length != 0 ? site % grid.length : grid.length;
			
			//将一个未open的site置为open
			open(row, col);
			//连接open的site的相邻sites
			unionAdjacentSites(row, col);
			
			isPercolating = uf.connected(0, grid.length * grid.length + 1);
			
		}
		
		return isPercolating;
	}
	
	private void unionAdjacentSites(int row, int col) {
		
		//如果位于第一行
		if(row == 1) {
			uf.union(col , 0);
			//如果下方open，连接本体和下方的site
			if(isOpen(row + 1, col)) {
				uf.union((row - 1) * grid.length + col, (row + 1 - 1) * grid.length + col);	
			}
			//如果位于第一列
			if(col == 1) {
				//如果左侧open，连接本体和左侧的site
				if(isOpen(row, col + 1)) {
					uf.union((row - 1) * grid.length + col, (row - 1) * grid.length + col + 1);
				}
		    //如果位于最后一列
			}else if(col == grid.length) {
				//如果右侧open，连接本体和右侧的site
				if(isOpen(row, col - 1)) {
					uf.union((row - 1) * grid.length + col, (row - 1) * grid.length + col - 1);
				}
			} else {
				//如果左侧open，连接本体和左侧的site
				if(isOpen(row, col + 1)) {
					uf.union((row - 1) * grid.length + col, (row - 1) * grid.length + col + 1);
				}
				//如果右侧open，连接本体和右侧的site
				if(isOpen(row, col - 1)) {
					uf.union((row - 1) * grid.length + col, (row - 1) * grid.length + col - 1);
				}
			}
		//如果位于最后一行
		} else if(row == grid.length) {
			uf.union((row - 1) * grid.length + col , grid.length * grid.length + 1);
			//如果上方open，连接本体和上方的site
			if(isOpen(row - 1, col)) {
				uf.union((row - 1) * grid.length + col, (row - 1 - 1) * grid.length + col);
			}
			//如果位于第一列
			if(col == 1) {
				//如果右侧open， 连接本体和右侧site
				if(isOpen(row, col + 1)) {
					uf.union((row - 1) * grid.length + col, (row - 1) * grid.length + col + 1);
				}
			//如果位于最后一列
			} else if (col == grid.length) {
				//如果左侧open， 连接本体和左侧site
				if(isOpen(row, col - 1)) {
					uf.union((row - 1) * grid.length + col, (row - 1) * grid.length + col - 1);
				}
			} else {
				//如果右侧open， 连接本体和右侧site
				if(isOpen(row, col + 1)) {
					uf.union((row - 1) * grid.length + col, (row - 1) * grid.length + col + 1);
				}
				//如果左侧open， 连接本体和左侧site
				if(isOpen(row, col - 1)) {
					uf.union((row - 1) * grid.length + col, (row - 1) * grid.length + col - 1);
				}
			}
		//如果既不在第一行也不再最后一行
		} else {
			//如果上方open， 连接本体和上方site
			if(isOpen(row - 1, col)) {
				uf.union((row - 1) * grid.length + col, (row - 1 - 1) * grid.length + col);
			}
			//如果下方open， 连接本体和下方site
			if (isOpen(row + 1, col)) {
				uf.union((row - 1) * grid.length + col, (row - 1 + 1) * grid.length + col);
			}
			//如果位于第一列
			if(col == 1) {
				if(isOpen(row, col + 1)) {
					uf.union((row - 1) * grid.length + col, (row - 1) * grid.length + col + 1);
				}
			//如果位于最后一列
			} else if (col == grid.length) {
				if(isOpen(row, col - 1)) {
					uf.union((row - 1) * grid.length + col, (row - 1) * grid.length + col - 1);
				}
			//如果既不位于第一列也不位于最后一列
			} else {
				if(isOpen(row, col + 1)) {
					uf.union((row - 1) * grid.length + col, (row - 1) * grid.length + col + 1);
				}
				if(isOpen(row, col - 1)) {
					uf.union((row - 1) * grid.length + col, (row - 1) * grid.length + col - 1);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		Percolation pc = new Percolation(200);
		pc.percolates();
		System.out.println(pc.numberOfOpenSites);
		
		int i = 0;
		i++;
	}
	
	
	
	
	
}
